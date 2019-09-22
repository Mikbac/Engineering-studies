package model;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "TOWARYWMAGAZYNACH" )

public class TowaryWMagazynach {
    @Id
    @GeneratedValue
    @Column(name = "idMagazynu")
    private int idMagazynu;


    @Column(name = "nazwaTowaru")
    private String nazwaTowaru;

    @Column(name = "ilosc")
    private float ilosc;


    @Column(name = "dataDostawy")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDostawy;

    public LocalDate getDataDostawy() {
        return dataDostawy;
    }

    public void setDataDostawy(int day, int month, int year) {
        LocalDate pomoc = null;
        dataDostawy =pomoc.of(year,month,day);
    }

    /* ManyToOne */
    @ManyToOne
    @JoinColumn(name = "id_Towaru_W_Magazynie", referencedColumnName = "idTowaru")
    private Towary towary;

    public Towary getTowary() {
        return towary;
    }

    public void setTowary(Towary towary) {
        this.towary = towary;
    }
    /* ============================ */

    public int getIdMagazynu() {
        return idMagazynu;
    }

    public void setIdMagazynu(int idMagazynu) {
        this.idMagazynu = idMagazynu;
    }



    public String getNazwaTowaru() {
        return nazwaTowaru;
    }

    public void setNazwaTowaru(String nazwaTowaru) {
        this.nazwaTowaru = nazwaTowaru;
    }

    public float getIlosc() {
        return ilosc;
    }

    public void setIlosc(float ilosc) {
        this.ilosc = ilosc;
    }
}
