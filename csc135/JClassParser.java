import java.io.IOException;
import java.util.Scanner;

public class JClassParser {
 static String inputString;
 static int index = 0;
 static int errorflag = 0;
 
 private char token() // convert every element in the user input to char to be used in the parser
   { return(inputString.charAt(index)); }
   
 private void advancePtr() // go to next element
   { if (index < (inputString.length()-1)) index++; }
   
 private void match(char T) // method that invokes error or advances pointer for each token
 { if (T == token()) advancePtr(); else error(); }
 
 private void error() // error method turns error
 {
 System.out.println("error at position: " + index);
 errorflag = 1;
 advancePtr();
 }
//----------------------
 private void jClass()
 {

 className();

 match('B');
 varlist();
 while(token() == 'P') {
 method();
 }
 match('E');
 }
// WRITE YOUR REST OF THE PARSER HERE
//----------------------
 private void start()
  {
 jClass();
 match('$');
 if (errorflag == 0)
 System.out.println("legal." + "\n");
 else
 System.out.println("errors found." + "\n");
 }
//----------------------
 public static void main (String[] args) throws IOException
 {
 JClassParser rec = new JClassParser();
 Scanner input = new Scanner(System.in);
 System.out.print("\n" + "enter an expression: ");
 inputString = input.nextLine();

 rec.start();
 }
}