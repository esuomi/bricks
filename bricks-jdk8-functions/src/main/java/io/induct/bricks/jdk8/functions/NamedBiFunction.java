package io.induct.bricks.jdk8.functions;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link BiFunction} which is aware of its parameter name.
 *
 * @since 2016-01-22
 */
public interface NamedBiFunction<T, U, R> extends LambdaFinder, BiFunction<T, U, R> {
    default List<String> names() {
        Stream<Parameter> lambdas = Arrays.stream(lambda().getParameters());
        Optional<Parameter> first = lambdas.findFirst();
        if (!first.isPresent()) {
            throw new IllegalStateException("No parameters available at all for BiFunction, something has to be wrong on a deeper level");
        }
        if (!first.get().isNamePresent()) {
            throw new IllegalStateException("You need to compile with javac -parameters for parameter reflection to work; You also need java 8u60 or newer to use it with lambdas");
        }
        return lambdas.map(Parameter::getName).collect(Collectors.toList());
    }
}
