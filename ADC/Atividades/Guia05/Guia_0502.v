
module ex2( output s, input a,b);
    wire nandb;

    nand nand_a(nanda, a , a);
    nand nand_2(s,nanda,b);
endmodule

module Guia_0502;
    reg x;
    reg y;

    wire s;
    ex2 result(s, x,y);

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

