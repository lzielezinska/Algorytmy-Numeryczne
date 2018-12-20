import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;

public class Hypothesis {

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
            JacobiMethod.jacobiMethod();
            iterationJacobi[i] = JacobiMethod.getIterator();

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod gaussSeidelMethod = new IteratedMethod(matrix);
            gaussSeidelMethod.gaussSeidelMethod();
            iterationGaussSeidel[i] = gaussSeidelMethod.getIterator();



            sizes[i] = sizeOfMatrix;
            sizeOfMatrix+=10;


        }

        File plik1 = new File("iterationJacobi.csv");
        File plik2 = new File("iterationGaussSeidel.csv");
        File plik3 = new File("xValues.csv");


        Save.writeFile("iterationJacobi.csv", iterationJacobi);
        Save.writeFile("iterationGaussSeidel.csv", iterationGaussSeidel);
        Save.writeFile("xValues.csv", sizes);




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
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
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

        Save.writeFile("timesPartGauss.csv", timesPartGauss);
        Save.writeFile("timesPartGaussForSparseMatrix.csv", timesPartGaussForSparseMatrix);
        Save.writeFile("xValues.csv", sizes);

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

        int sizeOfArray = 100;
        int sizeOfMatrix = 10;
        int sizes[] = new int[sizeOfArray];

        MyMatrix matrix;

        double errorJacobi[] = new double[sizeOfArray];
        double errorGaussSeidel[] = new double[sizeOfArray];
        double errorPartChoiceGaussForSparseMatrix[] = new double[sizeOfArray];
        double resultArray[];



        for(int i = 0; i<sizeOfArray; i++){
            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            matrix.partChoiceGaussForSparseMatrix();
            resultArray = matrix.mulMatrixVector();
            errorPartChoiceGaussForSparseMatrix[i] = MyMatrix.getNormInf(resultArray, matrix.getSavedVector());

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod jacobiMethod = new IteratedMethod(matrix);
            jacobiMethod.jacobiMethod();
            resultArray = jacobiMethod.matrix.mulMatrixVector();
            errorGaussSeidel[i] = IteratedMethod.getError(resultArray, matrix.getSavedVector());

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod gaussSeidelMethod = new IteratedMethod(matrix);
            gaussSeidelMethod.gaussSeidelMethod();
            resultArray = gaussSeidelMethod.matrix.mulMatrixVector();
            errorJacobi[i] = IteratedMethod.getError(resultArray, matrix.getSavedVector());

            sizes[i] = sizeOfMatrix;
            sizeOfMatrix+=1;



        }

        File plik1 = new File("errorJacobi.csv");
        File plik2 = new File("errorGaussSeidel.csv");
        File plik3 = new File("errorPartChoiceGaussForSparseMatrix.csv");
        File plik4 = new File("xValues.csv");


        Save.writeFile("errorJacobi.csv", errorJacobi);
        Save.writeFile("errorGaussSeidel.csv", errorGaussSeidel);
        Save.writeFile("errorPartChoiceGaussForSparseMatrix.csv", errorPartChoiceGaussForSparseMatrix);
        Save.writeFile("xValues.csv", sizes);


    }
    //Q3. Metoda Gaussa-Seidela daje wyniki o podanej dokładności szybciej(LZ)
    public static void Q3(double precision){
        int sizeOfArray = 8;
        int sizeOfMatrix = 10;
        long timestampBefore;
        long timestampAfter;

        MyMatrix matrix = new MyMatrix(sizeOfMatrix, sizeOfMatrix);

        long timesJacobi[] = new long[sizeOfArray];
        long timesGaussSeidel[] = new long[sizeOfArray];
        int sizes[] = new int[sizeOfArray];


        for(int i = 0; i<sizeOfArray; i++){
            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod JacobiMethod = new IteratedMethod(matrix, precision);
            timestampBefore = System.currentTimeMillis();
            JacobiMethod.jacobiMethod();
            timestampAfter = System.currentTimeMillis();
            timesJacobi[i] = timestampAfter - timestampBefore;

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod gaussSeidelMethod = new IteratedMethod(matrix, precision);
            timestampBefore = System.currentTimeMillis();
            gaussSeidelMethod.gaussSeidelMethod();
            timestampAfter = System.currentTimeMillis();
            timesGaussSeidel[i] = timestampAfter - timestampBefore;


            sizes[i] = sizeOfMatrix;
            sizeOfMatrix+=15;


        }

        File plik1 = new File("timesJacobi.csv");
        File plik2 = new File("seidel.csv");
        File plik3 = new File("xValues.csv");


        Save.writeFile("timesJacobi.csv", timesJacobi);
        Save.writeFile("timesGaussSeidel.csv", timesGaussSeidel);
        Save.writeFile("xValues.csv", sizes);

    }




}
