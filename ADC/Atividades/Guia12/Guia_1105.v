
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
