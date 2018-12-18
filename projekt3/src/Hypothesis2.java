import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Hypothesis2 {


    private static void WriteFile(String name, long resoultArray[]){

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

    //Metoda Gaussa-Seidela szybciej zbiega do poprawnego wyniku niż metoda Jacobiego.(LZ)
    public static void A1(){

        int sizeOfArray = 20;
        int sizeOfMatrix = 20;

        long timestampBefore;
        long timestampAfter;

        MyMatrix matrix;

        long timesJacobi[] = new long[sizeOfArray];
        long timesGaussSeidel[] = new long[sizeOfArray];
        int sizes[] = new int[sizeOfArray];


        for(int i = 0; i<sizeOfArray; i++){
            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod JacobiMethod = new IteratedMethod(matrix, matrix.vector);
            timestampBefore = System.currentTimeMillis();
            JacobiMethod.jacobiMethod();
            timestampAfter = System.currentTimeMillis();
            timesJacobi[i] = timestampAfter - timestampBefore;

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfArray);
            IteratedMethod gaussSeidelMethod = new IteratedMethod(matrix, matrix.vector);
            timestampBefore = System.currentTimeMillis();
            gaussSeidelMethod.gaussSeidelMethod();
            timestampAfter = System.currentTimeMillis();
            timesGaussSeidel[i] = timestampAfter - timestampBefore;


            sizes[i] = sizeOfMatrix;
            sizeOfMatrix+=10;


        }

        File plik1 = new File("timesJacobi.csv");
        File plik2 = new File("seidel.csv");

        WriteFile("timesJacobi.csv", timesJacobi);
        WriteFile("timesGaussSeidel.csv", timesGaussSeidel);

    }

    //Metoda z częściowym wyborem z optymalizacją daje lepsze wyniki niż bez optymalizacji.(LZ)
    public static void A2(){
        int sizeOfArray = 7;
        int sizeOfMatrix = 20;

        long timestampBefore;
        long timestampAfter;

        MyMatrix matrix;

        long timesPartGauss[] = new long[sizeOfArray];
        long timesPartGaussForSparseMatrix[] = new long[sizeOfArray];
        int sizes[] = new int[sizeOfArray];


        for(int i = 0; i<sizeOfArray; i++){
            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            timestampBefore = System.currentTimeMillis();
            matrix.partChoiceGauss();
            timestampAfter = System.currentTimeMillis();
            timesPartGauss[i] = timestampAfter - timestampBefore;

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfArray);
            timestampBefore = System.currentTimeMillis();
            matrix.partChoiceGaussForSparseMatrix();
            timestampAfter = System.currentTimeMillis();
            timesPartGaussForSparseMatrix[i] = timestampAfter - timestampBefore;


            sizes[i] = sizeOfMatrix;
            sizeOfMatrix+=200;


        }

        File plik1 = new File("timesPartGauss.csv");
        File plik2 = new File("timesPartGaussForSparseMatrix.csv");

        WriteFile("timesPartGauss.csv", timesPartGauss);
        WriteFile("timesPartGaussForSparseMatrix.csv", timesPartGaussForSparseMatrix);


    }

    //Błąd obliczeń maleje wraz z ilością symulacji.
    public static void Q1(){
        XYSeries gauss = new XYSeries("Gauss");
        XYSeries partGauss = new XYSeries("PartGauss");
        int index = 0;
        for(int i = 10; i < 10000; i += 100){
            Randomizer.resetRandomizer();
            MyMatrix matrix = MatrixGenerator.prepareMatrix(20);
            matrix.gauss();
            double er = IteratedMethod.getError(matrix.vector.vector, MonteCarlo.getSimulationVector(20, i));
            gauss.add(i, er);

            Randomizer.resetRandomizer();
            MyMatrix matrix2 = MatrixGenerator.prepareMatrix(20);
            matrix2.partChoiceGauss();
            double er2 = IteratedMethod.getError(matrix.vector.vector, MonteCarlo.getSimulationVector(20, i));
            partGauss.add(i, er2);


            System.out.print("*");
            if(index % 10 == 0){
                System.out.print("|");
            }
            index++;
        }
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(gauss);
        xySeriesCollection.addSeries(partGauss);
        ChartUtilis.printChart(
                xySeriesCollection,
                "Q1",
                "Q1: Błąd w zależności od ilości symulacji (rozmiar 20)",
                "Ilość symulacji",
                "Blad (Norma nieskonczonosc)"
        );
    }

    //Jak zależy błąd od rozmiaru planszy dla różnych metod
    public static void Q2(){

    }


}
