package br.com.cafebinario.logger;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder=true)
final class LogContext {

	private final String methodName;
	private final String[] parameterNames;
	private final Class<?>[] parameterTypes;
	private final Object[] parameterValues;
	private final Class<?> returnType;
	private final Object returnValue;
}
