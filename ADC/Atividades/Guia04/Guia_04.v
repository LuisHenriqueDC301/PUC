module ex21(output s1,output s2, input x,y);
    assign s1 = x & ~(~x | y);
    assign s2 = x & ~y;
endmodule;

module ex22(output s1, s2, input x,y);
    assign s1 = (~x | y) | (~x & y);
    assign s2 = (~x | y);
endmodule

module ex23(output s1, s2, input x,y);
    assign s1 = ~(~x & ~y) & (x | y);
    assign s2 = x | y;
endmodule

module main;
    reg x,y;
    wire s1,s2;
    //ex21 EX21(s1, s2, x,y);
    //ex22 EX22(s1,s2,x,y);
    ex23 EX23(s1,s2,x,y)
    initial begin: start
        x = 1'bx; y = 1'bx;
    end

    initial begin: Guia04
        $display("\nx'&y+x&y' = s\n");
        // monitoramento
        $display("x y = s1 s2");
        $monitor("%b %b = %b %b ", x, y, s1, s2);
        // sinalizacao
        #1 x=0; y=0;
        #1 x=0; y=1;
        #1 x=1; y=0;
        #1 x=1; y=1;
        #1 x=1; y=1;

    end
endmodule;