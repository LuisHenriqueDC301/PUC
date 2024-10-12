
module ex3( output s, input a,b);
    wire nora;
    wire nor1;
    wire nor2;
    wire nor3;
    nor nor_a(nora, a , a);
    nor nor_1(nor1,nora, b);
    nor nor_2(nor2, nor1, nor1);
    nor nor_3(s, nor2, nor2);
endmodule

module Guia_0501;
    reg x;
    reg y;

    wire s;
    ex3 result(s, x,y);

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

