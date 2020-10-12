//Beomjin Han
//Weide Chang
//full adder

module TestMod;
	reg [0:2] in;
	
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

