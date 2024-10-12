module lu ( 
    output Y1,      // Saída AND
    output Y2,      // Saída NAND
    input A,        // Entrada A
    input B         // Entrada B
);
// Descrever por portas
and AND1 (Y1, A, B);       // Operação AND
nand NAND1 (Y2, A, B);     // Operação NAND
endmodule // lu

// Multiplexador (mux) para chave de seleção
module mux ( 
    output S,     // Saída selecionável
    input A,      // Entrada para a operação AND
    input B,      // Entrada para a operação NAND
    input sel     // Chave de seleção
);
// Definir dados locais
wire not_sel;
wire sa;
wire sb;
// Descrever por portas
not NOT1 (not_sel, sel);
and AND1 (sa, A, not_sel);
and AND2 (sb, B, sel);
or OR1 (S, sa, sb);
endmodule // mux

// Módulo de teste para a LU e o multiplexador
module test_lu;
// Definir dados
reg x;
reg y;
reg sel;
wire w1;  // Saída AND
wire w2;  // Saída NAND
wire z;   // Saída do multiplexador

// Instanciar o módulo LU
lu LU_MOD (w1, w2, x, y);
// Instanciar o multiplexador para seleção
mux MUX_MOD (z, w1, w2, sel);

// Parte principal do teste
initial begin
    $display("Teste da Unidade Lógica com Multiplexador");
    $display("x  y  sel  z (saida selecionada)");
    
    // Testar entradas
    x = 1'b0; y = 1'b1; sel = 1'b0;
    #1 $monitor("%4b %4b %4b %4b", x, y, sel, z);
    #1 sel = 1'b1;
    
    // Testar outro conjunto de entradas
    #1 x = 1'b1; y = 1'b1; sel = 1'b0;
    #1 sel = 1'b1;
end
endmodule
