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
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;

/**
 * This is the main class that runs and display all the GUI
 * 
 */
public class Main extends Application {
  Scene scene1, scene2, scene3, scene4, scene5, sceneP, sceneE; // the different screens
  // the hashMap that stores all the questions
  protected QuestionDataBase questionDB = new QuestionDataBase();
  // question list that contains the question that will be used in one quiz
  protected List<Question> curQuestions = new ArrayList<Question>();
  // protected Question currQuestion;
  protected int currQuestionNum;
  protected int totalNumQuestion;
  protected int numIncorrect;
  protected TextField textFieldOfnumOfQuiz;
  protected ObservableList<String> selectableTopicList;
  protected AddQuestionFormNode form;
  protected List<Choice> choices = new ArrayList<Choice>();
  protected List<TextField> texts;
  protected int numCorrect;

  /**
   * This is the drive method that set the stage of the GUI
   */
  @Override
  public void start(Stage primaryStage) {
    numCorrect = 0;
    try {
      // setup
      setup(primaryStage);
      primaryStage.show();
      primaryStage.setTitle("Quiz Generator");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This methods can set and display all primary screen
   */
  private void setup(Stage primaryStage) {
    // scene1 main screen
    // create labels
    currQuestionNum = 0;
    List<Question> curQuestions = new ArrayList<Question>();
    Label empty1 = new Label("                                        ");
    Label empty2 = new Label("                 ");
    Label empty3 = new Label(" ");
    Label empty4 = new Label(" ");
    Label emptyLabel1 = new Label("\n" + "\n" + "\n" + "\n");
    Label emptyLabel2 = new Label("\n" + "\n");
    Label numOfEachQuiz = new Label("Number of questions in this quiz: ");
    Label totalQuestionLabel =
        new Label("Total number of questions in database " + +questionDB.questionNum);
    Label userTopic = new Label("Choose your topics(the chosen topic will be shown on the right)");

    // create a text field for numOfEachQuiz;
    textFieldOfnumOfQuiz = new TextField();

    // create two bottoms "Add Question", "Load Data".
    Button bottomButton1 = new Button("Add Question");
    bottomButton1.setOnAction(e -> displayAddQuestionForm(primaryStage));
    Button bottomButton3 = new Button("Load Data");
    bottomButton3.setOnAction(e -> load(questionDB, primaryStage));


    // create a topic ObservableList
    ObservableList<String> topics = questionDB.getTopics();


    ListView<String> list = new ListView<>(topics);
    selectableTopicList = FXCollections.observableArrayList();
    ListView<String> selected = new ListView<>(selectableTopicList);


    // create a horizontal box
    HBox hBox1 = new HBox(list, selected);
    list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    list.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
      selectableTopicList
      .addAll(FXCollections.observableArrayList(list.getSelectionModel().getSelectedItems()));
    });


    // create one more horizontal box
    HBox hBox2 = new HBox();
    hBox2.getChildren().addAll(numOfEachQuiz, textFieldOfnumOfQuiz);



    // create one more horizontal box
    HBox hBox3 = new HBox();
    hBox3.getChildren().addAll(empty1, bottomButton1, empty2, bottomButton3);

    // create a vertical box
    VBox vBox = new VBox();
    Button bottomButton4 = new Button("Save questions");
    vBox.getChildren().addAll(hBox1, emptyLabel2, hBox2, emptyLabel1, hBox3, bottomButton4);

    // construct a BoarderPane called root.
    BorderPane layout1 = new BorderPane();

    // setLeft
    layout1.setLeft(vBox);


    // setBottom
    VBox bottomBox = new VBox();
    bottomBox.getChildren().addAll(empty3, empty4);
    Button bottomButton2 = new Button("Start quiz");
    bottomButton2.setOnAction(e -> displayQuiz(primaryStage));
    bottomBox.getChildren().add(bottomButton2);
    layout1.setBottom(bottomBox);

    // setTop
    VBox topV = new VBox();
    topV.getChildren().addAll(totalQuestionLabel, userTopic);
    layout1.setTop(topV);
    scene1 = new Scene(layout1, 500, 500);
    scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


    primaryStage.setScene(scene1);
  }

