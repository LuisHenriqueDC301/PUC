module lu_xor_xnor_or_nor (
    output wire Y,  // Saída selecionável
    input wire A,   // Entrada A
    input wire B,   // Entrada B
    input wire [1:0] sel  // Seleção de operação (00: NOR, 01: OR, 10: XOR, 11: XNOR)
);
// Definir saídas intermediárias
wire nor_result, or_result, xor_result, xnor_result;

// Descrever por portas
nor NOR1 (nor_result, A, B);      // Operação NOR
or OR1 (or_result, A, B);         // Operação OR
xor XOR1 (xor_result, A, B);      // Operação XOR
xnor XNOR1 (xnor_result, A, B);   // Operação XNOR

// Seleção da operação
and AND1 (Y_nor, ~sel[1] & ~sel[0], nor_result);
and AND2 (Y_or, ~sel[1] & sel[0], or_result);
and AND3 (Y_xor, sel[1] & ~sel[0], xor_result);
and AND4 (Y_xnor, sel[1] & sel[0], xnor_result);
or OR2 (Y, Y_nor, Y_or, Y_xor, Y_xnor);

endmodule

// Módulo de teste
module test_lu_xor_xnor_or_nor;
reg x;
reg y;
reg [1:0] sel;
wire z;

lu_xor_xnor_or_nor LU_MOD (z, x, y, sel);

initial begin
    $display("Teste da LU XOR/XNOR OR/NOR");
    $display("x y sel z");
    
    // Testes
    x = 1'b0; y = 1'b1; sel = 2'b00; // NOR
    #1 $monitor("%4b %4b %2b %4b", x, y, sel, z);
    #1 sel = 2'b01; // OR
    #1 sel = 2'b10; // XOR
    #1 sel = 2'b11; // XNOR
end
endmodule
