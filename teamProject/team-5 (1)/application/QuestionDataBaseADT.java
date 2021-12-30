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

import java.io.File;
import java.util.List;
import javafx.collections.ObservableList;

/**
 * Filename: QuestionDataBaseADT.java Course: cs400 Authors: ateam75
 * 
 */
public interface QuestionDataBaseADT {

  // Add the key,value pair to the data structure and increase the number of keys.
  public void addQuestion(String s, Question q);


  // Save the current questions in the data base to the a json file
  public void saveQuestionToJSON(File file);

  // Retrieve questions using the topic as the key to search
  // return a list of questions
  public List<Question> getQuestions(String s);


  // load questions from a json file and add those questions to the current data base
  public void loadQuestionsFromJSON(File file);

  // traverse the data base to get all the topics
  public ObservableList<String> getTopics();
}
