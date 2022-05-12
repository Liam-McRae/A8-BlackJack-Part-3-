import java.util.Scanner;
import java.io.FileNotFoundException;

public class Game{

  public static Player player;
  private static SaveLoad sv;
  public static final String instaDebug = "";
  
  public static void main(String[] args) throws FileNotFoundException{
    
    // instadebug allows games to be played immediatly, skipping the log in process
    if(instaDebug.equals("")) {
      play();
    } else {
      player = new Player("debug", 100);
      launch(instaDebug);
    }
  }

  

  // calls all of the big methods
  public static void play() throws FileNotFoundException{
    Scanner scan = new Scanner(System.in);
    sv = new SaveLoad("PlayerData.csv");
    
    player = logIn(scan); // assign player   
    selection(); // begin gameplay
  }



  // saves player data. Although this doesn't need to be a method because it is only used once here, the other classes for games need to be able to themselves save, in order to prevent save-scumming
  public static void savePlayer(Player returnPlayer) throws FileNotFoundException{
    player = returnPlayer;
    sv.update(player.name(), player);
    sv.save();
  }
  


  // game loop, allows games to be played
  public static void selection() throws FileNotFoundException{
    Scanner scan = new Scanner(System.in);
    
    while(true){

      System.out.println("<<<Select a Game>>>");
      System.out.println("1) BlackJack (1 person)");
      System.out.println("2) Uno (1-4 people)");
      System.out.println("3) Quit");
      System.out.println("4) ");
      System.out.println("5) ");
      System.out.println("6) ");
      System.out.println();
      String input = scan.nextLine();

      if(input.charAt(0) == '1'){
        launch("BJ");
      } else if(input.charAt(0) == '2') {
        
      } else if(input.charAt(0) == '3') {
        break;
      } else if(false) {

      } else if(false) {

      } else if(false) {

      } else {
        System.out.println("\"" + input + "\" is not a valid option.");
      }
    }
    scan.close();
    System.out.println("Thank you for playing!");
  }



  // starts a game given an acronym for said game
  public static void launch(String gameName) throws FileNotFoundException{
    if(gameName.equals("BJ")){
      savePlayer(BlackJack.play(player));
    }
    if(gameName.equals("UNO")) {
      Scanner scan = new Scanner(System.in);
      System.out.println("Num of players: ");
      int playerNum = scan.nextInt();
      System.out.println();
      Player[] players = new Player[playerNum];
      for(int i = 0; i < playerNum; i++) {
        players[i] = logIn(scan);
      }
    
      Player[] data = Uno.play(players);
      
    }
  }

  

  // sets player, allows creation of new player
  public static Player logIn(Scanner scan) throws FileNotFoundException{

    boolean logging = true;
    
    System.out.println("<<< Log in >>>");
    Player tempPlayer = null;
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
        tempPlayer = sv.getPlayer(input);
        

      // gets player via UUID
      } else if(input.charAt(0) == '2') {
        System.out.print("Enter UUID: ");
        input = scan.nextLine();
        System.out.println();
        tempPlayer = sv.getPlayerUUID(input);


      // creates new player with $100 and random UUID
      } else if(input.charAt(0) == '3') {
        while(logging) {
          System.out.print("Enter name: ");
          tempPlayer = new Player(scan.nextLine(), 100);

          // addPlayer returns false if the adding was unsuccessful. Its very rare but possible that the error is due to a duplicate id, not a duplicate name, but that is too rare for me to bother fixing it, and it doesn't even break the code. It will only make one of the UUID twins inaccessible using the UUID search
          if(sv.addPlayer(tempPlayer) == false) {
            System.out.println("There is already a player with that name. Please choose another.");
          } else {
            logging = false;
            System.out.println();
          }
        }
        sv.save();
      } else {
        System.out.println("\"" + input + "\" is not a valid option.");
        System.out.println();
      }

      if(tempPlayer == null){
        System.out.println("That player could not be found.");
        System.out.println();
      } else {
        logging = false;
        System.out.println("You have selected " + tempPlayer.name() + " with $" + tempPlayer.cash() + ".");
        System.out.println();
      }
    }
    return tempPlayer;
  }

}