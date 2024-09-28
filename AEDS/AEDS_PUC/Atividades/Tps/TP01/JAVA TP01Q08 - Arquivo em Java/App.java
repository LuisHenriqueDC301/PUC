import java.io.RandomAccessFile;
import java.util.*;

public class App {

    public static void main(String[] args) throws Exception{
        RandomAccessFile file = new RandomAccessFile("archive.txt", "rw");
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        int n = sc.nextInt();

        for(int i = 0; i < n; i++){
            double x = sc.nextDouble();
            file.writeDouble(x);
        }
        for (int i = n - 1; i >= 0; i--) {
            file.seek(i * 8);
            double value = file.readDouble();
            System.out.println( value);
        }
        file.close();
        
    }
}
