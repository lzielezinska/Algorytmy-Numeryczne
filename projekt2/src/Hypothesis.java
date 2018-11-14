import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Hypothesis {
    private static double E1FloatG;
    private static double E1FloatP;
    private static double E1FloatF;

    private static double E1DoubleG;
    private static double E1DoubleP;
    private static double E1DoubleF;

    private static double E1MyTypeG;
    private static double E1MyTypeP;
    private static double E1MyTypeF;


    /**
     * Dla dowolnego ustalonego rozmiaru macierzy czas dzialania metody Gaussa w ko-
     * lejnych wersjach (1, 2, 3) rosnie.
     */
    public static void H1(){

    }

    /**
     * Dla dowolnego ustalonego rozmiaru macierzy bład uzyskanego wyniku metody
     * Gaussa w kolejnych wersjach (1, 2, 3) maleje.
     */
    public static void H2(){

    }

    /**
     * Uzycie wlasnej arytmetyki na uaamkach zapewnia bezbledne wyniki niezaleznie od
     * wariantu metody Gaussa i rozmiaru macierzy.
     */
    public static void H3(){

    }

    /**
     * Jak zalezy dokladnosc obliczen (blad) od rozmiaru macierzy dla dwóch wybranych
     * przez Ciebie wariantów metody Gaussa gdy obliczenia prowadzone sa na typie
     * podwójnej precyzji (TD)?
     */
    public static void Q1(){
        int sizes[] = {20, 40, 60, 80, 100};
        ANumber resultOfGauss[];
        ANumber resultOfPartGauss[];
        double normOfGauss;
        double normOfPartGauss;
        XYSeries gauss = new XYSeries("Gauss");
        XYSeries partGauss = new XYSeries("Part Gauss");


        for(int i : sizes){
            Randomizer.resetRandomizer();
            MyMatrix matrix = new MyMatrix<WrappedFloat>(i,i,WrappedDouble.class);
            matrix.fillMatrixAndVector();
            resultOfGauss = matrix.gauss();
            normOfGauss = matrix.getNormInf(resultOfGauss, matrix.getSavedVector());

            Randomizer.resetRandomizer();
            matrix = new MyMatrix<WrappedFloat>(i,i,WrappedDouble.class);
            matrix.fillMatrixAndVector();
            resultOfPartGauss = matrix.partChoiceGauss();
            normOfPartGauss = matrix.getNormInf(resultOfPartGauss, matrix.getSavedVector());

            gauss.add(i,normOfGauss);
            partGauss.add(i,normOfPartGauss);
        }
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(gauss);
        xySeriesCollection.addSeries(partGauss);
        ChartUtilis.printChart(
                xySeriesCollection,
                "Q1",
                "Q1: Blad od rozmiatu macierzy (TD)",
                "Rozmiar Macierzy",
                "Blad (Norma nieskonczonosc)"
        );
    }

    /**
     * Jak przy wybranym przez Ciebie wariancie metody Gaussa zalezy czas dzialnia
     * algorytmu od rozmiaru macierzy i róznych typów?
     */
    public static void Q2(){

    }

    /**
     * Podaj czasy rozwiazania ukladu równan uzyskane dla macierzy o rozmiarze 500
     * dla 9 testowanych wariantów.
     */
    public static void E1(){
        testTypeE1Float(WrappedFloat.class);
        testTypeE1Double(WrappedDouble.class);
        testTypeE1MyType(MyType.class);
        XYSeries gaussF = new XYSeries("Gauss Float");
        gaussF.add(1,E1FloatG);
        XYSeries partGaussF = new XYSeries("Part Gauss Float");
        partGaussF.add(1,E1FloatP);
        XYSeries fullGaussF = new XYSeries("Full Gauss Float");
        fullGaussF.add(1,E1FloatF);
        XYSeries gaussD = new XYSeries("Gauss Double");
        gaussD.add(2,E1DoubleG);
        XYSeries partGaussD = new XYSeries("Part Gauss Double");
        partGaussD.add(2,E1DoubleP);
        XYSeries fullGaussD = new XYSeries("Full Gauss Double");
        fullGaussD.add(2,E1DoubleF);
        XYSeries gaussM = new XYSeries("Gauss Mytype");
        gaussM.add(3,E1MyTypeG);
        XYSeries partGaussM = new XYSeries("Part Gauss Mytype");
        partGaussM.add(3,E1MyTypeP);
        XYSeries fullGaussM = new XYSeries("Full Gauss Mytype");
        fullGaussM.add(3,E1MyTypeF);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(gaussF);
        xySeriesCollection.addSeries(partGaussF);
        xySeriesCollection.addSeries(fullGaussF);
        xySeriesCollection.addSeries(gaussD);
        xySeriesCollection.addSeries(partGaussD);
        xySeriesCollection.addSeries(fullGaussD);
        xySeriesCollection.addSeries(gaussM);
        xySeriesCollection.addSeries(partGaussM);
        xySeriesCollection.addSeries(fullGaussM);
        ChartUtilis.printChart(
                xySeriesCollection,
                "E1",
                "E1: Czas dla macierzy 150",
                "Typ zmiennej 1 - Float, 2 - Double, 3 - MyType",
                "Czas [s]"
        );
    }
    public static void testTypeE1Float(Class type){
        long timestampBefore;
        long timestampAfter;
        long deltaTime;
        double deltaSeconds;
        //Test matrix with 3 different gauss methods

        //gauss
        Randomizer.resetRandomizer();
        MyMatrix matrix = new MyMatrix<WrappedFloat>(150,150,type);
        matrix.fillMatrixAndVector();
        timestampBefore = System.currentTimeMillis();
        matrix.gauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Normal Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
        E1FloatG = deltaSeconds;

        //Part gauss
        Randomizer.resetRandomizer();
        matrix = new MyMatrix<WrappedFloat>(150,150,type);
        matrix.fillMatrixAndVector();
        timestampBefore = System.currentTimeMillis();
        matrix.partChoiceGauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Part choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
        E1FloatP = deltaSeconds;
        //Full gaus
        Randomizer.resetRandomizer();
        matrix = new MyMatrix<WrappedFloat>(150,150,type);
        matrix.fillMatrixAndVector();
        timestampBefore = System.currentTimeMillis();
        matrix.fulChoiceGauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Full choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
        E1FloatF = deltaSeconds;
    }
    public static void testTypeE1Double(Class type){
        long timestampBefore;
        long timestampAfter;
        long deltaTime;
        double deltaSeconds;
        //Test matrix with 3 different gauss methods

        //gauss
        Randomizer.resetRandomizer();
        MyMatrix matrix = new MyMatrix<WrappedDouble>(150,150,type);
        matrix.fillMatrixAndVector();
        timestampBefore = System.currentTimeMillis();
        matrix.gauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Normal Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
        E1DoubleG = deltaSeconds;

        //Part gauss
        Randomizer.resetRandomizer();
        matrix = new MyMatrix<WrappedDouble>(150,150,type);
        matrix.fillMatrixAndVector();
        timestampBefore = System.currentTimeMillis();
        matrix.partChoiceGauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Part choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
        E1DoubleP = deltaSeconds;

        //Full gaus
        Randomizer.resetRandomizer();
        matrix = new MyMatrix<WrappedDouble>(150,150,type);
        matrix.fillMatrixAndVector();
        timestampBefore = System.currentTimeMillis();
        matrix.fulChoiceGauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Full choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
        E1DoubleF = deltaSeconds;
    }
    public static void testTypeE1MyType(Class type){
        long timestampBefore;
        long timestampAfter;
        long deltaTime;
        double deltaSeconds;
        //Test matrix with 3 different gauss methods

        //gauss
        Randomizer.resetRandomizer();
        MyMatrix matrix = new MyMatrix<MyType>(150,150,type);
        matrix.fillMatrixAndVector();
        timestampBefore = System.currentTimeMillis();
        matrix.gauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Normal Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
        E1MyTypeG = deltaSeconds;
        //Part gauss
        Randomizer.resetRandomizer();
        matrix = new MyMatrix<MyType>(150,150,type);
        matrix.fillMatrixAndVector();
        timestampBefore = System.currentTimeMillis();
        matrix.partChoiceGauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Part choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
        E1MyTypeP = deltaSeconds;

        //Full gaus
        Randomizer.resetRandomizer();
        matrix = new MyMatrix<MyType>(150,150,type);
        matrix.fillMatrixAndVector();
        timestampBefore = System.currentTimeMillis();
        matrix.fulChoiceGauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Full choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
        E1MyTypeF = deltaSeconds;
    }

}
