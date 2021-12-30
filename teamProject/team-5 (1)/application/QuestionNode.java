//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: QuizEnerator
// Files: Main.java
// Course: cs400, spring, 2019
// Known bugs: None
// Author: ateam75
// Lecturer's Name: Andrew Kuemmel
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: none
// Online Sources: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * This class represents a JavaFX elements for displaying a single Question to the user.
 * 
 */
public class QuestionNode {
  protected VBox node;
  protected ToggleGroup choices; // makes user to choose only one toggle representing the choice
  protected Question question;

  /**
   * This is the constructor of the QuestionNode class that initialize the above fields
   */
  public QuestionNode(Question question) {
    this.question = question;
    node = new VBox();
    Label text = new Label(question.questionText);
    node.getChildren().add(text);

    choices = new ToggleGroup();
    RadioButton a = new RadioButton("A: " + question.choices.get(0).choice);
    a.setUserData(0);
    choices.getToggles().add(a);
    node.getChildren().add(a);
    System.out.println(question.choices.get(0).choice);


    RadioButton b = new RadioButton("B: " + question.choices.get(1).choice);
    b.setUserData(1);
    choices.getToggles().add(b);
    node.getChildren().add(b);
    System.out.println(question.choices.get(1).choice);

    RadioButton c = new RadioButton("C: " + question.choices.get(2).choice);
    c.setUserData(2);
    choices.getToggles().add(c);
    node.getChildren().add(c);
    System.out.println(question.choices.get(2).choice);

    RadioButton d = new RadioButton("D: " + question.choices.get(3).choice);
    d.setUserData(3);
    choices.getToggles().add(d);
    node.getChildren().add(d);
    System.out.println(question.choices.get(3).choice);



  }

  public VBox getNode() {
    return node;
  }

}
