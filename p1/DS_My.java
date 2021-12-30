////////////////////////////////////////////////////////////////////////////////
// Main File:        p1 Implement and Test DataStructureADT
// This File:        DS_My.java
// Other Files:      DataStructureADTTest.java
// Semester:         CS 400 Spring 2019
//
// Author:           Jiatong Liu
// Email:            jliu794@wisc.edu
// CS Login:         jiatongl
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide ///////////////////////////////////


/**
 * The class represents an implementation of the ADT
 * 
 * @author Jiatong Liu
 */
public class DS_My implements DataStructureADT {

  // TODO may wish to define an inner class
  // for storing key and value as a pair
  // such a class and its members should be "private"
  /**
   * The class represents an pair in such list
   * 
   * @author Jiatong Liu
   */
  private class Pair<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private Pair nextPair; // last pair
    /**
     * The constructor of each pair
     * 
     * @param K key: the key in the pair
     *        V value: the value associated with the key
     */
    private Pair(K key, V value) {
      this.key = key;
      this.value = value;
      this.nextPair = null; // newly constructed pair has no next pair
    }

  }

  // Private Fields of the class
  // TODO create field(s) here to store data pairs
  private Pair firstPair; // first pair in the list
  private Pair lastPair; // last pair in the list
  private int size;    // number of pairs in the list
  private Pair curPair; // the current pair


  /**
   * The constructor of the ADT(A list)
   * 
   */
  public DS_My() {
    // TODO Auto-generated method stub
    size = 0;
    firstPair = null; // means it is a empty list
  }
  
  /**
   * Add the key,value pair to the data structure and increases size.
   * If key is null, throws IllegalArgumentException("null key");
   * If key is already in data structure, throws RuntimeException("duplicate key");
   * can accept and insert null values
   * @param Comparable k:the key of the added pair
   *        Object v:the value of the added pair
   */
  @Override
  public void insert(Comparable k, Object v) throws IllegalArgumentException {
    // TODO Auto-generated method stub
    // If key is null, throws IllegalArgumentException
    if (k == null)
      throw new IllegalArgumentException("null key");


    // search through the whole list to see if there is a pair with the duplicate key
    curPair = firstPair; // before search the list, set current pair to the first pair
    while (curPair != null) {
      if (curPair.key.equals(k))
        throw new RuntimeException("duplicate key");
      else
        curPair = curPair.nextPair;
    }

    Pair newPair = new Pair(k, v);
    // when the list is empty, add this pair as first pair
    if (firstPair == null) {
      firstPair = newPair;
      lastPair = newPair;
    } else {
      lastPair.nextPair = newPair;
      lastPair = newPair;
    }
    size++;
  }

  /**
   * If key is found, Removes the key from the data structure and decreases size
   * If key is null, throws IllegalArgumentException("null key") without decreasing size
   * If key is not found, returns false.
   * 
   * @param Comparable k:the key of the pair to be romoved
   * @return : true if the pair is removed, false otherwise
   */
  @Override
  public boolean remove(Comparable k) throws IllegalArgumentException {
    if (k == null)
      throw new IllegalArgumentException("null key");
    // set current pair to be the first pair
    curPair = firstPair;
    // check if the first pair has the specified key
    if (curPair.key.equals(k)) {
      firstPair = curPair.nextPair;
      size--;
      return true;
    }
    // check if the pair with the specified key exists in the middle part of the list
    while (curPair.nextPair != null) {
      if (curPair.nextPair.key.equals(k)) {
        curPair.nextPair = curPair.nextPair.nextPair;
        size--;
        return true;
      }
      // if the last pair has the specified key
      else if (curPair.nextPair.nextPair == null) {
        if (curPair.nextPair.key.equals(k)) {
          lastPair = curPair;
          lastPair.nextPair = null;
          size--;
          return true;
        } else
          curPair = curPair.nextPair;
      } else
        curPair = curPair.nextPair;

    }
    return false;

  }



  /**
   * Returns true if the key is in the data structure
   * Returns false if key is null or not present
   * 
   * @param Comparable k:the key of the pair to search
   * @return : true if the key is in the data structure, false otherwise
   */
  @Override
  public boolean contains(Comparable k) {
    if (k == null)
      return false;
    curPair = firstPair; // set current pair to be the first pair
    // traverse through the list one pair by one pair
    while (curPair != null) {
      if (curPair.key.equals(k)) // if the key is found in the list
          return true;
      curPair = curPair.nextPair;
    }
    return false;
  }

  /**
   * Returns the value associated with the specified key
   * If key is null, throws IllegalArgumentException("null key") without decreasing size
   * 
   * @param Comparable k:the key of the pair to search
   * @return Comparable k: the value associated with that key
   */
  @Override
  public Object get(Comparable k) throws IllegalArgumentException {
    if (k == null)
      throw new IllegalArgumentException("null key");
    curPair = firstPair; // set current pair to be the first pair
    while (curPair != null) {
      if (curPair.key.equals(k))
          return curPair.value;
      curPair = curPair.nextPair;
    }
    return null;
  }

  /**
   * Returns the number of pair in the data structure
   * 
   * 
   * @return int: thenumber of pair in the data structure
   */
  @Override
  public int size() {
    // TODO Auto-generated method stub
    return size;
  }




  

}
