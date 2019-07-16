package br.com.cafebinario.logger;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public final class LogFormater {

	static String format(final LogContext logContext) {
	    final String[] parameterNames = Optional.ofNullable(logContext.getParameterNames()).orElse(new String[0]);
	    
		return "m:{}" + formatArguments(parameterNames);
	}
	
	protected static String formatWithEnlapseTime(final LogContext logContext) {
		return format(logContext).concat(", enlapseTime:{}");
	}
	
	protected static String formatWithReturn(final LogContext logContext) {
		
		return format(logContext).concat(", return:{}, enlapseTime:{}");
	}

	private static String formatArguments(final String[] parameterNames) {
		
	    Assert.noNullElements(parameterNames, "The parameterNames must contain non-null elements");
	    
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
