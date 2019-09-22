import model.*;
import queries.Queries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class Main {

   public static void main(String[] args) {



       System.out.println("Start");

       EntityManager entityManager = null;

       EntityManagerFactory entityManagerFactory = null;

       Queries queries = null;

       try {


           entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

           entityManager = entityManagerFactory.createEntityManager();

           entityManager.getTransaction().begin();

           Queries query = new Queries(entityManager);
            /*  -----------------------------  */


           Samochody samochod = new Samochody();
           samochod.setMarka("Tesla");
           samochod.setRocznik(2010);


           Szefowie szef = new Szefowie();
           szef.setImie("Marek");
           szef.setNazwisko("Gaska");
           szef.setPesel("11111111111");





           Dostawcy dostawca = new Dostawcy();
           dostawca.setNazwa("Mainex");
           dostawca.setRokZalozenia(1984);
           dostawca.setIdSzefa(11);
           dostawca.setSzef(szef);
           dostawca.setSamochody(samochod);



           Towary towar1 = new Towary();
           towar1.setNazwa("jablko");
           towar1.setCena(11);
           towar1.setDostawcy(dostawca);

           Towary towar2 = new Towary();
           towar2.setNazwa("gruszka");
           towar2.setCena(22);
           towar2.setDostawcy(dostawca);

           TowaryWMagazynach magazyn1 = new TowaryWMagazynach();
           magazyn1.setNazwaTowaru("gruszka");
           magazyn1.setIlosc(15);
           magazyn1.setDataDostawy(4,3,2000);
           magazyn1.setTowary(towar1);

           TowaryWMagazynach magazyn2 = new TowaryWMagazynach();
           magazyn2.setNazwaTowaru("melon");
           magazyn2.setIlosc(16);



           //save
           entityManager.persist(dostawca);
           entityManager.persist(samochod);
           entityManager.persist(szef);
           entityManager.persist(towar1);
           entityManager.persist(towar2);
           entityManager.persist(magazyn1);
           entityManager.persist(magazyn2);




            /*  -----------------------------  */
           entityManager.getTransaction().commit();

           System.out.println("Done");

           entityManager.close();

       } catch (Throwable ex) {


           System.err.println("Initial SessionFactory creation failed." + ex);


       } finally {

           entityManagerFactory.close();

       }












   }
}
