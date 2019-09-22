package model;
import javax.persistence.*;


@Entity
@Table(name = "SAMOCHODY" )

public class Samochody {
    @Id
    @GeneratedValue
    private int idSamochodu;

    @Column(name = "marka" ,nullable = false)
    private String marka;

    @Column(name = "rocznik" , nullable = false)
    private int rocznik;




    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public int getRocznik() {
        return rocznik;
    }

    public void setRocznik(int rocznik) {
        this.rocznik = rocznik;
    }
}
