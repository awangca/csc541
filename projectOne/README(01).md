/* ********************************************************************************** *
* CSC 541 Advanced Operating Systems *
* Project 01 *
* 09/13/2017 *
* Reference: *
* Chapter 10 pages 470-480*
*
* Project for Dr. Bin Tang's CSC 541 Advanced Operating Systems class Fall 2017 Semester*
* at CSU Dominguez Hills. *
* ********************************************************************************** *
*/

*****READ ME*****
written by Gerardo Enriquez & Anyi Wang on 9/12/2017
This project was completed by both Gerardo Enriquez and Anyi Wang
We collaborated in dividing the work of each algorithm. This project is completed in JAVA language.

Compiling the program:
The project comes together as a package with the .Java files along with the .Class files so there
is no need to compile again but in the event of errors it can be compiled from command line
using :
javac Projectone.java
javac Generator.java


Run/Execute the program:
Upon compiling them they can be ran/executed by using the commands:
java Projectone
java Generator

Using the program:

A screenshot will be provided of the programming runnable it will be attached to this
folder.



QUESTION:
Page 501: Implementing disk-scheduling algorithms using Java, with the following modifications.

Instead of taking only one parameter (i.e., the initial position of disk head) on the command line, your Java program should instead take four parameters - the minimum cylinder number, the maximum cylinder number, the number of random cylinder requests, and the initial position of disk head).

Attached are two Java program templates that can be helpful in your implementation.

Please submit it your work in Blackboard as "LastNameOfFirstStudent_LastNameOfSecondStudent_541_proj1.zip", which includes your source Java codes and a README as how to execute your program, some screenshots to demonstrate the success execution of your program, and the specific contributions of each of the team members.

Also, to help me to test, your program should also print out the initial sequence of the request and the actual sequence of head movement. For example, in Figure 10.4 of the textbook (page 474),

The initial sequence of request is: 98, 183, 37, 122, 14, 124, 65, 67

The actual sequence of head movement is (with initial position of head is at 53): 53->98->183->37->122->14->124->65->67