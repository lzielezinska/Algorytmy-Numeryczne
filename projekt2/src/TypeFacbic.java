class TypeException extends Exception{
}
public class TypeFacbic {
    public static ANumber CreateNumber(Class type) throws Exception{
        ANumber createdNumber = null;
        if(type.equals(MyType.class)){
            createdNumber = MyType.generateRandomNumber();
        } else if(type.equals(WrappedFloat.class)){
            createdNumber = WrappedFloat.generateRandomNumber();
        } else if(type.equals(WrappedDouble.class)){
            createdNumber = WrappedDouble.generateRandomNumber();
        } else{
            throw new TypeException();
        }
        return createdNumber;
    }
}
