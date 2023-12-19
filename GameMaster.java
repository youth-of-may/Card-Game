/**

@author Princess May Giron (232869)
@version December 07,2023

**/
/*
I have not discussed the Java language code in my program
with anyone other than my instructor or the teaching assistants
assigned to this course.
I have not used Java language code obtained from another student,
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program
was obtained from another source, such as a textbook or website,
that has been clearly noted with a proper citation in the comments
of my program.
*/

import java.io.*;
import java.util.*;
/*
 * The GameMaster class is the backbone of all the classes because it is what is called by the class for the GUI or for the console .
 */
public class GameMaster extends Object{
    private int turnCounter;
    private Player player1, player2;
    private boolean random, nDeck, isThereAWinner;
    private ArrayList <Card> deck;  
    private Card p1Card = new Card("","",0,0);
    private Card p2Card = new Card("","",0,0);
    private Random rand;

    
    public GameMaster(Player a, Player b){
        /*this constructor initializes the Player 1 and player 2 fields and other related fields as well. */
        this.player1 = a;
        this.player2 = b;
        p1Card = player1.getActiveCard();
        p2Card = player2.getActiveCard();
        turnCounter = 1;
        deck = new ArrayList<Card>();
        isThereAWinner = false;
        assembleDeck();

        
        
    }
    public GameMaster(Player a, Player b, boolean r, boolean n){
        /*
         * Unlike the first one this constructor contains two boolean fields. This will initialize the random and nDeck instances.
         */
        this.player1 = a;
        this.player2 = b;
        this.random = r;
        this.nDeck = n;
        p1Card = player1.getActiveCard();
        p2Card = player2.getActiveCard();
        
        turnCounter = 0;
        deck = new ArrayList<Card>();
        isThereAWinner = false;
        rand = new Random();

       
        if (nDeck == false) {
            assembleDeck();
            //this will run if the user typed "new" in the console
        }
        else {
        assembleNewDeck();
        }
        
    }
    public void setTurnCounter(int i) {
        /*This method can be used to reset or set the turn counter. */
        turnCounter = i;
    }
    
    
    private void assembleNewDeck() {
        /*
         * The assembleNewDeck method runs if the user wants to use a new Deck. It has a try and catch that will check if the file exists or not.
         */
        try {
            
        FileReader reader = new FileReader("newCards.txt");
        Scanner in = new Scanner (reader);
        //initialize a reader and a scanner
        String[] line = new String[1];
         while (in.hasNextLine()) {
            
            line = in.nextLine().split(" ");
            //while the text file has lines left then it will split the String into an array
            deck.add(new Card(line[0], line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3])));
            //it's going to take the 3rd and 4th value of line and convert it to an integer which is going to be added to deck
       }
    }
    catch(FileNotFoundException f) {
        System.out.println("File not found. Try again.");
        //this will run if the file is not found 
    }
    }
     public Player getPlayerOne() {
        /*This method will return the player1 object/instance which will be used in updating the jicons */
   
        return player1;
    }
    public Player getPlayerTwo() {
        /*This method returns the player2 object/instance which will be used in updating the jicons */
   
        return player2;
    }
    public Player getActivePlayer() {
        /*This method returns the active player based on the number of turns.*/
   
        if (turnCounter %2 ==1) {
            return player1;
        }
        else {
            return player2;
        }
        
    }
    
    public String play(String action) {
        /*
         * The play method does all the dirty work, displaying the actions in a String that will be displayed to the user.
         */
        String message = "";
        p1Card = player1.getActiveCard();
        p2Card = player2.getActiveCard();
        String cardDrawn = "";
        int determiningProductOne = 0;
        int determiningProductTwo = 0;
        if (action.equals("attack")) {
            if (turnCounter %2 ==1) {
                message =message+ "    " +  player1.getName() + " attacks with " + p1Card.getName() + ". \n";
                message =  message+ "    " + dealDamage(p1Card, p2Card) + "\n";
                turnCounter++;
                if (p2Card.getHealth()<=0) {

                    player2.discard();
                    message = message + "    " + player2.getName() + " discards " + p2Card.getName() + ". \n";
                    determiningProductOne = deck.get(0).getHealth() * deck.get(0).getPower();//get the determining product of the next two cards to find out what's going to be drawn by the user
                    determiningProductTwo = deck.get(1).getHealth() * deck.get(1).getPower();
                    if(deck.size()>0){
                    if (determiningProductOne > determiningProductTwo) {
                        player2.drawCard(deck.get(0));
                        message = message + "    " + player2.getName() + " draws " + deck.get(0).getName() + ". \n";
                        deck.add(deck.get(1)); // put the other card at the back of the deck
                        deck.remove(deck.get(0));
                        deck.remove(deck.get(1));
                        
                    }
                    else {
                        player2.drawCard(deck.get(1));
                        message = message + "    " + player2.getName() + " draws " + deck.get(1).getName() + ". \n";
                        deck.add(deck.get(0));
                        deck.remove(deck.get(1));
                        deck.remove(deck.get(0));
                    }}
                    else {
                        message = message + "    " + "The deck is empty.";
                    }
                    player1.claimToken();
                    message = message + "    " + player1.getName() + " gets a token! \n";
                    // if the player gets a token check if the player's tokens is already 3. If 3 then that player is declared the winner of the game
                    if (player1.getTokens() == 3) {
                        isThereAWinner = true;
                        if (hasWinner()) {
                            message = message + "    " + player1.getName() + " wins!!! \n";
                        }
                    }

                }
            }
            else {
                message =message+ "    " +  player2.getName() + " attacks with " + p2Card.getName() + ". \n";
                message =  message+ "    " + dealDamage(p2Card, p1Card) + "\n";
                turnCounter++;

                if (p1Card.getHealth()<=0) {

                    player1.discard();
                    message = message + "    " + player1.getName() + " discards " + p1Card.getName() + ". \n";
                    determiningProductOne = deck.get(0).getHealth() * deck.get(0).getPower();
                    determiningProductTwo = deck.get(1).getHealth() * deck.get(1).getPower();
                    if(deck.size()>0){
                    if (determiningProductOne > determiningProductTwo) {
                        player1.drawCard(deck.get(0));
                        message = message + "    " +player1.getName() + " draws " + deck.get(0).getName() + ". \n";
                        deck.add(deck.get(1));
                        deck.remove(deck.get(0));
                        deck.remove(deck.get(1));
                        
                    }
                    else {
                        player1.drawCard(deck.get(1));
                        message = message + "    " + player1.getName() + " draws " + deck.get(1).getName() + ". \n";
                        deck.add(deck.get(0));
                        deck.remove(deck.get(1));
                        deck.remove(deck.get(0));
                    }}
                    else {
                        message = message + "    " + "The deck is empty.";
                    }
                    player2.claimToken();
                    message = message + "    " + player2.getName() + " gets a token! \n";
                    if (player2.getTokens() == 3) {
                        isThereAWinner = true;
                        if (hasWinner()) {
                            message = message + "    " + player2.getName() + " wins!!!";
                            
                        }
                    }

                }

            }
        }
        else if (action.equals("swap")) {
            if (turnCounter %2 ==1) {
                message = "    " + player1.getName() + " swaps out " + player1.getActiveCard().getName() + "... \n";
                message = message + player1.swap();
            }
            else {
                message = "    " + player2.getName() + " swaps out " + player2.getActiveCard().getName() + "... \n";
                message = message + player2.swap();
            }
            turnCounter++;
        }
    
        if(hasWinner()) {
            message = message +"\n" +gameReport();
            //will get printed if one of the players have 3 tokens na
        }
        return message;
    }
    
    public boolean checkResistance (String type1, String type2) {
        /*
         * This method checks if the active card of the other player is resistant to the current card.
         */
    if ((type2.equals("Ghost") && type1.equals("Dragon")) || (type2.equals("Fairy") && type1.equals("Ghost")) || (type2.equals("Dragon") && type1.equals("Fairy"))) {
        return true;
    }
    else {
        return false;
    }
    }
    public boolean checkWeakness (String type1, String type2) {
        /*
         * This method checks if the the card of the other player is weak to the current/active card.
         */
        if (type2.equals("Fairy") && type1.equals("Dragon") || type2.equals("Ghost") && type1.equals("Fairy") || (type2.equals("Dragon") && type1.equals("Ghost"))) {
            return true;
        }
        else {
            return false;
        }
    }
    
    
    
    private void moveCardUp(int index) {
        /*
         * This method helps move the card up one place and is used when dealing cards to players.
         */
        deck.remove(index);
    }
   
    public String dealCard() {
        /*
         * This method deals cards to both players until their hand is full (5 cards each).
         */
        //check if whose turn is it then check if hand is full or not
        String message = "";
        int cards =0;
        int randNum = 0;
        if (random == false) {
        if (turnCounter % 2 ==1 && player1.handIsFull() == false) {
            //once a card is drawn remove it from the deck
                player1.drawCard(deck.get(cards));
                message = player1.getName() + " draws " + deck.get(cards).getName() ;
                moveCardUp(cards);
                cards++;
                turnCounter++;
        }
        else if (turnCounter % 2 == 0 && player2.handIsFull() == false) {
                player2.drawCard(deck.get(cards));
                message = player2.getName() + " draws " + deck.get(cards).getName();
                moveCardUp(cards);
                cards++;
                turnCounter++;
                
            }
        else {
            if (player1.handIsFull() == true) {
                message = player1.getName() + "\'s hand is full.";
            }
            else {
                message = player2.getName() + "\'s hand is full.";
            }
        }
        
    }
    else {
        //to handle the random add-on
        randNum = rand.nextInt(deck.size()); //the random number generated depends on the size of the deck
        if (turnCounter % 2 ==1 && player1.handIsFull() == false) {
            
                player1.drawCard(deck.get(randNum));
                
                message = player1.getName() + " draws " + deck.get(randNum).getName() ;
                moveCardUp(randNum);
                cards++;
                turnCounter++;
        }
        else if (turnCounter % 2 == 0 && player2.handIsFull() == false) {
            
                player2.drawCard(deck.get(randNum));
                
                message = player2.getName() + " draws " + deck.get(randNum).getName();
                moveCardUp(randNum);
                cards++;
                turnCounter++;
                
            }
        else {
            if (player1.handIsFull() == true) {
                message = player1.getName() + "\'s hand is full.";
            }
            else {
                message = player2.getName() + "\'s hand is full.";
            }
        }
    }
        message = message + ". \n";
        return message;
    }
    
    
    public String dealDamage(Card inPlay, Card target) {
        /*
         * This method determines the damage dealt to the target card, using the values returned by the checkWeakness and checkResistant methods in the process.
         */
        boolean resistance = false;
        boolean weakness = false;
        int power = inPlay.getPower();
        int damage = 0;
        String targetType = target.getType();
        String inPlayType = inPlay.getType();
        String message = "";
        resistance = checkResistance(targetType, inPlayType);
        weakness = checkWeakness(targetType, inPlayType);
        if (resistance) {
            message = "      "+ message + targetType + " is resistant to " + inPlayType + ". \n";
            damage = power /2;  
            //if the target has resistance to the active card of the attacker than the damage is halved
        }
        else if (weakness) {
            message = "      "+ message + targetType + " is weak to " + inPlayType + ". \n";
            damage = power * 2;
            //damage is doubled
                   } 
        else {
            damage = power;
            //if it is neither resistant or weak to it then damage is equal to its initial power
        }
        target.takeDamage(damage);
        //card takes damage
        message = message + "    " + inPlay.getName() + " deals " + damage + " damage on " + target.getName() + ". \n";
        message = message + "    " + target.getName() + " has " + target.getHealth() + " health left.";
        return message;
    }
    public boolean hasWinner() {
        /*
         * This method determines if a player already reached three tokens, therefore there's already a winner.
         */
        return isThereAWinner;
    }
    public String whoIsTheWinner()  {
        /*This method returns the name of the winner. */
        if (player1.getTokens() ==3){
            return player1.getName();
        }
        else {
            return player2.getName();
        }
    }
    public String gameReport() {
        /*This method returns the summary of the game/game statistics. */
        String message;
        int tempT = turnCounter -1;
        message = "";
        message = message + " ---=== GAME SUMMARY ===--- \n";
        message = message + "This game lasted " + tempT + " turns. \n";
        
        message = message + player1.statusReport() + "\n";
        
        message = message + player2.statusReport();
        
        return message;
       
    }
    private void assembleDeck()
    {
        /*This assembles the deck/original deck to be used in the game. */
    deck.add( new Card( "Dragon", "Aquira", 174, 26 ) );
    deck.add( new Card( "Ghost", "Brawn", 130, 48 ) );
    deck.add( new Card( "Fairy", "Cerulea", 162, 29 ) );
    deck.add( new Card( "Dragon", "Demi", 147, 28 ) );
    deck.add( new Card( "Ghost", "Elba", 155, 37 ) );
    deck.add( new Card( "Fairy", "Fye", 159, 42 ) );
    deck.add( new Card( "Dragon", "Glyede", 129, 26 ) );
    deck.add( new Card( "Ghost", "Hydran", 163, 35 ) );
    deck.add( new Card( "Fairy", "Ivy", 146, 45 ) );
    deck.add( new Card( "Dragon", "Jet", 170, 24 ) );
    deck.add( new Card( "Ghost", "Kineti", 139, 21 ) );
    deck.add( new Card( "Fairy", "Levi", 160, 43 ) );
    deck.add( new Card( "Dragon", "Meadow", 134, 29 ) );
    deck.add( new Card( "Ghost", "Naidem", 165, 26 ) );
    deck.add( new Card( "Fairy", "Omi", 145, 21 ) );
    deck.add( new Card( "Dragon", "Puddles", 170, 34 ) );
    deck.add( new Card( "Ghost", "Quarrel", 151, 29 ) );
    deck.add( new Card( "Fairy", "Raven", 168, 32 ) );
    deck.add( new Card( "Dragon", "Surge", 128, 27 ) );
    deck.add( new Card( "Ghost", "Takiru", 140, 26 ) );
    deck.add( new Card( "Fairy", "Ustelia", 163, 47 ) );
    deck.add( new Card( "Dragon", "Verwyn", 145, 25 ) );
    deck.add( new Card( "Ghost", "Wyverin", 158, 32 ) );
    deck.add( new Card( "Fairy", "Xios", 155, 27 ) );
    deck.add( new Card( "Dragon", "Yora", 159, 44 ) );
    deck.add( new Card( "Ghost", "Zulu", 125, 46 ) );
    }
    
    
}