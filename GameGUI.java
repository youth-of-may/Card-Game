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
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
public class GameGUI {
    /**
     * This method creates a GUI of the GameMaster class.
    */
    private JFrame frame;
    private GameMaster g1;
    private String pName1, pName2;
    private boolean randNum, newD;
    
    private JButton startButton, playButton;
    private JButton backToMenu;
    private JButton attack, swap, drawCard;
    
    private JPanel welcomePage, playersPage, mainGame;
    private JPanel mainGameUp, mainGameDown;
    private JPanel mgL, mgC, mgR;
    private JPanel player2HandLabelHolder;
    private JPanel question;
    private boolean hasWinner;
    
    private ImageIcon bgIconWP, bgIconPP, bgIconMG;
    private ImageIcon cardHolderP1, cardHolderP2;

    private JLabel cardP1, cardP2;
    private JLabel backgroundHolderW, backgroundHolderP, backgroundHolderM;
    private JLabel player1Label, player2Label;
    private JLabel playerTurnName;
    private JLabel player1Mg, player2Mg;
    private JLabel p1Tokens, p2Tokens;
    private JLabel atkSwap;

    private JTextField player1Name, player2Name;
    private JTextArea results, cardReport;
    private JCheckBox randomCards, newDeck;
    private JScrollPane y;

    //for the card juggler
    private ArrayList<ImageIcon> image;
    CardLayout cardLayout;
    Container cp;

