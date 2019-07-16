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
	public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		final Method method = methodSignature.getMethod();
		final String methodName = methodSignature.getName();
		final String[] parameterNames = methodSignature.getParameterNames();
		final Class<?>[] parameterTypes = methodSignature.getParameterTypes();
		final Class<?> returnType = methodSignature.getReturnType();
		
		final Log logAnnotation = method.getDeclaredAnnotation(Log.class);
		
		final VerboseMode verboseMode = logAnnotation.verboseMode();
		
		final LogContext logContext = LogContext
				.builder()
				.methodName(methodName)
				.parameterNames(parameterNames)
				.parameterTypes(parameterTypes)
				.parameterValues(proceedingJoinPoint.getArgs())
				.returnType(returnType)
				.build();

		final Logger logger = LoggerFactory.getLogger(proceedingJoinPoint.getSignature().getDeclaringType());
		
		try {
			
			verboseMode.logParameters(logContext, logger);
			
			final Object returnValue = proceedingJoinPoint.proceed();
			
			verboseMode.logReturn(logContext
					.toBuilder()
					.returnValue(returnValue)
					.build(), logger, returnValue);

			return returnValue;
			
		}catch (Throwable t) {
			verboseMode.logException(logContext, logger, t);
			throw t;
		}
	}
}
