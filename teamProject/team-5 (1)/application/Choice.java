package application;

/**
 * Contains one of the several choices in a question
 *
 */
public class Choice {
  /**
   * whether the Choice is correct
   */
  protected boolean isCorrect;

  /**
   * The text of the Choice
   */
  protected String choice;

  /**
   * Constructor of class Choice
   * 
   * @param isCorrect
   * @param Choice
   */
  public Choice(boolean isCorrect, String choice) {
    this.isCorrect = isCorrect; // initialize boolean isCorrect
    this.choice = choice; // initialize String Choice
  }

}
