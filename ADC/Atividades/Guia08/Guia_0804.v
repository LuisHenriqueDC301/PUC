// -------------------------
// Guia_0804 - Comparador de Desigualdade para 5 bits
// Nome: xxx yyy zzz
// Matricula: 999999
// -------------------------

module inequalityComparator(output result,  // Resultado da comparação
                            input [4:0] a, // Primeiro operando (5 bits)
                            input [4:0] b  // Segundo operando (5 bits)
                            );

wire [4:0] xorResult; // Resultados da operação XOR bit a bit

// Comparar cada bit com XOR (a ^ b)
xor XOR0 (xorResult[0], a[0], b[0]);
xor XOR1 (xorResult[1], a[1], b[1]);
xor XOR2 (xorResult[2], a[2], b[2]);
xor XOR3 (xorResult[3], a[3], b[3]);
xor XOR4 (xorResult[4], a[4], b[4]);

// O resultado final será 1 se pelo menos um bit for diferente (XOR = 1 para qualquer bit)
or OR1 (result, xorResult[0], xorResult[1], xorResult[2], xorResult[3], xorResult[4]);

endmodule // inequalityComparator

// -------------------------
// Testar o Comparador de Desigualdade
// -------------------------
module test_inequalityComparator;

reg [4:0] x, y;       // Entradas para o comparador
wire result;          // Resultado da comparação

// Instanciar o comparador de desigualdade
inequalityComparator IC(result, x, y);

initial begin
  $display("Guia_0804 - xxx yyy zzz - 999999");
  $display("Testando o Comparador de Desigualdade (5 bits)");

  // Testes
  x = 5'b00000; y = 5'b00000;
  #1 $display("%b != %b -> %b", x, y, result);

  x = 5'b10101; y = 5'b10101;
  #1 $display("%b != %b -> %b", x, y, result);

  x = 5'b11111; y = 5'b00000;
  #1 $display("%b != %b -> %b", x, y, result);

  x = 5'b11010; y = 5'b11011;
  #1 $display("%b != %b -> %b", x, y, result);
end
endmodule
