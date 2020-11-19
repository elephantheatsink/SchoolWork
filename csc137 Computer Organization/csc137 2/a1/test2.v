// test2.v

`timescale 1ns/10ps

module MY_MOD(clk, a, x);
   input clk;
   input [0:3] a; // no need to mention "wire" since that's default
   output x;
   reg r;

// initially r is undefined ('x') will show
   assign x = r;  // connection of the output wire

// if-else can only be used in "always/initial"
   always @(posedge clk) // "being-end" optional for single statement
      if(a[0] == 1) r = 0; // if-else is a single statement
      else r = 1;

endmodule

module test;
   reg [0:3] d;
   wire y;

   reg clk;

   initial begin
      clk = 0;
      #20 $finish;
   end

   always #1 clk = ~clk;

   MY_MOD mod0(clk, d, y);

   initial begin
         assign d = 1'b0; // syntax variation, same as 4'b0000
      #1 assign d = 4'b1; // syntax variation, same as 4'b0001
      #1 assign d = 4'b10; // syntax variation, same as 4'b0010
      #1 assign d = 4'b11; // syntax variation, same as 4'b0011
      #1 assign d = 4'b100; // syntax variation, same as 4'b0100
      #1 assign d = 4'b101;
      #1 assign d = 4'b110;
      #1 assign d = 4'b111;
      #1 assign d = 4'b1000;
      #1 assign d = 4'b1001;
      #1 assign d = 4'b1010;
      #1 assign d = 4'b1011;
      #1 assign d = 4'b1100;
      #1 assign d = 4'b1101;
      #1 assign d = 4'b1110;
      #1 assign d = 4'b1111;
   end

   initial begin
      $display("\t\tTime     d     y  clk");
      $monitor("%d    %b   %b   %b", $time, d, y, clk);
   end
endmodule
