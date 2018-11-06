/**
 * Created by Lucyna Zielezińska on 11.10.18.
 * Informatyka rok 3 
 * tester programista 
 * projekt 1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class AlgorytmyNumeryczne {

    private static void ZapisDoPliku1(String nazwa){
        int rozmiarMAX = 87;
        double i=-0.999999;
        double roznica =0.000002;

        try {
            PrintWriter zapis1;
            zapis1 = new PrintWriter(nazwa);
            while(i<=1){
                zapis1.println(taylorOdPoczatku(i,rozmiarMAX));
                i+=roznica;
            }
            zapis1.close();
        }
        catch(FileNotFoundException e){
            System.out.print("Plik nie został utworzony\nBład: "+e);
        }

    }

    private static void ZapisDoPliku2(String nazwa){
        int rozmiarMAX = 87;
        double i=-0.999999;
        double roznica =0.000002;

        try {
            PrintWriter zapis1;
            zapis1 = new PrintWriter(nazwa);
            while(i<=1){
                zapis1.println(taylorOdKonca(i,rozmiarMAX));
                i+=roznica;
            }
            zapis1.close();
        }
        catch(FileNotFoundException e){
            System.out.print("Plik nie został utworzony\nBład: "+e);
        }

    }

    private static void ZapisDoPliku3(String nazwa){
        int rozmiarMAX = 87;
        double i=-0.999999;
        double roznica =0.000002;

        try {
            PrintWriter zapis1;
            zapis1 = new PrintWriter(nazwa);
            while(i<=1){
                zapis1.println(poprzedniWyrazOdPoczatku(i,rozmiarMAX));
                i+=roznica;
            }
            zapis1.close();
        }
        catch(FileNotFoundException e){
            System.out.print("Plik nie został utworzony\nBład: "+e);
        }

    }

    private static void ZapisDoPliku4(String nazwa){
        int rozmiarMAX = 87;
        double i=-0.999999;
        double roznica =0.000002;

        try {
            PrintWriter zapis1;
            zapis1 = new PrintWriter(nazwa);
            while(i<=1){
                zapis1.println(poprzedniWyrazOdKonca(i,rozmiarMAX));
                i+=roznica;
            }
            zapis1.close();
        }
        catch(FileNotFoundException e){
            System.out.print("Plik nie został utworzony\nBład: "+e);
        }

    }

    private static void ZapisDoPliku5(String nazwa){
        int rozmiarMAX = 87;
        double i=-0.999999;
        double roznica =0.000002;

        try {
            PrintWriter zapis1;
            zapis1 = new PrintWriter(nazwa);
            while(i<=1){
                zapis1.println(Math.cos(i)*Math.atan(i));
                i+=roznica;
            }
            zapis1.close();
        }
        catch(FileNotFoundException e){
            System.out.print("Plik nie został utworzony\nBład: "+e);
        }

    }
    private static void ZapisDoPliku6(String nazwa){
        int rozmiarMAX = 87;
        double i=-0.999999;
        double roznica =0.000002;

        try {
            PrintWriter zapis1;
            zapis1 = new PrintWriter(nazwa);
            while(i<=1){
                zapis1.println(i);
                i+=roznica;
            }
            zapis1.close();
        }
        catch(FileNotFoundException e){
            System.out.print("Plik nie został utworzony\nBład: "+e);
        }

    }


    //silnia
    private static  double silnia (double i){
        if (i == 0)
            return 1;
        else return i * silnia(i - 1);

    }

    private static double potega(double a, int b){
        double wynik = a;
        if(b==0)
            return 1;
        else {
            while (b > 1) {
                wynik = wynik * a;
                b--;
            }
            return wynik;
        }
    }


    double bezwzgledny(double x, double x_z){
        // x - wartosc dokladna, x_z - wartosc zmierzona
        double pomoc = x_z-x;
        double blad = silnia(pomoc);
        return blad;
    }

    double wzgledny(double x, double x_z){
        double blad = bezwzgledny(x, x_z)/silnia(x);
        return blad;
    }


    //sumowanie od początku
    private static double taylorOdPoczatku(double x, int k){
        int i = 0, j=0;
        double cosx = 0, arctgx = 0;
        double wynik1 = 0;
        double wynik2 = 0;
        double pi = 3.14159265359;

        //cos x
        while(i<=k){
            cosx = (double)(potega(-1, i) * (potega(x, i*2))) /( (silnia(i*2)));
            wynik1+=cosx;
            i++;
        }
        i = 0;

        //arctg x
        if(Math.abs(x)<=1) {
            while (i <= k) {
                arctgx = (double) (potega(-1, i) * potega(x, i*2 + 1)) / (i*2 + 1);
                wynik2+= arctgx;
                i++;

            }
        }else if(Math.abs(x)>1){
            wynik2 = (pi*Math.sqrt(Math.pow(x,2)))/(2*x);
            double pomoc = 0;
            while(i<=k){




                pomoc+=(double)(potega(-1, i) * potega(x, -1-i*2) )/ (i*2+ 1);
                i++;
                //j += 2;

            }
        }
        return wynik1*wynik2;
    }
    //sumowanie szregu od końca
    private static double taylorOdKonca(double x, int k) {
        int i = k -1;                              //przypisuje do i ostatni wyraz szeregu
        double wynik1 = 0;                      //wynik cos
        double wynik2 = 0;                      //wynik arctg
        double pi = 3.14159265359;

        //cos x
        while (i >= 0) {
            wynik1 += (double) (potega(-1, i) * (potega(x, i * 2))) / ((silnia(i * 2)));
            i--;
        }
        //arctg x
        i=k-1;
        if (Math.abs(x) <= 1) {
            while (i >= 0) {
                wynik2 += (double) (potega(-1, i) * potega(x, i * 2 + 1)) / (i * 2 + 1);
                i--;

            }
        } else if (Math.abs(x) > 1) {
            wynik2 = (pi * Math.sqrt(potega(x, 2))) / (2 * x);
            int pomoc = 0;
            while (i  > 0) {
                pomoc += (double)(potega(-1, i) * potega(x, -1 - i * 2)) / (i * 2 + 1);
                i--;
            }
            wynik2 -= pomoc;
        }
        return wynik1 * wynik2;

    }

    //Obliczanie z poprzedniego wyrazu

    public static double poprzedniWyrazOdPoczatku(double x, int k){
        double cosx = 1;
        double arctgx = x;
        double sumaCosx = 0;
        double sumaArctgx = 0;

        double wynikiCos[] = new double[k];
        wynikiCos[0] = 1;
        double wynikiArctg[] = new double[k];
        wynikiArctg[0] = x;

        int i = 1;
        while(i<k) {
            cosx = cosx * ((-1)*potega(x,2)*silnia(2*(i-1)))/silnia(2*i);
            wynikiCos[i] = cosx;
            arctgx = (arctgx * (-1) * potega(x, 2) * (((2 * i) - 1))) / ((2 * i) + 1);
            wynikiArctg[i] = arctgx;
            i++;
        }

        for(int j=0;j<k;j++) {
            sumaArctgx += wynikiArctg[j];
            sumaCosx += wynikiCos[j];
        }



        return sumaArctgx*sumaCosx;
    }


    public static double poprzedniWyrazOdKonca(double x, int k){
        double cosx = 1;
        double arctgx = x;
        double sumaCosx = 0;
        double sumaArctgx = 0;

        double wynikiCos[] = new double[k];
        wynikiCos[0] = 1;
        double wynikiArctg[] = new double[k];
        wynikiArctg[0] = x;

        int i = 1;
        while(i<k) {
            cosx = cosx * ((-1)*potega(x,2)*silnia(2*(i-1)))/silnia(2*i);
            wynikiCos[i] = cosx;
            arctgx = (arctgx * (-1) * potega(x, 2) * (((2 * i) - 1))) / ((2 * i) + 1);
            wynikiArctg[i] = arctgx;
            i++;
        }

        for(int j=k-1;j>=0;j--) {
            sumaArctgx += wynikiArctg[j];
            sumaCosx += wynikiCos[j];
        }



        return sumaArctgx*sumaCosx;
    }




    public static void main(String [] args){

        File plik1 = new File("TaylorOdPoczatku");
        ZapisDoPliku1("TaylorOdPoczatku");

        File plik2 = new File("TaylorOdKonca");
        ZapisDoPliku2("TaylorOdKonca");

        File plik3 = new File("PoprzedniWyrazOdPoczatku");
        ZapisDoPliku3("PoprzedniWyrazOdPoczatku");

        File plik4 = new File("PoprzedniWyrazOdKonca");
        ZapisDoPliku4("PoprzedniWyrazOdKonca");

        File plik5 = new File("FunkcjeWbudowane");
        ZapisDoPliku5("FunkcjeWbudowane");

        File plik6 = new File("WartoscX");
        ZapisDoPliku6("WartoscX");


    }

}