    public GameGUI() {
        /**
         * This initializes all the fields of the class.
         */
        pName1 = "";
        pName2 = "";
        randNum = false;
        newD = false;
        backToMenu = new JButton("Play Again");

        

        //adding stuff into the image arraylist
        image = new ArrayList<ImageIcon>();
        image.add(new ImageIcon("dragonCard.jpg"));
        image.add(new ImageIcon("fairyCard.jpg"));
        image.add(new ImageIcon("ghostCard.jpg"));
        image.add(new ImageIcon("bgCard.jpg"));

        //creating a card layout
        cardLayout = new CardLayout();
        
        //creating a frame and a container
        frame = new JFrame();
        cp = frame.getContentPane();
        cp.setLayout(cardLayout);
        
        //for the welcomePage
        bgIconWP = new ImageIcon("welcomescreen.jpg");
        backgroundHolderW = new JLabel(bgIconWP);
        backgroundHolderW.setSize(800,600);


        //I used null layout so that I can easily put stuff in the frame
        welcomePage = new JPanel();
        welcomePage.setLayout(null);
        startButton = new JButton("start");

        //setting the size and location of the button
        startButton.setBounds(300,450, 200,50);
        
        //adding another thingy to the jlabel that handles the background
        backgroundHolderW.add(startButton);
        welcomePage.add(backgroundHolderW);
        

        //for the playersPage where players are going to enter their names
        playersPage = new JPanel();
        playersPage.setLayout(null);
        bgIconPP = new ImageIcon("playerPage.jpg");
        backgroundHolderP = new JLabel(bgIconPP);
        backgroundHolderP.setSize(800,600);
        

        //adding the text fields and button to collect the player's info
        //this is for player1
        player1Label = new JLabel("Player 1", JLabel.CENTER);
        player1Label.setFont(new Font("Arial",Font.BOLD,15));
        player1Label.setForeground(Color.WHITE);
        player1Label.setBounds(375, 140, 300,200);
        player1Name = new JTextField(10);
        player1Name.setBounds(375, 265, 300, 30);
        backgroundHolderP.add(player1Label);
        backgroundHolderP.add(player1Name);

        //player 2
        player2Label = new JLabel("Player 2", JLabel.CENTER);
        player2Label.setFont(new Font("Arial",Font.BOLD,15));
        player2Label.setForeground(Color.WHITE);
        player2Label.setBounds(375, 215, 300,200);
        player2Name = new JTextField(10);
        player2Name.setBounds(375, 340, 300, 30);
        backgroundHolderP.add(player2Label);
        backgroundHolderP.add(player2Name);

        
        //making the play button
        playButton = new JButton("PLAY");
        playButton.setBounds(575, 400, 100,50);
        backgroundHolderP.add(playButton);

        //adding the checkboxes
        
        randomCards = new JCheckBox("Random?", false);
        newDeck = new JCheckBox("New Deck?", false);

        randomCards.setFont(new Font("Arial",Font.BOLD,12));
        randomCards.setForeground(Color.WHITE);
        randomCards.setBounds(375, 400, 100, 50);

        newDeck.setBounds(475, 400,100,50);
        newDeck.setFont(new Font("Arial",Font.BOLD,12));
        newDeck.setForeground(Color.WHITE);
        
        //setOpaque remove the backgroundColor of the checkbox
        randomCards.setOpaque(false);
        newDeck.setOpaque(false);

        //adding it to the holder
        backgroundHolderP.add(randomCards);
        backgroundHolderP.add(newDeck);

        playersPage.add(backgroundHolderP);


        //for the main game that contains everything
       mainGame = new JPanel();
       mainGame.setLayout(new GridLayout(2,1));
       bgIconMG = new ImageIcon("mainPage.jpg");
       backgroundHolderM = new JLabel(bgIconMG);
       backgroundHolderM.setSize(800,600);

       //mainGameUp
       mainGameUp  = new JPanel();
       mainGameUp.setLayout(new GridLayout(1,3));

       //mainGameUpLeft
       mgL = new JPanel();
       mgL.setLayout(null);
       cardHolderP1 = new ImageIcon("bgCard.jpg");
       cardP1 = new JLabel(cardHolderP1);
       cardP1.setBounds(25, 50, 200, 200);
       
       //tokenCounter
       p1Tokens = new JLabel("Tokens: ");
       p1Tokens.setBounds(80, 240, 150, 50);
       p1Tokens.setFont(new Font("Arial",Font.BOLD,15));
      
       mgL.add(p1Tokens);
       mgL.add(cardP1);

       //Jlabels that will contain the player 1's name

       player1Mg = new JLabel("", JLabel.CENTER);
       player1Mg.setBounds(50, 0, 150, 50);
       player1Mg.setFont(new Font("Arial",Font.BOLD,15));
       
       mgL.add(player1Mg);

       

       //mainGameUpcenter
       mgC = new JPanel();
       mgC.setLayout(new GridLayout(6,1, 0, 20));
       mgC.add(new JPanel());
       
       
       //creating a jpanel that will contain the player Turn
       JPanel playerT = new JPanel();
       playerT.setLayout(new GridLayout(1,1, 0,10 ));
       playerTurnName = new JLabel("Player turn", JLabel.CENTER);
       playerTurnName.setFont(new Font("CenturyGothic",Font.BOLD,20));
       playerT.add(playerTurnName);
       mgC.add(playerT);
       

       //creating a jpanel that will contain the attack or swap question
       question = new JPanel();
       atkSwap = new JLabel("Attack or Swap?");
       atkSwap.setFont(new Font("CenturyGothic",Font.BOLD,15));
       question.add(atkSwap);
       mgC.add(question);

       //creating a jpanel that will contain the attack and swap buttons
       JPanel mainButs = new JPanel();
       mainButs.setLayout(new GridLayout(1, 2, 20, 20));
       attack = new JButton("attack");
       swap = new JButton("swap");
       mainButs.add(attack);
       mainButs.add(swap);

       mgC.add(mainButs);

       //adding a drawCard Button
       backToMenu.setVisible(false);
       drawCard = new JButton("Draw Card");
       mgC.add(drawCard);
       mgC.add(backToMenu);
       

       //mainGameRight
       mgR = new JPanel();
       mgR.setLayout(null);
       cardHolderP2 = new ImageIcon("bgCard.jpg");
       cardP2 = new JLabel(cardHolderP2);
       cardP2.setBounds(35, 50, 200, 200);

       //p2tokens
       p2Tokens = new JLabel("Tokens: ");
       p2Tokens.setBounds(100, 240, 150, 50);
       p2Tokens.setFont(new Font("Arial",Font.BOLD,15));
      
       mgR.add(p2Tokens);
       mgR.add(cardP2);
       
       //JLabel of player2's name
       player2HandLabelHolder = new JPanel();
       player2HandLabelHolder.setLayout(new GridLayout(1,1));
       player2Mg = new JLabel("", JLabel.CENTER);
       player2Mg.setBounds(50, 0, 150,50);
       player2Mg.setFont(new Font("Arial",Font.BOLD,15));
       mgR.add(player2Mg);

       //adding it to mainGameUp
       mainGameUp.add(mgL);
       mainGameUp.add(mgC);
       mainGameUp.add(mgR);


       //mainGameDown
       mainGameDown = new JPanel();
       mainGameDown.setLayout(new GridLayout(1,2,5,0));
       
       results = new JTextArea(10, 2);
       results.setLineWrap(true);
       y = new JScrollPane( results); 
       y.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       y.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       cardReport = new JTextArea();
       

      
       mainGameDown.add(y);
       mainGameDown.add(cardReport);
       mainGameDown.setBorder(new EmptyBorder(10,10,10,10));

       //adding to the main Jpanel
       
       mainGame.add(mainGameUp);
       mainGame.add(mainGameDown);
       
       

        //adding to the container
        cp.add(welcomePage);
        cp.add(playersPage);
        cp.add(mainGame);
        

        
    }
    public void setUpGUI() {
        /**
         * This method sets up the GUI.
         */
        //putting all of the basic settings of a JFrame
        frame.setTitle("Yu-Noh!: Card Duel");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);//to center the window?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    private void backToFirstState() {
        /**
         * This method brings back everything to its normal state.
         */
        player1Name.setText("");
        player2Name.setText("");
        player1Mg.setText("");
        player2Mg.setText("");
        p1Tokens.setText("Tokens: ");
        p2Tokens.setText("Tokens: ");
        results.setText("");
        cardReport.setText("");
        cardP1.setIcon(image.get(3));
        cardP2.setIcon(image.get(3));
        atkSwap.setVisible(true);
        playerTurnName.setText("Player 's Turn");
        playerTurnName.setForeground(new Color(0,0,0));
        drawCard.setVisible(true);
        randomCards.setSelected(false);
        newDeck.setSelected(false);
        
        
    }
    private void gameEnding (boolean win) {
        /**
         * This method is called when a player won.
         */
        if (win){
        atkSwap.setText("");
        String m;
        m = "Congratulations to " + g1.whoIsTheWinner() + " for winning the game!!";
        playerTurnName.setText("Player " + g1.whoIsTheWinner() + " wins!");
        playerTurnName.setFont(new Font("CenturyGothic",Font.BOLD,30));
        playerTurnName.setForeground(new Color(255,0,0));
        cardReport.setText(m);
        atkSwap.setText("");
        attack.setVisible(false);
        swap.setVisible(false);
        backToMenu.setVisible(true);
        
    }}
    private void runGame() {
        /**
         * This method is called to start the game.
         */
                hasWinner = false;
                String message = "";
                Player player1 = new Player(pName1);
                Player player2 = new Player(pName2);
                randNum = randomCards.isSelected();
                newD = newDeck.isSelected();
                g1 = new GameMaster(player1, player2, randNum, newD);
                g1.setTurnCounter(1);
                message = String.format("Welcome back, %-20s\nThe game begins. \n\n", pName1 + " and " + pName2+ "!");
                for (int i = 0; i<10; i++) {
                    message = message + g1.dealCard();
                    }
                results.setText(message);
                playerTurnName.setText("Player " + g1.getActivePlayer().getName() + "\'s turn");
    }
    private void setPlayer2CardImages() {
        /**
         * This method is called to set the Player 2's imageHolder's to its respective images (based on card type).
         */
        String cardType = "";
        cardType = g1.getPlayerTwo().getActiveCard().getType(); //getting player 1's active card
         if (cardType == "Dragon") {
                    cardP2.setIcon(image.get(0));
                }
                else if (cardType == "Fairy") {
                    cardP2.setIcon(image.get(1));
                }
                else {
                    cardP2.setIcon(image.get(2));
                }
        
    }
    private void setPlayer1CardImages() {
        /**
         * This method is called to set the Player 1's imageHolder's to its respective images (based on card type).
         */
        String cardType = "";
        cardType = g1.getPlayerOne().getActiveCard().getType();
         if (cardType == "Dragon") {
                    cardP1.setIcon(image.get(0));
                }
                else if (cardType == "Fairy") {
                    cardP1.setIcon(image.get(1));
                }
                else {
                    cardP1.setIcon(image.get(2));
                }
        
    }
    public void setUpButtonListeners() {
        /**
         * This method is called to set up all the button listeners.
         */
        hasWinner = false;
        ActionListener toPlayersPage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLayout.next(cp);
            }
        };
        ActionListener toMainPage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cardLayout.next(cp);
                pName1 = player1Name.getText();
                pName2 = player2Name.getText();
                playerTurnName.setText("Player "+ pName1  + "'s Turn");
                
                //setting the JLabel's to the name of the player

                player1Mg.setText("Player " + pName1 + "'s hand");
                player2Mg.setText("Player " + pName2 + "'s hand");
                
                randNum = randomCards.isSelected();
                newD = newDeck.isSelected();
                attack.setVisible(false);
                swap.setVisible(false);

                
                //setting the tokens thingy
                p1Tokens.setText("Tokens: 0");
                p2Tokens.setText("Tokens: 0");

                
            }
            
        };
        ActionListener drawCardLst = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                attack.setVisible(true);
                swap.setVisible(true);
                runGame();
                drawCard.setVisible(false);
                playerTurnName.setText("Player " + g1.getActivePlayer().getName() + "\'s turn");
                
                setPlayer1CardImages();
                setPlayer2CardImages();
                //cardReport.setText(g1.getPlayerTwo().statusReport() + g1.getPlayerOne().statusReport());

                
            }
            
        };
        ActionListener attackListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hasWinner = g1.hasWinner();
                if (hasWinner == false) {
                    //how can we check it so that we know if the buttons have to be hidden na
                results.setText(g1.play("attack"));
                playerTurnName.setText("Player " + g1.getActivePlayer().getName() + "\'s turn");
                hasWinner = g1.hasWinner(); //double-checking because it might go uncaught
                cardReport.setText("---=== GAME STATISTICS ===--- \n" + g1.getPlayerOne().statusReport() + g1.getPlayerTwo().statusReport());
                gameEnding(hasWinner);
                p1Tokens.setText("Tokens: " + g1.getPlayerOne().getTokens());
                p2Tokens.setText("Tokens: " + g1.getPlayerTwo().getTokens());
            }
                else {
                    atkSwap.setText("");
                    playerTurnName.setText("Player " + g1.whoIsTheWinner() + "wins!");
                    playerTurnName.setForeground(new Color(255,255,255));
                    cardReport.setText(g1.gameReport());
                    atkSwap.setText("");
                    attack.setVisible(false);
                    swap.setVisible(false);
                }
                setPlayer1CardImages();
                setPlayer2CardImages();

            }
        };
        ActionListener swapListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                hasWinner = g1.hasWinner();
                if (hasWinner == false) {
                results.setText(g1.play("swap"));
                playerTurnName.setText("Player " + g1.getActivePlayer().getName() + "\'s turn");
                cardReport.setText(g1.getActivePlayer().statusReport());
                hasWinner = g1.hasWinner();
                gameEnding(hasWinner);
                }
                else {
                    atkSwap.setText("");
                    playerTurnName.setText("Player " + g1.whoIsTheWinner() + "wins!");
                    playerTurnName.setForeground(new Color(255,255,255));
                    cardReport.setText(g1.gameReport());
                    attack.setVisible(false);
                    swap.setVisible(false);
                }
                setPlayer1CardImages();
                setPlayer2CardImages();
            }
        };
        ActionListener backToMenuLst = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cardLayout.first(cp);
                backToFirstState();
                backToMenu.setVisible(false);
            }
        };
        startButton.addActionListener(toPlayersPage);
        playButton.addActionListener(toMainPage);
        drawCard.addActionListener(drawCardLst);
        attack.addActionListener(attackListener);
        swap.addActionListener(swapListener);
        backToMenu.addActionListener(backToMenuLst);
    }

}