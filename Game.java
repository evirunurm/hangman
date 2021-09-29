import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class Game {
  final private String[] stringArray = { "cat", "dog", "bunny", "tree", "thing" };
  private String mainWord;
  private String guessedWord = "";
  private ArrayList<String> notFoundChars = new ArrayList<String>();
  // TODO : Do not accept already entered characters.
  // private ArrayList<String> foundChars = new ArrayList<String>();
  private int totalTries = 0;
  private char choosenChar;
  private boolean isGameStopped = false;

  public void play() {
    System.out.println("Game starting");
    start();
    while (totalTries < 10) {
      // Thread.sleep(1000);
      render(); // Render all graphic features that helo the user understand what's happening during the game
      analizeInput(); // Take input from user, validate it.
      update(); // Update the game and take actions based on the input.
      totalTries++; // Take track of the tries. DEVELOPER BRANCH ONLY
    }
    stop();
  }

  private void start() {
    Random randomGenerator = new Random();
    int randomNumber = randomGenerator.nextInt(stringArray.length);
    mainWord = stringArray[randomNumber];

    // *** Works, but I found an easier way ...
    // char[] guessedArray = new char[mainWord.length()];
    // Arrays.fill(guessedArray, '_');
    // guessedWord = new String(guessedArray);
    // ***
    guessedWord = "_ ".repeat(mainWord.length());

    System.out.println(mainWord);
  }

  private void render() {

  }

  private void analizeInput() {
    Scanner input = new Scanner(System.in);
    String inputText = "";
    while ( !(inputText.length() == 1) ) {
      System.out.println("Write a letter!!");
      inputText = input.next();
    }
    choosenChar = inputText.charAt(0);
  }

  private void update() {
    // If secret word has the character
    if (mainWord.indexOf(choosenChar) >= 0) {
      int i = 0;
      // Search inside all characters of the secret word
      for (char character : mainWord.toCharArray()) {
        // If your find a character inside the secret word that matches the chosen one :
         if (character == choosenChar){
            char[] guessedArray = guessedWord.toCharArray();
            guessedArray[i * 2] = mainWord.toCharArray()[i];
            guessedWord = String.valueOf(guessedArray);


         }
         i++;
      }
      System.out.println(guessedWord);
      System.out.println("It has the letter!!");
    } else {

      System.out.println("Sorry the world doesnt have the letter you entered:(");
    }
  }

  private void stop() {

  }

  public static void main(String[] args) {
    Game game = new Game();
    game.play();
  }
}
