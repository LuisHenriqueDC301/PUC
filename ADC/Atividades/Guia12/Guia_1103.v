
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
