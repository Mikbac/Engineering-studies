public class Question {

    private String tekst;
    private int przyrost;
    private String testowyTekst;
    private int ilosc;
    private int numer;


    private String[][] doZapisania = new String[100][100];


    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public Question() {

        tekst = "";
        ilosc = 0;
    }

    public String[][] getDoZapisania() {
        return doZapisania;
    }


    public void setNazwa(int tekst) {
        this.numer = numer;

    }

    public void setTekst(String tekst) {
        this.tekst = tekst;

    }


    public boolean test() {
        przyrost = 0;
        testowyTekst = "";
        int kolejka = 1;
        String[] podzielonyTekst = null;

        String tekst1 = "";

        tekst1 = tekst.concat(" <<end>>");
        podzielonyTekst = tekst1.split(" ");


        while (!(podzielonyTekst[przyrost].equals("<<end>>"))) {


            if ((podzielonyTekst[przyrost].equals("SELECT")) && (kolejka == 1)) {
                kolejka++;
            }
            if ((podzielonyTekst[przyrost].equals("FROM")) && (kolejka == 2)) {
                kolejka++;
            }
            if ((podzielonyTekst[przyrost].equals("WHERE")) && (kolejka == 3)) {
                kolejka++;
            }
            if ((podzielonyTekst[przyrost].equals("ORDER")) && (podzielonyTekst[przyrost + 1].equals("BY")) && (kolejka == 4)) {
                kolejka++;
            }


            przyrost++;
        }


        if (kolejka == 5) {
            return true;
        } else {
            return false;
        }

    }

    private int kontrola;


    public void dodanie() {

        kontrola = 0;

        for (int i = 0; i < ilosc; i++) {

            if (doZapisania[0][i].equals(Integer.toString(numer))) {

                doZapisania[1][i] = tekst;
                doZapisania[0][i] = Integer.toString(numer);
                kontrola = 1;
            }

        }

        if (kontrola == 0) {
            doZapisania[1][ilosc] = tekst;
            doZapisania[0][ilosc] = Integer.toString(numer);
            ilosc++;
        }


        WritingToAFile.setNapis(doZapisania);
        WritingToAFile.setLinie(ilosc);

    }


    public String wypisanie() {


        return doZapisania[0][ilosc - 1] + " == " + doZapisania[1][ilosc - 1];


    }


}
