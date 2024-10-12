module comparador_igual_diferente (
    output wire resultado,  // Saída: 0 se igual, 1 se diferente
    input wire [2:0] A,     // Entrada A (3 bits)
    input wire [2:0] B,     // Entrada B (3 bits)
    input wire sel          // Chave de seleção (0-igual; 1-diferente)
);

// Fios intermediários para o comparador
wire [2:0] xor_result, xnor_result;
wire igual, diferente;

// Operações XOR e XNOR bit a bit
xor XOR0 (xor_result[0], A[0], B[0]);
xor XOR1 (xor_result[1], A[1], B[1]);
xor XOR2 (xor_result[2], A[2], B[2]);

xnor XNOR0 (xnor_result[0], A[0], B[0]);
xnor XNOR1 (xnor_result[1], A[1], B[1]);
xnor XNOR2 (xnor_result[2], A[2], B[2]);

// Verificar se todos os bits são iguais
and AND_IGUAL (igual, xnor_result[0], xnor_result[1], xnor_result[2]);

// Verificar se pelo menos um bit é diferente
or OR_DIFERENTE (diferente, xor_result[0], xor_result[1], xor_result[2]);

// Seleção entre igualdade e diferença
wire sel_not;
not NOT1 (sel_not, sel);
and AND_IGUAL_SEL (resultado_igual, igual, sel_not);
and AND_DIFERENTE_SEL (resultado_diferente, diferente, sel);
or OR_FINAL (resultado, resultado_igual, resultado_diferente);

endmodule

// Módulo de teste para o comparador
module test_comparador_igual_diferente;
reg [2:0] x;
reg [2:0] y;
reg sel;
wire z;

comparador_igual_diferente COMP_MOD (z, x, y, sel);

initial begin
    $display("Comparador de Igualdade/Diferença");
    $display("A    B    sel   resultado");

    // Testes
    x = 3'b000; y = 3'b000; sel = 1'b0;  // A igual B (Igualdade)
    #1 $monitor("%3b  %3b   %1b    %1b", x, y, sel, z);
    
    #1 sel = 1'b1;  // A igual B (Diferença)
    #1 x = 3'b001; y = 3'b010; sel = 1'b0;  // A diferente de B (Igualdade)
    #1 sel = 1'b1;  // A diferente de B (Diferença)
end
endmodule
