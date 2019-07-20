package br.com.cafebinario.logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver;

@Configuration
@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
public class LogConfiguration implements LoadTimeWeavingConfigurer {

	@Override
	public LoadTimeWeaver getLoadTimeWeaver() {

		return new ReflectiveLoadTimeWeaver();
	}

}
