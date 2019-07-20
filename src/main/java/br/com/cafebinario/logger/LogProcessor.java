package br.com.cafebinario.logger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

final class LogProcessor {

	private LogProcessor() {
		
	}
	
	public static void logReturn(final LogContext logContext, final Logger logger, final Object result) {
		
		if(verboseModeIsOn(logContext)) {
			
			final Object[] arguments = logContext.getParameterValues();
			final Object[] argumetsWithReturn = appendItem(appendItem(logContext.getMethodName(), arguments), result);
			final Object[] methodNameWithEnlapseTime = appendItem(argumetsWithReturn, logContext.getEnlapseTime());
			final String format = LogFormater.formatWithReturn(logContext);
			final LogLevel logLevel = logContext.getLog().logLevel();

			LogWriter.log(logLevel, logger, format, methodNameWithEnlapseTime);
		}
	}

	public static void logException(final LogContext logContext, final Logger logger, final Throwable e) {
		
		final Object[] arguments = logContext.getParameterValues();
		final Object[] methodNameWithArguments = appendItem(logContext.getMethodName(), arguments);
		final Object[] methodNameWithEnlapseTime = appendItem(methodNameWithArguments, logContext.getEnlapseTime());
		final Object[] methodNameWithException = appendItem(methodNameWithEnlapseTime, e);
		
		final String format = LogFormater.formatWithEnlapseTime(logContext);
		
		LogWriter.log(LogLevel.ERROR, logger, format, methodNameWithException);
	}
	
	public static void logParameters(final LogContext logContext, final Logger logger) {
		
		final Object[] arguments = logContext.getParameterValues();
		final Object[] methodNameWithArguments = appendItem(logContext.getMethodName(), arguments);
		final String format = LogFormater.format(logContext);
		final LogLevel logLevel = logContext.getLog().logLevel();
		
		LogWriter.log(logLevel, logger, format, methodNameWithArguments);
		
	}
	
	private static boolean verboseModeIsOn(final LogContext logContext) {
		
		final VerboseMode systeVerboseMode = logContext.getSystemVerboseMode();
		final Log log = logContext.getLog();
		final VerboseMode verboseMode = log.verboseMode();
		final boolean systeVerboseModeIsOn = systeVerboseMode.isOn();
		
		return systeVerboseModeIsOn ? systeVerboseModeIsOn : verboseMode.isOn();
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
