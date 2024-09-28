import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        String palavra = "", fim = "FIM";
        Scanner entrada = new Scanner(System.in);
        
        palavra = entrada.nextLine(); 
        while (!compare(palavra, fim)) {
            if (verificaParen(palavra)) {
                System.out.println("correto");
            } else {
                System.out.println("incorreto");
            }
            palavra = entrada.nextLine();
        }
        
        entrada.close(); // Fechar o Scanner
    }

    public static boolean verificaParen(String s) {
        boolean result = true;
        int abre = 0;
        int fecha = 0;
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if(s.charAt(0) == ')'){
                result = false;
            }
            if (temp == '(') {
                abre++;
            }
            if (temp == ')') {
                fecha++;
            }
        }
        if (abre != fecha) {
            result = false;
        }

        return result;
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
