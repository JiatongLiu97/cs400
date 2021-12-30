////////////////////////////////////////////////////////////////////////////////
// Main File: p1 Implement and Test DataStructureADT
// This File: DS_My.java
// Other Files: DataStructureADTTest.java
// Semester: CS 400 Spring 2019
//
// Author: Jiatong Liu
// Email: jliu794@wisc.edu
// CS Login: jiatongl
//
/////////////////////////// OTHER SOURCES OF HELP //////////////////////////////
// fully acknowledge and credit all sources of help,
// other than Instructors and TAs.
//
// Persons: Identify persons by name, relationship to you, and email.
// Describe in detail the the ideas and help they provided.
//
// Online sources: avoid web searches to solve your problems, but if you do
// search, be sure to include Web URLs and description of
// of any information you find.
//////////////////////////// 80 columns wide ///////////////////////////////////



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * The class represents a super test class to test the functionality of an ADT
 * 
 * @author Jiatong Liu
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }

  /**
   * Test functionality of the data structure about after create a instance of the ADT, if the size
   * is 0, the test passes otherwise the test fails.
   * 
   */
  @Test
  void test00_empty_ds_size() {
    if (dataStructureInstance.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
  }

  // TODO: implement tests 01 - 04
  /**
   * Test functionality of the data structure about after inserting a key value pair, if the size is
   * 1, the test passes otherwise the test fails.
   * 
   */
  @Test
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("A", "B"); // Add pair ("A","B") to the data structure
    if (dataStructureInstance.size() != 1)
      fail("data structure should with size=1, but size=" + dataStructureInstance.size());
  }

  /**
   * Test functionality of the data structure about inserting a key value pair and remove it if the
   * size is 0, the test passes otherwise the test fails.
   * 
   */
  @Test
  void test02_after_insert_one_remove_one_size_is_0() {
    dataStructureInstance.insert("A", "B"); // Add pair ("A","B") to the data structure
    dataStructureInstance.remove("A"); // Remove this pair with key "A"
    assertEquals(dataStructureInstance.size(), 0); // If size() does not return 0, this test counts
                                                   // as a fail
  }

  /**
   * Test functionality of the data structure about inserting a few key, value pairs such that one
   * of them has the same key as an earlier one. If a RuntimeException is thrown, the test passes
   * otherwise the test fails.
   * 
   */
  @Test
  void test03_duplicate_exception_is_thrown() {
    try {
      // Insert three pairs, and two of them have the same key
      dataStructureInstance.insert("A", "B");
      dataStructureInstance.insert("C", "B");
      dataStructureInstance.insert("A", "D");
      fail(
          "The insert mehod does not make a RuntimeException when inserting two pairs with same key");
    } catch (RuntimeException e) {

    }
  }

  /**
   * Test functionality of the data structure about inserting a few key, value pairs, then remove a
   * key that does not exist. If remove() returns false, the test passes otherwise the test fails.
   * 
   */
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    // Insert three pairs
    dataStructureInstance.insert("A", "B");
    dataStructureInstance.insert("C", "B");
    dataStructureInstance.insert("D", "D");
    if (dataStructureInstance.remove("E") != false)
      fail("remove() does not return false when remove a key not existing");
  }

  /**
   * Test functionality of the data structure about inserting a few key, value pairs, then remove a
   * key which is null, If remove() throws an IllegalArgumentException without decreasing size, the
   * test passes otherwise the test fails.
   * 
   */
  @Test
  void test05_remove_nullkey_throws_illegalArgumentException_without_decreasing_size() {
    // Insert three pairs
    dataStructureInstance.insert("A", "B");
    dataStructureInstance.insert("C", "B");
    dataStructureInstance.insert("D", "D");
    try {
      dataStructureInstance.remove(null);
      fail("The remove() method does not catch an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      if (dataStructureInstance.size() != 3)
        fail("The remove() method catches an IllegalArgumentException but the size is decreased");
    }
  }

  /**
   * Test functionality of the data structure about inserting a few key, value pairs. If get()
   * returns the value associated with the specified key the test passes otherwise the test fails.
   * 
   */
  @Test
  void test06_get_associated_value() {
    // Insert three pairs
    dataStructureInstance.insert("A", "B");
    dataStructureInstance.insert("C", "B");
    dataStructureInstance.insert("D", "D");
    // If the dataStructureInstance.get("A") will return its associated value "B"
    if (!dataStructureInstance.get("A").equals("B"))
      fail("the get() method does not return the associated value with the specified key");
  }

  /**
   * Test functionality of the data structure about inserting a few key, value pairs, then call
   * get() with a key which is null, If get() throws an IllegalArgumentException without decreasing
   * size, the test passes otherwise the test fails.
   * 
   */
  @Test
  void test07_get_nullkey_throws_illegalargumentexception_without_decreasing_size() {
    // Insert three pairs
    dataStructureInstance.insert("A", "B");
    dataStructureInstance.insert("C", "B");
    dataStructureInstance.insert("D", "D");
    try {
      dataStructureInstance.get(null);
      fail("The get() method does not catch an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      if (dataStructureInstance.size() != 3)
        fail("The get() method catches an IllegalArgumentException but the size is decreased");
    }
  }

  /**
   * Test functionality of the data structure about inserting a few key, value pairs, If the
   * contains() returns true if the key is in the data structure, return false if the key is null or
   * not present, the test passes otherwise the test fails.
   * 
   */
  @Test
  void test08_contains() {
    // Insert three pairs
    dataStructureInstance.insert("A", "B");
    dataStructureInstance.insert("C", "B");
    dataStructureInstance.insert("D", "D");
    assertTrue("The contains() returns true if the key is in the data structure",
        dataStructureInstance.contains("A"));
    assertFalse("The contains() returns false if the key is null",
        dataStructureInstance.contains(null));
    assertFalse("The contains() returns false if the key is not in the data structure",
        dataStructureInstance.contains("G"));
  }



  // TODO: add tests to ensure that you can detect implementation that fail

  // Tip: consider different numbers of inserts and removes and how different combinations of insert
  // and removes


}
