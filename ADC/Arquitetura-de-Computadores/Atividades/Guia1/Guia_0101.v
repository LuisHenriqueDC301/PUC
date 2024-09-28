/*
863566 - Luis Henrique Ferreira Costa
*/

module Guia;
    integer x = 26;
    reg[7:0] b = 0;
    reg[15:0] b16 = 0;
    reg [13:0][13:0] s = "PUC";
    reg [7:0]s1 [0:4];
    initial
        begin : main
            $display("Atividade1 ");
            b = x;
            $display("%d = %8b",x, b);
            x = 53;
            b = x;
            $display("%d = %8b",x,b);
            x = 713;
            b = x;
            $display("%d = %8b",x,b);
            x = 213;
            b = x;
            $display("%d = %8b",x,b);
            x = 365;
            b = x;
            $display("%d = %8b",x,b);

            $display("Atividade2");
            b = 8'b10101;
            x = b;
            $display(" %8b = %d", b , x);
            b = 8'b11011;
            x = b;
            $display(" %8b = %d", b , x);
            b = 8'b10010;
            x = b;
            $display(" %8b = %d", b , x);
            b = 8'b101011;
            x = b;
            $display(" %8b = %d", b , x);
            b = 8'b110111;
            x = b;
            $display(" %8b = %d", b , x);

            $display("Atividade3");
            x = 61;
            b = x;
            $display ( "%d = 100(4)",x );
            x = 53;
            b = x;
            $display ( "%d = %o(8)",x, b);
            x = 77;
            b = x;
            $display ( "%d = %X(16)",x, b);
            x = 153;
            b16 = x;
            $display ( "%d = %X(16)",x, b16);
            x = 753;
            b16 = x;
            $display ( "%d = %x(16)",x, b16);


            $display ( "Atividade 4" );
            $display ( "10100 = 213(4)");
            b16 = 8'b11010;
            $display ( "%8b = %o(8)",b16[5:0], b16[5:0]);
            b16 = 16'b100111;
            $display ( "%16b = %x(16)",b16[5:0], b16[5:0]);
            b16 = 8'b100101;
            $display ( "%8b = %o(8)",b16[5:0], b16[5:0]);
            $display ( "101101 = 231(4)");

            $display(" Atividade 5");
            s[0] = "P";
            s[1] = "U";
            s[2] = "C";
            s[3] = "-";
            s[4] = "M";
            s[5] = ".";
            s[6] = "G";
            $display("PUC MINAS: %d %d %d %d %d %d %d",s[0], s[1], s[2], s[3], s[4], s[5], s[6]);
            

            s[0] = "2";
            s[1] = "0";
            s[2] = "2";
            s[3] = "4";
            s[4] = "-";
            s[5] = "0";
            s[6] = "2";
            $display("2024-02 : %d %d %d %d %d %d %d", s[0], s[1], s[2], s[3], s[4], s[5], s[6]);

            s[0] = "B";
            s[1] = "e";
            s[2] = "l";
            s[3] = "o";
            s[4] = " ";
            s[5] = "H";
            s[6] = "o";
            s[7] = "r";
            s[8] = "i";
            s[9] = "z";
            s[10] = "o";
            s[11] = "n";
            s[12] = "t";
            s[13] = "e";
            $display("Belo Horizonte : %d %d %d %d %d %d %d %d %d %d %d %d %d %d", s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], s[10], s[11], s[12], s[13]);

            s1[0] = 8'o156; // 156 em octal
            s1[1] = 8'o157; // 157 em octal
            s1[2] = 8'o151; // 151 em octal
            s1[3] = 8'o164; // 164 em octal
            s1[4] = 8'o145; // 145 em octal
            $display("156 157 151 164 145 : %d %d %d %d %d", s1[0], s1[1], s1[2], s1[3], s1[4]);

            s1[0] = 16'h4d;
            s1[1] = 16'h61;
            s1[2] = 16'h6e;
            s1[3] = 16'h68;
            s1[3] = 16'h61;
            $display("4D 61 6E 68 61 : %d %d %d %d %d", s1[0], s1[1], s1[2], s1[3], s1[4]);
        end

endmodule;

