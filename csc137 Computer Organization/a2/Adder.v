// Beomjin Han
// Adder.v, 137 Verilog Programming Assignment #3
module TestMod;                     // the "main" thing
   parameter STDIN = 32'h8000_0000; // I/O address of keyboard input channel

   reg [7:0] str [1:3]; // typing in 2 chars at a time (decimal # and Enter key)
   reg [4:0] X, Y;      // 5-bit X, Y to sum
   wire [4:0] S;        // 5-bit Sum to see as result
   wire C5;             // like to know this as well from result of Adder

  // instantiate the big adder module (giving X and Y as input, getting S and C5 as output)
   BigAdder ba(X, Y, S, C5);

   initial begin
      $display("Enter X: ");
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

      $display("Enter Y: ");
      str[1] = $fgetc(STDIN);
      str[2] = $fgetc(STDIN);
      str[3] = $fgetc(STDIN);
      str[1] = str[1] - 48;
      str[1] = str[1] * 10;
      str[2] = str[2] - 48;
      Y = str[1] + str[2];

      //do the above to get input and convert it to Y

      #1; // wait until Adder gets them processed
      $display("X =  %d (%b)  Y = %d (%b)", X, X, Y, Y);  
      //and
      //$display S and C5 (run demo to see display format)
      $display("Result= %d (%b)  C5 = %d", S, S, C5);
   end
endmodule


module BigAdder(x, y, S, C5);
   input [4:0] x, y;   // two 5-bit input items
   output [4:0] S;          // S should be similar
   output C5;          // another output for a different size

   wire C1, C2, C3, C4;                 // declare temporary wires
   
   	FullAdderMod fa0(x[0], y[0], 0, C1, S[0]);//... (get an instance of a full adder, C0 is 0)
   	FullAdderMod fa1(x[1], y[1], C1, C2, S[1]);//... (get an instance of a full adder)
   	FullAdderMod fa2(x[2], y[2], C2, C3, S[2]);//... (get an instance of a full adder)
   	FullAdderMod fa3(x[3], y[3], C3, C4, S[3]);//... (get an instance of a full adder)
   	FullAdderMod fa4(x[4], y[4], C4, C5, S[4]);//... (get an instance of a full adder)
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

