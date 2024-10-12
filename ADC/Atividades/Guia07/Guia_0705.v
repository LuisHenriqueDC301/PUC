module lu_7x1 (
    output wire Y,         // Saída selecionável
    input wire A,          // Entrada A
    input wire B,          // Entrada B
    input wire sel_neg,    // Seleção para negação (nega A ou B)
    input wire [2:0] sel   // Chave de seleção de 3 bits
);
// Definir saídas intermediárias
wire not_a, and_result, nand_result, or_result, nor_result, xor_result, xnor_result;

// Descrever por portas
not NOT1 (not_a, A);               // Operação NOT
and AND1 (and_result, A, B);       // Operação AND
nand NAND1 (nand_result, A, B);    // Operação NAND
or OR1 (or_result, A, B);          // Operação OR
nor NOR1 (nor_result, A, B);       // Operação NOR
xor XOR1 (xor_result, A, B);       // Operação XOR
xnor XNOR1 (xnor_result, A, B);    // Operação XNOR

// Seleção das operações com base na chave de seleção
wire Y_not, Y_and, Y_nand, Y_or, Y_nor, Y_xor, Y_xnor;
and AND2 (Y_not, ~sel[2] & ~sel[1] & ~sel[0], not_a);
and AND3 (Y_and, ~sel[2] & ~sel[1] & sel[0], and_result);
and AND4 (Y_nand, ~sel[2] & sel[1] & ~sel[0], nand_result);
and AND5 (Y_or, ~sel[2] & sel[1] & sel[0], or_result);
and AND6 (Y_nor, sel[2] & ~sel[1] & ~sel[0], nor_result);
and AND7 (Y_xor, sel[2] & ~sel[1] & sel[0], xor_result);
and AND8 (Y_xnor, sel[2] & sel[1] & ~sel[0], xnor_result);

// Combinação final das saídas
or OR2 (Y, Y_not, Y_and, Y_nand, Y_or, Y_nor, Y_xor, Y_xnor);

endmodule

// Módulo de teste para o LU 7x1
module test_lu_7x1;
reg x;
reg y;
reg sel_neg;
reg [2:0] sel;
wire z;

lu_7x1 LU_MOD (z, x, y, sel_neg, sel);

initial begin
    $display("Teste da LU NOT/AND/NAND/OR/NOR/XOR/XNOR");
    $display("x y sel_neg sel z");
    
    // Testes
    x = 1'b0; y = 1'b1; sel = 3'b000; // NOT
    #1 $monitor("%4b %4b %4b %3b %4b", x, y, sel_neg, sel, z);
    #1 sel = 3'b001; // AND
    #1 sel = 3'b010; // NAND
    #1 sel = 3'b011; // OR
    #1 sel = 3'b100; // NOR
    #1 sel = 3'b101; // XOR
    #1 sel = 3'b110; // XNOR
end
endmodule
