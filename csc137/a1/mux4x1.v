//Beomjin Han
//


// mux4x1.v, 4x1 multiplexor, gate synthesis


module MuxMod(s1, s0, d0, d1, d2, d3, o);
   input s1, s0, d0, d1, d2, d3;
   output o;

   wire s1_inv, s0_inv, and0, and1, and2, and3, and00, and11, and22, and33; // additional needed wires

   not(s1_inv, s1);          // "not" is built-in gate
   not(s0_inv, s0);
   and(and0, d0, s1_inv, s0_inv);   // "and" is built-in gate
   and(and1, d1, s1_inv, s0);       // "and" is built-in gate
   and(and2, d2, s1    , s0_inv);   // "and" is built-in gate
   and(and3, d3, s1    , s0);   // "and" is built-in gate
   or(o, and0, and1, and2, and3);
endmodule

module TestMod;
   reg s1, s0, d0, d1, d2, d3;
   wire o;

   MuxMod my_mux(s1, s0, d0, d1, d2, d3, o);

//  initial #8 $finish; // end at $time 8

   initial begin
      $display("Time\ts1\ts0\td0\td1\td2\td3\to");
      $display("-----------------------------------------------------------");
      $monitor("%0d\t%b\t%b\t%b\t%b\t%b\t%b\t%b", $time, s1, s0, d0, d1, d2, d3, o);
   end

   initial begin
      s1 = 0; s0 = 0; d0 = 0; d1 = 0; d2 = 0; d3 = 0;  // initially 0 for all
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 0; d0 = 0; d1 = 0; d2 = 0; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 0; d0 = 0; d1 = 0; d2 = 1; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 0; d0 = 0; d1 = 0; d2 = 1; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 0; d0 = 0; d1 = 1; d2 = 0; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 0; d0 = 0; d1 = 1; d2 = 0; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 0; d0 = 0; d1 = 1; d2 = 1; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 0; d0 = 0; d1 = 1; d2 = 1; d3 = 1; 
      #1
      s1 = 0; s0 = 0; d0 = 1; d1 = 0; d2 = 0; d3 = 0; 
      #1
      s1 = 0; s0 = 0; d0 = 1; d1 = 0; d2 = 0; d3 = 1; 
      #1
      s1 = 0; s0 = 0; d0 = 1; d1 = 0; d2 = 1; d3 = 0; 
      #1
      s1 = 0; s0 = 0; d0 = 1; d1 = 0; d2 = 1; d3 = 1; 
      #1
      s1 = 0; s0 = 0; d0 = 1; d1 = 1; d2 = 0; d3 = 0; 
      #1
      s1 = 0; s0 = 0; d0 = 1; d1 = 1; d2 = 0; d3 = 1; 
      #1
      s1 = 0; s0 = 0; d0 = 1; d1 = 1; d2 = 1; d3 = 0; 
      #1
      s1 = 0; s0 = 0; d0 = 1; d1 = 1; d2 = 1; d3 = 1;
      #1 
      s1 = 0; s0 = 1; d0 = 0; d1 = 0; d2 = 0; d3 = 0;  //
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 1; d0 = 0; d1 = 0; d2 = 0; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 1; d0 = 0; d1 = 0; d2 = 1; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 1; d0 = 0; d1 = 0; d2 = 1; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 1; d0 = 0; d1 = 1; d2 = 0; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 1; d0 = 0; d1 = 1; d2 = 0; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 1; d0 = 0; d1 = 1; d2 = 1; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 0; s0 = 1; d0 = 0; d1 = 1; d2 = 1; d3 = 1; 
      #1
      s1 = 0; s0 = 1; d0 = 1; d1 = 0; d2 = 0; d3 = 0; 
      #1
      s1 = 0; s0 = 1; d0 = 1; d1 = 0; d2 = 0; d3 = 1; 
      #1
      s1 = 0; s0 = 1; d0 = 1; d1 = 0; d2 = 1; d3 = 0; 
      #1
      s1 = 0; s0 = 1; d0 = 1; d1 = 0; d2 = 1; d3 = 1; 
      #1
      s1 = 0; s0 = 1; d0 = 1; d1 = 1; d2 = 0; d3 = 0; 
      #1
      s1 = 0; s0 = 1; d0 = 1; d1 = 1; d2 = 0; d3 = 1; 
      #1
      s1 = 0; s0 = 1; d0 = 1; d1 = 1; d2 = 1; d3 = 0; 
      #1
      s1 = 0; s0 = 1; d0 = 1; d1 = 1; d2 = 1; d3 = 1; 
      #1
      s1 = 1; s0 = 0; d0 = 0; d1 = 0; d2 = 0; d3 = 0;  // s1 = 1
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 0; d0 = 0; d1 = 0; d2 = 0; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 0; d0 = 0; d1 = 0; d2 = 1; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 0; d0 = 0; d1 = 0; d2 = 1; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 0; d0 = 0; d1 = 1; d2 = 0; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 0; d0 = 0; d1 = 1; d2 = 0; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 0; d0 = 0; d1 = 1; d2 = 1; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 0; d0 = 0; d1 = 1; d2 = 1; d3 = 1; 
      #1
      s1 = 1; s0 = 0; d0 = 1; d1 = 0; d2 = 0; d3 = 0; 
      #1
      s1 = 1; s0 = 0; d0 = 1; d1 = 0; d2 = 0; d3 = 1; 
      #1
      s1 = 1; s0 = 0; d0 = 1; d1 = 0; d2 = 1; d3 = 0; 
      #1
      s1 = 1; s0 = 0; d0 = 1; d1 = 0; d2 = 1; d3 = 1; 
      #1
      s1 = 1; s0 = 0; d0 = 1; d1 = 1; d2 = 0; d3 = 0; 
      #1
      s1 = 1; s0 = 0; d0 = 1; d1 = 1; d2 = 0; d3 = 1; 
      #1
      s1 = 1; s0 = 0; d0 = 1; d1 = 1; d2 = 1; d3 = 0; 
      #1
      s1 = 1; s0 = 0; d0 = 1; d1 = 1; d2 = 1; d3 = 1;
      #1 
      s1 = 1; s0 = 1; d0 = 0; d1 = 0; d2 = 0; d3 = 0;  //
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 1; d0 = 0; d1 = 0; d2 = 0; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 1; d0 = 0; d1 = 0; d2 = 1; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 1; d0 = 0; d1 = 0; d2 = 1; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 1; d0 = 0; d1 = 1; d2 = 0; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 1; d0 = 0; d1 = 1; d2 = 0; d3 = 1;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 1; d0 = 0; d1 = 1; d2 = 1; d3 = 0;  
      #1;                      // wait 1 cycle
      s1 = 1; s0 = 1; d0 = 0; d1 = 1; d2 = 1; d3 = 1; 
      #1
      s1 = 1; s0 = 1; d0 = 1; d1 = 0; d2 = 0; d3 = 0; 
      #1
      s1 = 1; s0 = 1; d0 = 1; d1 = 0; d2 = 0; d3 = 1; 
      #1
      s1 = 1; s0 = 1; d0 = 1; d1 = 0; d2 = 1; d3 = 0; 
      #1
      s1 = 1; s0 = 1; d0 = 1; d1 = 0; d2 = 1; d3 = 1; 
      #1
      s1 = 1; s0 = 1; d0 = 1; d1 = 1; d2 = 0; d3 = 0; 
      #1
      s1 = 1; s0 = 1; d0 = 1; d1 = 1; d2 = 0; d3 = 1; 
      #1
      s1 = 1; s0 = 1; d0 = 1; d1 = 1; d2 = 1; d3 = 0; 
      #1
      s1 = 1; s0 = 1; d0 = 1; d1 = 1; d2 = 1; d3 = 1; 
   end
   
   
endmodule
