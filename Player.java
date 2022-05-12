import java.util.UUID;

public class Player{

  private String id;
  private String name;
  private int cash;
  private int BJWins;
  private int BJLosses;
  private int UNOWins;
  private int UNOLosses;

  public Player(String name, int cash, String id) {
    this.id = id;
    this.name = name;
    this.cash = cash;
  }

  public Player(String name, int cash, String id, int[] wins) {
    this.id = id;
    this.name = name;
    this.cash = cash;
    BJWins = wins[0];
    BJLosses = wins[1];
    UNOWins = wins[2];
    UNOLosses = wins[3];
  }
  
  public Player(String name, int cash){
    id = UUID.randomUUID().toString();
    this.name = name;
    this.cash = cash;
  }

  public Player(String name) {
    id = UUID.randomUUID().toString();
    this.name = name;
  }
  
  public void setMoney(int amount){
    cash = amount;
  }
  
  public String name() { 
	  return name;
  }
  
  public int cash() {
	  return cash;
  }

  public int[] wins() {
    return new int[]{BJWins, BJLosses, UNOWins, UNOLosses};
  }

  public void addWins(int[] amount) {
    BJWins += amount[0];
    BJLosses += amount[1];
    UNOWins += amount[2];
    UNOLosses += amount[3];
  }

  // sets all values that are likely to be changed after a match
  public void matchSet(int[] values) {
    cash = values[0];
    BJWins = values[0];
    BJLosses = values[1];
    UNOWins = values[2];
    UNOLosses = values[3];
  }

  public String id() {
    return id.toString();
  }
}