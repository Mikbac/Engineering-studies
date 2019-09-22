package SerializacjaIDeserializacja;

import model.*;
import ZapisIWczytanieZBazyDanych.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

import static ZapisIWczytanieZBazyDanych.Zapis.ZapisDoBazyDanych;

public class JacksonDeSerialization {

    Zapis ZapisDoBazyDanych = new Zapis();

    public static void fileDeserialize(ObjectMapper mapper, String nazwaPliku, String nazwaKlasy) throws IOException {

        boolean test = true;
        Object[] obiektDoZapisania = new Object[]{};




        switch (nazwaKlasy) {
            case "Dostawcy":
                obiektDoZapisania = mapper.readValue(new File(nazwaPliku), Dostawcy[].class);
                break;
            default:
                System.err.println("problem z klasa... prawdopodobnie nie ma takiej klasy");
                test = false;
                break;
        }


        ZapisDoBazyDanych(obiektDoZapisania,test);
    }


    public static void main(String[] args) throws IOException {
        ObjectMapper xmlMapper = new XmlMapper();
        fileDeserialize(xmlMapper, "dostawcy.xml","Dostawcy");

        // ObjectMapper jsonMapper = new ObjectMapper();
        // fileDeserialize(jsonMapper, "dostawcy.json","Dostawcy");

    }


}