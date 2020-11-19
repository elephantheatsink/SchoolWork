// test0.v

`timescale 1ns/10ps

module MY_MOD(clk, a, x);
   input clk;
   input [0:3] a; // input are wires even if came from reg
   output x;      // output are wires unless from reg in this module

   not(x, a[0]);  // or: assign x = ~a[0];
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
         assign d = 4'b0000;
      #1 assign d = 4'b0001;
      #1 assign d = 4'b0010;
      #1 assign d = 4'b0011;
      #1 assign d = 4'b0100;
      #1 assign d = 4'b0101;
      #1 assign d = 4'b0110;
      #1 assign d = 4'b0111;
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
