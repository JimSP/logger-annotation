package br.com.cafebinario.logger;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Log {

	VerboseMode verboseMode() default VerboseMode.OFF;
	
}
