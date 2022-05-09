public class Card{

  private int num;
  private int suit;
  private int value;
  public static final int[] points = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};


  public Card(int num) {

    this.num = num;
    this.suit = num / CardList.VALUES.length;
    this.value = num % CardList.VALUES.length;

  }

  public Card(int value, int suit){
    this.value = value;
    this.suit = suit;
  }


  public int points(){
    return points[num % 13];
  }

  public String toString() {
    return CardList.SUITES[suit] + CardList.VALUES[value];
  }

  public int num() {
    return num;
  }
  
}