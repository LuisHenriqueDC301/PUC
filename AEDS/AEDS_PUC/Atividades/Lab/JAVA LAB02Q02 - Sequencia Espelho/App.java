import java.util.Scanner;
public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inicio, fim;
        while (sc.hasNext()) {
            inicio = sc.nextInt();
            fim = sc.nextInt();
            String pala = sequencia(inicio, fim);
            String resultado = espelho(pala);
            System.out.println(resultado);
        }    
    }
    public static String sequencia(int menor, int maior){
        String s2 = "";
        for(int i = menor; i<=maior; i++){
            s2 += i;
        }
        return s2;
    } 
    public static String espelho(String s1){
        for(int i = s1.length()-1; i >= 0; i--){
            s1 += s1.charAt(i);
        }
        return s1;
    }
}
