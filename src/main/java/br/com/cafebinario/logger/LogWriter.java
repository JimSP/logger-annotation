package br.com.cafebinario.logger;

import org.slf4j.Logger;

public final class LogWriter {
	
	protected static void log(final LogLevel logLevel, final Logger logger, final String format, final Object... arguments) {
		switch (logLevel) {
		case TRACE:
			logger.trace(format, arguments);
			break;
		
		case DEBUG:
			logger.debug(format, arguments);
			break;
		
		case INFO:
			logger.info(format, arguments);
			break;
			
		case WARN:
			logger.warn(format, arguments);
			break;
			
		case ERROR:
			logger.error(format, arguments);
			break;
			
		case FATAL:
			logger.error(format, arguments);
			break;

		default:
			break;
		}
	}
	
	private LogWriter() {
		
	}
}
