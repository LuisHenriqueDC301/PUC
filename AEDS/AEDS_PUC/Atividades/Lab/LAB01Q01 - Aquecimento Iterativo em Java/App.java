import java.util.Scanner;
public class App{
    public static void main(String[] args) throws Exception{
         Scanner ler =  new Scanner(System.in);
         String palavra = ler.nextLine();
         while (!compare(palavra,"FIM")) {
            System.out.println(ContMaisculo(palavra));
            palavra = ler.nextLine();
         }
         
    }
    public static int ContMaisculo(String Palavra){
        int contador = 0;
        for(int i = 0; i < tamanho(Palavra); i++){
            if(Palavra.charAt(i) >= 'A' && Palavra.charAt(i)  <= 'Z'){
                contador ++;
            }
        }
        return contador;
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