  /**
   * This is the helper method for loading the questions
   */
  private void load(QuestionDataBase questionDB, Stage primaryStage) {
    final FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(primaryStage);
    if (file != null) {
      questionDB.loadQuestionsFromJSON(file);
      setup(primaryStage);
    }

  }

  /**
   * This methods can set and display the form that used to collect quiz question from the user
   */
  private void displayAddQuestionForm(Stage primaryStage) {

    // scene 2
    BorderPane layout2 = new BorderPane();

    // setTop
    Label label2 = new Label("Add a question!");
    layout2.setTop(label2);

    // setCenter
    form = new AddQuestionFormNode();

    texts = form.getChoiceTexts();
    form.getChoiceGroups();


    TextField t1 = form.getMetaData();
    TextField t2 = form.getQuestion();
    TextField t3 = form.getTopic();
    TextField t4 = form.getImage();

    layout2.setCenter(form.getNode());


    // setBottom
    VBox vv = new VBox();
    Button button2 = new Button("Back to main window");
    button2.setOnAction(e -> setup(primaryStage));
    Button button7 = new Button("Add this question");
    button7.setOnAction(e -> noAdd(t1, t2, t3, t4, primaryStage, choices));



    vv.getChildren().addAll(button7, button2);
    layout2.setBottom(vv);
    scene2 = new Scene(layout2, 600, 600);
    primaryStage.setScene(scene2);

  }

