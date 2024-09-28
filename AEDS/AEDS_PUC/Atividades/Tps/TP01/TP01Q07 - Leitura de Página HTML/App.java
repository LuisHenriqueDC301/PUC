import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.net.URL;

public class App {
    public static void main(String[] args) throws Exception {
        String palavra = "", nome;
        Scanner entrada = new Scanner(System.in);
        boolean printNewLine = false;

        String link = new String();
        String input = new String();

        char[] vog = { 97, 101, 105, 111, 117, 225, 233, 237, 243, 250, 224, 232, 236, 242, 249, 227, 245, 226, 234,
                238, 244, 251 };
        char[] cons = { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x',
                'y', 'z' };
        int[] count = new int[25];

        
        nome = entrada.nextLine(); 
        palavra = entrada.nextLine(); 
        while (!compare(nome, "FIM") || !compare(nome, "FIM")) {
            
            link = htmlCode(palavra);

            if (printNewLine)
                System.out.printf("\n");
            printNewLine = true;

            objCount(vog, cons, count, link);
            objPrint(vog, count,nome);
            System.out.print(input);

            arrayReset(count);
            nome = entrada.nextLine();
            if(compare(nome, "FIM")){
                System.out.print("");
            }else{
                palavra = entrada.nextLine();
            }
             
        }
    }

    static String htmlCode(String link) {
        StringBuilder resp = new StringBuilder();

        try {
            URL url = new URL(link);
            BufferedReader htmlRead = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((link = htmlRead.readLine()) != null)
                resp.append(link);
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return resp.toString();
    }

    static void verChar(char[] vog, char[] cons, int[] count, char c) {
        boolean aux = false;

        for (int i = 0; i < 21; i++) {
            if (c == cons[i]) {
                count[22]++;
                aux = true;
                i = 21;
            }
        }
        if (!aux) {
            for (int i = 0; i < 22; i++) {
                if (c == vog[i]) {
                    count[i]++;
                    i = 21;
                }
            }
        }
    }

    static void objCount(char[] vog, char[] cons, int[] count, String link) {
        for (int i = 0; i < link.length(); i++) {
            if (link.charAt(i) == '<') {
                if (link.substring(i, i + 4).equals("<br>"))
                    count[23]++;
                else if (link.substring(i, i + 7).equals("<table>"))
                    count[24]++;
            } else {
                verChar(vog, cons, count, link.charAt(i));
            }
        }
        count[0] -= count[24];
        count[1] -= count[24];
        count[22] -= (count[24] * 3) + (count[23] * 2);
    }

    static void objPrint(char[] vog, int[] count, String nome) {
        for (int i = 0; i < 22; i++) {
            System.out.printf("%s(%d) ", vog[i], count[i]);
        }
        System.out.printf("consoante(%d) ", count[22]);
        System.out.printf("<br>(%d) ", count[23]);
        System.out.printf("<table>(%d) ", count[24]);
        System.out.print(nome);
    }

    static void arrayReset(int[] count) {
        for (int i = 0; i < count.length; i++)
            count[i] = 0;
    }

    public static boolean compare(String palavra1, String palavra2) {
        boolean result = true;
        int n = tamanho(palavra1);
        if (tamanho(palavra1) != tamanho(palavra2)) {
            result = false;
        } else {
            for (int i = 0; i < tamanho(palavra1); i++) {
                if (palavra1.charAt(i) != palavra2.charAt(i)) {
                    result = false;
                    i = tamanho(palavra1);
                }
            }
        }

        return result;
    }

    public static int tamanho(String palavra) {
        int i = 0;

        try {
            while (true) {
                palavra.charAt(i);
                i++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            return i;
        }
    }
}
