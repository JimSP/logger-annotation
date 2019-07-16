package br.com.cafebinario.logger;

import java.util.Arrays;

import org.springframework.util.StringUtils;

final class LogFormater {

	static String format(final LogContext logContext) {

		return "m:{}" + formatArguments(logContext.getParameterNames());
	}
	
	public static String formatWithReturn(final LogContext logContext) {
		
		return format(logContext).concat(", return:{}");
	}

	private static String formatArguments(final String[] parameterNames) {
		
		final String format = Arrays
			.asList(parameterNames)
			.stream()
			.map(parameterName->parameterName.concat(":{}"))
			.reduce((a,b)->a
				.concat(", ")
				.concat(b))
			.orElse("");
		
		return StringUtils.hasText(format) ? ", ".concat(format) : format;
	}
	
	private LogFormater() {
		
	}
}
