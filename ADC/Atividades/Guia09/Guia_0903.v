`include "clock.v"

module pulse (output reg signal, input clock);
    reg [1:0] counter = 2'b00; // Contador de 2 bits para dividir a frequência por 3

    always @ (posedge clock) begin
        if (counter == 2'b10) begin // A cada 3 ciclos
            signal = ~signal; // Inverte o sinal
            counter = 2'b00; // Zera o contador
        end else begin
            counter = counter + 1; // Incrementa o contador
        end
    end
endmodule

module Guia_0903;
    wire clock;
    clock clk (clock);
    wire p1;

    pulse pulse1 (p1, clock);

    initial begin
        $dumpfile("Guia_0903.vcd");
        $dumpvars(1, clock, p1);
        #480 $finish;
    end
endmodule
