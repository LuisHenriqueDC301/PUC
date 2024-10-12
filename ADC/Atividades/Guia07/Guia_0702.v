module lu_or_nor (
    output wire Y,  // Saída selecionável
    input wire A,   // Entrada A
    input wire B,   // Entrada B
    input wire sel  // Chave de seleção (0-NOR, 1-OR)
);
// Definir saídas intermediárias
wire or_result;
wire nor_result;

// Descrever por portas
or OR1 (or_result, A, B);      // Operação OR
nor NOR1 (nor_result, A, B);   // Operação NOR

// Seleção da saída
and AND1 (Y_nor, nor_result, ~sel);   // Se sel for 0, NOR
and AND2 (Y_or, or_result, sel);      // Se sel for 1, OR
or OR2 (Y, Y_nor, Y_or);              // Combina as duas saídas

endmodule

// Módulo de teste
module test_lu_or_nor;
reg x;
reg y;
reg sel;
wire z;

lu_or_nor LU_MOD (z, x, y, sel);

initial begin
    $display("Teste da LU OR/NOR");
    $display("x y sel z");
    
    // Testes
    x = 1'b0; y = 1'b1; sel = 1'b0; // NOR
    #1 $monitor("%4b %4b %4b %4b", x, y, sel, z);
    #1 sel = 1'b1; // OR
    
    #1 x = 1'b1; y = 1'b1; sel = 1'b0; // NOR
    #1 sel = 1'b1; // OR
end
endmodule
