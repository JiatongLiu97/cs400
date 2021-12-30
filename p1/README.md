# Project Overview
Overview:
For this assignment, you are given an ADT and several implementations of that ADT in the form of class files.  You are also given an abstract JUnit super class with a few example JUnit tests.  You are required to implement the ADT and write JUnit tests to ensure that your implementation and the other implementations work correctly.  

For each data structure implementation class given to you that you wish to test, you must write a test class. In this case, we will be giving you many implementations to test so that means many test classes are required.  However, the tests in each test class will be the same.  This means that we can use inheritance to reduce the amount of code that needs to be written and still ensure that all implementations are tested with all tests.

This is an Inheritance Diagram showing that the test methods are defined in the super class DataStructureADTTest, and that each sub-class must only define the createInstance() method. 

Note: The arrow is from the sub-class to the super-class because it is the sub-class that knows it's super-class.  

