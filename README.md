# logger-annotation


## Pré Requisito.
	O Projeto deve obrigatóriamente ser uma aplicação Spring Boot com suporte para Aspectos.


### Anotação @Log para métodos.

	Será feita escrita automática na saída padrão de log (api SLF4) quando o método for anotado com @Log.

	VerboseMode.ON = escreve no Log os parametros e o retorno do método.
	VerboseMode.OFF = escreve no Log apenas os parametros método.
   
	Sempre que ocorrer uma Exception, a anotação @Log irá interceptar o Erro e escrever a mesma no log.


### Propriedade br.com.cafebinario.logger.verboseMode:

	br.com.cafebinario.logger.verboseMode: 'ON'
	-> será desconsiderado o parametro informado na anotação @Log(verboseMode) e assumirá a propriedade.
	
	br.com.cafebinario.logger.verboseMode: 'OFF'
	-> será considerado o parametro informado na anotação @Log(verboseMode)


### Exemplo: (assumindo br.com.cafebinario.logger.verboseMode=OFF)

```java
package br.com.cafebinario.logger.example;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.cafebinario.logger.Log;
import br.com.cafebinario.logger.VerboseMode;

@Component
public class Example {

	private static final String EMPTY = "";

	/*** 
	 * LOG OUTPUT
	 * [INFO] m:testVerboseON, value:a, values:[b, c]
	 * [DEBUG] m:testVerboseON, value:a, values:[b, c], return:abc, enlapseTime:18
	 ***/
	@Log(verboseMode=VerboseMode.ON)
	public String testVerboseON(final String value, final List<String> values) {
		
	    return value
				.concat(values.stream()
				.reduce((a, b) -> orEmpty(a).concat(orEmpty(b)))
				.orElse(EMPTY));
	}
	
	/***
	 * LOG OUTPUT
	 * [INFO] testVerboseOFF, value:a, values:[b, c]
	 ***/
	@Log(verboseMode=VerboseMode.OFF)
	public String testVerboseOFF(final String value, final List<String> values) {
		
	    return value
				.concat(values.stream()
				.reduce((a, b) -> orEmpty(a).concat(orEmpty(b)))
				.orElse(EMPTY));
	}
	
	/***
	 * LOG OUTPUT
	 * [INFO] testVerboseOFF, value:a, values:[b, c]
	 * [ERROR] m:testVerboseWithError, value:a, values:[b, c], enlapseTime:0
	 * java.lang.IllegalArgumentException: error
	 * ...
	 ***/
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
```


### Maven

	<dependencies>
	...
	
		<dependency>
			<groupId>br.com.cafebinario</groupId>
			<artifactId>logger-annotation</artifactId>
			<version>1.0.0-RELEASE</version>
		</dependency>
	
	...
	<dependencies>

	
	<repositories>
	...
	
		<repository>
			<id>mvn-repo</id>
			<url>https://raw.github.com/JimSP/artifacts/master</url>
	
			<releases>
				<enabled>true</enabled>
			</releases>
	
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
	
	...
	</repositories>


### Gradle

	buildscript {
	    repositories {
			...
			
			maven {
	      		url 'https://raw.github.com/JimSP/artifacts/master'
	    	}
	    	
	    	...
		}
	}

	dependencies {
		...
		
		compile 'br.com.cafebinario:logger-annotation:1.0.0-RELEASE'
		
		...
	}