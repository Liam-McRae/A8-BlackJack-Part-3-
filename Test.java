import java.io.FileNotFoundException;
import java.util.Random;
import java.util.UUID;

class Test{



  public static void main(String[] args) throws FileNotFoundException {

    SaveLoad sv = new SaveLoad("PlayerData.csv");
    Player[] players = sv.getPlayers();

    
    


    
  }

  public static void makeRandomPlayer(SaveLoad sv){
    Random rand = new Random();
    makeRandomPlayer(sv, rand.nextInt());
  }
  
  public static void makeRandomPlayer(SaveLoad sv, int seed){
    Random rand = new Random(seed);
    String id = UUID.randomUUID().toString();
    int money = (int)((rand.nextInt(10) + 1)^4 / 16); //max 1 million dabloons
    int nameLength = rand.nextInt(8) + 3; // 3 - 10 ainclusive num of chars in name
    String name = "";
    for(int i = 0; i < nameLength; i++){
      int textType = rand.nextInt(3); // numbers, captical, or lowercase
      if(textType == 0) { 
        name += (char)(rand.nextInt(10) + 48); // numbers
      } else if(textType == 1){
        name += (char)(rand.nextInt(26) + 65); // capital letters
      } else {
        name += (char)(rand.nextInt(26) + 97); // lowercase letters
      }
    }
    Player player = new Player(name, money, id);
    
    sv.addPlayerToList(player);
  } 










  
}