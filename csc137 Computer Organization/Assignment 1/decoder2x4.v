//Beomjin Han
//
//decoder 2x4.v, 2x4 decoder, gate synthesis

module DecoderMod(s1, s0, o0, o1, o2, o3); // module definition
   input s1, s0;
   output o0, o1, o2, o3;

   wire inv_s1, inv_s0;
   not(inv_s1, s1);  // "not" is built-in, inverter gate
   not(inv_s0, s0);
   and(o0, 	inv_s1,	inv_s0);
   and(o1, 	inv_s1,		s0);
   and(o2, 		s1,	inv_s0);
   and(o3,		s1, 	s0);   
endmodule

module TestMod;               // module to test-run
   reg s1, s0;       // s is a 1-bit flipflop
   wire o0, o1, o2, o3; // 2 additional wires

   DecoderMod my_decoder(s1, s0, o0, o1, o2, o3); // create instance

   initial begin
      $monitor("%0d\t%b\t%b\t%b", $time, s, o0, o1, o2, o3); //need s1, s0?
      $display("Time\ts\to0\to1\t02\t03");
      $display("------------------------------------------");
   end

   initial begin
      s1 = 0; s0 = 0: #1;
	  s1 = 0; s0 = 1: #1;
	  s1 = 1; s0 = 0: #1;
	  s1 = 1; s0 = 1: #1;
   end
endmodule
