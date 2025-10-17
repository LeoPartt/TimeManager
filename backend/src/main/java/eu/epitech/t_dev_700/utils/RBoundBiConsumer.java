package eu.epitech.t_dev_700.utils;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface RBoundBiConsumer<T> extends Consumer<T> {

    static <T, U> Consumer<T> of(BiConsumer<T, U> bi, U value) {
        return t -> bi.accept(t, value);
    }
}
