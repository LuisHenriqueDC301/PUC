`include "clock.v"

module pulse (output reg signal, input clock);

    always @ (negedge clock) begin
        signal = 1'b1;
        #5 signal = 1'b0;
    end
endmodule

module Guia_0906;
    wire clock;
    clock clk (clock);
    wire p1;

    pulse pulse1 (p1, clock);

    initial begin
        $dumpfile("Guia_0906.vcd");
        $dumpvars(1, clock, p1);
        #480 $finish;
    end
endmodule
