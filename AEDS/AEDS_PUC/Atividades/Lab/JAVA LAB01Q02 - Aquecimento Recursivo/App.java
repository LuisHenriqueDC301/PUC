import java.util.Scanner;

public class App{

    public static void main(String [] args) throws Exception{
        String palavra = "", fim = "FIM";
        Scanner entrada = new Scanner(System.in);
        palavra = entrada.nextLine(); 
        while(!compare(palavra, fim)){
            System.out.println(contarLetrasMais(palavra, 0, 0));  
            palavra = entrada.nextLine();
        }

    }
    static int contarLetrasMais(String palavra, int i, int n) {
        return contarLetrasMaisRec(palavra, i, n);
    }
    
    private static int contarLetrasMaisRec(String palavra, int i, int n) {
        int resultado;
        if (i == tamanho(palavra)) {
            resultado = n;
        } else {
            if (palavra.charAt(i) >= 'A' && palavra.charAt(i) <= 'Z') {
                n++;
            }
            resultado = contarLetrasMaisRec(palavra, i + 1, n);
        }
        
        // Retorna o resultado
        return resultado;
    }
    public static int tamanho(String palavra){
        int i = 0;

        try{
            while (true) {
                palavra.charAt(i);
                i++;
            }
        }catch(StringIndexOutOfBoundsException e){
            return i;
        }
    }
    public static boolean compare(String palavra1, String palavra2){
        boolean result = true;
        int n = tamanho(palavra1);
        if(tamanho(palavra1) != tamanho(palavra2)){
            result = false;
        }else{
            for(int i = 0; i < tamanho(palavra1); i++){
                if(palavra1.charAt(i) != palavra2.charAt(i)){
                    result = false;
                    i = tamanho(palavra1);
                }
            }
        }

        return result;
    }

}