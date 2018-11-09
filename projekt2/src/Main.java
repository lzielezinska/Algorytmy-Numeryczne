import java.math.BigInteger;

public class Main {
    public static void main(String[] args){
        //Debug section
        System.out.println("hello");
        ANumber debugNum1 = new MyType(new BigInteger("2"), new BigInteger("3"));
        ANumber debugNum2 = new MyType(4, 5);
        ANumber debugAdd = (ANumber)debugNum1.add(debugNum2);
        ANumber debugSub = (ANumber)debugNum1.sub(debugNum2);
        ANumber debugMul = (ANumber)debugNum1.mul(debugNum2);
        ANumber debugDiv = (ANumber)debugNum1.div(debugNum2);
        System.out.println(debugAdd.toString());
        System.out.println(debugSub.toString());
        System.out.println(debugMul.toString());
        System.out.println(debugDiv.toString());
        for (int i = 0; i < 1000; i++){
           // System.out.println(Randomizer.generateRandomShort());
        }

    }
}
