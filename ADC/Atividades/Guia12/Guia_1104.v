
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
