import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class SaveLoad {

  private File file;
  
  private Player[] players;
  
  public SaveLoad(String file) throws FileNotFoundException {
    this.file = new File(file);
    players = loadPlayers();
  }

  public boolean addPlayer(Player p) {

    if(getPlayer(p.name()) != null || getPlayerUUID(p.id()) != null){
      return false;
    }
    
    Player[] newPlayers = new Player[players.length + 1];
    for(int i = 0; i < players.length; i++){
      newPlayers[i] = players[i];
    }
    newPlayers[players.length] = p;

    players = newPlayers;
    return true;
  }

  private Player[] loadPlayers() throws FileNotFoundException {
    Scanner scan = new Scanner(file);
    Player[] output = {};

    while(scan.hasNext()){
      String UUID = scan.next();
      int money = scan.nextInt();
      String name = scan.next();
      
      Player[] newOut = new Player[output.length + 1];
      for(int i = 0; i < output.length; i++) {
        newOut[i] = output[i];
      }
      
      newOut[newOut.length - 1] = new Player(name, money, UUID);
      output = newOut;
    }
    scan.close();


    return output;
  }

  public Player getPlayerUUID(String UUID){
    Player output = null;

    for(Player p : players){
      if(p.id().equals(UUID)){
        output = p;
      }
    }
    
    return output;
  }


  // returns the player with the specified name, unless it is not found in which case null will be returned
  public Player getPlayer(String name){
    Player output = null;
    for(int i = 0; i < players.length; i++) {
      //System.out.println("Found: " + players[i].getName() + "<---");
      //System.out.println("Target: " + name + "<---");
      if(players[i].name().equals(name)){
        output = players[i];
        //System.out.println("!");
        
        break;
      }
    }
    return output;
  }


  // returns every loaded player
  public Player[] getPlayers() {
    return players;
  }


  // adds a player to the players array. Does not save the new player to the playerdata file
  public void addPlayerToList(Player player){
    Player[] newArray = new Player[players.length + 1];
    for(int i = 0; i < players.length; i++){
      newArray[i] = players[i];
    }
    newArray[newArray.length - 1] = player;
    
    players = newArray;
  }


  // saves all of the player data to the file
  public void save() throws FileNotFoundException{
    PrintStream ps = new PrintStream(file);
    for(int i = 0; i < players.length; i++) {
      ps.println(players[i].id() + " " + players[i].cash() + " " + players[i].name());
    }
    ps.close();
  }










  
}