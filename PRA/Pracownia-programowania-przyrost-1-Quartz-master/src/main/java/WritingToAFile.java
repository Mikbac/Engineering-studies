
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public final class WritingToAFile {

    private static String napis[][] = new String[100][100];
    private static int linie;

    public static void setNapis(String napiss[][]) {
        napis = napiss;
    }

    public static void setLinie(int liniee) {
        linie = liniee;
    }

    public static void zapis() {


        PrintWriter zapis = null;
        try {
            zapis = new PrintWriter("odp.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int j = 0; j <= linie; j++) {
            for (int i = 0; i < linie; i++) {
                if (Integer.parseInt(napis[0][i]) == j) {
                    zapis.println(napis[0][i]);
                    zapis.println(napis[1][i] + "\n" + "\n");
                }
            }
        }


        zapis.close();

    }


}
