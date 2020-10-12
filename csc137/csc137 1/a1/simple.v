// simple.v
// simple use of built-in logic gate
// loop: register A is incremented by 1, then first 4 bits of
// B is last 4 bits of A "inverted". then C is last 2 bits
// of A "and" together.

module simple;

   reg [0:7] A;  // 8-bit registers
   wire [0:7] B; // 8 wires
   wire C;       // single wire

// "initial"s and "always" code blocks will run concurrently
   initial begin
      #20;         // wait 20 simulation time units/cycles
      $finish;     // end execution run
   end

// these statements done at time 0 (since no #?)
   initial begin
    // initialize register A.  Others have unknown values "z"
       A = 0;
       $display("Time   A         B    C");  // display a header

// "monitor" prints out values when changes happen (A, B or C)
       $monitor("  %0d %b %b %b", $time, A, B, C);
   end

   always begin
       #1;           // wait for 1 simulation time unit/cycle
       A = A + 1;
   end

   not(B[0], A[4]);  // these are directly hardwired
   not(B[1], A[5]);  // with built-in logic gates
   not(B[2], A[6]);  // as key words
   not(B[3], A[7]);
   and(C, A[6], A[7]);

endmodule  // end of module simple
