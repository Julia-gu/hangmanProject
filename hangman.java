public class hangman {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}

class Game {
    private Player[] players;
    private Phrase phrase;
    private int currentTurn;
    private boolean solved;

    public Game() {
        players = new Player[2];
        phrase = new Phrase("");
        currentTurn = 0;
        solved = false;
    }
    public void startGame() {
        System.out.print("Player 1's name: ");
        String player1Name = System.console().readLine();
        System.out.print("Player 2's name: ");
        String player2Name = System.console().readLine();

        players[0] = new Player(player1Name);
        players[1] = new Player(player2Name);

        System.out.println(players[0].getName() + ", enter a word or phrase to start playing the game:");
        String word = System.console().readLine().toUpperCase();
        phrase = new Phrase(word);

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
