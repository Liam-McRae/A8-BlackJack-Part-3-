import java.util.Scanner;
import java.io.FileNotFoundException;

class BlackJack {
  
  public static Player play(Player player) throws FileNotFoundException{   //returns money change & win change
    
    int money = player.cash();
    Scanner scan = new Scanner(System.in);

    CardList deck = new CardList();
    deck.shuffle();
    CardList hand = new CardList(deck.draw(2));
    CardList dealerHand = new CardList(deck.draw(2));
      
    //get player bet
    int bet = money / 2;
    System.out.println("You have $" + money + ".");
    System.out.print("How much would you like to wager? $");
    int input2 = scan.nextInt();

    if(input2 > money) {
      input2 = money;
    }
    bet = input2;
    money -= bet;
    
    //quicksave player money to prevent save-scumming
    player.setMoney(money);
    Game.savePlayer(player);
    
    System.out.println();

    // find naturals and inform player of cards
    System.out.println("Dealer has: " + dealerHand.display(true, 1));
    System.out.println("You have: " + hand.display(true, 0));
    System.out.println();

    boolean matchOver = false;
    boolean dealerBlackJack = false;
    boolean playerBlackJack = false;
      
    if(dealerHand.bestPoints() == 21){
      dealerBlackJack = true;
      System.out.println("Dealer has a blackjack!");
    }
    if(hand.bestPoints() == 21){
      playerBlackJack = true;
      System.out.println("You have a blackjack!");
    }
  
    if(playerBlackJack){
      if(dealerBlackJack){
        matchOver = true;
        System.out.println("It is a draw. Your bet is returned."); //~0.06% chance, could honestly not program this in and no one would notice
        System.out.println();
      } else {
        matchOver = true;
        System.out.println("You are a winner! 2 times your bet is given.");
        player.addWins(new int[]{1, 0, 0, 0});
        money += bet * 2;
        System.out.println();
      }
  
    } else {
      if(dealerBlackJack){
        matchOver = true;
        bet = 0;
        System.out.println("The dealer has a natural. Your bet has been forfeited.");
        player.addWins(new int[]{0, 1, 0, 0});
        System.out.println();
      }
    }



    // player's turn
    if(!matchOver) {
    
      System.out.println("It is your turn.");
    
      while(true){
        System.out.println("1) hit");
        System.out.println("2) stand");
    
        boolean notValidIn = true;
        boolean hit = false;
    
        while(notValidIn){
          String input = scan.next();
    
          if(input.charAt(0) == '1'){
            hit = true;
            notValidIn = false;
          } else if(input.charAt(0) == '2') {
            hit = false;
            notValidIn = false;
          } else {
            notValidIn = true;
            System.out.println("Invalid option. Please retype your answer.");
          }
        }
        System.out.println();
          
        if(hit){
          hand.add(deck.take(0));
          System.out.println(hand.display(true, 0));
  
          if(hand.bestPoints() > 21){
            System.out.println("You have gone bust. Your bet has been forfeited.");
            player.addWins(new int[]{0, 1, 0, 0});
            matchOver = true;
            System.out.println();
            break;
          }
    
        } else if(!hit) {
          break;
        }        
      }
    }



    if(!matchOver) {
      // dealers turn
      System.out.println("It is the dealer's turn.");
      System.out.println(dealerHand.display(true, 0));
  
      while(dealerHand.bestPoints() < 17) {
        System.out.println("The dealer draws a " + deck.get(0) + ".");
        dealerHand.add(deck.take(0));
        System.out.println("Dealer has: " + dealerHand.display());
      }


    
      // get final tally of points and act acoordingly
      if(dealerHand.bestPoints() <= 21) {
        System.out.println(dealerHand.bestPoints() + " : " + hand.bestPoints());      
      }
      if(dealerHand.bestPoints() > 21){
        System.out.println("The dealer has gone bust. 2 times your bet has been paid.");
        player.addWins(new int[]{1, 0, 0, 0});
        money += 2 * bet;
      } else if(dealerHand.bestPoints() > hand.bestPoints()) {
        System.out.println("The dealer has more points than you. Your bet has been collected.");
        player.addWins(new int[]{0, 1, 0, 0});
      } else if(dealerHand.bestPoints() < hand.bestPoints()) {
        System.out.println("The dealer has less points than you. 2 times your bet has been paid.");
        money += 2 * bet;
        player.addWins(new int[]{1, 0, 0, 0});
      } else {
        System.out.println("It is a standoff. Your bet has been paid.");
        money += bet;
      }
    }
    System.out.println();
    System.out.println("You now have $" + money + ".");
    System.out.println();
    player.setMoney(money);

    scan.close();
    return player;
    
  }
  //display cards
}
