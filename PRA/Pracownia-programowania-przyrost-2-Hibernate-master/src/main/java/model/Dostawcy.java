package model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.joda.time.DateTime;

//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="refId", scope=Dostawcy.class)
@Entity
@Table(name = "DOSTAWCY")
public class Dostawcy {

    @Id
    @Column(name = "idDostawcy")
    private int idDostawcy;

    @JsonProperty("nazwa")
    @Column(name = "nazwa")
    private String nazwa;

    @JsonProperty("rokZalozenia")
    @Column(name = "rokZalozenia")
    private int rokZalozenia;

    @JsonProperty("idSzefa")
    @Column(name = "idSzefa")
    private int idSzefa;



    /* == RELACJE ==*/


    /* -OneToOne-*/
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="SZEFOWIEID", referencedColumnName = "szefId")
    Szefowie szef;

    public Szefowie getSzef() {
        return szef;
    }

    public void setSzef(Szefowie szef) {
        this.szef = szef;
    }


    /* -OneToOne-*/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="SAMOCHOD", referencedColumnName = "idSamochodu")
    Samochody samochod;

    public Samochody getSamochody() {
        return samochod;
    }

    public void setSamochody(Samochody samochody) {
        this.samochod = samochody;
    }




    /* OneToMany */
  /*  @OneToMany(cascade = CascadeType.ALL,mappedBy = "dostawcy")
    private List<Towary> towary = new ArrayList<Towary>();

    public List<Towary> getTowary() {
        return towary;
    }

    public void setTowary(List<Towary> towary) {
        this.towary = towary;
    }
*/

/* ============================ */

    public int getIdDostawcy() {
        return idDostawcy;
    }

    public void setIdDostawcy(int idDostawcy) {
        this.idDostawcy = idDostawcy;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getRokZalozenia() {
        return rokZalozenia;
    }

    public void setRokZalozenia(int rokZalozenia) {
        this.rokZalozenia = rokZalozenia;
    }

    public int getIdSzefa() {
        return idSzefa;
    }

    public void setIdSzefa(int idSzefa) {
        this.idSzefa = idSzefa;
    }
}