module RAM_8x8 (
    input wire clk,
    input wire rw,
    input wire clr,
    input wire [2:0] addr,
    input wire [7:0] data_in,
    output wire [7:0] data_out
);
    wire [7:0] data_out0, data_out1, data_out2, data_out3, data_out4, data_out5, data_out6, data_out7;

    RAM_4x8 bank0 (clk, rw && ~addr[2], clr, addr[1:0], data_in, data_out0);
    RAM_4x8 bank1 (clk, rw && addr[2], clr, addr[1:0], data_in, data_out1);

    assign data_out = addr[2] ? (addr[1:0] ? data_out7 : data_out6) : (addr[1:0] ? data_out5 : data_out4);
endmodule
