package br.com.cafebinario.logger.example;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.cafebinario.logger.Log;
import br.com.cafebinario.logger.VerboseMode;

@Component
public class Example {

	private static final String EMPTY = "";

	@Log(verboseMode=VerboseMode.ON)
	public String testVerboseON(final String value, final List<String> values) {
		return value
				.concat(values.stream()
				.reduce((a, b) -> orEmpty(a).concat(orEmpty(b)))
				.orElse(EMPTY));
	}
	
	@Log(verboseMode=VerboseMode.OFF)
	public String testVerboseOFF(final String value, final List<String> values) {
		return value
				.concat(values.stream()
				.reduce((a, b) -> orEmpty(a).concat(orEmpty(b)))
				.orElse(EMPTY));
	}
	
	@Log(verboseMode=VerboseMode.ON)
	public String testVerboseWithError(final String value, final List<String> values) {
		throw new IllegalArgumentException("error");
	}

	private String orEmpty(final String value) {
		return Optional
				.ofNullable(value)
				.orElse(EMPTY);
	}
}
