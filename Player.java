import java.util.UUID;

public class Player{

  private String id;
  private String name;
  private int cash;
  private int wins;

  public Player(String name, int cash, String id) {
    this.id = id;
    this.name = name;
    this.cash = cash;
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

  public int wins() {
    return wins;
  }

  public void setWins(int amount) {
    wins = amount;
  }

  public void matchSet(int[] values) {
    cash = values[0];
    wins = values[1];
  }

  public String id() {
    return id.toString();
  }
}