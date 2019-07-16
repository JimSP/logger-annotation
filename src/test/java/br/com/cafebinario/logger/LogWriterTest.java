package br.com.cafebinario.logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LogInterceptor.class)
public class LogWriterTest {

    private static final Object[] PARAMETERS = { "teste" };
    private static final String FORMAT = "teste:{}";
    @MockBean
    private Logger logger;

    @Test
    public void testDebug() {
        LogWriter.logParametersDebug(logger, FORMAT, PARAMETERS);
        Mockito.verify(logger).debug(FORMAT, PARAMETERS);
    }
    
    @Test
    public void testInfo() {
        LogWriter.logParametersInfo(logger, FORMAT, PARAMETERS);
        Mockito.verify(logger).info(FORMAT, PARAMETERS);
    }
    
    @Test
    public void testError() {
        LogWriter.logParametersError(logger, FORMAT, PARAMETERS);
        Mockito.verify(logger).error(FORMAT, PARAMETERS);
    }
}
