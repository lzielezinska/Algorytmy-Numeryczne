import java.math.BigInteger;

/**
 * Created by Lucyna & Kacper on 05.11.18.
 */
public class MyType extends ANumber<MyType>{
    public static MyType ZERO = new MyType(0,1);
    public static MyType ONE = new MyType(1,1);
    private BigInteger licz;
    private BigInteger mian;
    public MyType(Integer licz, Integer mian){
        this(new BigInteger(licz.toString()), new BigInteger(mian.toString()));
    }
    public MyType(BigInteger licz, BigInteger mian){
        this.licz = licz;
        this.mian = mian;
    }

    public static MyType generateRandomNumber() {
        MyType result;
        result = new MyType(Randomizer.generateRandomShort(),65536);
        return result;
    }
    private void simplyfy(){
        BigInteger a = this.licz;
        BigInteger b = this.mian;
        while(!a.abs().equals(b.abs())){
            if(a.doubleValue() > b.doubleValue()){
                a = a.subtract(b);
            } else {
                b = b.subtract(a);
            }
        }
        this.licz = this.licz.divide(a);
        this.mian = this.mian.divide(a);
    }
    @Override
    public MyType add(MyType num) {
        MyType result;
        if(this.mian.equals(num.mian)){
            result = new MyType(this.licz.add(num.mian), this.mian);
        } else {
            BigInteger newMian = this.mian.multiply(num.mian);
            BigInteger newLicz1 = this.licz.multiply(num.mian);
            BigInteger newLicz2 = num.licz.multiply(this.mian);
            result = new MyType(newLicz1.add(newLicz2), newMian);
        }
        result.simplyfy();
        return result;
    }

    @Override
    public MyType sub(MyType num) {
        MyType result;
        if(this.mian.equals(num.mian)){
            result = new MyType(this.licz.subtract(num.mian), this.mian);
        } else {
            BigInteger newMian = this.mian.multiply(num.mian);
            BigInteger newLicz1 = this.licz.multiply(num.mian);
            BigInteger newLicz2 = num.licz.multiply(this.mian);
            result = new MyType(newLicz1.subtract(newLicz2), newMian);
        }
        result.simplyfy();
        return result;
    }

    @Override
    public MyType mul(MyType num) {
        MyType result;
        BigInteger newMian = this.mian.multiply(num.mian);
        BigInteger newLicz = this.licz.multiply(num.licz);
        result = new MyType(newLicz, newMian);
        result.simplyfy();
        return result;
    }

    @Override
    public MyType div(MyType num) {
        MyType result;
        BigInteger newMian = this.mian.multiply(num.licz);
        BigInteger newLicz = this.licz.multiply(num.mian);
        result = new MyType(newLicz, newMian);
        result.simplyfy();
        return result;
    }

    @Override
    public MyType sign(MyType myType) {
        return null;
    }

    @Override
    public String toString() {
        Double result = this.licz.doubleValue() / this.mian.doubleValue();
        return result.toString();
    }

    @Override
    public Double doubleValue() {
        return this.licz.doubleValue() / this.mian.doubleValue();
    }
    @Override
    public void setToZERO() {
        this.licz = new BigInteger("0");
        this.mian = new BigInteger("1");
    }
    @Override
    public void setToONE() {
        this.licz = new BigInteger("1");
        this.mian = new BigInteger("1");
    }

}
