//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//
//
//public class Hypothesis {
//    private static double E1FloatG;
//    private static double E1FloatP;
//    private static double E1FloatF;
//
//    private static double E1DoubleG;
//    private static double E1DoubleP;
//    private static double E1DoubleF;
//
//    private static double E1MyTypeG;
//    private static double E1MyTypeP;
//    private static double E1MyTypeF;
//
//
//    /**
//     * Dla dowolnego ustalonego rozmiaru macierzy czas dzialania metody Gaussa w ko-
//     * lejnych wersjach (1, 2, 3) rosnie.
//     */
//    public static void H1(){
//
//        ANumber resultOfGauss[];
//        ANumber resultOfPartGauss[];
//        ANumber resultOfFullGauss[];
//
//        int sizeOfArray = 20;
//        int sizeOfMatrix = 20;
//
//        long timestampBefore;
//        long timestampAfter;
//
//        long timesGauss[] = new long[sizeOfArray];
//        long timesPartGauss[] = new long[sizeOfArray];
//        long timesFullGauss[] = new long[sizeOfArray];
//        int sizes[] = new int[sizeOfArray];
//
//
//        for(int i = 0; i<sizeOfArray; i++){
//            Randomizer.resetRandomizer();
//            MyMatrix matrix = new MyMatrix<WrappedDouble>(sizeOfMatrix,sizeOfMatrix, WrappedDouble.class);
//            matrix.fillMatrixAndVector();
//            timestampBefore = System.currentTimeMillis();
//            resultOfGauss = matrix.gauss();
//            timestampAfter = System.currentTimeMillis();
//            timesGauss[i] = timestampAfter - timestampBefore;
//
//            Randomizer.resetRandomizer();
//            matrix.fillMatrixAndVector();
//            timestampBefore = System.currentTimeMillis();
//            resultOfPartGauss = matrix.partChoiceGauss();
//            timestampAfter = System.currentTimeMillis();
//            timesPartGauss[i] = timestampAfter - timestampBefore;
//
//            Randomizer.resetRandomizer();
//            matrix.fillMatrixAndVector();
//            timestampBefore = System.currentTimeMillis();
//            resultOfFullGauss = matrix.fulChoiceGauss();
//            timestampAfter = System.currentTimeMillis();
//            timesFullGauss[i] = timestampAfter - timestampBefore;
//
//            sizes[i] = sizeOfMatrix;
//            sizeOfMatrix+=50;
//
//
//        }
//
//    }
//
//
//    /**
//     * Dla dowolnego ustalonego rozmiaru macierzy bład uzyskanego wyniku metody
//     * Gaussa w kolejnych wersjach (1, 2, 3) maleje.
//     */
//    public static void H2(){
//        ANumber resultOfGauss[];
//        ANumber resultOfPartGauss[];
//        ANumber resultOfFullGauss[];
//
//        int sizeOfArray = 20;
//        int sizeOfMatrix = 20;
//
//        double normOfGauss[] = new double[sizeOfArray];
//        double normOfPartGauss[] = new double[sizeOfArray];
//        double normOfFullGauss[] = new double[sizeOfArray];
//        int sizes[] = new int[sizeOfArray];
//
//
//        for(int i = 0; i<sizeOfArray; i++){
//            Randomizer.resetRandomizer();
//            MyMatrix matrix = new MyMatrix<WrappedDouble>(sizeOfMatrix,sizeOfMatrix, WrappedDouble.class);
//            matrix.fillMatrixAndVector();
//            matrix.gauss();
//            resultOfGauss = matrix.mulMatrixVector();
//            normOfGauss[i] =matrix.getNormInf(resultOfGauss, matrix.getSavedVector());
//
//            Randomizer.resetRandomizer();
//            matrix = new MyMatrix<WrappedDouble>(sizeOfMatrix,sizeOfMatrix, WrappedDouble.class);
//            matrix.fillMatrixAndVector();
//            matrix.partChoiceGauss();
//            resultOfPartGauss = matrix.mulMatrixVector();
//            normOfPartGauss[i] = matrix.getNormInf(resultOfPartGauss, matrix.getSavedVector());
//
//
//            Randomizer.resetRandomizer();
//            matrix = new MyMatrix<WrappedDouble>(sizeOfMatrix,sizeOfMatrix, WrappedDouble.class);
//            matrix.fillMatrixAndVector();
//            matrix.fulChoiceGauss();
//            resultOfFullGauss = matrix.mulMatrixVector();
//            normOfFullGauss[i]  = matrix.getNormInf(resultOfFullGauss, matrix.getSavedVector());
//            sizeOfMatrix+=50;
//            sizes[i] = sizeOfMatrix;
//
//        }
//
//
//
//    }
//
//
//    /**
//     * Uzycie wlasnej arytmetyki na uaamkach zapewnia bezbledne wyniki niezaleznie od
//     * wariantu metody Gaussa i rozmiaru macierzy.
//     */
//    public static void H3(){
//        int size = 100;
//        ANumber resultOfGauss[];
//        ANumber resultOfPartGauss[];
//        ANumber resultOfFullGauss[];
//        double normOfGauss;
//        double normOfPartGauss;
//        double normOfFullGauss;
//
//
//
//        for(int i = 10; i<= size; i += 10){
//            Randomizer.resetRandomizer();
//            MyMatrix matrix = new MyMatrix<MyType>(i,i, MyType.class);
//            matrix.fillMatrixAndVector();
//            matrix.gauss();
//            resultOfGauss = matrix.mulMatrixVector();
//            normOfGauss = matrix.getNormInf(resultOfGauss, matrix.getSavedVector());
//            System.out.println("Metoda : Gauus      | Rozmiar " + i + " Norma: " + normOfGauss);
//
//            Randomizer.resetRandomizer();
//            MyMatrix matrix2 = new MyMatrix<MyType>(i,i, MyType.class);
//            matrix2.fillMatrixAndVector();
//            matrix2.partChoiceGauss();
//            resultOfPartGauss = matrix2.mulMatrixVector();
//            normOfPartGauss = matrix2.getNormInf(resultOfPartGauss, matrix2.getSavedVector());
//            System.out.println("Metoda : Part Gauss | Rozmiar " + i + " Norma: " + normOfPartGauss);
//
//            Randomizer.resetRandomizer();
//            MyMatrix matrix3 = new MyMatrix<MyType>(i,i, MyType.class);
//            matrix3.fillMatrixAndVector();
//            matrix3.fulChoiceGauss();
//            resultOfFullGauss = matrix3.mulMatrixVector();
//            normOfFullGauss = matrix2.getNormInf(resultOfFullGauss, matrix3.getSavedVector());
//            System.out.println("Metoda : Part Gauss | Rozmiar " + i + " Norma: " + normOfFullGauss);
//        }
//    }
//
//    /**
//     * Jak zalezy dokladnosc obliczen (blad) od rozmiaru macierzy dla dwóch wybranych
//     * przez Ciebie wariantów metody Gaussa gdy obliczenia prowadzone sa na typie
//     * podwójnej precyzji (TD)?
//     */
//    public static void Q1(){
//        int size = 1000;
//        ANumber resultOfGauss[];
//        ANumber resultOfPartGauss[];
//        double normOfGauss;
//        double normOfPartGauss;
//        XYSeries gauss = new XYSeries("Gauss");
//        XYSeries partGauss = new XYSeries("Part Gauss");
//
//
//        for(int i = 100; i<= size; i += 50){
//            Randomizer.resetRandomizer();
//            MyMatrix matrix = new MyMatrix<WrappedDouble>(i,i, WrappedDouble.class);
//            matrix.fillMatrixAndVector();
//            matrix.gauss();
//            resultOfGauss = matrix.mulMatrixVector();
//            normOfGauss = matrix.getNormInf(resultOfGauss, matrix.getSavedVector());
//
//
//            Randomizer.resetRandomizer();
//            MyMatrix matrix2 = new MyMatrix<WrappedDouble>(i,i, WrappedDouble.class);
//            matrix2.fillMatrixAndVector();
//            matrix2.partChoiceGauss();
//            resultOfPartGauss = matrix2.mulMatrixVector();
//            normOfPartGauss = matrix2.getNormInf(resultOfPartGauss, matrix2.getSavedVector());
//
//            gauss.add(i,Math.abs(Math.log10(normOfGauss)));
//            partGauss.add(i,Math.abs(Math.log10(normOfPartGauss)));
//        }
//        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(gauss);
//        xySeriesCollection.addSeries(partGauss);
//        ChartUtilis.printChart(
//                xySeriesCollection,
//                "Q1",
//                "Q1: Blad od rozmiatu macierzy (TD)",
//                "Rozmiar Macierzy",
//                "Blad (Norma nieskonczonosc) skala logarytmiczna"
//        );
//    }
//
//    /**
//     * Jak przy wybranym przez Ciebie wariancie metody Gaussa zalezy czas dzialnia
//     * algorytmu od rozmiaru macierzy i róznych typów?
//     */
//    public static void Q2(){
//
//        ANumber resultOfGauss[];
//
//        int sizeOfArray = 5;
//        int sizeOfMatrix = 300;
//
//
//
//        long timestampBefore;
//        long timestampAfter;
//        long timesGaussDouble[] = new long[sizeOfArray];
//        long timesGaussFloat[] = new long[sizeOfArray];
//        long timesGaussMyType[] = new long[sizeOfArray];
//        int sizes[] = new int[sizeOfArray];
//
//
//
//        for(int i = 0; i<5; i++){
//            Randomizer.resetRandomizer();
//            MyMatrix matrixDouble = new MyMatrix<WrappedDouble>(sizeOfMatrix,sizeOfMatrix, WrappedDouble.class);
//            matrixDouble.fillMatrixAndVector();
//            timestampBefore = System.currentTimeMillis();
//            resultOfGauss = matrixDouble.gauss();
//            timestampAfter = System.currentTimeMillis();
//            timesGaussDouble[i] =timestampAfter - timestampBefore;
//
//            Randomizer.resetRandomizer();
//            MyMatrix matrixFloat = new MyMatrix<WrappedFloat>(sizeOfMatrix,sizeOfMatrix,WrappedFloat.class);
//            matrixFloat.fillMatrixAndVector();
//            timestampBefore = System.currentTimeMillis();
//            resultOfGauss = matrixFloat.gauss();
//            timestampAfter = System.currentTimeMillis();
//            timesGaussFloat[i] = timestampAfter - timestampBefore;
//
//            Randomizer.resetRandomizer();
//            MyMatrix matrixMyType = new MyMatrix<MyType>(sizeOfMatrix,sizeOfMatrix, MyType.class);
//            matrixMyType.fillMatrixAndVector();
//            timestampBefore = System.currentTimeMillis();
//            resultOfGauss = matrixMyType.gauss();
//            timestampAfter = System.currentTimeMillis();
//            timesGaussMyType[i] = timestampAfter - timestampBefore;
//
//            sizes[i] = sizeOfMatrix;
//
//            sizeOfMatrix+=300;
//
//        }
//
//    }
//
//    /**
//     * Podaj czasy rozwiazania ukladu równan uzyskane dla macierzy o rozmiarze 500
//     * dla 9 testowanych wariantów.
//     */
//    public static void E1(){
//        testTypeE1Float(WrappedFloat.class);
//        testTypeE1Double(WrappedDouble.class);
//        testTypeE1MyType(MyType.class);
//
//
//    }
//    public static void testTypeE1Float(Class type){
//        long timestampBefore;
//        long timestampAfter;
//        long deltaTime;
//        double deltaSeconds;
//        MyMatrix matrix;
//        //Test matrix with 3 different gauss methods
//
//
//
//
//        //gauss
//        Randomizer.resetRandomizer();
//        matrix = new MyMatrix<WrappedFloat>(100,100,type);
//        matrix.fillMatrixAndVector();
//        timestampBefore = System.currentTimeMillis();
//        matrix.gauss();
//        timestampAfter = System.currentTimeMillis();
//        deltaTime = timestampAfter - timestampBefore;
//        deltaSeconds = (double)deltaTime / 1000d;
//        System.out.println("Method: Normal Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
//        E1FloatG = deltaSeconds;
//
//        //Part gauss
//        Randomizer.resetRandomizer();
//        matrix = new MyMatrix<WrappedFloat>(100,100,type);
//        matrix.fillMatrixAndVector();
//        timestampBefore = System.currentTimeMillis();
//        matrix.partChoiceGauss();
//        timestampAfter = System.currentTimeMillis();
//        deltaTime = timestampAfter - timestampBefore;
//        deltaSeconds = (double)deltaTime / 1000d;
//        System.out.println("Method: Part choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
//        E1FloatP = deltaSeconds;
//
//        //Full gaus
//        Randomizer.resetRandomizer();
//        matrix = new MyMatrix<WrappedFloat>(100,100,type);
//        matrix.fillMatrixAndVector();
//        timestampBefore = System.currentTimeMillis();
//        matrix.fulChoiceGauss();
//        timestampAfter = System.currentTimeMillis();
//        deltaTime = timestampAfter - timestampBefore;
//        deltaSeconds = (double)deltaTime / 1000d;
//        System.out.println("Method: Full choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
//        E1FloatF = deltaSeconds;
//
//    }
//    public static void testTypeE1Double(Class type){
//        long timestampBefore;
//        long timestampAfter;
//        long deltaTime;
//        double deltaSeconds;
//        MyMatrix matrix;
//        //Test matrix with 3 different gauss methods
//
//        //gauss
//        Randomizer.resetRandomizer();
//        matrix = new MyMatrix<WrappedDouble>(100,100,type);
//        matrix.fillMatrixAndVector();
//        timestampBefore = System.currentTimeMillis();
//        matrix.gauss();
//        timestampAfter = System.currentTimeMillis();
//        deltaTime = timestampAfter - timestampBefore;
//        deltaSeconds = (double)deltaTime / 1000d;
//        System.out.println("Method: Normal Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
//        E1DoubleG = deltaSeconds;
//
//        //Part gauss
//        Randomizer.resetRandomizer();
//        matrix = new MyMatrix<WrappedDouble>(100,100,type);
//        matrix.fillMatrixAndVector();
//        timestampBefore = System.currentTimeMillis();
//        matrix.partChoiceGauss();
//        timestampAfter = System.currentTimeMillis();
//        deltaTime = timestampAfter - timestampBefore;
//        deltaSeconds = (double)deltaTime / 1000d;
//        System.out.println("Method: Part choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
//        E1DoubleP = deltaSeconds;
//
//        //Full gaus
//        Randomizer.resetRandomizer();
//        matrix = new MyMatrix<WrappedDouble>(100,100,type);
//        matrix.fillMatrixAndVector();
//        timestampBefore = System.currentTimeMillis();
//        matrix.fulChoiceGauss();
//        timestampAfter = System.currentTimeMillis();
//        deltaTime = timestampAfter - timestampBefore;
//        deltaSeconds = (double)deltaTime / 1000d;
//        System.out.println("Method: Full choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
//        E1DoubleF = deltaSeconds;
//    }
//    public static void testTypeE1MyType(Class type){
//        long timestampBefore;
//        long timestampAfter;
//        long deltaTime;
//        double deltaSeconds;
//        MyMatrix matrix;
//        //Test matrix with 3 different gauss methods
//
//        //gauss
//        Randomizer.resetRandomizer();
//        matrix = new MyMatrix<MyType>(100,100,type);
//        matrix.fillMatrixAndVector();
//        timestampBefore = System.currentTimeMillis();
//        matrix.gauss();
//        timestampAfter = System.currentTimeMillis();
//        deltaTime = timestampAfter - timestampBefore;
//        deltaSeconds = (double)deltaTime / 1000d;
//        System.out.println("Method: Normal Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
//        E1MyTypeG = deltaSeconds;
//        //Part gauss
//        Randomizer.resetRandomizer();
//        matrix = new MyMatrix<MyType>(100,100,type);
//        matrix.fillMatrixAndVector();
//        timestampBefore = System.currentTimeMillis();
//        matrix.partChoiceGauss();
//        timestampAfter = System.currentTimeMillis();
//        deltaTime = timestampAfter - timestampBefore;
//        deltaSeconds = (double)deltaTime / 1000d;
//        System.out.println("Method: Part choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
//        E1MyTypeP = deltaSeconds;
//
//        //Full gaus
//        Randomizer.resetRandomizer();
//        matrix = new MyMatrix<MyType>(100,100,type);
//        matrix.fillMatrixAndVector();
//        timestampBefore = System.currentTimeMillis();
//        matrix.fulChoiceGauss();
//        timestampAfter = System.currentTimeMillis();
//        deltaTime = timestampAfter - timestampBefore;
//        deltaSeconds = (double)deltaTime / 1000d;
//        System.out.println("Method: Full choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
//        E1MyTypeF = deltaSeconds;
//    }
//
//}
