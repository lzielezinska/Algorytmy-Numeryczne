
public abstract class ANumber<T> implements Comparable<ANumber> {
    public abstract T returnZero();

    public abstract T add(T t);

    public abstract T sub(T t);

    public abstract T mul(T t);

    public abstract T div(T t);

    public abstract T changeSign();

    public abstract String toString();

    public abstract Double doubleValue();

    public abstract void setToZERO();

    public abstract void setToONE();

    public abstract double abs();

    public int compareTo(ANumber o) {
        if (this.doubleValue() > o.doubleValue())
            return -1;
        else if (this.doubleValue() < o.doubleValue())
            return 1;
        else
            return 0;
    }
}
