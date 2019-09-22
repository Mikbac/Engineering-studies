package model;
import javax.persistence.*;


@Entity
@Table(name = "SZEFOWIE" )
public class Szefowie {
    @Id
    @GeneratedValue
    private int szefId;

    @Column(name = "imie", nullable = false)
    private String imie;


    @Column(name = "nazwisko" , nullable = false)
    private String nazwisko;


    @Column(name = "pesel", nullable = false)
    private String pesel;










    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getSzefId() {
        return szefId;
    }

    public void setSzefId(int szefId) {
        this.szefId = szefId;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
