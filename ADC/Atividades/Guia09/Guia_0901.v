// ---------------------------
// -- test clock generator (2)
// ---------------------------
module clock (output reg clk);
initial
 begin
    clk = 1'b0;
 end
always
 begin
    #12 clk = ~clk; // Gera um clock com período de 24 unidades de tempo
 end
endmodule

// ---------------------------
// -- pulse generator
// ---------------------------
module pulse (output reg signal, input clock);
always @ (posedge clock)
 begin
    signal = 1'b1;
    #3 signal = 1'b0;
    #3 signal = 1'b1;
    #3 signal = 1'b0;
 end
endmodule // pulse

// ---------------------------
// -- trigger generator
// ---------------------------
module trigger (output reg signal, input on, input clock);
always @ (posedge clock)
 begin
    if (on) begin
        #60 signal = 1'b1;
        #60 signal = 1'b0;
    end
 end
endmodule // trigger

// ---------------------------
// -- main testbench
// ---------------------------
module Guia_0901;
wire clock;
clock clk (clock); // Instancia o gerador de clock

reg p; // Sinal de controle do trigger
wire p1, t1; // Sinais de saída do pulse e trigger

pulse pulse1 (p1, clock); // Instancia o gerador de pulso
trigger trigger1 (t1, p, clock); // Instancia o gerador de trigger

initial begin
    p = 1'b0; // Inicializa o sinal de controle p como 0
end

initial begin
    $dumpfile("Guia_0901.vcd"); // Arquivo de saída para visualização no GTKWave
    $dumpvars(1, clock, p1, p, t1); // Variáveis que serão monitoradas
    #060 p = 1'b1; // Muda o estado de p em diferentes momentos
    #120 p = 1'b0;
    #180 p = 1'b1;
    #240 p = 1'b0;
    #300 p = 1'b1;
    #360 p = 1'b0;
    #376 $finish; // Termina a simulação
end
endmodule // Guia_0901
