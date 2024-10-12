#863566 - Luis Henrique Ferreira Costa

def dec2bin(x):
    return (bin(x))[2:]

def bin2dec(x):
    return int(x,2)

lista1= [26,53,713,213, 265]
lista2 = ["10101","11011","10010","101011","110111"]


print("Atividade 1")
for i in lista1:
    print(f"{i} = {dec2bin(i)}")

print("\nAtividade 2")
for i in lista2:
    print(f"{i} = {bin2dec(i)}")