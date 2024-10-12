module ex5(output s, input a, input b);
  
  wire not_a, not_b;
  wire a_nor_b, a_and_b, not_a_and_not_b;

  // NOT gates using NOR
  nor NOR1(not_a, a, a);     // ~a
  nor NOR2(not_b, b, b);     // ~b
  
  // NOR gate (a NOR b)
  nor NOR3(a_nor_b, a, b);   // a NOR b
  
  // AND gates using NOR
  nor NOR4(a_and_b, a_nor_b, a_nor_b);  // a AND b = ~(a NOR b) NOR ~(a NOR b)
  nor NOR5(not_a_and_not_b, not_a, not_b);  // ~a AND ~b
  
  // Final XNOR using NOR
  nor NOR6(s, a_and_b, not_a_and_not_b);   // s = ~(a AND b) NOR ~(~a AND ~b) = a XNOR b

endmodule
module Guia_0502;
    reg x;
    reg y;

    wire s;
    ex5 result(s, x,y);

    initial begin : main
        $display("Test module"); 
        $display("   x    y    s");
        // projetar testes do modulo
        $monitor("%4b %4b %4b", x, y, s);
        x = 1'b0; y = 1'b0;
        #1 x = 1'b0; y = 1'b1;
        #1 x = 1'b1; y = 1'b0;
        #1 x = 1'b1; y = 1'b1;
    end
endmodule

