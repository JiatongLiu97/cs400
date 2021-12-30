/**
 * Filename: GraphTest.java 
 * Project: p4 
 * Authors: Ivan Wu, Jiatong Liu
 * 
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class consists of test methods to test the functionality of the implementation of the graph
 *
 */
class GraphTest {
    Graph testGraph;
    int testInserSize = 100;

    @BeforeEach
    void setUp() {
        testGraph = new Graph();
    }

    // also test getIndexByString and getNodebyString
    @Test
    void addVertex() {

        for (int i = 0; i < testInserSize; i++) {
            String stringToInsert = "" + "test" + i;
            testGraph.addVertex(stringToInsert);
        }
        if(testGraph.order() != 100) {
          fail("insertion failed");
        }
    }

    @Test
    @SuppressWarnings("Duplicates")
    void removeVertex() {
      // insert nodes and edge
      String st1 = "1";
      String st2 = "2";
      String st3 = "3";
      String st4 = "4";
      testGraph.addVertex(st1);
      testGraph.addVertex(st2);
      testGraph.addVertex(st3);
      testGraph.addVertex(st4);
      testGraph.removeVertex(st2);
      if(testGraph.order() != 3) {
        fail("remove failed");
      }
    }




    @Test
    @SuppressWarnings("Duplicates")
    void addEdge() {
            String insertString1 = "test" ;
            String insertString2 = "test1" ;
            testGraph.addVertex(insertString1);
            testGraph.addVertex(insertString2);
            testGraph.addEdge(insertString1, insertString2);

            // test if node1's list have the index of node2
            if(!testGraph.getAdjacentVerticesOf(insertString1).contains(insertString2)) {
              fail("edge insertion failed");
            }
        
    }

    @Test
    @SuppressWarnings("Duplicates")
    void removeEdge() {
            String insertString1 = "testa";
            String insertString2 = "testb";
            String insertString3 = "testc";
            testGraph.addVertex(insertString1);
            testGraph.addVertex(insertString2);
            testGraph.addVertex(insertString3);
            testGraph.addEdge(insertString1, insertString2);
            testGraph.addEdge(insertString1, insertString3);
            testGraph.removeEdge(insertString1, insertString3);
             if(!testGraph.getAdjacentVerticesOf(insertString1).contains(insertString2)
                 || testGraph.getAdjacentVerticesOf(insertString1).contains(insertString3)) {
               fail("deletion fail!");
             }

}
}  
