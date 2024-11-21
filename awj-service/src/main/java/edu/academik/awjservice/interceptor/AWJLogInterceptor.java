package edu.academik.awjservice.interceptor;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import lombok.extern.java.Log;

@Interceptor
@AWJLog
@Priority(Interceptor.Priority.APPLICATION)
@Log
public class AWJLogInterceptor {

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        // Reflection
        log.log(Level.INFO, "Método: {0}.", context.getMethod().getName());

        if (context.getParameters() != null) {

            Stream.of(context.getParameters())
                    .forEach(parameter -> log.log(Level.INFO, "Parámetro:  {0}.", parameter));

        }

        Object result = context.proceed();

        log.log(Level.INFO, "Resultado: {0}.", result);

        return result;
    }
}
