public class GameApp {
    /**
     * This class runs the GameGUI class of GameMaster.
     */
    public static void main(String[] args) {
        GameGUI g1 = new GameGUI();
        g1.setUpGUI();
        g1.setUpButtonListeners();
    }
}