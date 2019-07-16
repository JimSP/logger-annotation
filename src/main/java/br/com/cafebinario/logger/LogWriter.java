package br.com.cafebinario.logger;

import org.slf4j.Logger;

final class LogWriter {
	
	static void logParametersWarn(final Logger logger, final String format, final Object... arguments) {
		logger.debug(format, arguments);
	}

	static void logParametersInfo(final Logger logger, final String format, final Object... arguments) {
		logger.info(format, arguments);
	}
	
	static void logParametersDebug(final Logger logger, final String format, final Object... arguments) {
		logger.debug(format, arguments);
	}
	
	static void logParametersTrace(final Logger logger, final String format, final Object... arguments) {
		logger.trace(format, arguments);
	}
	
	static void logParametersError(final Logger logger, final String format, final Object... arguments) {
		logger.error(format, arguments);
	}
	
	private LogWriter() {
		
	}
}
