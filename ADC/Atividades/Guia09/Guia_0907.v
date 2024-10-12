`include "clock.v"

module pulse (output reg signal, input clock);

    always @ (clock) begin
        if (clock == 1'b1) begin
            signal = 1'b1;
            #8 signal = 1'b0;
        end
    end
endmodule

module Guia_0907;
    wire clock;
    clock clk (clock);
    wire p1;

    pulse pulse1 (p1, clock);

    initial begin
        $dumpfile("Guia_0907.vcd");
        $dumpvars(1, clock, p1);
        #480 $finish;
    end
endmodule
