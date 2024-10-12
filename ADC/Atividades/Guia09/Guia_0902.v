`include "clock.v" // Inclui o módulo clock a partir de um arquivo externo

// ---------------------------
// -- pulse1 generator
// ---------------------------
module pulse1 (output reg signal, input clock);
always @ (posedge clock)
 begin
    signal = 1'b1;
    #4 signal = 1'b0;
    #4 signal = 1'b1;
    #4 signal = 1'b0;
    #4 signal = 1'b1;
    #4 signal = 1'b0;
 end
endmodule // pulse1

// ---------------------------
// -- pulse2 generator
// ---------------------------
module pulse2 (output reg signal, input clock);
always @ (posedge clock)
 begin
    signal = 1'b1;
    #5 signal = 1'b0;
 end
endmodule // pulse2

// ---------------------------
// -- pulse3 generator
// ---------------------------
module pulse3 (output reg signal, input clock);
always @ (negedge clock)
 begin
    signal = 1'b1;
    #15 signal = 1'b0;
    #15 signal = 1'b1;
 end
endmodule // pulse3

// ---------------------------
// -- pulse4 generator
// ---------------------------
module pulse4 (output reg signal, input clock);
always @ (negedge clock)
 begin
    signal = 1'b1;
    #20 signal = 1'b0;
    #20 signal = 1'b1;
    #20 signal = 1'b0;
 end
endmodule // pulse4

// ---------------------------
// -- main testbench
// ---------------------------
module Guia_0902;
wire clock;
clock clk (clock); // Instancia o gerador de clock

wire p1, p2, p3, p4; // Sinais de saída dos pulsos

// Instancia os geradores de pulso com diferentes períodos
pulse1 pls1 (p1, clock);
pulse2 pls2 (p2, clock);
pulse3 pls3 (p3, clock);
pulse4 pls4 (p4, clock);

initial begin
    $dumpfile("Guia_0902.vcd"); // Arquivo de saída para visualização no GTKWave
    $dumpvars(1, clock, p1, p2, p3, p4); // Monitoramento dos sinais
    #480 $finish; // Termina a simulação após 480 unidades de tempo
end
endmodule // Guia_0902
