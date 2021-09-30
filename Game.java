import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class Game {
  final private String[] stringArray = { "cat", "dog", "bunny", "tree", "thing" };
  private String mainWord;
  private String guessedWord = "";
  // private String[] notFoundChars = new String[10];
  // TODO : Do not accept already entered characters.
  private ArrayList<String> notFoundChars = new ArrayList<String>();
  private int totalTries = 10;
  private char choosenChar;
  private boolean isGameStopped = false;

  public void play() {
    System.out.println("Game starting");
    start();
    while (!isGameStopped) {
      // Thread.sleep(1000);
      render(); // Render all graphic features that helo the user understand what's happening during the game
      analizeInput(); // Take input from user, validate it.
      update(); // Update the game and take actions based on the input.
      // totalTries++; // Take track of the tries. DEVELOPER BRANCH ONLY
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
    String identation = "	  ";
    switch (notFoundChars.size()) {
      case 0:
        System.out.printf("%n%n%n%n%n%n 	________________%n");
        break;
      case 1:
        System.out.printf("	  %n	  |%n	  |%n	  |%n	  |%n	  |%n	__|_____________%n");
        break;
      case 2:
        System.out.printf("	  ____________%n	  |%n	  |%n	  |%n	  |%n	  |%n	__|_____________%n");
        break;
      case 3:
        System.out.printf("	  ____________%n	  | /%n	  |/%n	  |%n	  |%n	  |%n	__|_____________%n");
        break;
      case 4:
        System.out.printf("	  ____________%n	  | /        |%n	  |/%n          |%n          |%n	  |%n	__|_____________%n");
        break;
      case 5:
        System.out.printf("	  ____________%n	  | /        |%n	  |/         O%n	  |%n	  |%n	  |%n	__|_____________%n");
        break;
      case 6:
        System.out.printf("	  ____________%n	  | /        |%n	  |/         O%n	  |          |%n	  |          |%n	  |%n	__|____________%n");
        break;
      case 7:
        System.out.printf("	  ____________%n	  | /        |%n	  |/         O%n	  |         /|%n	  |          |%n	  |%n	__|____________%n");
        break;
      case 8:
        System.out.printf("	  ____________%n	  | /        |%n	  |/         O%n	  |         /|\\%n	  |          |%n	  |%n	__|_____________%n");
        break;
      case 9:
        System.out.printf("	  ____________%n	  | /        |%n	  |/         O%n	  |         /|\\%n	  |          |%n	  |         /%n	__|_____________%n");
        break;
      case 10:
        System.out.printf("	  _____________%n	  | /        |%n	  |/         O%n	  |         /|\\%n	  |          |%n	  |         / \\%n 	__|_______ _ ___%n");
        break;
    }
    System.out.println(identation + guessedWord);
    System.out.println(identation + "Not found : " + String.valueOf(notFoundChars).replace("[", "").replace("]", ""));
    if (guessedWord.replace(" ", "").equals(mainWord)) {
      System.out.println(identation + "You've won!");
    }
    if (mainWord.indexOf(choosenChar) >= 0 && guessedWord.indexOf(choosenChar) < 0) {
      Random randomGenerator = new Random();
      // TODO not working because inside if chages with previous things.
      final String[] positiveFeedback = {"Nice job!", "Good", "Yes!", "Nice work"};
        System.out.println(positiveFeedback[randomGenerator.nextInt(positiveFeedback.length)]);
    }

  }

  private void analizeInput() {
    Scanner input = new Scanner(System.in);
    String inputText = "";
    while ( !(inputText.length() == 1) ) {
      System.out.println("Enter a letter");
      inputText = input.next();
    }
    choosenChar = inputText.charAt(0);
  }

  private void update() {
    // If secret word has the character and user hasent yet entered it
    if (mainWord.indexOf(choosenChar) >= 0 && guessedWord.indexOf(choosenChar) < 0) {
      int iterator = 0;
      // Search inside all characters of the secret word
      for (char character : mainWord.toCharArray()) {
        // If your find a character inside the secret word that matches the chosen one :
         if (character == choosenChar){
            char[] guessedArray = guessedWord.toCharArray();
            guessedArray[iterator * 2] = mainWord.toCharArray()[iterator];
            guessedWord = String.valueOf(guessedArray);
         }
         iterator++;
         if (guessedWord.replace(" ", "").equals(mainWord)) {
           isGameStopped = true;
         }
      }
    // If user has already entered it
    } else if (guessedWord.contains(String.valueOf(choosenChar)) || notFoundChars.contains(String.valueOf(choosenChar))) {

      System.out.println("That letter is right there.");
    // If user letter is not correct and havent been entered before
    } else {
      notFoundChars.add(String.valueOf(choosenChar));
      if (notFoundChars.size() == totalTries) {
        isGameStopped = true;
      }
      System.out.println("Letter not found.");
    }
  }

  private void stop() {
    render();
  }

  public static void main(String[] args) {
    Game game = new Game();
    game.play();
  }
}
