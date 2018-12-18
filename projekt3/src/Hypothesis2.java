import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Hypothesis2 {

    //Metoda Gaussa-Seidela szybciej zbiega do poprawnego wyniku niż metoda Jacobiego.(LZ)
    public static void A1(){

        int sizeOfArray = 10;
        int sizeOfMatrix = 2;

        MyMatrix matrix = new MyMatrix(sizeOfMatrix, sizeOfMatrix);

        int iterationJacobi[] = new int[sizeOfArray];
        int iterationGaussSeidel[] = new int[sizeOfArray];
        int sizes[] = new int[sizeOfArray];


        for(int i = 0; i<sizeOfArray; i++){
            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod JacobiMethod = new IteratedMethod(matrix);
            iterationJacobi[i] = JacobiMethod.jacobiMethod();

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod gaussSeidelMethod = new IteratedMethod(matrix);
            iterationGaussSeidel[i] = gaussSeidelMethod.gaussSeidelMethod();



            sizes[i] = sizeOfMatrix;
            sizeOfMatrix+=10;


        }

        File plik1 = new File("iterationJacobi.csv");
        File plik2 = new File("iterationGaussSeidel.csv");
        File plik3 = new File("xValues.csv");


        WriteFile("iterationJacobi.csv", iterationJacobi);
        WriteFile("iterationGaussSeidel.csv", iterationGaussSeidel);
        WriteFile("xValues.csv", sizes);




    }

    //Metoda z częściowym wyborem z optymalizacją daje lepsze wyniki niż bez optymalizacji.(LZ)
    public static void A2(){
        int sizeOfArray = 30;
        int sizeOfMatrix = 1;

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
            sizeOfMatrix+=1;


        }

        File plik1 = new File("timesPartGauss.csv");
        File plik2 = new File("timesPartGaussForSparseMatrix.csv");
        File plik3 = new File("xValues.csv");

        WriteFile("timesPartGauss.csv", timesPartGauss);
        WriteFile("timesPartGaussForSparseMatrix.csv", timesPartGaussForSparseMatrix);
        WriteFile("xValues.csv", sizes);

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
            double er = IteratedMethod.getError(matrix.vector, MonteCarlo.getSimulationVector(20, i));
            gauss.add(i, er);

            Randomizer.resetRandomizer();
            MyMatrix matrix2 = MatrixGenerator.prepareMatrix(20);
            matrix2.partChoiceGauss();
            double er2 = IteratedMethod.getError(matrix.vector, MonteCarlo.getSimulationVector(20, i));
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

    //Q2.Jak zależy błąd od rozmiaru planszy dla różnych metod
    public static void Q2(){

        int sizeOfArray = 10;
        int sizeOfMatrix = 2;

        MyMatrix matrix = new MyMatrix(sizeOfMatrix, sizeOfMatrix);

        double errorJacobi[] = new double[sizeOfArray];
        double errorGaussSeidel[] = new double[sizeOfArray];
        double errorPartChoiceGaussForSparseMatrix[] = new double[sizeOfArray];
        double resultArray[] = new double[matrix.vector.length];
        int sizes[] = new int[sizeOfArray];


        for(int i = 0; i<sizeOfArray; i++){
            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            matrix.partChoiceGaussForSparseMatrix();
            resultArray = matrix.mulMatrixVector();
            errorPartChoiceGaussForSparseMatrix[i] = matrix.getNormInf(resultArray, matrix.savedVector);

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod jacobiMethod = new IteratedMethod(matrix);
            jacobiMethod.jacobiMethod();
            resultArray = jacobiMethod.matrix.mulMatrixVector();
            errorGaussSeidel[i] = jacobiMethod.getError(resultArray, matrix.savedVector);

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod gaussSeidelMethod = new IteratedMethod(matrix);
            gaussSeidelMethod.gaussSeidelMethod();
            resultArray = gaussSeidelMethod.matrix.mulMatrixVector();
            errorJacobi[i] = gaussSeidelMethod.getError(resultArray, matrix.savedVector);


            sizes[i] = sizeOfMatrix;
            sizeOfMatrix+=10;


        }

        File plik1 = new File("errorJacobi.csv");
        File plik2 = new File("errorGaussSeidel.csv");
        File plik3 = new File("errorPartChoiceGaussForSparseMatrix.csv");
        File plik4 = new File("xValues.csv");


        WriteFile("errorJacobi.csv", errorJacobi);
        WriteFile("errorGaussSeidel.csv", errorGaussSeidel);
        WriteFile("errorPartChoiceGaussForSparseMatrix.csv", errorPartChoiceGaussForSparseMatrix);
        WriteFile("xValues.csv", sizes);


    }
    //Q3. Metoda Gaussa-Seidela daje wyniki o podanej dokładności szybciej(LZ)
    public static void Q3(){
        int sizeOfArray = 10;
        int sizeOfMatrix = 2;

        long timestampBefore;
        long timestampAfter;

        MyMatrix matrix = new MyMatrix(sizeOfMatrix, sizeOfMatrix);

        long timesJacobi[] = new long[sizeOfArray];
        long timesGaussSeidel[] = new long[sizeOfArray];
        int sizes[] = new int[sizeOfArray];


        for(int i = 0; i<sizeOfArray; i++){
            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod JacobiMethod = new IteratedMethod(matrix);
            timestampBefore = System.currentTimeMillis();
            JacobiMethod.jacobiMethod();
            timestampAfter = System.currentTimeMillis();
            timesJacobi[i] = timestampAfter - timestampBefore;

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod gaussSeidelMethod = new IteratedMethod(matrix);
            timestampBefore = System.currentTimeMillis();
            gaussSeidelMethod.gaussSeidelMethod();
            timestampAfter = System.currentTimeMillis();
            timesGaussSeidel[i] = timestampAfter - timestampBefore;


            sizes[i] = sizeOfMatrix;
            sizeOfMatrix+=10;


        }

        File plik1 = new File("timesJacobi.csv");
        File plik2 = new File("seidel.csv");
        File plik3 = new File("xValues.csv");


        WriteFile("timesJacobi.csv", timesJacobi);
        WriteFile("timesGaussSeidel.csv", timesGaussSeidel);
        WriteFile("xValues.csv", sizes);

    }

    private static void WriteFile(String name, long resultArray[]){

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
    private static void WriteFile(String name, int resoultArray[]){

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
    private static void WriteFile(String name, double resoultArray[]){

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
