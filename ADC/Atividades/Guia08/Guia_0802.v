
module halfSubtractor(output borrowOut,  // "vem-um" de saída
                      output diff,       // Diferença (bit de saída)
                      input a,           // Minuendo
                      input b);          // Subtraendo
                      
// Diferença: a XOR b
xor XOR1 (diff, a, b);

// "vem-um": ~a AND b
and AND1 (borrowOut, ~a, b);

endmodule // halfSubtractor

// -------------------------
// Subtrator Completo (Full Subtractor)
// -------------------------
module fullSubtractor(output borrowOut,  // "vem-um" de saída
                      output diff,       // Diferença (bit de saída)
                      input a,           // Minuendo
                      input b,           // Subtraendo
                      input borrowIn);   // "vem-um" de entrada

wire d1, b1, b2; // Sinais intermediários

// Meias-diferenças para os bits de entrada
halfSubtractor HS1(b1, d1, a, b);        // Primeira meia-diferença
halfSubtractor HS2(b2, diff, d1, borrowIn); // Segunda meia-diferença

// "Vem-um" final: combina os "vem-um" intermediários
or OR1 (borrowOut, b1, b2);

endmodule // fullSubtractor

// -------------------------
// Unidade Aritmética (AU) com Subtração Completa para 5 bits
// -------------------------
module AU_5bits(output [4:0] result,      // Resultado da subtração (5 bits)
                output finalBorrow,       // "Vem-um" final
                input [4:0] a,            // Minuendo (5 bits)
                input [4:0] b,            // Subtraendo (5 bits)
                input borrowIn);          // "Vem-um" inicial arbitrário

wire [3:0] borrow; // Fios para os "vem-um" intermediários

// Subtrator para os 4 bits menos significativos (amplitude)
fullSubtractor FS0(borrow[0], result[0], a[0], b[0], borrowIn);
fullSubtractor FS1(borrow[1], result[1], a[1], b[1], borrow[0]);
fullSubtractor FS2(borrow[2], result[2], a[2], b[2], borrow[1]);
fullSubtractor FS3(borrow[3], result[3], a[3], b[3], borrow[2]);

// Subtração para o bit de sinal (mais significativo)
fullSubtractor FS4(finalBorrow, result[4], a[4], b[4], borrow[3]);

endmodule // AU_5bits

// -------------------------
// Testar Unidade Aritmética com Subtrator Completo
// -------------------------
module test_AU_5bits;
// ------------------------- definir dados
reg [4:0] x;           // 5 bits para o minuendo (a)
reg [4:0] y;           // 5 bits para o subtraendo (b)
reg vemUmInicial;      // "Vem-um" inicial arbitrário
wire [4:0] result;     // Resultado da subtração
wire vemUmFinal;       // "Vem-um" final

// Instanciar a AU
AU_5bits AU(result, vemUmFinal, x, y, vemUmInicial);

// ------------------------- parte principal
initial begin
  $display("Guia_0802 - xxx yyy zzz - 999999");
  $display("Testando a Unidade Aritmética (AU) com Subtrator Completo");

  // Teste 1: Subtração de dois números positivos
  x = 5'b01001; y = 5'b00110; vemUmInicial = 1'b0;
  #1 $display("%b - %b = %b (vem-um final: %b)", x, y, result, vemUmFinal);

  // Teste 2: Subtração com número negativo (complemento de 2)
  x = 5'b11001; y = 5'b00110; vemUmInicial = 1'b0;
  #1 $display("%b - %b = %b (vem-um final: %b)", x, y, result, vemUmFinal);

  // Teste 3: Subtração com "vem-um" inicial arbitrário
  x = 5'b01111; y = 5'b01010; vemUmInicial = 1'b1;
  #1 $display("%b - %b = %b (vem-um final: %b)", x, y, result, vemUmFinal);

  // Outros testes podem ser adicionados aqui
end
endmodule
