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
import java.util.List;


public class JacksonSerializationZObiektyZBazyDanych {
    public static void serializacja(ObjectMapper mapper, String typ, String nazwaKlasy, String nazwa) throws IOException {

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


        ObiektyZBazyDanych baza = new ObiektyZBazyDanych();

            switch (nazwaKlasy) {
                case "Dostawcy":
                    List<Dostawcy> dostawcy = baza.getDostawcyy();
                    String dostawcyListSerialized = mapper.writeValueAsString(dostawcy);
                    mapper.writeValue(new File(nazwa + "." + typ), dostawcy);
                    System.out.println(dostawcyListSerialized);
                    break;
                case "Samochody":
                    List<Samochody> samochody = baza.getSamochodyy();
                    String samochodyList = mapper.writeValueAsString(samochody);
                    mapper.writeValue(new File(nazwa + "." + typ), samochody);
                    System.out.println(samochodyList);
                    break;
                case "Szefowie":
                    List<Szefowie> szefowie = baza.getSzefowiey();
                    String szefowieList = mapper.writeValueAsString(szefowie);
                    mapper.writeValue(new File(nazwa + "." + typ), szefowie);
                    System.out.println(szefowieList);
                    break;
                case "Towary":
                    List<Towary> towary = baza.getTowaryy();
                    String towaryList = mapper.writeValueAsString(towary);
                    mapper.writeValue(new File(nazwa + "." + typ), towary);
                    System.out.println(towaryList);
                    break;
                case "TowaryWMagazynach":
                    List<TowaryWMagazynach> towaryWMagazynach = baza.getTowaryWMagazynachy();
                    String towaryWMagazynachList = mapper.writeValueAsString(towaryWMagazynach);
                    mapper.writeValue(new File(nazwa + "." + typ), towaryWMagazynach);
                    System.out.println(towaryWMagazynachList);
                    break;
                default:
                    System.err.println("problem z klasa");
                    break;
            }

    }

    public static void main(String[] args) throws IOException {


        ObjectMapper jsonMapper = new ObjectMapper();
        ObjectMapper xmlMapper = new XmlMapper();

        //Dostawcy
        serializacja(jsonMapper,"json","Dostawcy","dostawcyZBazyDanych");
        serializacja(xmlMapper,"xml","Dostawcy","dostawcyZBazyDanych");

        //Samochody

        serializacja(jsonMapper,"json","Samochody","SamochodyZBazyDanych");
        serializacja(xmlMapper,"xml","Samochody","SamochodyZBazyDanych");

        //Szefowie

        serializacja(jsonMapper,"json","Szefowie","SzefowieZBazyDanych");
        serializacja(xmlMapper,"xml","Szefowie","SzefowieZBazyDanych");

        //Towary

        serializacja(jsonMapper,"json","Towary","TowaryZBazyDanych");
        serializacja(xmlMapper,"xml","Towary","TowaryZBazyDanych");

        //TowaryWMagazynach

        serializacja(jsonMapper,"json","TowaryWMagazynach","TowaryWMagazynachZBazyDanych");
        serializacja(xmlMapper,"xml","TowaryWMagazynach","TowaryWMagazynachZBazyDanych");



    }
}