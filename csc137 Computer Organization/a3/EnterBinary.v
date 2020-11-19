module EnterBinary;
// stdin is given by default, console input (keyboard)
   parameter STDIN = 32'h8000_0000; // 32 bit number 0x80000000

   reg [7:0] x, newline;
   
   always begin // enter 0 or 1 in loop until entering other
      #1;       // always-loop requires time/event condition

      $display("Enter binary, 0 or 1, others to end:");

      x = $fgetc( STDIN );      // get ASCII order/value
      x = x - 48;               // convert ASCII order to meant value
      $display("Got x: %d", x); // show 0 or 1, or ends


      newline = $fgetc( STDIN );    // get newline, discard
   end
endmodule
