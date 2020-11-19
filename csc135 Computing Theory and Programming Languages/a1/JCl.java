//Beomjin Han
//CSC 135
//Professor Chidella
//Assignment 1


import java.io.IOException;
import java.util.Scanner;

public class a1 {
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

//WRITE YOUR REST OF THE PARSER HERE
private void javaclass(){ //<javaclass> ::= <classname> [X <classname>] B <varlist>; {<method>} E
	classname();
	if (token()=='X') {
		match('X');
		classname();
	}
	match('B');
	varlist();
	match(';');
	while (token()=='P'||token()=='V') {
		method();
	}
	match('E');
}
 
private void classname() { //<classname> ::= C|D
	if(token()=='C')
		match('C');
	else if(token()=='D')
		match('D');
	else error();
}

private void varlist() { //<varlist> ::= <vardef> {, <vardef>}
	vardef();
	while (token()==',') {
		match(',');
		vardef();
	}
}

private void vardef() { //<vardef> ::= <type> <varname> | <classname> <varref>	
	if (token()=='I'||token()=='S') { 
		type();
		varname();
	}
	else if (token()=='C'||token()=='D') {
		classname();
		varref();
	}else error();
}

private void type() { //<type> ::= I|S
	if(token()=='I')
		match('I');
	else if(token()=='S')
		match('S');
	else error();
}

private void varname() { //<varname> ::= <letter> {<char>}
	letter();
	while (token()=='Y'||token()=='Z'||token()=='0'||token()=='1'||token()=='2'||token()=='3') {
		character();
	}
}

private void letter() { //<letter> ::= Y|Z
	if(token()=='Y')
		match('Y');
	else if(token()=='Z')
		match('Z');
	else error();
}

private void character() { //<char> ::= <letter> | <digit>
	                           //renamed from <char> because of java syntax error
	if (token()=='Y'||token()=='Z')
		letter();
	else if (token()=='0'||token()=='1'||token()=='2'||token()=='3')
		digit();
	else error();
}

private void digit() { //<digit> ::= 0|1|2|3
	if(token()=='0')
		match('0');
	else if(token()=='1')
		match('1');
	else if(token()=='2')
		match('2');
	else if(token()=='3')
		match('3');
	else error();
}

private void integer() { //<integer> ::= <digit> {<digit>}
	digit();
	while(token()=='0'||token()=='1'||token()=='2'||token()=='3') {
		digit();
	}
}
 
private void varref() {//<varref> ::= J|K
	if(token()=='J') {
		match('J');
	}
	else if(token()=='K') {
		match('K');
	}
	else error();
}

private void method() { //<method> ::= <accessor> <type> <methodname> ([<varlist>]) B {<statemt>} <returnstatemt> E
	accessor();
	type();
	methodname();
	match('(');
	if(token()=='I'||token()=='S'||token()=='C'||token()=='D') {
		varlist();
	}
	match(')');
	match('B');
	while(token()=='F'||token()=='Y'||token()=='Z'||token()=='J'||token()=='K'||token()=='W') {
		statemt();
	}
	returnstatemt();
	match('E');
	
}

private void accessor() { //<accessor> ::= P|V
	if(token()=='P')
		match('P');
	else if(token()=='V')
		match('V');
	else error();
}

private void methodname() { //<methodname> ::= M|N
	if(token()=='M')
		match('M');
	else if(token()=='N')
		match('N');
	else error();
}

private void statemt() { //<statemt> ::= <ifstatemt> | <assignstatemt>;|<whilestatemt>
	if (token()=='F') {
		ifstatemt();
	}
	else if (token()=='Y'||token()=='Z'||token()=='J'||token()=='K') {
		assignstatemt();
		match(';');
	}
	else if (token()=='W') {
		whilestatemt();
	}else error();
}

private void ifstatemt() { //<ifstatemt> ::= F <cond> T B {<statemt>} E [L B {<statemt>} E]
	match('F');
	cond();
	match('T');
	match('B');
	while(token()=='F'||token()=='Y'||token()=='Z'||token()=='J'||token()=='K'||token()=='W') {
		statemt();
	}
	match('E');
	if(token()=='L') {
		match('L');
		match('B');
		while(token()=='F'||token()=='Y'||token()=='Z'||token()=='J'||token()=='K'||token()=='W') {
			statemt();
		}
		match('E');
		
	}
}

private void assignstatemt() { //<assignstatemt> ::= <varname> = <mathexpr> | <varref> = <getvarref> 
	if(token()=='Y'||token()=='Z') {
		varname();
		match('=');
		mathexpr();
	}
	else if(token()=='J'||token()=='K') {
		varref();
		match('=');
		getvarref();
	}else error();
}

private void mathexpr() { //<mathexpr> ::= <factor> {+ factor}
	factor();
	while(token()=='+') {
		match('+');
		factor();
	}
}

private void factor() { //<factor> ::= <oprnd> {* oprnd}
	oprnd();
	while(token()=='*') {
		match('*');
		oprnd();
	}
}

private void oprnd() { //<oprnd> ::= <integer> | <varname> | (<mathexpr>) | <methodcall>
	if (token()=='0'||token()=='1'||token()=='2'||token()=='3') {
		integer();
	}
	else if(token()=='Y'||token()=='Z') {
		varname();
	}
	else if(token()=='(') {
		match('(');
		mathexpr();
		match(')');
	}
	else if(token()=='J'||token()=='K') {
		methodcall();
	}else error();
}

private void getvarref() { //<getvarref> ::= O <classname>() | <methodcall>
	if (token()=='O') {
		match('O');
		classname();
		match('(');
		match(')');
	}
	else if(token()=='J'||token()=='K') {
		methodcall();
	}else error();
}

private void whilestatemt() { //<whilestatemt> ::= W <cond> T B {<statemt>} E
	match('W');
	cond();
	match('T');
	match('B');
	while(token()=='F'||token()=='Y'||token()=='Z'||token()=='J'||token()=='K'||token()=='W') {
		statemt();
	}
	match('E');
}

private void cond() { //<cond> ::= (<oprnd> <operator> <oprnd>)
	match('(');
	oprnd();
	operator();
	oprnd();
	match(')');
}

private void operator() { //<operator> ::= < | = | > | !
	if(token()=='<')
		match('<');
	else if(token()=='=')
		match('=');
	else if(token()=='>')
		match('>');
	else if(token()=='!')
		match('!');
	else error();
}

private void returnstatemt() { //<returnstatemt> ::= R <varname>;
	match('R');
	varname();
	match(';');
}

private void methodcall() { //<methodcall> ::= <varref>.<methodname>( [ <varlist> ] )
	varref();
	match('.');
	methodname();
	match('(');
	if(token()=='I'||token()=='S'||token()=='C'||token()=='D') {
		varlist();
	}
	match(')');
	
}

//----------------------

 private void start()  {
 javaclass();
 match('$');
 if (errorflag == 0)
 System.out.println("legal." + "\n");
 else
 System.out.println("errors found." + "\n");
}
//----------------------
 public static void main (String[] args) throws IOException
 {
 a1 rec = new a1();
 Scanner input = new Scanner(System.in);
 
 System.out.print("\n" + "enter an expression: ");
 inputString = input.nextLine();

 rec.start();
 
 
 
 }
 
 
 
}
