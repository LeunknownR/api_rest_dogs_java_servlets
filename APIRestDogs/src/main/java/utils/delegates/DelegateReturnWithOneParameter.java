package utils.delegates;

@FunctionalInterface
public interface DelegateReturnWithOneParameter<R, P> {
    public R Execute(P parameter);
}