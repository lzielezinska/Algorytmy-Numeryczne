class TypeException extends Exception{

}
public class TypeFacbic {
    private static final String MYTYPE = "MYTYPE";
    private static final String WRAPPEDFLOAT = "WRAPPEDFLOAT";
    private static final String WRAPPEDDOUBLE = "WRAPPEDDOUBLE";

    public static ANumber CreateNumber(String type) throws Exception{
        ANumber createdNumber = null;
        switch (type) {
            case MYTYPE:
                createdNumber = MyType.generateRandomNumber();
                break;
            case WRAPPEDFLOAT:
                createdNumber = WrappedFloat.generateRandomNumber();
                break;
            case WRAPPEDDOUBLE:
                createdNumber = WrappedDouble.generateRandomNumber();
                break;
        }
        if(createdNumber == null){
            throw new TypeException();
        }
        return createdNumber;
    }
}
