package Obiekty;
import model.*;


import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import queries.*;


public class ObiektyZBazyDanych {

    List<Dostawcy> dostawcyy;
    List<Samochody> samochodyy;
    List<Szefowie> szefowiey;
    List<Towary> towaryy;
    List<TowaryWMagazynach> towaryWMagazynachy;

    public List<Dostawcy> getDostawcyy() {
        return dostawcyy;
    }

    public void setDostawcyy(List<Dostawcy> dostawcyy) {
        this.dostawcyy = dostawcyy;
    }

    public List<Samochody> getSamochodyy() {
        return samochodyy;
    }

    public void setSamochodyy(List<Samochody> samochodyy) {
        this.samochodyy = samochodyy;
    }

    public List<Szefowie> getSzefowiey() {
        return szefowiey;
    }

    public void setSzefowiey(List<Szefowie> szefowiey) {
        this.szefowiey = szefowiey;
    }

    public List<Towary> getTowaryy() {
        return towaryy;
    }

    public void setTowaryy(List<Towary> towaryy) {
        this.towaryy = towaryy;
    }

    public List<TowaryWMagazynach> getTowaryWMagazynachy() {
        return towaryWMagazynachy;
    }

    public void setTowaryWMagazynachy(List<TowaryWMagazynach> towaryWMagazynachy) {
        this.towaryWMagazynachy = towaryWMagazynachy;
    }

    public void init() {

      //  Queries queries;
        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

      //  Queries  queries = new Queries(entityManager);
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // zapytania
    //    Queries quer = new Queries(entityManager);
 /*       Query q1 = null, q2 = null,q3 = null,q4 = null,q5 = null;



        q1 =  entityManager.createQuery("SELECT a FROM Samochody a");
        q2 =  entityManager.createQuery("SELECT b FROM Szefowie b");
        q3 =  entityManager.createQuery("SELECT c FROM Dostawcy c");
        q4 =  entityManager.createQuery("SELECT d FROM Towary d");
        q5 =  entityManager.createQuery("SELECT e FROM TowaryWMagazynach e");


        samochodyy = q1.getResultList();
        szefowiey = q2.getResultList();
        dostawcyy = q3.getResultList();
        towaryy = q4.getResultList();
        towaryWMagazynachy = q5.getResultList();


        */
        this.samochodyy= new Queries(entityManager).getSamochody();
        this.szefowiey= new Queries(entityManager).getSzefowie();
        this.dostawcyy= new Queries(entityManager).getDostawcy();
        this.towaryy= new Queries(entityManager).getTowary();
        this.towaryWMagazynachy= new Queries(entityManager).getTowaryWMagazynach();


       /* this.szefowiey = quer.getSzefowie();
        this.dostawcyy = quer.getDostawcy();
        this.towaryy = quer.getTowary();
        this.towaryWMagazynachy = quer.getTowaryWMagazynach();
*/


      //  dostawcyy = queries.getDostawcy();


        entityManager.close();

        entityManagerFactory.close();




    }


  public  ObiektyZBazyDanych(){
        init();
    }

}
