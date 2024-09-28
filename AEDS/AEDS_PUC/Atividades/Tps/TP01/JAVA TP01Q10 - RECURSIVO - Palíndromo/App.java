import java.util.Scanner; 
public class App {
    public static void main(String[] args) throws Exception {
        String palavra = "", fim = "FIM";
        Scanner entrada = new Scanner(System.in);
        palavra = entrada.nextLine(); 
        while(!compare(palavra, fim)){
            if(Palindromo(palavra, 0, tamanho(palavra)-1)){
                System.out.println("SIM");
            }else{
                System.out.println("NAO");
            }
            palavra = entrada.nextLine();
        }

        

    }
    public static Boolean Palindromo(String palavra,int i, int j){
        return (i >= j) || (palavra.charAt(i) == palavra.charAt(j) && Palindromo(palavra, i + 1, j - 1));
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
