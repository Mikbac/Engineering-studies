package ZapisIWczytanieZBazyDanych;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import javax.persistence.Persistence;

import java.util.Arrays;

public class Zapis {


    public static void ZapisDoBazyDanych(Object[] obiektDoZapisania, boolean test) {

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;
        try {

            System.out.println(Arrays.toString(obiektDoZapisania));

            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            if (test==true) {
                for (Object obj : obiektDoZapisania) {
                    entityManager.persist(obj);
                }
            } else {
                System.out.println("Brak danych do zapisania");
            }
            entityManager.getTransaction().commit();

            entityManager.close();
        } catch (Throwable ex) {

            System.err.println("blad");
        } finally {
            entityManagerFactory.close();
        }


    }
}