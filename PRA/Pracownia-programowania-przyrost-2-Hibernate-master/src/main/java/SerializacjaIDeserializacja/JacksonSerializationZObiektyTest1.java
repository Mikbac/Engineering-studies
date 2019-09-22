package SerializacjaIDeserializacja;

import Obiekty.ObiektyTest1;
import model.*;
import Obiekty.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JacksonSerializationZObiektyTest1 {

  private static  ObiektyTest1 baza = new ObiektyTest1();

  private static String wartoscZwracana;

    public JacksonSerializationZObiektyTest1() {
        baza.init();



    }

    public static void inneDane(List<Dostawcy> dane)
    {
        baza.setDostawcyy(dane);
    }

    public static String getWartoscZwracana() {
        return wartoscZwracana;
    }

    public static void serialize(ObjectMapper mapper, String typ, String nazwaKlasy, String nazwa) throws IOException {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);




            //zrobione w ten sposob zeby moc dodawac kolejne tabele (tak jak w JacksonSerializationZObiektyZBazyDanych)
            switch (nazwaKlasy) {
                case "Dostawcy":
                    List<Dostawcy> dostawcy = baza.getDostawcyy();
                    String dostawcyList = mapper.writeValueAsString(dostawcy);
                    mapper.writeValue(new File(nazwa + "." + typ), dostawcy);
                 //   System.out.println(dostawcyList);
                    wartoscZwracana=dostawcyList;

                    break;
                default:
                    System.err.println("problem z klasa");
                    break;
            }

    }

    public static void main(String[] args) throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();
        serialize(jsonMapper,"json","Dostawcy","Dostawcy");

        ObjectMapper xmlMapper = new XmlMapper();
        serialize(xmlMapper,"xml","Dostawcy","Dostawcy");

    }


}
