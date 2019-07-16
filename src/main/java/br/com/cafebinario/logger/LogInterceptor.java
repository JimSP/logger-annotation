package br.com.cafebinario.logger;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogInterceptor {

    @Around("@annotation(Log)")
    public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        final Long begin = System.currentTimeMillis();

        final Class<?> targetClass = proceedingJoinPoint.getSignature().getDeclaringType();
        
        final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint
                .getSignature();
        
        final Object[] args = proceedingJoinPoint.getArgs();

        final LogContext logContext = createLogContext(methodSignature, args);

        final VerboseMode verboseMode = getVerboseMode(methodSignature);

        final Logger logger = LoggerFactory.getLogger(targetClass);

        try {

            return proccessLog(proceedingJoinPoint, begin, logContext, verboseMode, logger);

        } catch (Throwable t) {

            verboseMode.logException(
                    logContext
                    .toBuilder()
                    .enlapseTime(System.currentTimeMillis() - begin)
                    .build(),
                    logger, t);

            throw t;
        }
    }

    private LogContext createLogContext(final MethodSignature methodSignature,
            final Object[] args) {

        final String methodName = methodSignature.getName();
        final String[] parameterNames = methodSignature.getParameterNames();
        final Class<?>[] parameterTypes = methodSignature.getParameterTypes();
        final Class<?> returnType = methodSignature.getReturnType();

        return LogContext
                .builder()
                .methodName(methodName)
                .parameterNames(parameterNames)
                .parameterTypes(parameterTypes)
                .parameterValues(args)
                .returnType(returnType)
                .build();
    }

    private Object proccessLog(final ProceedingJoinPoint proceedingJoinPoint, final Long begin,
            final LogContext logContext, final VerboseMode verboseMode, final Logger logger)
            throws Throwable {

        verboseMode.logParameters(logContext, logger);

        final Object returnValue = proceedingJoinPoint.proceed();

        verboseMode.logReturn(
                logContext
                    .toBuilder()
                    .returnValue(returnValue)
                        .enlapseTime(System.currentTimeMillis() - begin)
                        .build(),
                logger, returnValue);

        return returnValue;
    }

    private VerboseMode getVerboseMode(final MethodSignature methodSignature) {

        final Method method = methodSignature.getMethod();

        final Log logAnnotation = method.getDeclaredAnnotation(Log.class);

        return logAnnotation.verboseMode();
    }
}