  /**
   * This methods can set and display all primary screen
   */
  private void noAdd(TextField t1, TextField t2, TextField t3, TextField t4, Stage stage,
      List<Choice> choices) {
    if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty()) {
      Button buttonP = new Button("Click here to return to add question");
      buttonP.setOnAction(e -> stage.setScene(scene2));
      Label labelP =
          new Label("Any input of MetaData," + "\n" + "Question or Topic should not be empty!");
      FlowPane fp = new FlowPane();
      fp.getChildren().addAll(labelP, buttonP);
      fp.setPadding(new Insets(20));
      fp.setVgap(50);
      sceneP = new Scene(fp, 300, 200);
      stage.setScene(sceneP);
    } else {
      Question newq =
          new Question(t1.getText(), t2.getText(), t3.getText(), t4.getText(), addChoicesHelper());
      questionDB.addQuestion(newq.topic, newq);
      setup(stage);

    }
  }

  /**
   * This is the helper method to add choices to the questions
   */
  private List<Choice> addChoicesHelper() {
    List<Choice> cs = new ArrayList<Choice>();

    for (int i = 0; i < 4; i++) {
      if (form.choiceGroups.get(0).getToggles().get(i).isSelected()) {
        Choice choice = new Choice(true, texts.get(i).getText());
        cs.add(choice);
      } else {
        Choice choice = new Choice(false, texts.get(i).getText());
        cs.add(choice);
      }
    }

    return cs;
  }

  /**
   * This methods can set and display the quiz screen
   */
  private void displayQuiz(Stage primaryStage) {
    // scene3
    BorderPane layout3 = new BorderPane();
    // setTop
    Label QuizLabel = new Label("Quiz Time!");
    layout3.setTop(QuizLabel);


    String t = textFieldOfnumOfQuiz.getText();
    if (!textFieldOfnumOfQuiz.getText().isEmpty())
      try {
        totalNumQuestion = Integer.parseInt(textFieldOfnumOfQuiz.getText());
      } catch (Exception e) {
        errorMsg(primaryStage);
        return;
      }


    List<Question> quesForQuiz = new ArrayList<Question>();// all questions in selected topics

    for (int i = 0; i < selectableTopicList.size(); i++) {// loop through the topic list
      quesForQuiz.addAll(questionDB.getQuestions(selectableTopicList.get(i)));// add each question
      // list by topic into
      // one list
    }

    Random chooseQues = new Random();


    if (totalNumQuestion == 0) {
      Label message = new Label("Invalid number of questions");
      layout3.setCenter(message);
    } else {// valid number of questions
      if (quesForQuiz.size() < totalNumQuestion)
        totalNumQuestion = quesForQuiz.size();
      int q = totalNumQuestion;
      List<Question> ls = new ArrayList<Question>();
      for (Question a : quesForQuiz) {
        ls.add(a);
      }
      while (q > 0) {
        int k = chooseQues.nextInt(ls.size());
        Question qs = ls.get(k);
        q--;
        ls.remove(k);
        curQuestions.add(qs);
      }
      VBox b = new VBox();
      HBox c = new HBox();

      QuestionNode node1 = new QuestionNode(curQuestions.get(this.currQuestionNum++));
      b.getChildren().add(node1.getNode());
      // }
      try {
        ImageView myPic =
            new ImageView(new Image(curQuestions.get(this.currQuestionNum - 1).picture));
        myPic.setFitHeight(400);
        myPic.setFitWidth(400);
        myPic.setPreserveRatio(true);
        c.getChildren().add(myPic);
      } catch (Exception e) {
        Label lb = new Label("this question do not have a image");
        b.getChildren().add(lb);
      }
      //



      if (this.currQuestionNum < totalNumQuestion) {
        Button nextButton = new Button("next question");
        nextButton.setOnAction(e -> {
          checkAnswerHelper(node1);
          displayQuiz(primaryStage);
        });
        b.getChildren().add(nextButton);
        // setBottom
        VBox quizBottom = new VBox();
        Button quizButton1 = new Button("Back to main window");
        quizButton1.setOnAction(e -> setup(primaryStage));
        quizBottom.getChildren().add(quizButton1);
        layout3.setBottom(quizBottom); // set layout

      } else {
        Label tEnd = new Label("Reach the end of the quiz");
        b.getChildren().add(tEnd);

        // setBottom
        VBox quizBottom = new VBox();
        Button quizButton1 = new Button("Back to main window");
        quizButton1.setOnAction(e -> setup(primaryStage));
        Button quizButton2 = new Button("Submit");
        quizButton2.setOnAction(e -> {
          checkAnswerHelper(node1);
          displayResult(primaryStage);
        });
        quizBottom.getChildren().addAll(quizButton2, quizButton1);
        layout3.setBottom(quizBottom); // set layout
      }
      layout3.setLeft(b); // set layout
      layout3.setRight(c); // set layout

    }

    scene3 = new Scene(layout3, 800, 500);
    primaryStage.setScene(scene3);
  }

  private void checkAnswerHelper(QuestionNode node) {
    int i = (int) node.choices.getSelectedToggle().getUserData();
    System.out.println(i);
    if (node.question.choices.get(i).isCorrect) {

      numCorrect++;
      System.out.println(numCorrect);
    }
  }

  /**
   * This methods generates a pop out window to warn the user for their invalid input
   */
  private void errorMsg(Stage stage) {
    Button buttonE = new Button("Back to main window");
    buttonE.setOnAction(e -> stage.setScene(scene1));
    Label labelE =
        new Label("The input for number of questions " + "\n" + "in this quiz must be NUMBER!");
    FlowPane fe = new FlowPane();
    fe.getChildren().addAll(labelE, buttonE);
    fe.setPadding(new Insets(20));
    fe.setVgap(50);
    sceneE = new Scene(fe, 300, 200);
    stage.setScene(sceneE);
  }


  /**
   * This methods can calculate and display the result of the quiz
   */
  private void displayResult(Stage primaryStage) {

    System.out.println(numCorrect);
    // scene4
    BorderPane layout4 = new BorderPane();
    // for()

    // setTop
    Label result = new Label("Your Result is:");

    layout4.setTop(result);
    // // setCenter
    VBox center3 = new VBox();
    Label result1 = new Label("All question number: " + totalNumQuestion);

    Label result2 = new Label("percent correct: " + numCorrect + "/" + totalNumQuestion);
    center3.getChildren().addAll(result1, result2);
    layout4.setCenter(center3); // set layout
    // setBottom
    Button button100 = new Button("Back to main window");
    button100.setOnAction(e -> setup(primaryStage));
    layout4.setBottom(button100); // set layout
    scene4 = new Scene(layout4, 800, 800);
    primaryStage.setScene(scene4);
  }

  public static void main(String[] args) {
    launch(args);

  }
}
