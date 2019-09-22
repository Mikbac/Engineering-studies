package SerializacjaIDeserializacja;

import Obiekty.ObiektyTest1;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.Dostawcy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test;

import static org.junit.Assert.*;

public class JacksonSerializationZObiektyTest1Test {
    @Test
    public void serializeJSON() throws Exception {


        List<Dostawcy> dostawcyy;


        Dostawcy dostawcy1 = new Dostawcy();
        dostawcy1.setNazwa("PugsTestyyyy");
        dostawcy1.setRokZalozenia(2222);
        dostawcy1.setIdSzefa(33);
        dostawcy1.setSzef(null);
        dostawcy1.setSamochody(null);

        dostawcyy = new ArrayList<Dostawcy>();
        dostawcyy.add(dostawcy1);


//ustawienie wartosci


        JacksonSerializationZObiektyTest1.inneDane(dostawcyy);




        ObjectMapper jsonMapper = new ObjectMapper();
        JacksonSerializationZObiektyTest1.serialize(jsonMapper,"json","Dostawcy","testDostawcy");



        String txsml, tjson;

        tjson="[ {\r\n" +
                "  \"idDostawcy\" : 0,\r\n" +
                "  \"szef\" : null,\r\n" +
                "  \"samochody\" : null,\r\n" +
                "  \"nazwa\" : \"PugsTestyyyy\",\r\n" +
                "  \"rokZalozenia\" : 2222,\r\n" +
                "  \"idSzefa\" : 33\r\n" +
                "} ]";

        System.out.println(  tjson);
      System.out.println(  JacksonSerializationZObiektyTest1.getWartoscZwracana());


        //  assertEquals("eee","eee");
        assertEquals(tjson, JacksonSerializationZObiektyTest1.getWartoscZwracana());


    }
    @Test
    public void serializeXML() throws Exception {


        List<Dostawcy> dostawcyy;


        Dostawcy dostawcy1 = new Dostawcy();
        dostawcy1.setNazwa("PugsTestyyyy");
        dostawcy1.setRokZalozenia(2222);
        dostawcy1.setIdSzefa(33);
        dostawcy1.setSzef(null);
        dostawcy1.setSamochody(null);

        dostawcyy = new ArrayList<Dostawcy>();
        dostawcyy.add(dostawcy1);


//ustawienie wartosci


        JacksonSerializationZObiektyTest1.inneDane(dostawcyy);




         ObjectMapper xmlMapper = new XmlMapper();
          JacksonSerializationZObiektyTest1.serialize(xmlMapper,"xml","Dostawcy","testDostawcy");


        String txsml, tjson;

        txsml="<ArrayList>\r\n" +
                "  <item>\r\n" +
                "    <idDostawcy>0</idDostawcy>\r\n" +
                "    <szef/>\r\n" +
                "    <samochody/>\r\n" +
                "    <nazwa>PugsTestyyyy</nazwa>\r\n" +
                "    <rokZalozenia>2222</rokZalozenia>\r\n" +
                "    <idSzefa>33</idSzefa>\r\n" +
                "  </item>\r\n" +
                "</ArrayList>\r\n";




        System.out.println(  txsml);
        System.out.println(  JacksonSerializationZObiektyTest1.getWartoscZwracana());


        assertEquals(txsml, JacksonSerializationZObiektyTest1.getWartoscZwracana());


    }
}