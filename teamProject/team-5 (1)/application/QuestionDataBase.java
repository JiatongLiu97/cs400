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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
// Java program to read JSON from a file
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


/**
 * Contains all questions (of type Question) from .json files and added manually by user.
 */
public class QuestionDataBase implements QuestionDataBaseADT {
  protected Map<String, List<Question>> topics;
  protected int questionNum;

  public QuestionDataBase() {
    topics = new HashMap<String, List<Question>>();
    questionNum = 0;
  }

  /**
   * add Question in the question bank
   * 
   * @param topic
   * @param question
   */
  @Override
  public void addQuestion(String topic, Question question) {
    if (topics.containsKey(topic)) {
      topics.get(topic).add(question);
    } else {
      List<Question> newList = new ArrayList();
      newList.add(question);
      topics.put(topic, newList);
    }


    questionNum++;

  }

  /**
   * save questions to .json file
   * 
   * @param file
   */
  @Override
  public void saveQuestionToJSON(File file) {
    // creating JSONObject
    JSONObject jo = new JSONObject();
    // for questionArray, first create JSONArray
    JSONArray ja = new JSONArray();
    for (Map.Entry<String, List<Question>> entry : topics.entrySet()) {
      List<Question> quesList = entry.getValue();
      for (int i = 0; i < quesList.size(); i++) {
        Question aquestion = quesList.get(i);
        Map m = new LinkedHashMap(5);
        m.put("meta-data", aquestion.metaData);
        m.put("questionText", aquestion.questionText);
        m.put("topic", aquestion.topic);
        m.put("image", aquestion.picture);
        JSONArray ja1 = new JSONArray();
        for (int j = 0; j < aquestion.choices.size(); j++) {
          Map m1 = new LinkedHashMap(2);
          String Choice = aquestion.choices.get(j).choice;
          String isRight = null;
          boolean isCorrect = aquestion.choices.get(j).isCorrect;
          if (isCorrect)
            isRight = "T";
          else
            isRight = "F";
          m1.put("isCorrect", isRight);
          m1.put("choice", Choice);

          // adding map to list
          ja1.add(m1);

        }
        m.put("choiceArray", ja1);
        ja.add(m);
      }

    }

    // putting questionArray to JSONObject
    jo.put("questionArray", ja);


    // writing JSON to file:"JSONExample.json" in cwd
    String filename = file.getName();
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(filename);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    pw.write(jo.toJSONString());

    pw.flush();
    pw.close();



  }

  /**
   * get Questions from specific topic
   * 
   * @param topic
   */
  @Override
  public List<Question> getQuestions(String topic) {
    return topics.get(topic);
  }

  /**
   * load Questions from .json file
   * 
   * @param file
   */
  @Override
  public void loadQuestionsFromJSON(File JSONExample) {
    String jsonName = JSONExample.getName();
    // parsing file "JSONExample"
    Object obj = null;


    try {

      obj = new JSONParser().parse(new FileReader(jsonName));


    } catch (IOException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // typecasting obj to JSONObject
    JSONObject jo = (JSONObject) obj;
    // get "questionArray"
    JSONArray ja = (JSONArray) jo.get("questionArray");
    // iterating Maps
    Iterator<Map.Entry> itr1 = null;
    // iterating questionArray
    Iterator itr2 = ja.iterator();



    while (itr2.hasNext()) {
      String metaData = null;
      String questionText = null;
      String topic = null;
      String picture = null;
      List<Choice> choices = new ArrayList();
      String answer = null;
      itr1 = ((Map) itr2.next()).entrySet().iterator();
      while (itr1.hasNext()) {

        Map.Entry pair = itr1.next();

        if (pair.getKey().equals("meta-data")) {
          metaData = (String) pair.getValue();
        }
        if (pair.getKey().equals("questionText")) {
          questionText = (String) pair.getValue();
        }
        if (pair.getKey().equals("topic")) {
          topic = (String) pair.getValue();
        }
        if (pair.getKey().equals("image")) {
          picture = (String) pair.getValue();
        }
        if (pair.getKey().equals("choiceArray")) {
          // in iterator in a map
          Iterator<Map.Entry> itr3 = null;
          // getting choiceArray
          JSONArray ja1 = (JSONArray) pair.getValue();
          // iterating the array of maps
          Iterator itr4 = ja1.iterator();
          while (itr4.hasNext()) {
            itr3 = ((Map) itr4.next()).entrySet().iterator();
            boolean isCorrect = true;
            String choice = null;

            while (itr3.hasNext()) {

              Map.Entry pair1 = itr3.next();

              if (pair1.getKey().equals("isCorrect")) {
                if (pair1.getValue().equals("T"))
                  isCorrect = true;
                else
                  isCorrect = false;
              }
              if (pair1.getKey().equals("choice")) {
                choice = (String) pair1.getValue();
              }
            }

            Choice eachChoice = new Choice(isCorrect, choice);

            choices.add(eachChoice);
          }
        }
      }
      Question eachQuestion = new Question(metaData, questionText, topic, picture, choices);
      addQuestion(topic, eachQuestion);



    }



  }

  /**
   * get all topics from the Question bank
   */
  @Override
  public ObservableList<String> getTopics() {
    ObservableList<String> list = FXCollections.observableArrayList();
    for (Map.Entry<String, List<Question>> entry : topics.entrySet())
      list.add(entry.getKey());
    return list;
  }


}
