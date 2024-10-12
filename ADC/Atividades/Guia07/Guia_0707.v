module comparador_magnitude (
    output wire resultado,   // Saída: 1 se A > B ou A < B, conforme seleção
    input wire [2:0] A,      // Entrada A (3 bits)
    input wire [2:0] B,      // Entrada B (3 bits)
    input wire sel           // Chave de seleção (0-menor; 1-maior)
);

// Comparação bit a bit com portas básicas
wire a2_maior_b2, a1_maior_b1, a0_maior_b0;
wire a2_menor_b2, a1_menor_b1, a0_menor_b0;

// A > B comparações
and A2_MAIOR_B2 (a2_maior_b2, A[2], ~B[2]);
and A1_MAIOR_B1 (a1_maior_b1, A[1], ~B[1], ~A[2], ~B[2]);
and A0_MAIOR_B0 (a0_maior_b0, A[0], ~B[0], ~A[2], ~B[2], ~A[1], ~B[1]);

// A < B comparações
and A2_MENOR_B2 (a2_menor_b2, ~A[2], B[2]);
and A1_MENOR_B1 (a1_menor_b1, ~A[1], B[1], ~A[2], ~B[2]);
and A0_MENOR_B0 (a0_menor_b0, ~A[0], B[0], ~A[2], ~B[2], ~A[1], ~B[1]);

// Verificar se A > B ou A < B
or OR_MAIOR (maior, a2_maior_b2, a1_maior_b1, a0_maior_b0);
or OR_MENOR (menor, a2_menor_b2, a1_menor_b1, a0_menor_b0);

// Seleção entre maior ou menor
wire sel_not;
not NOT1 (sel_not, sel);
and AND_MENOR_SEL (resultado_menor, menor, sel_not);
and AND_MAIOR_SEL (resultado_maior, maior, sel);
or OR_FINAL (resultado, resultado_menor, resultado_maior);

endmodule

// Módulo de teste para o comparador de magnitude
module test_comparador_magnitude;
reg [2:0] x;
reg [2:0] y;
reg sel;
wire z;

comparador_magnitude COMP_MAG_MOD (z, x, y, sel);

initial begin
    $display("Comparador de Magnitude (A < B ou A > B)");
    $display("A    B    sel   resultado");

    // Testes
    x = 3'b000; y = 3'b001; sel = 1'b0;  // A < B
    #1 $monitor("%3b  %3b   %1b    %1b", x, y, sel, z);
    
    #1 sel = 1'b1;  // A > B
    #1 x = 3'b011; y = 3'b001; sel = 1'b0;  // A > B (selecionando menor)
    #1 sel = 1'b1;  // A > B (selecionando maior)
end
endmodule
