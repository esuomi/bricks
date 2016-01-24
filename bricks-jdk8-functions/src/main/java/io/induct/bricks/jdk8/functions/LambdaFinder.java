package io.induct.bricks.jdk8.functions;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * Lookup for finding matching lambda instance (if any). Will break if used in non-lambda context.
 *
 * @since 2015-12-30
 */
public interface LambdaFinder extends Serializable {
    // this has been unrolled for optimal performance
    default Method lambda() {
        try {
            Method replaceMethod = getClass().getDeclaredMethod("writeReplace");
            replaceMethod.setAccessible(true);
            SerializedLambda lambda = (SerializedLambda) replaceMethod.invoke(this);
            Class<?> containingClass = Class.forName(lambda.getImplClass().replace("/", "."));
            return containingClass.getDeclaredMethod(lambda.getImplMethodName(), getClass());
        } catch (ReflectiveOperationException e) {
            throw new UnableToGuessMethodException(e);
        }
    }

    class UnableToGuessMethodException extends RuntimeException {
        public UnableToGuessMethodException(Throwable t) {
            super(t);
        }
    }
}
