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
      int BJWins = scan.nextInt();
      int BJlosses = scan.nextInt();
      int UNOWins = scan.nextInt();
      int UNOLosses = scan.nextInt();
      
      Player[] newOut = new Player[output.length + 1];
      for(int i = 0; i < output.length; i++) {
        newOut[i] = output[i];
      }
      
      newOut[newOut.length - 1] = new Player(name, money, UUID, new int[]{BJWins, BJlosses, UNOWins, UNOLosses});
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



  
  public boolean update(String name, Player player) {
    for(int i = 0; i < players.length; i++) {
      if(players[i].name().equals(name)) {
        players[i] = player;
        return true;
      }
    }
    return false;
  }
  

  

  // returns every loaded player
  public Player[] getPlayers() {
    return players;
  }

  public void setPlayers(Player[] players) {
    this.players = players;
  }


  // saves all of the player data to the file
  public void save() throws FileNotFoundException{
    PrintStream ps = new PrintStream(file);
    for(int i = 0; i < players.length; i++) {
      ps.println(players[i].id() + " " + players[i].cash() + " " + players[i].name() + " " + players[i].wins()[0] + " " + players[i].wins()[1] + " " + players[i].wins()[2] + " " + players[i].wins()[3]);
    }
    ps.close();
  }










  
}