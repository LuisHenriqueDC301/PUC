module mux (
    output wire y,
    input wire a,
    input wire b,
    input wire c
);
    wire not_a;
    wire not_b;
    wire not_c;
    wire mux1_out;
    wire mux2_out;

    // Inverter a e b
    assign not_a = ~a;
    assign not_b = ~b;
    assign not_c = ~c;

    // Primeiro MUX: MUX(b, a, c)
    assign mux1_out = (c == 0) ? b : a;

    // Segundo MUX: MUX(NOT(a), NOT(b), c)
    assign mux2_out = (c == 0) ? not_a : not_b;

    // Terceiro MUX: MUX(mux1_out, mux2_out, NOT(c))
    assign y = (not_c == 0) ? mux1_out : mux2_out;

endmodule
module PO1;
    reg x;
    reg y;
    reg z;
    wire s;
    mux result(s, x,y,z);

    initial begin : main
        $display("Test module"); 
        $display("   x    y    z    s");
        // projetar testes do modulo
        $monitor("%4b %4b %4b %4b", x, y,z, s);
        x = 1'b0; y = 1'b0; z = 1'b0;
        #1 x = 1'b0; y = 1'b0; z = 1'b1;
        #1 x = 1'b0; y = 1'b1; z = 1'b0;
        #1 x = 1'b0; y = 1'b1; z = 1'b1;
        #1 x = 1'b1; y = 1'b0; z = 1'b0;
        #1 x = 1'b1; y = 1'b0; z = 1'b1;
        #1 x = 1'b1; y = 1'b1; z = 1'b0;
        #1 x = 1'b1; y = 1'b1; z = 1'b1;
        
    end
endmodule