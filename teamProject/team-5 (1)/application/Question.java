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

import java.util.List;
import javafx.scene.control.Label;

/**
 * Represents a single question, with members for metadata, question, topic, image, choices (a list
 * of type Choice), and answer
 * 
 */
public class Question {
  protected String metaData; // the field that checks whether the question is used
  protected String questionText; // the text field that represents the question text
  protected String topic; // the topic of this topic
  protected String picture;// the picture that this question used
  protected List<Choice> choices; // the choice list that contains choices


  /**
   * This is the constructor of the question class that initialize the above fields
   */
  public Question(String metaData, String questionText, String topic, String picture,
      List<Choice> choices) {
    this.metaData = metaData;
    this.questionText = questionText;
    this.topic = topic;
    this.picture = picture;
    this.choices = choices;
  }



}
