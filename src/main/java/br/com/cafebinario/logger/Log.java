package br.com.cafebinario.logger;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface Log {

	VerboseMode verboseMode() default VerboseMode.OFF;
	LogLevel logLevel() default LogLevel.INFO;

}
