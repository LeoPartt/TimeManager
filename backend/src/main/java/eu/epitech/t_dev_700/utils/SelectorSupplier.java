package eu.epitech.t_dev_700.utils;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class SelectorSupplier<T, R> implements BiFunction<Optional<T>, Optional<T>, R> {

    private BiFunction<T, T, R> supplierOnBoth;
    private Function<T, R> supplierOnLeft;
    private Function<T, R> supplierOnRight;
    private Supplier<R> supplierOnNone;

    public SelectorSupplier<T, R> ifBoth(BiFunction<T, T, R> supplier) {
        this.supplierOnBoth = supplier;
        return this;
    }

    public SelectorSupplier<T, R> ifLeft(Function<T, R> supplier) {
        this.supplierOnLeft = supplier;
        return this;
    }

    public SelectorSupplier<T, R> ifRight(Function<T, R> supplier) {
        this.supplierOnRight = supplier;
        return this;
    }

    public SelectorSupplier<T, R> ifNone(Supplier<R> supplier) {
        this.supplierOnNone = supplier;
        return this;
    }

    @Override
    public R apply(Optional<T> leftValue, Optional<T> rightValue) {
        boolean left = leftValue.isPresent();
        boolean right = rightValue.isPresent();

        return (left && right) ? this.supplierOnBoth.apply(leftValue.get(), rightValue.get()) :
                (left) ? this.supplierOnLeft.apply(leftValue.get()) :
                        (right) ? this.supplierOnRight.apply(rightValue.get()) :
                                this.supplierOnNone.get();
    }
}
