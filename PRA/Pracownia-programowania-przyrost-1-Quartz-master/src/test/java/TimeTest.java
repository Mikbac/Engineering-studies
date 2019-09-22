import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;


public class TimeTest {


    @Test
    public void test1() throws Exception {


        LocalTime time = LocalTime.of(8, 40);

        assertEquals("zajecia", Time.przerwaCzyZajecia(time));

    }

    @Test
    public void test2() throws Exception {


        LocalTime time = LocalTime.of(13, 40);

        assertEquals("przerwa", Time.przerwaCzyZajecia(time));

    }

    @Test
    public void test3() throws Exception {


        LocalTime time = LocalTime.of(00, 00);

        assertEquals("o tej godzinie nie ma zajec", Time.przerwaCzyZajecia(time));

    }


}
