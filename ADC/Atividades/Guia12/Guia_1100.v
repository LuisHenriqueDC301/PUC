
// --------------------
// --- Mealy-Moore FSM
// --------------------
`include "mealy_1101.v"
`include "moore_1101.v"
module Guia_1100;
    reg clk, reset, x;
    wire m1, m2;
    mealy_1101 mealy1 ( m1, x, clk, reset );
    moore_1101 moore1 ( m2, x, clk, reset );    
// Testbench code to be added here
endmodule // Guia_1101
