module f ( output s, input x, input y );
    wire w1, w2, w3, w4, w5;
    not NOT_1 (w1, x);
    not NOT_2 (w2, y);
    and OR__1 (w3, y, w1);
    or OR__2 (w4, w2, x);
    not NOT_3 (w5, w4);
    and AND_1 (s, w3, w5);
endmodule // s = f (x,y)
module PO1;
    reg x;
    reg y;

    wire s;
    f result(s, x,y);

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