# Project Overview
For this project, you will be implementing a small program to analyze the performance of your hash table against Java's built-in TreeMap.  TreeMap (Links to an external site.) is a known and in-built data structure of Java.

To analyze the performance, you need to write a small program that performs the same operations on both your custom HashTable class and Java's TreeMap class. You will be using Java Flight recorder to create a program profile, and Java Mission Control to analyze the profile information.  Both are open source and packaged with Oracle JVM’s (The lab machines have this installed).

For this assignment, you are required to:

Write MyProfiler.java, a program that will be profiled to compare the your HashTable and Java's TreeMap.
Use make and Makefiles (use our Makefile - no need to define your own)
Run your program using Oracle Java Flight Recorder to profile your MyProfiler program. 
Use Oracle Java Mission Control (jmc) to analyze the generated my_profile.jfr data.
Answer the questions in conclusions.txtLinks to an external site. file.
Take screenshots of the relevant parts of your profile data as viewed from Java Mission Control
Submit your files to p3b Performance Analysis
All files must be named correctly (case-sensitive). You may define and submit other package level (not public or private) classes as needed and you may add private members to your classes.

The goal for your HashTable was to build a searchable data structure that achieves constant time O(1) for lookup, insert, and delete operations with comparable performance to Java's built-in TreeMap type.  This assignment attempts to determine if your hash table achieved that goal.

Ensure that your hash table implementation works correctly prior to analyzing its performance against Java's TreeMap class. 
