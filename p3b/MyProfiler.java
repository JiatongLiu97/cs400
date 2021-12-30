////////////////////////////////////////////////////////////////////////////////
// Main File:        HashTable.java
// This File:        MyProfiler.java
// Other Files:      MyProfiler.java
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


import java.util.ArrayList;
import java.util.TreeMap;

public class MyProfiler {
	public static void main(String args[]) {
        try {
            long numElements = Long.parseLong(args[0]);
            MyProfiler.testHashTable(numElements);
            MyProfiler.testTreeMap(numElements);            
            System.out.println(String.format("inserted %d elements to hashtable and treemap", numElements));
        } catch (Exception e) {
            System.out.println("Usage: java MyProfiler <num_elements_as_positive_integer>"); 
            System.exit(1); 
        }
    }
	
	
	public static void testHashTable(long numElements) throws IllegalNullKeyException, DuplicateKeyException {
		HashTableADT htIntegerKey = new HashTable<Integer,String>();   
        for(int i=0;i<numElements;i++) {
        	htIntegerKey.insert(i, String.valueOf(i));
        }
	}
	
	
	public static void testTreeMap(long numElements) {
		TreeMap<Integer,String> tmp=new TreeMap<Integer,String>(); 
        for(int i=0;i<numElements;i++) {
        	tmp.put(i, String.valueOf(i));
        }
	}
	
	
}
