//Beomjin Han
//Weide Chang
// d2x4.v, 2x4 decoder, arrays and composite modules
//
// how to compile: iverilog d2x4.v
// how to run: a.out

module DecoderMod(s, o); // module definition
   input [0:1] s;
   output [0:3] o;

   wire [0:1] nots;
   
   not(nots[0], s[0]);
   not(nots[1], s[1]);

	and(o[0], nots[0], nots[1]);
	and(o[1], nots[0], s[1]);
	and(o[2], s[0], nots[1]);
	and(o[3], s[0], s[1]);

endmodule

module TestMod;
   reg [0:1] s;
   wire [0:3] o;

   DecoderMod my_decoder(s, o); // create instance

   initial begin
      $monitor("%0d\t%b\t%b", $time, s, o);
      $display("Time\ts\to");
      $display("--------------------");
   end

   initial begin
	s = 0; #1;
	s = 1; #1;
	s = 2; #1;
	s = 3;
      //s[0] = 0; s[1] = 0; #1;
      //s[0] = 0; s[1] = 1; #1;
      //s[0] = 1; s[1] = 0; #1;
      //s[0] = 1; s[1] = 1;
   end
endmodule
