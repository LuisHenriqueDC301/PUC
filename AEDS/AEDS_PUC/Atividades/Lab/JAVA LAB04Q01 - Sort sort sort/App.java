import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int n = entrada.nextInt();
        int modulo = entrada.nextInt();
        int[] lista = new int[0];

        while ((n != 0) || (modulo != 0)) {
            lista = new int[n];
            for (int i = 0; i < n; i++) {
                lista[i] = entrada.nextInt();
            }
            ordenacaoPorModulo(lista, n, modulo);
            n = entrada.nextInt();
            modulo = entrada.nextInt();
        }
        System.out.println("0 0");

    }

    public static int retornaModulo(int n, int m) {
        // Ajuste para o módulo de números negativos, conforme a regra da linguagem C
        int modulo = n % m;
        return (modulo < 0) ? modulo + m : modulo;
    }
    
    public static void ordenacaoPorModulo(int[] lista, int n, int modulo) {
        for (int i = 0; i < (n - 1); i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                int modMenor = retornaModulo(lista[menor], modulo);
                int modJ = retornaModulo(lista[j], modulo);
    
                // Compara os valores do módulo
                if (modJ < modMenor) {
                    menor = j;
                } else if (modJ == modMenor) {
                    // Se os módulos são iguais, aplicamos as regras para ímpares e pares
                    boolean isMenorImpar = lista[menor] % 2 != 0;
                    boolean isJImpar = lista[j] % 2 != 0;
    
                    if (isMenorImpar && isJImpar) {
                        // Se ambos são ímpares, o maior ímpar vem primeiro
                        if (lista[menor] < lista[j]) {
                            menor = j;
                        }
                    } else if (!isMenorImpar && !isJImpar) {
                        // Se ambos são pares, o menor par vem primeiro
                        if (lista[menor] > lista[j]) {
                            menor = j;
                        }
                    } else if (isJImpar) {
                        // Ímpares precedem pares
                        menor = j;
                    }
                }
            }
            // Troca os elementos
            int temp = lista[i];
            lista[i] = lista[menor];
            lista[menor] = temp;
        }
    
        // Exibe a lista ordenada
        System.out.println(n + " " + modulo);
        for (int i = 0; i < n; i++) {
            System.out.println(lista[i]);
        }
    }
    
    

    public static boolean compare(String palavra1, String palavra2) {
        boolean result = true;
        int n = palavra1.length();
        if (palavra1.length() != palavra2.length()) {
            result = false;
        } else {
            for (int i = 0; i < palavra1.length(); i++) {
                if (palavra1.charAt(i) != palavra2.charAt(i)) {
                    result = false;
                    break; // Parar o loop assim que encontrar a diferença
                }
            }
        }
        return result; // Retornar o resultado
    }
}
