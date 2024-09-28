import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception{
        String palavra = "";
        Scanner entrada = new Scanner(System.in);
        palavra = entrada.nextLine();

        while(!compare(palavra,"FIM")){
            if(isVogal(palavra, 0)){
                System.out.print("SIM "); 
            }else{
                System.out.print("NAO ");
            }
            
            if(isConsoante(palavra,0)){
                System.out.print("SIM ");
            }else{
                System.out.print("NAO ");
            }
            if(isInteger(palavra,0)){
                System.out.print("SIM ");
            }else{
                System.out.print("NAO ");
            }
            
            if(isReal(palavra,0)){
                System.out.print("SIM ");
            }else{
                System.out.print("NAO ");
            }
            System.out.println("");

            palavra = entrada.nextLine();
            
        }
    }
    public static boolean isVogal(String palavra, int i){
        boolean result = true;
        if(i < tamanho(palavra)){
            char letra =  palavra.charAt(i);
            if(!(letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u')){
                result = false;
                i = tamanho(palavra);
            }else{  
                result = isVogal(palavra, i+1);
            }
        } 
        return result;
        
        
    }
    public static boolean isConsoante(String palavra, int i){
        boolean result = true;
        if(i < tamanho(palavra)){
            char letra =  palavra.charAt(i);
            if(!(letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u')){
                result = false;
                i = tamanho(palavra);
            }
            if((letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u')){
                result = false;
                i = tamanho(palavra);
            }
            else{  
                result = isConsoante(palavra, i+1);
            }
        } 
        return result;
    }
    public static boolean isInteger(String palavra, int i){
        boolean result = true;
        if(i < tamanho(palavra)){
            char letra =  palavra.charAt(i);
            if(!(letra >= 48 && letra <= 57 )){
                result = false;
                i = tamanho(palavra);
            }else{  
                result = isInteger(palavra, i+1);
            }
        } 
        return result;
    }
    public static boolean isReal(String palavra, int i){
        boolean result = true;
        if(i < tamanho(palavra)){
            char letra =  palavra.charAt(i);
            if(!(letra >= 48 && letra <= 57 )){
                if(letra == '.' || letra == ','){
                    i = i;
                }else{
                    result = false;
                    i = tamanho(palavra);
                }
            }else{  
                result = isReal(palavra, i+1);
            }
        } 
        return result;
        
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