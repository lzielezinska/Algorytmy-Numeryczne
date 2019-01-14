import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Hypothesis {


    //H1. - Pomiary czasu działania programu stopniowo zwiększając liczbę agentów do liczby rzędu 1-2 tysięcy równań.
    //Oddzielny pomiar czasu dla budowanie układu i jego rozwiązania.
    public static void H1(){
        //testForGauss();
        testForSParsePartGauss();
        //testForGauusSeidel();
    }
    //H2. - Stosując aproksymację średniokwadratową dyskretną znaleźć wielomian aproksymacyjny dla każdej z serii pomiarów.
    //Uzyskane współczynniki wielomianu podać w sprawozdaniu.
    public static void H2(){
        double args[] = {66, 231, 496, 861, 1326, 1891};

        double valuesForGauss[] = {1.35, 22.2, 132.4, 750.3, 2695.8, 7886.95};
        double valuesForPartGauss[] = {0.75, 3.05, 15.55, 43.95, 114.7, 317.35};
        double valuesForGaussSeidel[] = {1.25, 14.65, 104.35, 444.95, 1356.3, 3191.45};

        Aproximation.solveAproximationEquasionForGauss(args, valuesForGauss);
        Aproximation.solveAproximationEquasionForGaussSparseMatrix(args, valuesForPartGauss);
        Aproximation.solveAproximationEquasionForGaussSeidelMethod(args, valuesForGaussSeidel);
    }

    //H3. - Sprawdzenie uzyskanego w H2 rozwiązania
    public static void H3(){

    }

    //H4. - Wyliczyć jak długo trwałyby obliczenia, gdyby rozmiar układu był rzędu
    //100 000 równań  ignorując ograniczenia pamięciowe.
    public static void H4(){

    }

    //H5. - Próba obliczenia problemu o wyznaczonym rozmiarze najszybszą metodą.
    public static void H5(){

    }

    private static void testForGauss(){
        XYSeries solveGauss = new XYSeries("Solve Gauss");
        XYSeries buildGauss = new XYSeries("Build Gauss");
        MyMatrix m1;
        int numberOfEquasions;
        int iterationStartSize = 10;
        int iterationEndSize = 60;
        int iterationInterval = 10;
        long timestampBefore;
        long timestampAfter;
        long timesBuildingGauss[] = new long[iterationEndSize/iterationStartSize];
        double timesSolvingGauss[] = new double[iterationEndSize/iterationStartSize];
        double numbersOfEquasions[] = new double[iterationEndSize/iterationStartSize];
        int index = 0;

        for(int i = iterationStartSize; i <= iterationEndSize; i+=iterationInterval){
            numberOfEquasions = ((i + 1) * (i + 2)) / 2;
            numbersOfEquasions[index] = numberOfEquasions;
            for(int t = 0;t < 20;t++){
                timestampBefore = System.currentTimeMillis();
                m1 = MatrixGenerator.prepareMatrix(i);
                timestampAfter = System.currentTimeMillis();
                timesBuildingGauss[index] += timestampAfter - timestampBefore;

                timestampBefore = System.currentTimeMillis();
                m1.gauss();
                timestampAfter = System.currentTimeMillis();
                timesSolvingGauss[index] += (double)(timestampAfter - timestampBefore);
            }
            timesBuildingGauss[index] /= 20.0;
            timesSolvingGauss[index] /=20.0;

            buildGauss.add(numberOfEquasions, timesBuildingGauss[index]);
            solveGauss.add(numberOfEquasions, timesSolvingGauss[index]);

            System.out.println(numberOfEquasions + " rozw: " + timesSolvingGauss[index] + " budowanie: " + timesBuildingGauss[index]);
            index++;
        }
        Aproximation.solveAproximationEquasionForGauss(numbersOfEquasions, timesSolvingGauss);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(solveGauss);
        xySeriesCollection.addSeries(buildGauss);
        ChartUtilis.printChart(
                xySeriesCollection,
                "H1",
                "Czas od ilosci rownan",
                "Ilość rownan",
                "czas w minisekundach"
        );

    }


    private static void testForSParsePartGauss(){
        XYSeries solveGauss = new XYSeries("Solve Part Gauss");
        XYSeries buildGauss = new XYSeries("Build Part Gauss");
        MyMatrix m1;
        int numberOfEquasions;
        int iterationStartSize = 10;
        int iterationEndSize = 60;
        int iterationInterval = 10;
        long timestampBefore;
        long timestampAfter;
        long timesBuildingGauss[] = new long[iterationEndSize/iterationStartSize];
        double timesSolvingGauss[] = new double[iterationEndSize/iterationStartSize];
        double numbersOfEquasions[] = new double[iterationEndSize/iterationStartSize];
        int index = 0;

        for(int i = iterationStartSize; i <= iterationEndSize; i+=iterationInterval){
            numberOfEquasions = ((i + 1) * (i + 2)) / 2;
            numbersOfEquasions[index] = numberOfEquasions;
            for(int t = 0;t < 20;t++){
                timestampBefore = System.currentTimeMillis();
                m1 = MatrixGenerator.prepareMatrix(i);
                timestampAfter = System.currentTimeMillis();
                timesBuildingGauss[index] += timestampAfter - timestampBefore;

                timestampBefore = System.currentTimeMillis();
                m1.partChoiceGaussForSparseMatrix();
                timestampAfter = System.currentTimeMillis();
                timesSolvingGauss[index] += (double)(timestampAfter - timestampBefore);
            }
            timesBuildingGauss[index] /= 20.0;
            timesSolvingGauss[index] /=20.0;

            buildGauss.add(numberOfEquasions, timesBuildingGauss[index]);
            solveGauss.add(numberOfEquasions, timesSolvingGauss[index]);

            System.out.println(numberOfEquasions + " rozw: " + timesSolvingGauss[index] + " budowanie: " + timesBuildingGauss[index]);
            index++;
        }
        Aproximation.solveAproximationEquasionForGauss(numbersOfEquasions, timesSolvingGauss);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(solveGauss);
        xySeriesCollection.addSeries(buildGauss);
        ChartUtilis.printChart(
                xySeriesCollection,
                "H1PartGauss",
                "Czas od ilosci rownan",
                "Ilość rownan",
                "czas w minisekundach"
        );

    }
    private static void testForGauusSeidel(){
        XYSeries solveGauss = new XYSeries("Solve Gauss Seidel");
        XYSeries buildGauss = new XYSeries("Build Gauss Seidel");
        MyMatrix m1;
        int numberOfEquasions;
        int iterationStartSize = 10;
        int iterationEndSize = 60;
        int iterationInterval = 10;
        long timestampBefore;
        long timestampAfter;
        long timesBuildingGauss[] = new long[iterationEndSize/iterationStartSize];
        double timesSolvingGauss[] = new double[iterationEndSize/iterationStartSize];
        double numbersOfEquasions[] = new double[iterationEndSize/iterationStartSize];
        int index = 0;
        IteratedMethod iteratedMethod;
        for(int i = iterationStartSize; i <= iterationEndSize; i+=iterationInterval){
            numberOfEquasions = ((i + 1) * (i + 2)) / 2;
            numbersOfEquasions[index] = numberOfEquasions;
            for(int t = 0;t < 20;t++){
                timestampBefore = System.currentTimeMillis();
                m1 = MatrixGenerator.prepareMatrix(i);
                timestampAfter = System.currentTimeMillis();
                timesBuildingGauss[index] += timestampAfter - timestampBefore;

                iteratedMethod = new IteratedMethod(m1);
                timestampBefore = System.currentTimeMillis();
                iteratedMethod.gaussSeidelMethod();
                timestampAfter = System.currentTimeMillis();
                timesSolvingGauss[index] += (double)(timestampAfter - timestampBefore);
            }
            timesBuildingGauss[index] /= 20.0;
            timesSolvingGauss[index] /=20.0;

            buildGauss.add(numberOfEquasions, timesBuildingGauss[index]);
            solveGauss.add(numberOfEquasions, timesSolvingGauss[index]);

            System.out.println(numberOfEquasions + " rozw: " + timesSolvingGauss[index] + " budowanie: " + timesBuildingGauss[index]);
            index++;
        }
        Aproximation.solveAproximationEquasionForGauss(numbersOfEquasions, timesSolvingGauss);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(solveGauss);
        xySeriesCollection.addSeries(buildGauss);
        ChartUtilis.printChart(
                xySeriesCollection,
                "H1GaussSeidel",
                "Czas od ilosci rownan",
                "Ilość rownan",
                "czas w minisekundach"
        );

    }

}
