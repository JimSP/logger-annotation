package br.com.cafebinario.logger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

public enum VerboseMode {

	ON, OFF;
		
	public void logReturn(final LogContext logContext, final Logger logger, final Object result) {
		
		if(ON == this) {
			final Object[] arguments = logContext.getParameterValues();
			final Object[] argumetsWithReturn = appendItem(appendItem(logContext.getMethodName(), arguments), result);
			final Object[] methodNameWithEnlapseTime = appendItem(argumetsWithReturn, logContext.getEnlapseTime());
			final String format = LogFormater.formatWithReturn(logContext);

			LogWriter.logParametersDebug(logger, format, methodNameWithEnlapseTime);
		}
	}
	
	public void logException(final LogContext logContext, final Logger logger, final Throwable e) {
		
		final Object[] arguments = logContext.getParameterValues();
		final Object[] methodNameWithArguments = appendItem(logContext.getMethodName(), arguments);
		final Object[] methodNameWithEnlapseTime = appendItem(methodNameWithArguments, logContext.getEnlapseTime());
		final Object[] methodNameWithException = appendItem(methodNameWithEnlapseTime, e);
		
		final String format = LogFormater.formatWithEnlapseTime(logContext);
		
		LogWriter.logParametersError(logger, format, methodNameWithException);
	}
	
	public void logParameters(final LogContext logContext, final Logger logger) {
		
		final Object[] arguments = logContext.getParameterValues();
		final Object[] methodNameWithArguments = appendItem(logContext.getMethodName(), arguments);
		final String format = LogFormater.format(logContext);
		LogWriter.logParametersInfo(logger, format, methodNameWithArguments);
		
	}
	
	private static Object[] appendItem(final Object[] items, final Object result) {
		final List<Object> temp = new ArrayList<>();
		
		for (Object object : items) {
			temp.add(object);
		}
		
		temp.add(result);
		
		return temp.toArray();
	}
	
	private static Object[] appendItem(final Object methodName, final Object[] items) {
		final List<Object> temp = new ArrayList<>();
		
		temp.add(methodName);
		
		for (Object object : items) {
			temp.add(object);
		}
		
		return temp.toArray();
	}
}
