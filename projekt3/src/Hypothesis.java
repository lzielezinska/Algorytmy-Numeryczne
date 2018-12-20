import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;

public class Hypothesis {

    public static void M2(){
        XYSeries jakobi = new XYSeries("Jacobi");
        XYSeries sidel = new XYSeries("Gauss-Seidel");
        int size = 12;
        double[] precision = MonteCarlo.getSimulationVector(size, 1000000);
        MyMatrix m1;
        MyMatrix m2;
        IteratedMethod i1;
        IteratedMethod i2;
        for(int i = 5; i < 80;i++){
            IteratedMethod.maxIteration = i;
            m1 = MatrixGenerator.prepareMatrix(size);
            i1 = new IteratedMethod(m1);
            i1.jacobiMethod();
            double er1 = IteratedMethod.getError(m1.vector, precision);
            jakobi.add(i, er1);



            m2 = MatrixGenerator.prepareMatrix(size);
            i2 = new IteratedMethod(m2);
            i2.gaussSeidelMethod();
            double er2 = IteratedMethod.getError(m2.vector, precision);
            sidel.add(i, er2);

            if(i % 10 == 5)System.out.println("*");
        }






        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(jakobi);
        xySeriesCollection.addSeries(sidel);
        ChartUtilis.printChart(
                xySeriesCollection,
                "M2",
                "M2: Błąd w zależności od metody i ilosci iteracji (Agentów: "+ size +") Liczba symulacji: 10^6 ",
                "Ilość iteracji",
                "Blad (Norma nieskonczonosc)"
        );
    }

    public static void M1(){
        XYSeries gauss = new XYSeries("Gauss");
        XYSeries partGauss = new XYSeries("PartGauss");
        int sizes[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        int index = 0;
        for(int i : sizes){
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
                "M1",
                "M1: Błąd w zależności od ilości symulacji (rozmiar 20)",
                "Ilość symulacji",
                "Blad (Norma nieskonczonosc)"
        );
    }

    //Metoda Gaussa-Seidela szybciej zbiega do poprawnego wyniku niż metoda Jacobiego.(LZ)
    public static void A1(){

        int sizeOfArray = 10;
        int sizeOfMatrix = 10;

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
            sizeOfMatrix+=5;


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
        int sizeOfMatrix = 10;
        XYSeries jakobi = new XYSeries("Jacobi");
        XYSeries sidel = new XYSeries("Gauss-Seidel");
        XYSeries part = new XYSeries("Optimized part choice");
        MyMatrix matrix;
        double precision = 0.00000000000001;
        int sizeOfArray = 10;
        long timestampBefore;
        long timestampAfter;


        long time = 0;


        for(int i = 0; i<2; i++){

            timestampBefore = System.currentTimeMillis();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            matrix.partChoiceGaussForSparseMatrix();
            timestampAfter = System.currentTimeMillis();
            time = timestampAfter - timestampBefore;
            part.add(sizeOfMatrix, time);

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod JacobiMethod = new IteratedMethod(matrix, precision);
            timestampBefore = System.currentTimeMillis();
            JacobiMethod.jacobiMethod();
            timestampAfter = System.currentTimeMillis();
            time = timestampAfter - timestampBefore;
            jakobi.add(sizeOfMatrix, time);

            Randomizer.resetRandomizer();
            matrix = MatrixGenerator.prepareMatrix(sizeOfMatrix);
            IteratedMethod gaussSeidelMethod = new IteratedMethod(matrix, precision);
            timestampBefore = System.currentTimeMillis();
            gaussSeidelMethod.gaussSeidelMethod();
            timestampAfter = System.currentTimeMillis();
            time = timestampAfter - timestampBefore;
            sidel.add(sizeOfMatrix, time);

            sizeOfMatrix+=5;
            System.out.println("*");

        }




        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(jakobi);
        xySeriesCollection.addSeries(sidel);
        xySeriesCollection.addSeries(part);
        ChartUtilis.printChart(
                xySeriesCollection,
                "Q2",
                "Q2: Jak zależy błąd od rozmiaru planszy dla różnych metod",
                "Ilosc agentow",
                "Czas w milisekundach"
        );


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
            sizeOfMatrix+=10;


        }

        File plik1 = new File("timesJacobi.csv");
        File plik2 = new File("seidel.csv");
        File plik3 = new File("xValues.csv");


        Save.writeFile("timesJacobi.csv", timesJacobi);
        Save.writeFile("timesGaussSeidel.csv", timesGaussSeidel);
        Save.writeFile("xValues.csv", sizes);

    }




}
