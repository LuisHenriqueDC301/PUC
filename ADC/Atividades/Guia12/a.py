# Gerar os códigos Verilog de acordo com as especificações dadas para cada módulo solicitado.
# Criar arquivos Verilog com as máquinas de estado conforme as descrições fornecidas.

# Conteúdo dos módulos Verilog para os arquivos Guia_1101.v, Guia_1102.v, Guia_1103.v, Guia_1104.v, Guia_1105.v

verilog_code_1101 = """
// Guia_1101.v - FSM para reconhecer uma vez a sequência 1001 e parar.
module Guia_1101 (output reg y, input x, clk, reset);
  parameter START=3'b000, ID1=3'b001, ID10=3'b010, ID100=3'b011, ID1001=3'b100;
  reg [2:0] state, next_state;

  always @(posedge clk or posedge reset) begin
    if (reset) state <= START;
    else state <= next_state;
  end

  always @(state or x) begin
    y = 0;
    case (state)
      START: if (x) next_state = ID1; else next_state = START;
      ID1: if (!x) next_state = ID10; else next_state = ID1;
      ID10: if (x) next_state = ID100; else next_state = START;
      ID100: if (!x) next_state = ID1001; else next_state = ID1;
      ID1001: begin y = 1; next_state = ID1001; end // Stop after one match
      default: next_state = START;
    endcase
  end
endmodule
"""

verilog_code_1102 = """
// Guia_1102.v - Mealy FSM para reconhecer uma vez a sequência 1001 sem interseção.
module Guia_1102 (output reg y, input x, clk, reset);
  parameter START=3'b000, ID1=3'b001, ID10=3'b010, ID100=3'b011;
  reg [2:0] state, next_state;

  always @(posedge clk or posedge reset) begin
    if (reset) state <= START;
    else state <= next_state;
  end

  always @(state or x) begin
    y = 0;
    case (state)
      START: if (x) next_state = ID1; else next_state = START;
      ID1: if (!x) next_state = ID10; else next_state = ID1;
      ID10: if (x) next_state = ID100; else next_state = START;
      ID100: if (!x) begin y = 1; next_state = START; end
      default: next_state = START;
    endcase
  end
endmodule
"""

verilog_code_1103 = """
// Guia_1103.v - Moore FSM para reconhecer sequência 1001 com interseção.
module Guia_1103 (output reg y, input x, clk, reset);
  parameter START=3'b000, ID1=3'b001, ID10=3'b010, ID100=3'b011, ID1001=3'b100;
  reg [2:0] state, next_state;

  always @(posedge clk or posedge reset) begin
    if (reset) state <= START;
    else state <= next_state;
  end

  always @(state or x) begin
    y = 0;
    case (state)
      START: if (x) next_state = ID1; else next_state = START;
      ID1: if (!x) next_state = ID10; else next_state = ID1;
      ID10: if (x) next_state = ID100; else next_state = START;
      ID100: if (!x) next_state = ID1001; else next_state = ID1;
      ID1001: begin y = 1; next_state = ID1; end // Allows intersecion
      default: next_state = START;
    endcase
  end
endmodule
"""

verilog_code_1104 = """
// Guia_1104.v - FSM para reconhecer qualquer sequência terminando com três zeros (x000).
module Guia_1104 (output reg y, input x, clk, reset);
  parameter START=3'b000, ID0=3'b001, ID00=3'b010, ID000=3'b011;
  reg [2:0] state, next_state;

  always @(posedge clk or posedge reset) begin
    if (reset) state <= START;
    else state <= next_state;
  end

  always @(state or x) begin
    y = 0;
    case (state)
      START: if (!x) next_state = ID0; else next_state = START;
      ID0: if (!x) next_state = ID00; else next_state = START;
      ID00: if (!x) next_state = ID000; else next_state = START;
      ID000: begin y = 1; next_state = ID0; end
      default: next_state = START;
    endcase
  end
endmodule
"""

verilog_code_1105 = """
// Guia_1105.v - FSM para reconhecer sequência alternada de três dígitos (010 ou 101).
module Guia_1105 (output reg y, input x, clk, reset);
  parameter START=2'b00, ID1=2'b01, ID10=2'b10, ID101=2'b11;
  reg [1:0] state, next_state;

  always @(posedge clk or posedge reset) begin
    if (reset) state <= START;
    else state <= next_state;
  end

  always @(state or x) begin
    y = 0;
    case (state)
      START: if (x) next_state = ID1; else next_state = START;
      ID1: if (!x) next_state = ID10; else next_state = ID1;
      ID10: if (x) begin y = 1; next_state = START; end else next_state = START;
      default: next_state = START;
    endcase
  end
endmodule
"""

# Salvando os arquivos gerados no diretório sandbox.
with open("Guia_1101.v", "w") as f:
    f.write(verilog_code_1101)
with open("Guia_1102.v", "w") as f:
    f.write(verilog_code_1102)
with open("Guia_1103.v", "w") as f:
    f.write(verilog_code_1103)
with open("Guia_1104.v", "w") as f:
    f.write(verilog_code_1104)
with open("Guia_1105.v", "w") as f:
    f.write(verilog_code_1105)

"Guia_1101.v", "Guia_1102.v", "Guia_1103.v", "Guia_1104.v", "Guia_1105.v"
