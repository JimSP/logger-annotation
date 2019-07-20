package br.com.cafebinario.logger.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cafebinario.logger.LogInterceptor;
import br.com.cafebinario.logger.example.Example;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Example.class, LogInterceptor.class, ConfigTest.class })
@TestPropertySource(properties = { "br.com.cafebinario.logger.verboseMode=ON" })
public class ExampleTestVerboseModeOn {

    @Autowired
    private Example example;

    @Test
    public void testVerboseON() {
        assertThat(example.testVerboseON("a", Arrays.asList("b", "c"))).isEqualTo("abc");
    }

    @Test
    public void testVerboseOFF() {
        assertThat(example.testVerboseOFF("a", Arrays.asList("b", "c"))).isEqualTo("abc");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithError() {
        example.testVerboseWithError("a", Arrays.asList("b", "c"));
    }
}
