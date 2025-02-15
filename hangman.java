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
 
 
        while (!solved) {
            Player currentPlayer = players[currentTurn];
            System.out.println("\n" + currentPlayer.getName() + "'s turn");
            System.out.println("word/phrase: " + phrase.getDisplay());
            System.out.println("Points: " + currentPlayer.getScore());
 
 
            System.out.print("Guess a letter or type 'solve' to solve the phrase/word: ");
            String input = System.console().readLine().toUpperCase();
 
 
            if (input.equals("SOLVE")) {
                System.out.print("Enter your solution: ");
                String solution = System.console().readLine().toUpperCase();
                if (phrase.isSolved(solution)) {
                    solved = true;
                    System.out.println("Yay, you solved the phrase!");
                    currentPlayer.addPoints(100);
                } else {
                    System.out.println("Nice try");
                }
            } else if (input.length() == 1) {
                char letter = input.charAt(0);
                if (phrase.revealLetter(letter)) {
                    System.out.println("The letter '" + letter + "' is in the phrase.");
                    currentPlayer.addPoints(50);
                } else {
                    System.out.println("The letter '" + letter + "' is not in the phrase :(");
                    currentPlayer.addPoints(-5);
                    if (currentPlayer.getScore() < 0) {
                        currentPlayer.setScore(0);
                    }
                }
            } else {
                System.out.println("Invalid input.");
            }
 
 
            if (phrase.isSolved()) {
                solved = true;
                System.out.println("Good job, you solved it!");
            }
 
 
            currentTurn = (currentTurn + 1) % players.length;
        }
 
 
        System.out.println("\nGame over! Final scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore() + " points");
        }
    }
 }
 
 
 class Player {
    private String name;
    private int score;
 
 
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
 
 
    public String getName() {
        return name;
    }
 
 
    public int getScore() {
        return score;
    }
 
 
    public void addPoints(int points) {
        this.score += points;
    }
 
 
    public void setScore(int score) {
        this.score = score;
    }
 }
 
 
 class Phrase {
    private String text;
    private boolean[] revealedLetters;
 
 
    public Phrase(String text) {
        this.text = text.toUpperCase();
        this.revealedLetters = new boolean[text.length()];
    }
 
 
    public String getText() {
        return text;
    }
 
 
    public String getDisplay() {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ' || revealedLetters[i]) {
                display.append(c);
            } else {
                display.append("_");
            }
            display.append(" ");
        }
        return display.toString();
    }
 
 
    public boolean revealLetter(char letter) {
        boolean found = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == letter && !revealedLetters[i]) {
                revealedLetters[i] = true;
                found = true;
            }
        }
        return found;
    }
 
 
    public boolean isSolved() {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ' && !revealedLetters[i]) {
                return false;
            }
        }
        return true;
    }
 
 
    public boolean isSolved(String solution) {
        return text.equals(solution);
    }
 }
 