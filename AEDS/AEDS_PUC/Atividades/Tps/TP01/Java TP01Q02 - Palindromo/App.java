import java.util.Scanner; 
public class App {
    public static void main(String[] args) throws Exception {
        String palavra = "", fim = "FIM";
        Scanner entrada = new Scanner(System.in);
        palavra = entrada.nextLine(); 
        while(!compare(palavra, fim)){
            Palindromo(palavra);
            palavra = entrada.nextLine();
        }

        

    }

    public static void Palindromo(String palavra){
        int i = 0;
        String  resposta = "SIM";
        int j = tamanho(palavra) - 1;
        
        while(i<j){
            if(palavra.charAt(i) != palavra.charAt(j)){
                resposta = "NAO";
                i = j;
            }
            i++; j--;
        }   
  
        System.out.println(resposta);
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
