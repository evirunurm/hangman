import java.util.Random;
import java.util.Scanner;

class Game {
  private String mainWord;
  private String guessedWord = "";
  private String notFoundChars;
  private int totalTries = 0;
  private char choosenChar;
  private boolean isGameStopped = false;

  public void play() {
    System.out.println("Game starting");
    start();
    while (totalTries < 10) {
      System.out.println("Game loop!");
      // Thread.sleep(1000);
      render();
      analizeInput();
      update();
      totalTries++;
    }
    stop();
  }

  private void start() {
    final String[] stringArray = { "cat", "dog", "bunny", "tree", "thing" };
    Random randomGenerator = new Random();
    int randomNumber = randomGenerator.nextInt(stringArray.length);
    mainWord = stringArray[randomNumber];

    System.out.println("Game started");
    System.out.println(mainWord);
  }

  private void render() {

  }

  private void analizeInput() {
    Scanner input = new Scanner(System.in);
    String inputText = "";
    while (inputText.length() > 1 || inputText.length() == 0) {
      System.out.println("Write a letter!!");
      inputText = input.next();
    }
    choosenChar = inputText.charAt(0);
    System.out.println("Finished analizing");
  }

  private void update() {
    // if (true) {
    //   System.out.println("Yes");
    // } else {
    //   System.out.println("No");
    // }
    System.out.println("Finished updating");
  }

  private void stop() {

  }

  public static void main(String[] args) {
    Game game = new Game();
    game.play();
  }

}
