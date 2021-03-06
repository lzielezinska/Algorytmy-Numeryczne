import java.math.BigInteger;

/**
 * Created by Lucyna & Kacper on 05.11.18.
 */
public class MyType extends ANumber<MyType> implements Comparable<MyType>{
    private BigInteger licz;
    private BigInteger mian;
    public MyType(Integer licz, Integer mian){
        this(new BigInteger(licz.toString()), new BigInteger(mian.toString()));
    }
    public MyType(BigInteger licz, BigInteger mian){
        this.licz = licz;
        this.mian = mian;
    }
    @Override
    public MyType returnZero(){
        return new MyType(0,1);
    }

    public static MyType generateRandomNumber() {
        MyType result;
        result = new MyType(Randomizer.generateRandomShort(),65536);
        return result;
    }
    public void simplyfy(){
        BigInteger a = this.licz.abs();
        BigInteger b = this.mian.abs();
        BigInteger c;
        while(!b.equals(BigInteger.valueOf(0))){
            c = a.mod(b);
            a = b;
            b = c;
        }
        if(!a.equals(BigInteger.valueOf(0))){
            this.licz = this.licz.divide(a);
            this.mian = this.mian.divide(a);
        }
    }

    @Override
    public MyType add(MyType num) {
        MyType result;
        if(this.mian.equals(num.mian)){
            result = new MyType(this.licz.add(num.licz), this.mian);
        } else {
            BigInteger newMian = this.mian.multiply(num.mian);
            BigInteger newLicz1 = this.licz.multiply(num.mian);
            BigInteger newLicz2 = num.licz.multiply(this.mian);
            BigInteger newLicz3 = newLicz1.add(newLicz2);
            if(newLicz3.signum() == -1 && newMian.signum() == -1){
                newLicz3.negate();
                newMian.negate();
            }
            result = new MyType(newLicz3, newMian);
        }
        result.simplyfy();
        if(this.mian.intValue() == 0 || this.licz.intValue() == 0 || this.mian.intValue() == -0 || this.licz.intValue() == -0){
            this.licz = new BigInteger("0");
            this.mian = new BigInteger("1");
        }
        return result;
    }

    @Override
    public MyType sub(MyType num) {
        MyType result;
        if(this.mian.equals(num.mian)){
            result = new MyType(this.licz.subtract(num.licz), this.mian);
        } else {
            BigInteger newMian = this.mian.multiply(num.mian);
            BigInteger newLicz1 = this.licz.multiply(num.mian);
            BigInteger newLicz2 = num.licz.multiply(this.mian);
            result = new MyType(newLicz1.subtract(newLicz2), newMian);
        }
        result.simplyfy();
        if(this.mian.intValue() == 0 || this.licz.intValue() == 0 || this.mian.intValue() == -0 || this.licz.intValue() == -0){
            this.licz = new BigInteger("0");
            this.mian = new BigInteger("1");
        }
        return result;
    }

    @Override
    public MyType mul(MyType num) {
        MyType result;
        BigInteger newMian = this.mian.multiply(num.mian);
        BigInteger newLicz = this.licz.multiply(num.licz);
        result = new MyType(newLicz, newMian);
        result.simplyfy();
        if(this.mian.intValue() == 0 || this.licz.intValue() == 0 || this.mian.intValue() == -0 || this.licz.intValue() == -0){
            this.licz = new BigInteger("0");
            this.mian = new BigInteger("1");
        }
        return result;
    }

    @Override
    public MyType div(MyType num) {
        MyType result;
        BigInteger newMian = this.mian.multiply(num.licz);
        BigInteger newLicz = this.licz.multiply(num.mian);
        result = new MyType(newLicz, newMian);
        result.simplyfy();
        if(this.mian.intValue() == 0 || this.licz.intValue() == 0 || this.mian.intValue() == -0 || this.licz.intValue() == -0){
            this.licz = new BigInteger("0");
            this.mian = new BigInteger("1");
        }
        return result;
    }

    @Override
    public MyType changeSign() {
        BigInteger minusOne = new BigInteger("-1");
        MyType result = new MyType(this.licz.multiply(minusOne), this.mian);
        return result;
    }

    @Override
    public String toString() {
        /*Double result = this.licz.doubleValue() / this.mian.doubleValue();
        return result.toString();*/
        String result = this.licz.toString() + "/" + this.mian.toString();
        return result;
    }

    @Override
    public Double doubleValue() {
        double result = this.licz.doubleValue() / this.mian.doubleValue();
        if(Double.isNaN(result)){
            System.out.println(this.licz.doubleValue() + " " + this.mian.doubleValue());
        }
        return this.licz.doubleValue() / this.mian.doubleValue();
    }

    @Override
    public MyType abs() {
        return new MyType(this.licz.abs(),this.mian.abs());
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
    @Override
    public int compareTo(MyType o) {
        return this.licz.multiply(o.mian).compareTo(o.licz.multiply(this.mian));
    }
    public String fractValue(){
        return this.licz + "/" + this.mian;
    }
}
