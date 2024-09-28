//beecrowd | 2312
import java.util.*;
/**
 * QuadroDeMedalhas
 */
public class QuadroDeMedalhas {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);

        int n = sc.nextInt();
        Olimpico[] olimpicos  = new Olimpico[n];
        
        for(int i = 0; i < n; i++){
            String nome = sc.next();
            int ouro = sc.nextInt();
            int prata = sc.nextInt();
            int bronze = sc.nextInt();
            Olimpico olimpico = new Olimpico(nome, ouro, prata, bronze);
            olimpicos[i] = olimpico;
        }
        bolhaOlimpiadas(olimpicos, n);
        for(int i = 0; i < n; i++){
            olimpicos[i].mostrar();
        }
    }
    
    public static void bolhaOlimpiadas(Olimpico[] array, int n){
        int nls;
        int ls = n - 1;
        for(int i = 0; i<n; i++){
            nls = 0;
            for(int j = 0; j < ls; j++){
                if(array[j].mOuro < array[j+1].mOuro){
                    Olimpico temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    nls = j;
                }else if(array[j].mOuro == array[j+1].mOuro){
                    if(array[j].mPrata < array[j+1].mPrata){
                        Olimpico temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                        nls = j;
                    }
                }else if (array[j].mPrata == array[j + 1].mPrata) {
                    if (array[j].mBronze < array[j + 1].mBronze) {
                        Olimpico temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        nls = j;
                    }
                }
            }   
        }
    }
}
class Olimpico{
    String nome;
    int mOuro;
    int mPrata;
    int mBronze;

    Olimpico(String nome, int mOuro, int mPrata, int mBronze){
        this.nome = nome;
        this.mOuro = mOuro;
        this.mPrata = mPrata;
        this.mBronze = mBronze;
    }
    void mostrar(){
        System.out.println(this.nome+" "+ this.mOuro+" "+ this.mPrata+" "+ this.mBronze);
    }
}