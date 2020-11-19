// Beomjin Han
// AddSub.v, 137 Verilog Programming Assignment #4
module TestMod;                     // the "main" thing
   parameter STDIN = 32'h8000_0000; // I/O address of keyboard input channel

   reg [7:0] str [1:3]; // typing in 2 chars at a time (decimal # and Enter key)
   reg [4:0] X, Y;      // 5-bit X, Y to sum
   reg [1:0] sign;
	reg C0;
  	
   wire [4:0] S;        // 5-bit Sum to see as result
                // like to know this as well from result of Adder

 	wire C4, C5, E;
	

 // instantiate the big adder module (giving X and Y as input, getting S and C5 as output)
   BigAdderSub bas(X, Y, C0, S, C4, C5, E);



   initial begin
      $display("Enter X: (range 00 ~ 15): ");
      str[1] = $fgetc(STDIN);
      str[2] = $fgetc(STDIN);
      //and the ENTER key:        --> ...
      str[3] = $fgetc(STDIN);
      //convert str to value for X:
        
      // str[1] - 48 first, then times 10, then + str[2] - 48

      str[1] = str[1] - 48;
      str[1] = str[1] * 10;
      str[2] = str[2] - 48;
      X = str[1] + str[2];

      //do the above to get input and convert it to Y
      $display("Enter Y: (range 00 ~ 15)");
      str[1] = $fgetc(STDIN);
      str[2] = $fgetc(STDIN);
      str[3] = $fgetc(STDIN);
      str[1] = str[1] - 48;
      str[1] = str[1] * 10;
      str[2] = str[2] - 48;
      Y = str[1] + str[2];

	//getting the input for +/-
	$display("Enter either '+' or '-': ");
	str[1] = $fgetc(STDIN);
	str[2] = $fgetc(STDIN);
	str[1] = str[1] - 75;    //Tried using the number value of + and -
	sign = str[1];           //in order to set C0 as 0 or 1
	C0 = sign[1];  
	
      #1; // wait until Adder gets them processed
      $display("X =  %b (%d)  Y = %b (%d) C0=%d", X, X, Y, Y, C0);  
      $display("Result= %b (as unsigned %d)", S, S);
	$display("C4=%b C5=%b E=%b",C4,C5,E);
	if(E==0)
	begin
		$display("Since E is 0, C5 is not needed");
	end
	else if(E==1)
	begin
		$display("Since E is 1, correct with C5 in front: %b%b",C5,S);
	end
   end
endmodule

module BigAdderSub(x, y, C0, S, C4, C5, E);
   input [4:0] x, y, Yxor;   // two 5-bit input items
   input C0;
   output [4:0] S;          // S should be similar
   output  E, C4, C5;
	
	wire [4:1] C;  

	/////////////////// Assignment 4 XOR gates for the Subtractor
	
	xor(Yxor[0],y[0],C0);
	xor(Yxor[1],y[1],C0);
	xor(Yxor[2],y[2],C0);
	xor(Yxor[3],y[3],C0);
	xor(Yxor[4],y[4],C0);

   	FullAdderMod fa0(x[0], Yxor[0], C0, C[1], S[0]);//... (get an instance of a full adder)
   	FullAdderMod fa1(x[1], Yxor[1], C[1], C[2], S[1]);//.. (get an instance of a full adder)
   	FullAdderMod fa2(x[2], Yxor[2], C[2], C[3], S[2]);//.. (get an instance of a full adder)
   	FullAdderMod fa3(x[3], Yxor[3], C[3], C4, S[3]);//.. (get an instance of a full adder)
   	FullAdderMod fa4(x[4], Yxor[4], C4, C5, S[4]);//.. (get an instance of a full adder)

	xor(E, C4, C5);

endmodule

module FullAdderMod(x, y, Cin, Cout, sum); // single-bit adder module
   	input x, y, Cin;
	output Cout, sum;

	MajorityMod m(x, y, Cin, Cout);
	ParityMod p(x, y, Cin, sum);
endmodule

module MajorityMod(X, Y, Cin, Cout); // carry-bit generator module
   //... code the full adder (like in previous assignment) ...
	input X, Y, Cin;
	output Cout;
	
	wire and1, and2, and3;
	
	and(and0, X, Y);
	and(and1, X, Cin);
	and(and2, Cin, Y);
	or(Cout, and0, and1, and2);
endmodule

module ParityMod(X, Y, Cin, sum); // sum-bit generator module
  	input X, Y, Cin;
	output sum;
	
	wire xor0;
	xor(xor0, X, Y);
	xor(sum, xor0, Cin);
endmodule

