// complete this code first
// then expand it to count 0~15, and name it as count16.v
// use only arrays for all registers and wires (except CLK)
// Beomjin Han
// CSC 137 - 2
// Assignment 5

module TestMod;
   reg CLK;
   wire [0:15] Q;
   wire [0:3] C; 

   always begin // this is clock wave
      CLK = 0;  // 0 for half cycle (#1)
      #1;
      CLK = 1;  // 1 for half cycle (#1)
      #1;
   end

   RippleMod my_ripple(CLK, Q[0], Q[1], Q[2], Q[3], Q[4], Q[5], Q[6], Q[7], Q[8], Q[9], Q[10], Q[11], Q[12], Q[13], Q[14], Q[15]);
   CoderMod my_coder(Q[0], Q[1], Q[2], Q[3], Q[4], Q[5], Q[6], Q[7], Q[8], Q[9], Q[10], Q[11], Q[12], Q[13], Q[14], Q[15], C[0], C[1], C[2], C[3]);

   initial #36 $finish;

   initial begin
      $display("Time\tCLK\tQ\tC\tC");
      $monitor("%0d\t%b\t%b\t%b\t%0d", $time, CLK, Q, C, C);
   end
endmodule

module CoderMod(Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15, C0, C1, C2, C3);
   input Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15;
   output C0, C1, C2, C3;
   or(C0, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15);
   or(C1, Q4, Q5, Q6, Q7, Q12, Q13, Q14, Q15);
   or(C2, Q2, Q3, Q6, Q7, Q10, Q11, Q14, Q15);
   or(C3, Q1, Q3, Q5, Q7, Q9, Q11, Q13, Q15);

endmodule


module RippleMod(CLK, Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15);
   input CLK;
   output Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15;

   reg Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15;

   always @(posedge CLK) begin
      Q0 <= Q15;
      Q1 <= Q0;
      Q2 <= Q1;
      Q3 <= Q2;
      Q4 <= Q3;
      Q5 <= Q4;
      Q6 <= Q5;
      Q7 <= Q6;
      Q8 <= Q7;
      Q9 <= Q8;
      Q10 <= Q9;
      Q11 <= Q10;
      Q12 <= Q11;
      Q13 <= Q12;
      Q14 <= Q13;
      Q15 <= Q14;

   end


   initial begin // here we conveniently set flipflops to 1000 (binary)
      Q0 = 1;
      Q1 = 0;
      Q2 = 0;
      Q3 = 0;
      Q4 = 0;
      Q5 = 0;
      Q6 = 0;
      Q7 = 0;
      Q8 = 0;
      Q9 = 0;
      Q10 = 0;
      Q11 = 0;
      Q12 = 0;
      Q13 = 0;
      Q14 = 0;
      Q15 = 0;
   end
endmodule
