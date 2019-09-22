import java.time.LocalTime;


public final class Time {


    private static LocalTime localTime1 = LocalTime.now();
    private static LocalTime testTime = LocalTime.now();


    private static int czas2;
    private static int minuty;


    public static String przerwaCzyZajecia(LocalTime localTime11) {
        String odpowiedz = "o tej godzinie nie ma zajec";

        if ((localTime11.isAfter(testTime.of(8, 15)) && (localTime11.isBefore(testTime.of(9, 45)))) ||
                (localTime11.isAfter(testTime.of(10, 00)) && (localTime11.isBefore(testTime.of(11, 30)))) ||
                (localTime11.isAfter(testTime.of(11, 45)) && (localTime11.isBefore(testTime.of(13, 15)))) ||
                (localTime11.isAfter(testTime.of(13, 45)) && (localTime11.isBefore(testTime.of(15, 15)))) ||
                (localTime11.isAfter(testTime.of(15, 30)) && (localTime11.isBefore(testTime.of(17, 00)))) ||
                (localTime11.isAfter(testTime.of(17, 15)) && (localTime11.isBefore(testTime.of(18, 45))))) {
            odpowiedz = "zajecia";
        }
        if ((localTime11.isAfter(testTime.of(9, 45)) && (localTime11.isBefore(testTime.of(10, 00)))) ||
                (localTime11.isAfter(testTime.of(11, 30)) && (localTime11.isBefore(testTime.of(11, 45)))) ||
                (localTime11.isAfter(testTime.of(13, 15)) && (localTime11.isBefore(testTime.of(13, 45)))) ||
                (localTime11.isAfter(testTime.of(15, 15)) && (localTime11.isBefore(testTime.of(15, 30)))) ||
                (localTime11.isAfter(testTime.of(17, 00)) && (localTime11.isBefore(testTime.of(17, 15))))) {
            odpowiedz = "przerwa";
        }


        return odpowiedz;
    }

    public static void podajCzasDoKonca() {


        //-------------------------ZAJECIA

        localTime1 = LocalTime.now();

        if (localTime1.isAfter(testTime.of(8, 15)) && (localTime1.isBefore(testTime.of(9, 45)))) {

            czas2 = 9 * 60 + 45;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca zajęć");
        }

        if (localTime1.isAfter(testTime.of(10, 00)) && (localTime1.isBefore(testTime.of(11, 30)))) {

            czas2 = 11 * 60 + 30;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca zajęć");
        }

        if (localTime1.isAfter(testTime.of(11, 45)) && (localTime1.isBefore(testTime.of(13, 15)))) {

            czas2 = 13 * 60 + 15;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca zajęć");
        }


        if (localTime1.isAfter(testTime.of(13, 45)) && (localTime1.isBefore(testTime.of(15, 15)))) {

            czas2 = 15 * 60 + 15;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca zajęć");
        }


        if (localTime1.isAfter(testTime.of(15, 30)) && (localTime1.isBefore(testTime.of(17, 00)))) {

            czas2 = 17 * 60 + 0;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca zajęć");
        }

        if (localTime1.isAfter(testTime.of(17, 15)) && (localTime1.isBefore(testTime.of(18, 45)))) {

            czas2 = 18 * 60 + 45;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca zajęć");
        }

//-------------------------PRZERWY


        if (localTime1.isAfter(testTime.of(9, 45)) && (localTime1.isBefore(testTime.of(10, 00)))) {

            czas2 = 10 * 60 + 00;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca przerywy");
        }

        if (localTime1.isAfter(testTime.of(11, 30)) && (localTime1.isBefore(testTime.of(11, 45)))) {

            czas2 = 11 * 60 + 45;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca przerywy");
        }

        if (localTime1.isAfter(testTime.of(13, 15)) && (localTime1.isBefore(testTime.of(13, 45)))) {

            czas2 = 13 * 60 + 45;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca przerywy");
        }

        if (localTime1.isAfter(testTime.of(15, 15)) && (localTime1.isBefore(testTime.of(15, 30)))) {

            czas2 = 15 * 60 + 30;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca przerywy");
        }

        if (localTime1.isAfter(testTime.of(17, 00)) && (localTime1.isBefore(testTime.of(17, 15)))) {

            czas2 = 17 * 60 + 15;

            minuty = localTime1.getHour() * 60 + localTime1.getMinute();

            System.err.println(czas2 - minuty + " minut do końca przerywy");
        }


    }


}
