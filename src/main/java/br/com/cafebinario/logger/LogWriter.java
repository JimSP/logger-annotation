package br.com.cafebinario.logger;

import org.slf4j.Logger;

public final class LogWriter {

	protected static void logParametersInfo(final Logger logger, final String format, final Object... arguments) {
		logger.info(format, arguments);
	}
	
	protected static void logParametersDebug(final Logger logger, final String format, final Object... arguments) {
		logger.debug(format, arguments);
	}

	
	protected static void logParametersError(final Logger logger, final String format, final Object... arguments) {
		logger.error(format, arguments);
	}
	
	private LogWriter() {
		
	}
}
