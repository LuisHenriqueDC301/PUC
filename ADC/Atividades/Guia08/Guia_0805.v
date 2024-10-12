// -------------------------
// Guia_0805 - Complemento de 2 para 6 bits
// Nome: xxx yyy zzz
// Matricula: 999999
// -------------------------

module complemento2(output [5:0] result,   // Complemento de 2
                    input [5:0] a);       // Operando (6 bits)

wire [5:0] comp1;  // Complemento de 1
wire carry;        // Vai-um do somador

// Complemento de 1 (inversão dos bits)
not NOT0 (comp1[0], a[0]);
not NOT1 (comp1[1], a[1]);
not NOT2 (comp1[2], a[2]);
not NOT3 (comp1[3], a[3]);
not NOT4 (comp1[4], a[4]);
not NOT5 (comp1[5], a[5]);

// Somar 1 ao complemento de 1
fullAdder FA0 (carry, result[0], comp1[0], 1'b1, 1'b0);  // Adiciona 1 ao primeiro bit
fullAdder FA1 (carry, result[1], comp1[1], 1'b0, carry); // Propaga o vai-um
fullAdder FA2 (carry, result[2], comp1[2], 1'b0, carry);
fullAdder FA3 (carry, result[3], comp1[3], 1'b0, carry);
fullAdder FA4 (carry, result[4], comp1[4], 1'b0, carry);
fullAdder FA5 ( , result[5], comp1[5], 1'b0, carry);     // Último bit

endmodule // complemento2

// -------------------------
// Testar o Complemento de 2
// -------------------------
module test_complemento2;

reg [5:0] x;        // Operando de 6 bits
wire [5:0] result;  // Resultado do complemento de 2

// Instanciar o complemento de 2
complemento2 C2(result, x);

initial begin
  $display("Guia_0805 - xxx yyy zzz - 999999");
  $display("Testando o Complemento de 2 (6 bits)");

  // Testes
  x = 6'b000001;
  #1 $display("Complemento de 2 de %b = %b", x, result);

  x = 6'b100000;
  #1 $display("Complemento de 2 de %b = %b", x, result);

  x = 6'b111111;
  #1 $display("Complemento de 2 de %b = %b", x, result);
end
endmodule
