module f ( output s, input a, input b );
    wire w1, w2, w3, w4;

    xor xor_1 (w1, a,b);
    not NOT_a (w2, a);
    not NOT_b (w3, b);
    nor NOR__1 (w4, w2, w3);
    nand (s, w1,w4);
endmodule // s = f (x,y)
module PO1;
    reg x;
    reg y;

    wire s;
    f result(s, x,y);

    initial begin : main
        $display("EX 5"); 
        $display("   x    y    s");
        // projetar testes do modulo
        $monitor("%4b %4b %4b", x, y, s);
        x = 1'b0; y = 1'b0;
        #1 x = 1'b0; y = 1'b1;
        #1 x = 1'b1; y = 1'b0;
        #1 x = 1'b1; y = 1'b1;
    end
endmodule