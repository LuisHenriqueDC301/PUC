// -------------------------
// Guia_0800 - FULL ADDER com 5 bits (bit de sinal)
// Nome: xxx yyy zzz
// Matricula: 999999
// -------------------------
// -------------------------
// half adder
// -------------------------
module halfAdder (output carryOut,   // carry-out
                  output sum,        // sum
                  input a, 
                  input b);
// descrever por portas
xor XOR1 (sum, a, b);  // soma
and AND1 (carryOut, a, b);  // carry
endmodule // halfAdder

// -------------------------
// full adder
// -------------------------
module fullAdder (output carryOut,
                  output sum,
                  input a, 
                  input b, 
                  input carryIn);
// descrever por portas e/ou modulos
wire c1, c2, s1; // sinais intermediários

halfAdder HA1 (c1, s1, a, b);     // primeiro meio somador
halfAdder HA2 (c2, sum, s1, carryIn);  // segundo meio somador
or OR1 (carryOut, c1, c2);        // calculo do carry-out
endmodule // fullAdder

// -------------------------
// test fullAdder com 5 bits
// -------------------------
module test_fullAdder;
// ------------------------- definir dados
reg [4:0] x;           // 5 bits para o valor x (primeiro bit é de sinal)
reg [4:0] y;           // 5 bits para o valor y (primeiro bit é de sinal)
wire [3:0] carry;      // “vai-um” intermediário (carry) entre os bits
wire [4:0] soma;       // soma final (5 bits para incluir carry-out e bit de sinal)

// Somadores para os 4 bits menos significativos (sem contar o bit de sinal)
fullAdder FA0 (carry[0], soma[0], x[0], y[0], 1'b0);
fullAdder FA1 (carry[1], soma[1], x[1], y[1], carry[0]);
fullAdder FA2 (carry[2], soma[2], x[2], y[2], carry[1]);
fullAdder FA3 (carry[3], soma[3], x[3], y[3], carry[2]);

// O bit de sinal (mais significativo) é somado sem considerar o carry-in
// Para o bit de sinal, você pode decidir ignorar o carry-out ou tratá-lo conforme a regra do complemento de 2
xor XOR1 (soma[4], x[4], y[4]);  // soma do bit de sinal sem carry-in

// ------------------------- parte principal
initial begin
  $display("Guia_0800 - xxx yyy zzz - 999999");
  $display("Test ALU’s full adder com bit de sinal");

  // Testar diferentes combinações de valores para x e y com bit de sinal
  x = 5'b01101; y = 5'b00110; // soma de dois números positivos
  #1 $display("%b + %b = %b", x, y, soma);

  x = 5'b10110; y = 5'b00110; // soma de um número negativo e um positivo
  #1 $display("%b + %b = %b", x, y, soma);

  x = 5'b11111; y = 5'b00001; // soma de dois números negativos
  #1 $display("%b + %b = %b", x, y, soma);

  // você pode adicionar mais testes conforme necessário
end
endmodule
