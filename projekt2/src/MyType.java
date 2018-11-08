import java.math.BigInteger;

/**
 * Created by Lucyna & Kacper on 05.11.18.
 */
public class MyType extends ANumber<MyType> {
    private BigInteger licz;
    private BigInteger mian;
    public MyType(BigInteger licz, BigInteger mian){
        this.licz = licz;
        this.mian = mian;
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
        return result;
    }

    @Override
    public MyType mul(MyType num) {
        MyType result;
        BigInteger newMian = this.mian.multiply(num.mian);
        BigInteger newLicz = this.licz.multiply(num.licz);
        result = new MyType(newLicz, newMian);
        return result;
    }

    @Override
    public MyType div(MyType num) {
        MyType result;
        BigInteger newMian = this.mian.multiply(num.licz);
        BigInteger newLicz = this.licz.multiply(num.mian);
        result = new MyType(newLicz, newMian);
        return result;
    }

    @Override
    public String toString() {
        String result = this.licz + "/" + this.mian;
        return result;
    }
}
