module lu_and_nand_or_nor (
    output wire Y,  // Saída selecionável
    input wire A,   // Entrada A
    input wire B,   // Entrada B
    input wire sel_group,  // Seleção de grupo (0: AND/NAND, 1: OR/NOR)
    input wire sel_op      // Seleção de operação dentro do grupo (0: NAND/NOR, 1: AND/OR)
);
// Definir saídas intermediárias
wire and_result, nand_result, or_result, nor_result;

// Descrever por portas
and AND1 (and_result, A, B);      // Operação AND
nand NAND1 (nand_result, A, B);   // Operação NAND
or OR1 (or_result, A, B);         // Operação OR
nor NOR1 (nor_result, A, B);      // Operação NOR

// Seleção de operações em cada grupo
wire Y_and_nand, Y_or_nor;
and AND2 (Y_and_nand, ~sel_op, nand_result);
and AND3 (Y_and_nand, sel_op, and_result);

and AND4 (Y_or_nor, ~sel_op, nor_result);
and AND5 (Y_or_nor, sel_op, or_result);

// Seleção final entre grupos
and AND6 (Y_group, ~sel_group, Y_and_nand);
and AND7 (Y_group, sel_group, Y_or_nor);
or OR2 (Y, Y_group);

endmodule

// Módulo de teste
module test_lu_and_nand_or_nor;
reg x;
reg y;
reg sel_group;
reg sel_op;
wire z;

lu_and_nand_or_nor LU_MOD (z, x, y, sel_group, sel_op);

initial begin
    $display("Teste da LU AND/NAND OR/NOR");
    $display("x y sel_group sel_op z");
    
    // Testes
    x = 1'b0; y = 1'b1; sel_group = 1'b0; sel_op = 1'b0; // NAND
    #1 $monitor("%4b %4b %4b %4b %4b", x, y, sel_group, sel_op, z);
    #1 sel_op = 1'b1; // AND
    #1 sel_group = 1'b1; sel_op = 1'b0; // NOR
    #1 sel_op = 1'b1; // OR
end
endmodule
