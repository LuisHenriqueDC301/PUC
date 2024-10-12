module Guia_0201;
 real x = 0 ;
 real power2 = 1.0; 
 integer y = 7 ; // counter
 reg [7:0] b = 8'b11001000; // binary (only fraction part, Big Endian)
// actions
 initial
 begin : main
 $display ( "Guia_0201 - Tests" );
 $display ( "x = %f" , x );
 $display ( "b = 11.%8b", b );
 while ( y >= 0 )
 begin
 power2 = power2 / 2.0;
 if ( b[y] == 1 )
 begin
 x = x + power2;
 end
 $display ( "x = %f", x );
 y=y-1;
 end // end while
 end // main
endmodule // Guia_0201
