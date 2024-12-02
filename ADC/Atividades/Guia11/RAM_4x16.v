module RAM_4x16 (
    input wire clk,
    input wire rw,
    input wire clr,
    input wire [1:0] addr,
    input wire [15:0] data_in,
    output wire [15:0] data_out
);
    wire [7:0] data_out0, data_out1;

    RAM_2x8 bank0 (clk, rw && ~addr[1], clr, addr[0], data_in[7:0], data_out0);
    RAM_2x8 bank1 (clk, rw && ~addr[1], clr, addr[0], data_in[15:8], data_out1);

    assign data_out = {data_out1, data_out0};
endmodule
