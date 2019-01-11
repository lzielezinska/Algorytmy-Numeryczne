import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by lucyna on 19.12.18.
 */
public class Save {
    public static void writeFile(String name, long resultArray[]){

        try {
            PrintWriter zapis1;
            zapis1 = new PrintWriter(name);
            for(int i = 0; i<resultArray.length;i++) zapis1.println(resultArray[i]);
            zapis1.close();
        }
        catch(FileNotFoundException e){
            System.out.print("Plik nie został utworzony\nBład: "+e);
        }

    }
    public static void writeFile(String name, int resoultArray[]){

        try {
            PrintWriter zapis1;
            zapis1 = new PrintWriter(name);
            for(int i = 0; i<resoultArray.length;i++) zapis1.println(resoultArray[i]);
            zapis1.close();
        }
        catch(FileNotFoundException e){
            System.out.print("Plik nie został utworzony\nBład: "+e);
        }

    }
    public static void writeFile(String name, double resoultArray[]){

        try {
            PrintWriter zapis1;
            zapis1 = new PrintWriter(name);
            for(int i = 0; i<resoultArray.length;i++) zapis1.println(resoultArray[i]);
            zapis1.close();
        }
        catch(FileNotFoundException e){
            System.out.print("Plik nie został utworzony\nBład: "+e);
        }

    }
}
