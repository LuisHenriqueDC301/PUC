# ___________________________________
# PUC-Minas - ICEI - CRC
# Makefile for Verilog v.01 -  2024-2
# ___________________________________

PROG = hello

CC = iverilog
RC = vvp

default: $(PROG) run

$(PROG): $(PROG).v
	$(CC) -o $(PROG).vvp $(PROG).v
  
run:
	$(RC) $(PROG).vvp

clean:
	# Linux
	#rm $(PROG).vvp
	# Windows
	#del $(PROG).vvp