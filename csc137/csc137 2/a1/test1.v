// test1.v

`timescale 1ns/10ps

module MY_MOD(clk, a, x);
   input clk;
   input [0:3] a; // default is "wires"
   output x;
   reg r;

// initially r is undefined, 'x' will show
   assign x = r;  // connection to the output wire

// can't "assign" a reg since a reg is not a wire
// hence in "always" block use a regular assignment
// to a reg in "always" triggered by signal edge
   always @(posedge clk) r = ~a[0];

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

// can also: #1 d = d + 1; d must init from 0
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
