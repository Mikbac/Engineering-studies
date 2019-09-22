package Obiekty;
import model.*;

import java.util.ArrayList;
import java.util.List;
public class ObiektyTest1 {


    Dostawcy dostawcy1;
    Samochody samochody1;
    Szefowie szefowie1;
    Towary towary1;
    Towary towary2;
    TowaryWMagazynach TowaryWMagazynach1;

    public void setDostawcyy(List<Dostawcy> dostawcyy) {
        this.dostawcyy = dostawcyy;
    }

    List <Dostawcy> dostawcyy;

    public Dostawcy getDostawcy1() {
        return dostawcy1;
    }

    public Samochody getSamochody1() {
        return samochody1;
    }

    public Szefowie getSzefowie1() {
        return szefowie1;
    }

    public Towary getTowary1() {
        return towary1;
    }

    public Towary getTowary2() {
        return towary2;
    }

    public TowaryWMagazynach getTowaryWMagazynach1() {
        return TowaryWMagazynach1;
    }

    public List<Dostawcy> getDostawcyy() {
        return dostawcyy;
    }

    public void init() {


        Samochody samochody1 = new Samochody();
        samochody1.setMarka("Tesla");
        samochody1.setRocznik(2010);

        Szefowie szefowie1 = new Szefowie();
        szefowie1.setImie("Marek");
        szefowie1.setNazwisko("Gaska");
        szefowie1.setPesel("11111111111");

        Dostawcy dostawcy1 = new Dostawcy();
        dostawcy1.setNazwa("Pugs");
        dostawcy1.setRokZalozenia(1990);
        dostawcy1.setIdSzefa(11);
        dostawcy1.setSzef(szefowie1);
        dostawcy1.setSamochody(samochody1);

       Towary towary1 = new Towary();
        towary1.setNazwa("jablko");
        towary1.setCena(11);
        towary1.setDostawcy(dostawcy1);

        Towary towary2 = new Towary();
        towary2.setNazwa("gruszka");
        towary2.setCena(22);
        towary2.setDostawcy(dostawcy1);

        TowaryWMagazynach TowaryWMagazynach1 = new TowaryWMagazynach();
        TowaryWMagazynach1.setNazwaTowaru("gruszka");
        TowaryWMagazynach1.setIlosc(15);
        TowaryWMagazynach1.setDataDostawy(4,3,2000);
        TowaryWMagazynach1.setTowary(towary1);


      dostawcyy = new ArrayList<Dostawcy>();
      dostawcyy.add(dostawcy1);





    }


    public ObiektyTest1(){
        init();
    }
}

