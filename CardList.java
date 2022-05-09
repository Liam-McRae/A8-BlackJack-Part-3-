import java.util.ArrayList;
import java.util.Random;

public class CardList{
  
  static final String[] SUITES = {"♠","♥","♦","♣"};
  static final String[] VALUES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

  private ArrayList<Card> cards = new ArrayList<Card>();


  
  public String display(){
    return display(true, 0);
  }

  public String display(boolean showTotal) {
    return display(showTotal, 0);
  }

  public String display(boolean showTotal, int hideEnd) {
    String print = "";
    for(int i = 0; i < cards.size() - hideEnd; i++) {
      print += cards.get(i) + ", ";
    }
    for(int i = cards.size() - hideEnd; i < cards.size(); i++) {
      print += "??, ";
    }
    print = print.substring(0, print.length() - 2);
    if(showTotal) {
      int[] total = this.pointTotal(hideEnd);
      print += " (total points: ";
      for(int i = 0; i < total.length; i++) {
        print += total[i] + ", ";
      }
      if(hideEnd > 0) {
        print += "+ ??";
      } else {
        print = print.substring(0, print.length() - 2);
      }
      print += ")";
    }

    return print;
  }

  public CardList() {
    cards = createCards(0);
  }


  
  public CardList(int[] contents){
    for(int i = 0; i < contents.length; i++){
      cards.add(new Card(contents[i]));
    }
  }

  public CardList(Card[] contents){
    for(Card card : contents) {
      cards.add(card);
    }
  }
  
  public CardList(int deckType){
    cards = createCards(deckType);
  }


  
  public ArrayList<Card> cards() {
    return cards;
  }


  
  public Card[] cardArr() {
    Card[] arr = new Card[cards.size()];
    for(int i = 0; i < arr.length; i++) {
      arr[i] = cards.get(i);
    }
    return arr;
  }


  
  private ArrayList<Card> createCards(int type) {
    ArrayList<Card> output = new ArrayList<Card>();
    
    if(type == 0){ // one deck
      output = new ArrayList<Card>();
      for(int i = 0; i < 52; i++){
        output.add(new Card(i));
      }
    } else if(type == 1) { // empty
      //empty
    } else if(type == 2) { // 4 decks
      output = new ArrayList<Card>();
      for(int i = 0; i < 208; i++){
        output.add(new Card(i % 52));
      }
    }

    return output;
  }


  
  public void add(Card card) {
    cards.add(card);
  }


  
  public Card get(int index) {
    return cards.get(index);
  }


  
  public Card take(int index) {
    return cards.remove(index);
  }


  
  public Card[] draw(int num) {
    Card[] returnArr = new Card[num];
    for(int i = 0; i < num; i++) {
      returnArr[i] = cards.remove(0);
    }
    return returnArr;
  }


  
  public int[] pointTotal(int removeEnd) {
    int aces = 0;
    int points = 0;
    
    for(int i = 0; i < cards.size() - removeEnd; i++) {
      if(cards.get(i).points() == 1) {
        aces++;
      } else {
        points += cards.get(i).points();
      }
    }

    int[] total = new int[aces + 1];

    for(int i = 0; i < total.length; i++) {
      total[i] = points + (i * 11) + (aces - i);
    }

    return total;    
  }


  
  public int bestPoints() {
    int[] arr = this.pointTotal(0);
    int best = arr[arr.length - 1];

    for(int i = arr.length - 2; i >= 0; i--) {
      if((arr[i] > best || best > 21) && arr[i] <= 21) {
        best = arr[i];
      }
    }

    return best;
  }


  
  public void shuffle() {
    Random rand = new Random();
    
    for(int i = 0; i < cards.size(); i++) {
      Card temp = cards.get(i);
      int rint = rand.nextInt(cards.size());
      cards.set(i, cards.get(rint));
      cards.set(rint, temp);
    }
  }


  
}