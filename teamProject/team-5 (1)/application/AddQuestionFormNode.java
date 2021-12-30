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

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * Represents a form that collect quiz questions from the user and display these questions
 * 
 */
public class AddQuestionFormNode {
  protected List<TextField> choiceTexts; // the content of each choice
  protected List<ToggleGroup> choiceGroups; // A,B,C,D
  protected VBox form; // the layout to put into the scene when used

  /**
   * This is the constructor that initialize the non static field of this class
   * 
   */
  public AddQuestionFormNode() {
    form = new VBox();
    choiceTexts = new ArrayList<TextField>();
    choiceGroups = new ArrayList<ToggleGroup>();
  }

  /**
   * This is the constructor that initialize the non static field of this class
   * 
   */
  public TextField getMetaData() {
    Label question = new Label("please enter your metaData:");
    TextField tx1 = new TextField();
    form.getChildren().add(question);
    form.getChildren().add(tx1);
    return tx1;
  }

  /**
   * This is the constructor that initialize the non static field of this class
   * 
   */
  public TextField getQuestion() {
    Label question = new Label("please enter your question:");
    TextField tx2 = new TextField();
    form.getChildren().add(question);
    form.getChildren().add(tx2);
    return tx2;

  }

  /**
   * This is the constructor that initialize the non static field of this class
   * 
   */
  public TextField getTopic() {
    Label quesTopic = new Label("please enter your topic here:");
    TextField tx1 = new TextField();
    form.getChildren().add(quesTopic);
    form.getChildren().add(tx1);
    return tx1;
  }


  /**
   * This is the constructor that initialize the non static field of this class
   * 
   */
  public TextField getImage() {
    Label quesTopic = new Label("please enter your picture here:");
    TextField tx3 = new TextField();
    form.getChildren().add(quesTopic);
    form.getChildren().add(tx3);
    return tx3;
  }

  /**
   * This method gets the choice Text from the user to display the question
   * 
   */
  public List<TextField> getChoiceTexts() {
    // prompt the user for the choice field of the question
    Label anwser1 = new Label("Enter Choice A:");
    TextField tx4 = new TextField(); // display textfield for user to write
    Label anwser2 = new Label("Enter Choice B:");
    TextField tx5 = new TextField();
    Label anwser3 = new Label("Enter Choice C:");
    TextField tx6 = new TextField();
    Label anwser4 = new Label("Enter Choice D:");
    TextField tx7 = new TextField();

    // adding the information retrieved from the user to the javafx
    form.getChildren().add(anwser1);
    form.getChildren().add(tx4);
    choiceTexts.add(tx4);

    form.getChildren().add(anwser2);
    form.getChildren().add(tx5);
    choiceTexts.add(tx5);

    form.getChildren().add(anwser3);
    form.getChildren().add(tx6);
    choiceTexts.add(tx6);

    form.getChildren().add(anwser4);
    form.getChildren().add(tx7);
    choiceTexts.add(tx7);
    return choiceTexts;
  }

  /**
   * returning the layout to the scene
   * 
   */
  public VBox getNode() {
    return form;
  }

  /**
   * This method gets the correct choice from the user to display the question
   * 
   */
  public List<ToggleGroup> getChoiceGroups() {
    Label promptUser = new Label("Enter correct answer");
    form.getChildren().add(promptUser);

    ToggleGroup group = new ToggleGroup();

    // prompt the user for the correct choice for this question
    RadioButton a = new RadioButton("Choice A");
    group.getToggles().add(a);
    form.getChildren().add(a); // adding all the element to the layout

    RadioButton b = new RadioButton("Choice B");
    group.getToggles().add(b);
    form.getChildren().add(b);

    RadioButton c = new RadioButton("Choice C");
    group.getToggles().add(c);
    form.getChildren().add(c);

    RadioButton d = new RadioButton("Choice D");
    group.getToggles().add(d);
    form.getChildren().add(d);

    choiceGroups.add(group);

    return choiceGroups;
  }

}
