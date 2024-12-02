module RAM_2x8 (
    input wire clk,
    input wire rw,
    input wire clr,
    input wire addr,
    input wire [7:0] data_in,
    output wire [7:0] data_out
);
    wire [7:0] data_out0, data_out1;

    RAM_1x8 bank0 (clk, rw && ~addr, clr, data_in, data_out0);
    RAM_1x8 bank1 (clk, rw && addr, clr, data_in, data_out1);

    assign data_out = addr ? data_out1 : data_out0;
endmodule
