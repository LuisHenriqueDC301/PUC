// ---------------------------
// -- clock generator
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