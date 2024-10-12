module ex4( output s, input a,b);
    wire nandb;
    wire nand_2;
    wire nand_3;
    nand nand_a(nandb, b , b);
    nand nand1(nand_2,a,nandb);
    nand nand2(nand_3, nand_2, nand_2);
    nand nand3(s, nand_3, nand_3);
endmodule

module Guia_0502;
    reg x;
    reg y;

    wire s;
    ex4 result(s, x,y);

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

