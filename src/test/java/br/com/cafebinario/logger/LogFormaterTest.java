package br.com.cafebinario.logger;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

public class LogFormaterTest {

    @Test
    public void testWithEmptyLogContext() {
        assertThat(LogFormater.format(LogContext.builder().build()), IsEqual.equalTo("m:{}"));
    }
    
    @Test
    public void test() {
        assertThat(LogFormater.format(LogContext
                .builder()
                .parameterNames(new String[] {"parameterA", "parameterB"})
                .build()), IsEqual.equalTo("m:{}, parameterA:{}, parameterB:{}"));
    }
    
    @Test
    public void testWithEmptyParametersName() {
        assertThat(LogFormater.format(LogContext
                .builder()
                .parameterNames(new String[] {})
                .build()), IsEqual.equalTo("m:{}"));
    }
    
    @Test
    public void testWithEnlapseTime() {
        assertThat(LogFormater.formatWithEnlapseTime(LogContext
                .builder()
                .parameterNames(new String[] {"parameterA", "parameterB"})
                .build()), IsEqual.equalTo("m:{}, parameterA:{}, parameterB:{}, enlapseTime:{}"));
    }
    
    @Test
    public void testWithReturn() {
        assertThat(LogFormater.formatWithReturn(LogContext
                .builder()
                .parameterNames(new String[] {"parameterA", "parameterB"})
                .build()), IsEqual.equalTo("m:{}, parameterA:{}, parameterB:{}, return:{}, enlapseTime:{}"));
    }
}
