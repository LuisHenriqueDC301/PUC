// ~a.b
module ex1( output s, input a,b);
    wire norb;

    nor nor_b(norb, b , b);
    nor nor_1(s,a, norb);
endmodule

module Guia_0501;
    reg x;
    reg y;

    wire s;
    ex1 result(s, x,y);

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

