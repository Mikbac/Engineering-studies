import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuestionTest {


    @Test
    public void test1() throws Exception {

        Question d1 = new Question();
        d1.setTekst("zadanie1 SELECT * FROM * WHERE * ORDER BY **");

        assertEquals(true, d1.test());


    }


    @Test
    public void test2() throws Exception {

        Question d1 = new Question();
        d1.setTekst("SELECT nazwisko,   (placa + ISNULL(dod_funkc, 0)) * 12  AS [placa roczna] FROM   Pracownicy WHERE * ORDER BY **");

        assertEquals(true, d1.test());


    }

    @Test
    public void test3() throws Exception {

        Question d1 = new Question();
        d1.setTekst("zadanie1  * FROM * WHERE * ORDER BY **");

        assertEquals(false, d1.test());


    }


}
