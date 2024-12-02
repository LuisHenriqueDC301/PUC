module RAM_1x4 (
    input wire clk,              // Clock
    input wire rw,               // Read/Write signal: 1 for write, 0 for read
    input wire clr,              // Clear signal to reset memory
    input wire [3:0] data_in,    // Data input (4 bits)
    output reg [3:0] data_out    // Data output (4 bits)
);
    reg [3:0] memory; // 4-bit storage using JK flip-flops

    always @(posedge clk or posedge clr) begin
        if (clr) 
            memory <= 4'b0000;       // Reset memory
        else if (rw) 
            memory <= data_in;       // Write operation
    end

    always @(negedge clk) begin
        if (!rw) 
            data_out <= memory;      // Read operation
    end
endmodule
