import java.lang.reflect.Array;
import java.util.*;

public class App {


    public static void main(String[] args) throws Exception{
        int n = -1, a;
        String s1="";
        //ArrayList<Integer> lista = new ArrayList<Integer>();
        int[] lista = new int[3];

        Scanner entrada = new Scanner(System.in);

        n = entrada.nextInt();
        while(n != 0){
            for(int i = 0; i < n; i++){ 
                a = entrada.nextInt();
                lista[i] = a;
            }
        
            s1 = entrada.nextLine();
            s1 = s1.replace("A", Integer.toString(lista[0]) );
            s1 = s1.replace("B", Integer.toString(lista[1]) );
            s1 = s1.replace("C", Integer.toString(lista[2]) );
            s1 = s1.replace("and", "a" );
            s1 = s1.replace("not", "n" );
            s1 = s1.replace("or", "o" );
            s1 = s1.replace(" ", "" );
            s1 = s1.replace(",", "" );
            algebra(s1);

            n = entrada.nextInt();
        }
        
    }
    public static String replace(String palavra, char s1, char s2) {
        StringBuilder resultado = new StringBuilder(palavra);
    
        for (int i = 0; i < palavra.length(); i++) {
            if (palavra.charAt(i) == s1) {
                resultado.setCharAt(i, s2);
            }
        }
    
        return resultado.toString();
    }
    
    public static void algebra(String s1){
        Stack<Character> pP = new Stack<Character>();
        for(int i = 0; i < s1.length(); i++){
            char letra = s1.charAt(i);
            if(letra != ')'){
                pP.push(letra);
            }else{
                String sAux = new String();
                char oP;
                while (letra != '(') {
                    sAux += letra;
                    letra = pP.pop();
                }
                oP = pP.pop();
                boolean result = true;
                switch (oP) {
                    case 'a':
                        for(int a = 0; a < sAux.length(); a++){
                            if(sAux.charAt(a) == '0'){
                                result = false;
                            }
                        }
                        if(result == false){
                            pP.push('0');
                        }else{
                            pP.push('1');
                        }
                        break;
                        case 'o':
                        for(int a = 0; a < sAux.length(); a++){
                            if(sAux.charAt(a) == '1'){
                                result = false;
                            }
                        }
                        if(result != false){
                            pP.push('0');
                        }else{
                            pP.push('1');
                        }
                        break;
                        case 'n':
                        for(int a = 0; a < sAux.length(); a++){
                            if(sAux.charAt(a) == '0'){
                                result = false;
                            }
                        }
                        if(result == false){
                            pP.push('1');
                        }else{
                            pP.push('0');
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println(pP.pop());
    }
    
    public static boolean compare(String palavra1, String palavra2){
        boolean result = true;
        int n = palavra1.length();
        if(palavra1.length() != palavra2.length()){
            result = false;
        }else{
            for(int i = 0; i < palavra1.length(); i++){
                if(palavra1.charAt(i) != palavra2.charAt(i)){
                    result = false;
                    i = palavra1.length();
                }
            }
        }

        return result;
    }
}

