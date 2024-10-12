// -------------------------
// Guia_0803 - Comparador de Igualdade para 5 bits
// Nome: xxx yyy zzz
// Matricula: 999999
// -------------------------

module equalityComparator(output result,  // Resultado da comparação
                          input [4:0] a, // Primeiro operando (5 bits)
                          input [4:0] b  // Segundo operando (5 bits)
                          );

wire [4:0] xnorResult; // Resultados da operação XNOR bit a bit

// Comparar cada bit com XNOR (a ~^ b)
xnor XNOR0 (xnorResult[0], a[0], b[0]);
xnor XNOR1 (xnorResult[1], a[1], b[1]);
xnor XNOR2 (xnorResult[2], a[2], b[2]);
xnor XNOR3 (xnorResult[3], a[3], b[3]);
xnor XNOR4 (xnorResult[4], a[4], b[4]);

// O resultado final será 1 se todos os bits forem iguais (XNOR = 1 para todos)
and AND1 (result, xnorResult[0], xnorResult[1], xnorResult[2], xnorResult[3], xnorResult[4]);

endmodule // equalityComparator

// -------------------------
// Testar o Comparador de Igualdade
// -------------------------
module test_equalityComparator;

reg [4:0] x, y;       // Entradas para o comparador
wire result;          // Resultado da comparação

// Instanciar o comparador de igualdade
equalityComparator EC(result, x, y);

initial begin
  $display("Guia_0803 - xxx yyy zzz - 999999");
  $display("Testando o Comparador de Igualdade (5 bits)");

  // Testes
  x = 5'b00000; y = 5'b00000;
  #1 $display("%b == %b -> %b", x, y, result);

  x = 5'b10101; y = 5'b10101;
  #1 $display("%b == %b -> %b", x, y, result);

  x = 5'b11111; y = 5'b00000;
  #1 $display("%b == %b -> %b", x, y, result);

  x = 5'b11010; y = 5'b11011;
  #1 $display("%b == %b -> %b", x, y, result);
end
endmodule
