import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception{
        String palavra = "";
        Scanner entrada = new Scanner(System.in);
        palavra = entrada.nextLine();

        while(!compare(palavra,"FIM")){
            cesar(palavra,0);
            System.out.println("");
            palavra = entrada.nextLine();
        }
    }
    public static String cesar(String palavra, int i) {
        if (i > palavra.length() - 1) {
            return ""; 
        }

        
        char c = palavra.charAt(i);
        if(c >= 32 && c <= 126 ){
            char deslocado = (char) (c + 3);
            System.out.print(deslocado);
        }else{
            System.out.print(c);
        }
        
        return cesar(palavra, i + 1);
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