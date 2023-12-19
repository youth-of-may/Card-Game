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
public class Player extends Object{
    /**The Player class creates Players that will contend in the game. **/
    private String playerName;
    private int tokenCounter, activeCard, cardCounter;
    private boolean fullHand;
    private Card[] hand;
    
// The array of Card objects is instantiated.
    public Player(String n) {
        /**This constructor initializes the fields to be used later. **/
        //instantiate all the variables that'll be used later
        this.playerName = n;
        tokenCounter = 0;
        fullHand = false;
        hand = new Card[5];
        activeCard = 0;
        cardCounter = 0;
        
    }
    public void drawCard(Card c) {
        /**This method adds the cards dealt to the hand array. **/
        if (cardCounter ==4) {
            hand[cardCounter] = c;
            fullHand = true;
        }
        hand[cardCounter] = c;
        cardCounter++;
        
        }

    public void discard() {
        /**This method discards a card when the GameMaster has determined that it's health is less than or equal to zero. **/
        //if a card is discarded subtract one from cardCounter and set the activeCard = null 
        cardCounter-=1;
        hand[activeCard] = null;
        
        for (int i = 0; i<4; i++) {
            
            //move the cards up and set fullHand = false;
            fullHand= false;
            hand[i] = hand[i+1];
        }
        //set the last card equal to null
        hand[4] = null;
    }
    private int findCard(int numCards) {
        /**
         * This method finds the card with the highest determining product.
         */
        //this is for the swap function (which gets the product of the health and power of a card to determine which card is going to be swapped to the active card)
        int index = 0;
        int product = 0;
        boolean cardFound = false;
        for (int i = activeCard +1; i <numCards; i++) {
             if ((hand[i].getHealth() * hand[i].getPower())> product) {
                product = hand[i].getHealth() * hand[i].getPower();
                index = i;
                cardFound = true;
            }
        }
        if (cardFound) {
        return index;
        }
        else {
            return -1;
        }
    }
    public String swap() {
        /**
         * This method swaps out the active card to the card with the highest determining product;
         */
        int cardsCounter =0;
        Card temp;
        String message;
        for (int i = 0; i<5; i++) {
            if (hand[i] != null) {
                cardsCounter++;
            }
        }
        //this is done to make sure that more than 1 card is in the hand if the player chooses to swap
        if (cardsCounter>1) {
            //set temp equal to the active card
            //set the activeCard equal to the card which has the highest product
            temp = hand[activeCard];
            hand[activeCard] = hand[findCard(cardsCounter)];
            hand[findCard(cardsCounter)] = temp;
            message = "    " +hand[activeCard].getName() + " is now active with " + hand[activeCard].getHealth() + " health.\n";
            return message;
        }
        else {
            message = playerName + " has no other card to swap with. Turn forfeited.\n";
            return message;
        }
    }
    public void claimToken() {
        /*
         * This method is called when the player eliminated the opponent's card. Adds one token to their tokens.
         */
        tokenCounter+=1;
    }
    public Card getActiveCard() {
        /**This method returns the active card in a player's hand. */
        Card activeC = hand[activeCard];
        return activeC;
    }
    public String getName() {
        /*This method returns the name of the player. */
        return playerName;
    }
    public int getTokens() {
        /*This method returns the number of tokens of a player. */
        return tokenCounter;
    }
    public boolean handIsFull() {
        /*This method returns true if the player's hand is full; otherwise false. */
        return fullHand;
    }
    public String statusReport() {
        /*This method returns a string that contains a report/overview of their hand. */
        String s = "";
        String m = "";
        String h = "";
        s = s + playerName.toUpperCase() + "\n";
        for (int i = 0; i<5; i++) {
            if (hand[i] != null){
            m = String.format("%10s : %d",hand[i].getName(), hand[i].getHealth());
            s = s + m + "\n" ; 
            
        }
        
    }
    //this will return the status of each card in a player's hand
    return s;
}
}