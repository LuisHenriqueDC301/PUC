module RAM_4x8 (
    input wire clk,
    input wire rw,
    input wire clr,
    input wire [1:0] addr,
    input wire [7:0] data_in,
    output wire [7:0] data_out
);
    wire [7:0] data_out0, data_out1, data_out2, data_out3;

    RAM_2x8 bank0 (clk, rw && ~addr[1], clr, addr[0], data_in, data_out0);
    RAM_2x8 bank1 (clk, rw && addr[1], clr, addr[0], data_in, data_out1);

    assign data_out = addr[1] ? (addr[0] ? data_out3 : data_out2) : (addr[0] ? data_out1 : data_out0);
endmodule
