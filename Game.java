import java.util.Scanner;
import java.io.FileNotFoundException;

public class Game{

  public static Player player;
  public static SaveLoad sv;
  public static String instaDebug = "BJ";
  
  public static void main(String[] args) throws FileNotFoundException{

    /*
    int[] s = {13, 1, 2, 8, 3, 6, 1, 12};
    CardList cl = new CardList(s);
    cl.shuffle();
    System.out.println(cl.display(true, 1));
    System.out.println("best: " + cl.bestPoints());
    */

    if(instaDebug.equals("")) {
      play();
    } else {
      player = new Player("debug", 100);
      launch(instaDebug);
    }
    
  }

  public static void play() throws FileNotFoundException{
    
    Scanner scan = new Scanner(System.in);
    sv = new SaveLoad("PlayerData.csv");
    
    logIn(scan);
    
    System.out.println("<<<Select a Game>>>");

    while(true){
      System.out.println("1) BlackJack");
      String input = scan.nextLine();
      System.out.println();
      if(input.charAt(0) == '1'){
        launch("BJ");
        break;
      } else {
        System.out.println("\"" + input + "\" is not a valid option.");
      }
      
    }
    
  }



  public static void launch(String gameName) {
    while(true) {
      if(gameName.equals("BJ")){
        player.matchSet(BlackJack.play(player.cash()));
      }

      boolean playMatches = true;
      Scanner scan = new Scanner(System.in);
      while(true){
          System.out.print("Would you like to play again? y/n: ");
          String input = scan.next();
          
          if(input.charAt(0) == 'y'){
            break;
          } else if(input.charAt(0) == 'n') {
            playMatches = false;
            break;
          } else {
            System.out.println("Please choose \"y\" or \"n\".");
          }
        }
        System.out.println();

      }
  }

  

  public static void logIn(Scanner scan){

    boolean logging = true;
    
    System.out.println("<<< Log in >>>");
    while(logging) {
      System.out.println("1) Select profile from username");
      System.out.println("2) Select profile from UUID");
      System.out.println("3) Create new profile");
  
      String input = scan.nextLine();
      
      // gets player via username
      if(input.charAt(0) == '1') {
        
        System.out.print("Enter username: ");
        input = scan.nextLine();
        System.out.println();
        player = sv.getPlayer(input);
        

      // gets player via UUID
      } else if(input.charAt(0) == '2') {
        System.out.print("Enter UUID: ");
        input = scan.nextLine();
        System.out.println();
        player = sv.getPlayerUUID(input);


      // creates new player with $100 and random UUID
      } else if(input.charAt(0) == '3') {
        while(logging) {
          System.out.print("Enter name: ");
          player = new Player(scan.nextLine());

          // addPlayer returns false if the adding was unsuccessful. Its very rare but possible that the error is due to a duplicate id, not a duplicate name, but that is too rare for me to bother fixing it, and it doesn't even break the code. It will only make one of the UUID twins unaccessible using the UUID search
          if(sv.addPlayer(player) == false) {
            System.out.println("There is already a player with that name. Please choose another.");
          } else {
            logging = false;
            System.out.println();
          }
        }
        
        
      } else {
        System.out.println("\"" + input + "\" is not a valid option.");
        System.out.println();
      }

      if(player == null){
        System.out.println("That player could not be found.");
        System.out.println();
      } else {
        logging = false;
        System.out.println("You have selected " + player.name() + " with $" + player.cash() + ".");
        System.out.println();
      }
    }
  }

}