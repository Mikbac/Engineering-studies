package model;
import javax.persistence.*;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "TOWARY" )
public class Towary {
    @Id
    @GeneratedValue
    private int idTowaru;


    @Column(name = "DostawcaId")
    private int id;


    @Column(name = "nazwa")
    private String nazwa;


    @Column(name = "cena")
    private float cena;





    /* ManyToOne */
    @ManyToOne
    @JoinColumn(name = "dostawcyID", referencedColumnName = "idDostawcy")
    private Dostawcy dostawcy;


    public Dostawcy getDostawcy() {
        return dostawcy;
    }

    public void setDostawcy(Dostawcy dostawcy) {
        this.dostawcy = dostawcy;
    }


    /* OneToMany */
   /* @OneToMany(cascade = CascadeType.ALL,mappedBy = "towary")
    private List<TowaryWMagazynach> towarywmagazynach = new ArrayList<TowaryWMagazynach>();

    public List<TowaryWMagazynach> getTowarywmagazynach() {
        return towarywmagazynach;
    }

    public void setTowarywmagazynach(List<TowaryWMagazynach> towarywmagazynach) {
        this.towarywmagazynach = towarywmagazynach;
   }
*/

    /* ============================ */


    public int getIdTowaru() {
        return idTowaru;
    }

    public void setIdTowaru(int idTowaru) {
        this.idTowaru = idTowaru;
    }



    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {

        return nazwa;
    }

    public float getCena() {
        return cena;
    }

    public int getId() {
        return id;
    }
}
