module RAM_1x8 (
    input wire clk,
    input wire rw,
    input wire clr,
    input wire [7:0] data_in,
    output wire [7:0] data_out
);
    wire [3:0] lower, upper;

    RAM_1x4 lower_half (clk, rw, clr, data_in[3:0], lower);
    RAM_1x4 upper_half (clk, rw, clr, data_in[7:4], upper);

    assign data_out = {upper, lower};
endmodule
