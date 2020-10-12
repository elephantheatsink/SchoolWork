//Beomjin Han
//Weide Chang
// m4x1.v, 4x1 multiplexor, arrays and composite modules
//
// how to compile: iverilog m2x1.v
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

module MuxMod(s, d, o);
   input [0:1] s;
   input [0:3] d;
   output o;

   wire [0:3] s_decoded, and_out;

   DecoderMod my_decoder(s, s_decoded); // create instance
	
	
	
   and(and_out[0], d[0], s_decoded[0]);
   and(and_out[1], d[1], s_decoded[1]);
   and(and_out[2], d[2], s_decoded[2]);
   and(and_out[3], d[3], s_decoded[3]);
   or(o, and_out[0], and_out[1], and_out[2], and_out[3]);
endmodule

module TestMod;
   reg [0:1] s;
   reg [0:3] d;
   wire o;

   MuxMod my_mux(s, d, o);

   initial begin
      $display("Time  \ts  \td   \to");
      $display("-----------------------------");
      $monitor("%0d  \t%b  \t%b  \t%b", $time, s, d, o);
   end

   initial begin
      s = 0; d = 4'b0000; #1;
      s = 0; d = 4'b0001; #1;
      s = 0; d = 4'b0010; #1;
      s = 0; d = 4'b0011; #1;
      s = 0; d = 4'b0100; #1;
      s = 0; d = 4'b0101; #1;
      s = 0; d = 4'b0110; #1;
      s = 0; d = 4'b0111; #1;//8
      s = 0; d = 4'b1000; #1;
      s = 0; d = 4'b1001; #1;
      s = 0; d = 4'b1010; #1;
      s = 0; d = 4'b1011; #1;
      s = 0; d = 4'b1100; #1;
      s = 0; d = 4'b1101; #1;
      s = 0; d = 4'b1110; #1;
      s = 0; d = 4'b1111; #1;//16
      s = 1; d = 4'b0000; #1;
      s = 1; d = 4'b0001; #1;
      s = 1; d = 4'b0010; #1;
      s = 1; d = 4'b0011; #1;
      s = 1; d = 4'b0100; #1;
      s = 1; d = 4'b0101; #1;
      s = 1; d = 4'b0110; #1;
      s = 1; d = 4'b0111; #1;//8
      s = 1; d = 4'b1000; #1;
      s = 1; d = 4'b1001; #1;
      s = 1; d = 4'b1010; #1;
      s = 1; d = 4'b1011; #1;
      s = 1; d = 4'b1100; #1;
      s = 1; d = 4'b1101; #1;
      s = 1; d = 4'b1110; #1;
      s = 1; d = 4'b1111; #1;//16      
      s = 2; d = 4'b0000; #1;
      s = 2; d = 4'b0001; #1;
      s = 2; d = 4'b0010; #1;
      s = 2; d = 4'b0011; #1;
      s = 2; d = 4'b0100; #1;
      s = 2; d = 4'b0101; #1;
      s = 2; d = 4'b0110; #1;
      s = 2; d = 4'b0111; #1;//8
      s = 2; d = 4'b1000; #1;
      s = 2; d = 4'b1001; #1;
      s = 2; d = 4'b1010; #1;
      s = 2; d = 4'b1011; #1;
      s = 2; d = 4'b1100; #1;
      s = 2; d = 4'b1101; #1;
      s = 2; d = 4'b1110; #1;
      s = 2; d = 4'b1111; #1;//16      
      s = 3; d = 4'b0000; #1;
      s = 3; d = 4'b0001; #1;
      s = 3; d = 4'b0010; #1;
      s = 3; d = 4'b0011; #1;
      s = 3; d = 4'b0100; #1;
      s = 3; d = 4'b0101; #1;
      s = 3; d = 4'b0110; #1;
      s = 3; d = 4'b0111; #1;//8
      s = 3; d = 4'b1000; #1;
      s = 3; d = 4'b1001; #1;
      s = 3; d = 4'b1010; #1;
      s = 3; d = 4'b1011; #1;
      s = 3; d = 4'b1100; #1;
      s = 3; d = 4'b1101; #1;
      s = 3; d = 4'b1110; #1;
      s = 3; d = 4'b1111; //16
   end
endmodule
