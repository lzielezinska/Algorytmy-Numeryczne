
public abstract class ANumber<T> {
    protected T value;
    public abstract T add(T t);
    public abstract T sub(T t);
    public abstract T mul(T t);
    public abstract T div(T t);
    public abstract String toString();
}
