package br.com.cafebinario.logger;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class LogConfiguration {

}
