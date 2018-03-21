package tests;

import org.junit.Before;
import org.junit.Test;
import ru.isu.compmodels.imitation.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 */
public class MockitoTests {
    Request request;

    @Before
    public void prepare(){
        //Создаем, например, мок запроса
        request = mock(Request.class);
    }

    @Test
    public void testDemo(){
        //todo пишем какой-то код, который дергает метод getLoad()
        request.getLoad();

        // Проверяем, что метод getLoad() был вызван
        verify(request).getLoad();
    }
}
