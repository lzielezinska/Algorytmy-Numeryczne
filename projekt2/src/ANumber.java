
public abstract class ANumber<T> {
    protected T ZERO;
    protected T value;
    public abstract T add(T t);
    public abstract T sub(T t);
    public abstract T mul(T t);
    public abstract T div(T t);
    public abstract String toString();
    public abstract Double doubleValue();
    public abstract void setToZERO();
    public abstract void setToONE();
}
