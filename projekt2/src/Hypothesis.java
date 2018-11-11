public class Hypothesis {
    /**
     * Dla dowolnego ustalonego rozmiaru macierzy czas dzialania metody Gaussa w ko-
     * lejnych wersjach (1, 2, 3) ro±nie.
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
     * Jak zalezy dokaadnos¢ obliczen (baad) od rozmiaru macierzy dla dwóch wybranych
     * przez Ciebie wariantów metody Gaussa gdy obliczenia prowadzone sa na typie
     * podwójnej precyzji (TD)?
     */
    public static void Q1(){

    }

    /**
     * Jak przy wybranym przez Ciebie wariancie metody Gaussa zalezy czas dzialnia
     * algorytmu od rozmiaru macierzy i róznych typów?
     */
    public static void Q2(){

    }

    /**
     * Podaj czasy rozwiazania ukaadu równa« uzyskane dla macierzy o rozmiarze 500
     * dla 9 testowanych wariantów.
     */
    public static void E1(){
        testTypeE1(WrappedFloat.class);
        testTypeE1(WrappedDouble.class);
        testTypeE1(MyType.class);
    }
    public static void testTypeE1(Class type){
        long timestampBefore;
        long timestampAfter;
        long deltaTime;
        double deltaSeconds;
        //Test matrix with 3 different gauss methods

        //gauss
        Randomizer.resetRandomizer();
        MyMatrix matrix = new MyMatrix<WrappedFloat>(500,500,type);
        matrix.fillMatrix();
        timestampBefore = System.currentTimeMillis();
        matrix.gauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Normal Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");

        //Part gauss
        Randomizer.resetRandomizer();
        matrix = new MyMatrix<WrappedFloat>(500,500,type);
        matrix.fillMatrix();
        timestampBefore = System.currentTimeMillis();
        matrix.partChoiceGauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Part choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");

        //Full gaus
        Randomizer.resetRandomizer();
        matrix = new MyMatrix<WrappedFloat>(500,500,type);
        matrix.fillMatrix();
        timestampBefore = System.currentTimeMillis();
        matrix.fulChoiceGauss();
        timestampAfter = System.currentTimeMillis();
        deltaTime = timestampAfter - timestampBefore;
        deltaSeconds = (double)deltaTime / 1000d;
        System.out.println("Method: Full choice Gauss " +"Type: " + type + " time: " + deltaSeconds + "s");
    }

}
