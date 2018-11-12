import static java.lang.System.*;

public class Main {
    public static void main(String[] args){
        out.println("Lucyna Zielezińska & Kacper Dondziak");
       // Tests.testAllTypes();
        Hypothesis.E1();
        MyType m = new MyType(1,2);
        System.out.println(m.changeSign().toString());
    }
}
