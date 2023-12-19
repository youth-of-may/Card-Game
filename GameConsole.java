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

import java.util.Scanner;
import java.lang.*;
/**
 * The Game Console class initializes the GameMaster class in the console. It is the text-based version of the game.
 */
public class GameConsole extends Object{
    /**
     * In the main method, a try-catch is written that will determine which of the two constructors (there are two constructors with different signatures) will be called.
     * @param args
     */
    public static void main(String[] args) {
        //declaring variables to be used later
        Scanner in = new Scanner(System.in);
        String p1 = "player1", p2 = "player2";
        String s = "";
        boolean random = false, newDeck = false;
        Player player1 = new Player(p1);
        Player player2 = new Player(p2);
        GameMaster g1= new GameMaster(player1, player2);

        /*
         * I'm thinking of ano like set the random and newDeck variables to false talaga and having a default name na lang for player1 and player2
         */

        try {
            //checking if the first two args are available
            p1 = args[0];
            p2 = args[1];
            player1 = new Player(p1);
            player2 = new Player(p2);
            try {
                //checks if the third and fourth arguments are available
                random = args[2].equals("random");
                newDeck = args[3].equals("new");
                g1 = new GameMaster(player1, player2, random, newDeck);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                g1 = new GameMaster(player1, player2, false, false);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //uses the default player name if there are no provided names
            player1 = new Player(p1);
            player2 = new Player(p2);
            g1 = new GameMaster(player1, player2, false, false);
        }
        
        
        
            
        System.out.printf("Welcome back, %-20s\nThe game begins. \n\n", p1 +" and " + p2+"!");
        g1.setTurnCounter(1);
        for (int i = 0; i<10; i ++) {
        System.out.println(g1.dealCard());
        }
        //the user is asked for another input if the input is not attack or swap
        while (g1.hasWinner() == false) {
            System.out.print("Attack or swap? ");
            s = in.next().toLowerCase();
            if (s.equals("attack" )|| s.equals("swap"))
            {
            System.out.println(g1.play(s));
            }
            else if (s.equals("quit")) {
                System.exit(0);
            }
            else {
                System.out.print("Please enter a valid keyword. \n");
            
            }            
        }
        }
}