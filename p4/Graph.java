import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Filename: Graph.java 
 * Project: p4 
 * Authors: Ivan Wu, Jiatong Liu
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {

  private ArrayList<String>[] adjacencyList;
  private int numVertices;
  private int numEdges;

  /*
   * Default no-argument constructor
   */
  public Graph() {
    adjacencyList = (ArrayList<String>[]) new ArrayList<?>[1000];
    for (int i = 0; i < 1000; i++) {
      adjacencyList[i] = new ArrayList<String>(); // initializing the ArrayLists
    }
  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void addVertex(String vertex) {
    if (vertex != null) { // check if the vertex is null
      if (!this.contains(vertex)) {
        adjacencyList[numVertices].add(vertex);
        numVertices++;
      }
    }
  }


  /**
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex, edges, or throwing
   * an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void removeVertex(String vertex) {
    ArrayList<String>[] temp = adjacencyList.clone();
    int j = 0;
    if (vertex != null) { // check if the vertex is null
      for (int i = 0; i < numVertices; i++) {
        if (adjacencyList[i].get(0).equals(vertex)) {
          adjacencyList[i] = new ArrayList<String>();
          j = i;
        }
      }
      while (j < numVertices) {
        adjacencyList[j] = adjacencyList[j + 1];
        j++;
      }
      numVertices--;
    }
  }

  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted) If either
   * vertex does not exist, add vertex, and add edge, no exception is thrown. If the edge exists in
   * the graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge is not in the graph
   */
  public void addEdge(String vertex1, String vertex2) {
    // if the vertices are null, do nothing
    if (vertex1 != null && vertex2 != null) {
      // if either vertex does not exist, add vertex to the graph
      if (!this.contains(vertex1)) {
        this.addVertex(vertex1);
      }
      if (!this.contains(vertex2)) {
        this.addVertex(vertex2);
      }
      for (int i = 0; i < numVertices; i++) {
        if (adjacencyList[i].get(0).equals(vertex1)) {
          if (!adjacencyList[i].contains(vertex2)) { // if the edge already exist, do nothing
            adjacencyList[i].add(vertex2);
            numEdges++;
          }
        }
      }
    }
  }

  /**
   * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed and unweighted) If
   * either vertex does not exist, or if an edge from vertex1 to vertex2 does not exist, no edge is
   * removed and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge from vertex1 to vertex2 is in the graph
   */
  public void removeEdge(String vertex1, String vertex2) {
    // if the vertices are null or the edge does not exist, do nothing
    if (this.contains(vertex1) && vertex1 != null && vertex2 != null && this.contains(vertex2)) {
      for (int i = 0; i < numVertices; i++) {
        if (adjacencyList[i].get(0).equals(vertex1)) {
          if (adjacencyList[i].contains(vertex2)) {
            adjacencyList[i].remove(vertex2);
            numEdges--;
          }
        }
      }
    }
  }

  /**
   * Returns a Set that contains all the vertices
   * 
   */
  public Set<String> getAllVertices() {
    Set<String> all = (Set<String>) new HashSet<String>();
    // traversing the graph
    for (int i = 0; i < numVertices; i++) {
      all.add(adjacencyList[i].get(0)); // adding all the vertices to the list
    }
    return all;
  }


  /**
   * Get all the neighbor (adjacent) vertices of a vertex
   *
   */
  public List<String> getAdjacentVerticesOf(String vertex) {
    List<String> temp = new ArrayList<String>();
    // traversing the graph
    for (int i = 0; i < numVertices; i++) {
      if (adjacencyList[i].get(0).equals(vertex)) {
        for (int j = 1; j < adjacencyList[i].size(); j++) {
          temp.add(adjacencyList[i].get(j));
        }
      }
    }
    return temp;
  }

  /**
   * Returns the number of edges in this graph.
   */
  public int size() {
    return this.numEdges;
  }

  /**
   * Returns the number of vertices in this graph.
   */
  public int order() {
    return this.numVertices;
  }

  /**
   * Helper method to find if the vertex is in the graph
   */
  protected boolean contains(String vertex) {
    for (int i = 0; i < numVertices; i++) {
      if (adjacencyList[i].get(0).equals(vertex)) {
        return true;
      }
    }
    return false;
  }
}
