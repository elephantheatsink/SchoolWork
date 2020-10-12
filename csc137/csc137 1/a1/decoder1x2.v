// decoder1x2.v, 1x2 decoder, gate synthesis
// compile: ~changw/ivl/bin/iverilog decoder1x2.v
// run: ./a.out

module DecoderMod(s, o0, o1); // module definition
   input s;
   output o0, o1;

   assign o1 = s;
   not(o0, s);  // "not" is built-in, inverter gate
endmodule

module TestMod;               // module to test-run
   reg s;       // s is a 1-bit flipflop
   wire o0, o1; // 2 additional wires

   DecoderMod my_decoder(s, o0, o1); // create instance

   initial begin
      $monitor("%0d\t%b\t%b\t%b", $time, s, o0, o1);
      $display("Time\ts\to0\to1");
      $display("--------------------------");
   end

   initial begin
      s = 0;       // initially s is 0
      #1;          // wait 1 simulation time unit/cycle
      s = 1;       // change s to 1
      #1;          // wait 1 simulation time unit/cycle
      s = 0;       // change s to 0
      #1;          // wait 1 simulation time unit/cycle
      s = 1;       // change s to 1
   end
endmodule

