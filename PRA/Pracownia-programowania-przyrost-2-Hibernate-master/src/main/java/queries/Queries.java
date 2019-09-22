package queries;



import model.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.Query;
import java.sql.Array;


public class Queries {


        EntityManager entityManager;

        public Queries(EntityManager entityManager) {
            this.entityManager = entityManager;
        }


  /*  public List<Dostawcy> getDostawcy() {
        TypedQuery<Dostawcy> query = manager.createQuery("SELECT c FROM Dostawcy c", Dostawcy.class);
        return query.getResultList();
    }
*/
/*



    public List<Dostawcy> getDostawcy(){
        query = (Query) manager.createQuery("select d from Dostawcy d");
        return query.getResultList();
    }

*/

/*
    public List<Samochody> getSamochody() {
        TypedQuery<Samochody> query = manager.createQuery("SELECT c FROM Samochody c", Samochody.class);
        return query.getResultList();
    }

    public List<Szefowie> getSzefowie() {
        TypedQuery<Szefowie> query = manager.createQuery("SELECT c FROM Szefowie c", Szefowie.class);
        return query.getResultList();
    }

    public List<Towary> getTowary() {
        TypedQuery<Towary> query = manager.createQuery("SELECT c FROM Towary c", Towary.class);
        return query.getResultList();
    }

    public List<TowaryWMagazynach> getTowaryWMagazynach() {
        TypedQuery<TowaryWMagazynach> query = manager.createQuery("SELECT c FROM TowaryWMagazynach c", TowaryWMagazynach.class);
        query.setFirstResult(1);
        query.setMaxResults(5);
        return query.getResultList();
    }
*/


    public List<Dostawcy> getDostawcy() {
        Query query = entityManager.createQuery("SELECT a FROM Dostawcy a");
        List<Dostawcy> result = query.getResultList();
        return result;
    }

    public List<Samochody> getSamochody() {
        Query query = entityManager.createQuery("SELECT b FROM Samochody b");
        List<Samochody> result = query.getResultList();
        return result;
    }

    public List<Szefowie> getSzefowie() {
        Query query = entityManager.createQuery("SELECT c FROM Szefowie c");
        List<Szefowie> result = query.getResultList();
        return result;
    }

    public List<Towary> getTowary() {
        Query query = entityManager.createQuery("SELECT d FROM Towary d");
        List<Towary> result = query.getResultList();
        return result;
    }

    public List<TowaryWMagazynach> getTowaryWMagazynach() {
        Query query = entityManager.createQuery("SELECT e FROM TowaryWMagazynach e");
        List<TowaryWMagazynach> result = query.getResultList();
        query.setFirstResult(1);
        query.setMaxResults(5);
        return result;
    }







}




