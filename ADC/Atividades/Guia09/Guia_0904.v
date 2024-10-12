`include "clock.v"

module pulse (output reg signal, input clock);
    reg [1:0] counter = 2'b00; // Contador de 2 bits para dividir o período por 4

    always @ (posedge clock) begin
        if (counter == 2'b11) begin
            signal = ~signal; // Inverte o sinal a cada 4 ciclos
            counter = 2'b00;
        end else begin
            counter = counter + 1;
        end
    end
endmodule

module Guia_0904;
    wire clock;
    clock clk (clock);
    wire p1;

    pulse pulse1 (p1, clock);

    initial begin
        $dumpfile("Guia_0904.vcd");
        $dumpvars(1, clock, p1);
        #480 $finish;
    end
endmodule
