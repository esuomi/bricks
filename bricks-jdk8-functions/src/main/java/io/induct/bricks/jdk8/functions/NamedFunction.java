package io.induct.bricks.jdk8.functions;

import java.util.Objects;
import java.util.function.Function;

/**
 * {@link Function} which is aware of its parameter name.
 *
 * @since 2016-01-22
 */
public interface NamedFunction<I, O> extends LambdaFinder, Function<I, O> {

    default String name() {
        String lambdaName = lambda().getParameters()[0].getName();
        if (Objects.equals("arg0", lambdaName)) {
            throw new IllegalStateException("You need to compile with javac -parameters for parameter reflection to work; You also need java 8u60 or newer to use it with lambdas");
        }
        return lambdaName;
    }
}
