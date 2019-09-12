import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;


/*
        1.s326156 - Alexandros Messaritakis Chousein Aga

*/
public class Oblig1 {


    //oppgave 1
    public static int maks(int[] a) {

        // Kaster exception hvis arrayet er tomt
        if (a.length == 0) {
            System.err.println("OBS: Tomt array i oppgave 1");
            throw new NoSuchElementException();
        }

        for (int k = 1; k < a.length; k++) { // 2n+1

            //Sammenlikner arrayverdi k med forrige verdi og setter største verdi i k
            if (a[k] < a[k - 1]) { // 6*Hn
                int big = a[k - 1];
                int small = a[k];

                a[k] = big;
                a[k - 1] = small;
            }
        }

        return a[a.length - 1];
    }

    // 2n+2n+1+6*Hn+1
    // 6Hn+4n+2

    public static int ombyttinger(int[] a) {

        int counter = 0;

        for (int k = 1; k < a.length; k++) {

            //Sammenlikner arrayverdi k med forrige verdi og inkrementerer teller hvis den må gjøre et bytte
            if (a[k - 1] > a[k]) {
                int big = a[k - 1];
                int small = a[k];

                a[k] = big;
                a[k - 1] = small;

                counter++;
            }
        }
        return counter;
    }

    //Oppgave 2
    public static int antallUlikeSortert(int[] a) {

        int teller;

        //Setter tellervariabelen til 0 hvis arrayet er tomt, men
        //hvis tabellen ikke er tom inneholder den minst 1 verdi.
        if (a.length == 0) {
            teller = 0;
        } else {
            teller = 1;
        }

        for (int k = 1; k < a.length; k++) {

            //Kaster IllegalStateException hvis en tabellverdi er mindre enn
            //forrige tabellverdi, altså ikke sortert stigende.
            if (a[k - 1] > a[k]) {
                System.err.println("OBS: Usorert tabell i oppgave 2");
                throw new IllegalStateException();
            }

            //Inkrementerer tellervariabelen hver gang tabellen inneholder en ny verdi,
            //gitt at den er sortert stigende og metoden dermed ikke kastet exception.
            if (a[k - 1] < a[k]) {
                teller++;
            }

        }
        return teller;
    }

    //Oppgave 3
    public static int antallUlikeUsortert(int[] a) {

        int teller;

        //Setter tellervariabelen til 0 hvis arrayet er tomt, men
        //hvis tabellen ikke er tom inneholder den minst 1 verdi.
        if (a.length == 0) {
            teller = 0;
        } else {
            teller = 1;
        }

        for (int k = 1; k < a.length; k++) {
            int sjekker = a[k];
            boolean duplikat = false;

            //For hver verdi av k itereres det gjennom arrayet bakover for å sjekke om
            //det er en ny unik verdi.
            for (int i = k - 1; i >= 0; i--) {
                if (sjekker == a[i]) {
                    duplikat = true;
                }
            }

            //Hvis det ikke er duplikat registres det at det er en ny unik verdi
            //så langt i arrayet og telleren blir inkrementert
            if (!duplikat) {
                teller++;
            }
        }

        return teller;
    }

    //Oppgave 4
    public static void delsortering(int[] a) {

        int oddetallsTeller = 0;

        //teller antall oddetall
        for (int k = 0; k < a.length; k++) {
            if (!erPartall(a[k])) {
                oddetallsTeller++;
            }
        }

        int partallsIndeks = oddetallsTeller;
        //sorterer oddetall og partall fra hverandre
        for (int f = 0; f < oddetallsTeller; ) {
            if (erPartall(a[f]) && !erPartall(a[partallsIndeks])) {
                int temp = a[f];
                a[f]=a[partallsIndeks];
                a[partallsIndeks] = temp;
                f++;
            } else if (erPartall(a[f]) && erPartall(a[partallsIndeks])) {
                partallsIndeks++;
            } else if (!erPartall(a[f])) {
                f++;
            }
        }

        Arrays.sort(a,0,oddetallsTeller);
        Arrays.sort(a,oddetallsTeller,a.length);

    }

    public static boolean erPartall(int a) {
        boolean partall = true;

        if (a % 2 == 1 || a % 2 == -1) {
            partall = false;
        }

        return partall;
    }

    //Oppgave 5
    public static void rotasjon(char[] a) {

        try {
            //Lagrer siste bokstav i en hjelpevariabel
            char sisteBokstav = a[a.length - 1];

            //Itererer gjennom arrayt bakover og flytter alle bokstaver en plass opp
            for (int i = a.length - 2; i >= 0; i--) {
                char temp = a[i];
                a[i + 1] = temp;
            }

            //Setter til slutt siste bokstav i indeks 0
            a[0] = sisteBokstav;

        } catch (Exception e) {
            System.err.println("OBS: Tomt array i oppgave 5");
        }
    }

    public static void snu(char[] a, int left, int right){
        if(a == null || a.length == 1)
            return;

        while(left < right){
            char temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            left++;
            right--;
        }
    }
    public static void rotasjon(char[] a, int k)  {
        int n = a.length;
        if (a.length >= 1)
        {
            k %= n;
            if (k < 0) k += n;

            // bruker hjelpemetoden snu - se øverst
            snu(a, 0, n - 1);    // snur hele tabellen
            snu(a, 0, k - 1);    // snur de k første
            snu(a, k, n - 1);    // snur resten
        }
    }

    //assignment 7, part a
    //tested
    //to merge together the character strings s and t so that the result becomes a character string.
    // If s and t have different lengths, what is "left over" must be inserted in the back
    //For example,  String a = flett("ABC","DEFGH");
    //String b = flett("IJKLMN","OPQ");
    //String b = flett("","AB");   // the result would be //// Utskrift: ADBECFGH IOJPKQLMN AB
    public static String flett(String s, String t){
        // Build a long string
        StringBuilder str = new StringBuilder();
        int k =0;

        if (s.length()< t.length())
        {
            k = s.length();
        }   else
        {
            k=t.length();
        }

        // insert the value in the new string
        for (int i = 0; i < k; i++)
        {
            str.append(s.charAt(i)).append(t.charAt(i));
        }
        //intsert the overfollow valuses of s
        for (int i = k; i < s.length(); i++)
        {
            str.append(s.charAt(i));  // tar med resten
        }
        //intsert the overfollow valuses of t
        for (int i = k; i < t.length(); i++)
        {
            str.append(t.charAt(i));  // tar med resten
        }
        return str.toString();
    }
    //assignment 7, part b
    //tested
    //character strings
    //
    public static String flett(String ...s){
        StringBuilder str = new StringBuilder();
        if (s.length==0) str.append("");
        int y  = s.length;
        // to get the length
        for (int i = 1; i < s.length; i++)
        {
            int temp = s[i].length();
            if (temp > y) y = temp;
        }
        //
        for (int i=0; i<y; i++)
        {
            for (String t:s)
            {
                if (i<t.length())
                {
                    str.append(t.charAt(i));
                }
            }
        }
        return str.toString();

    }

    //Oppgave 8
    public static int[] indekssortering(int[] a) {

        int b[] = a.clone();                        //Lager to kloner av a
        int d[] = a.clone();
        int c[] = new int[a.length];                //lager et tomt array c med samme lengde som a

        Arrays.sort(b);                             //Sorterer den ene klonen av a

        int cIndeks = 0;                            //Setter startindeksen til det tomme arrayt c til 0

        for (int i = 0; i < a.length; i++) {        //itererer gjennom b
            for (int e = 0; e < a.length; e++) {    //itererer gjennom d for hver verdi av b
                if (b[i] == d[e]) {                 //Hvis de er like settes indeksen til verdien i d
                    c[cIndeks] = e;                 //inn i c.
                    d[e]=-1;                        //Fjerner verdien fra d, slik at samme indeks ikke blir satt i c for duplikater
                    cIndeks++;                      //inkrementerer c
                }
            }
        }
        return c;
    }

    public static int[] indekssortering(int[] a) {

        if (a.length < 3) {

            throw new NoSuchElementException("Tabellen er for liten");//Kaster Exception hvis tabell lengden er mindre enn 3

        }

        int[] indeks = {0, 1, 2}; //Lager verdiene ti tre indekser

        int min = a[0];//Sier hva indeksen til min er lik

        int maks = a[0];



        for (int i = 0; i < 3; i++) {//Lager en løkke for å loope gjennom tallene

            if (a[i] <= min) { //Endrer indeks til min.

                min = a[i];

                indeks[0] = i;

            }

            if (a[i] >= maks) {//Endrer indeksen til maks

                maks = a[i];

                indeks[2] = i;

            }

        }

        indeks[1] = Math.abs((indeks[0] + indeks[2] - 3));

        return indeks;

    }



    public static int[] tredjeMin(int[] a) {

        int n = a.length; //lengden til tabellen

        //Hvis tabellen har mindre enn 3 verdier skal det "kastes Exception"

        if (n < 2) {

            throw new java.util.NoSuchElementException("a.length(" + n + ") < 2!");

        }



        int[] indeks = indekssortering(a);



        int m = indeks[0]; //Dette er indeksen/posisjonen til minste verdi

        int nm = indeks[1]; //Dette er indeksen/posisjonen til nest miinste verdi

        int mn = indeks[2]; //Dette er indeksen/posisjonen til tredj minste verdi



        int minverdi = a[m]; //Minste verdi

        int nestminverdi = a[nm]; //Nest minste verdi

        int tredjminsteverdi = a[mn]; //Tredj minste verdi



        //Lager en løkke for å loope gjennom alle tallene

        for (int i = 3; i < n; i++) {

            if (a[i] < tredjminsteverdi) { //Hvis a[i] er mindre enn trejminste så skal neste setning kjøres

                if (a[i] < nestminverdi) {  //Hvis a[i] er mindre enn nestminste verdi så skal neste setning kjøres

                    if (a[i] < minverdi) {  //Hvis a[i] er mindre en minst verdi så skal ny verdi settes

                        mn = nm;

                        tredjminsteverdi = nestminverdi;



                        nm = m;

                        nestminverdi = minverdi;



                        m = i;

                        minverdi = a[m];

                    } else {

                        mn = nm;

                        tredjminsteverdi = nestminverdi;



                        nm = i;

                        nestminverdi = a[nm];

                    }

                } else {

                    mn = i;

                    tredjminsteverdi = a[mn];

                }

            }

        }

        return new int[]{m, nm, mn};//returnerer tabell med inndekser

    }





    public static boolean inneholdt(String a, String b) {

        boolean fant;                           //variabel som angir om man fant bokstaven i begge ordene
        char aArray[] = new char[a.length()];   //array for string a
        char bArray[] = new char[b.length()];   //array for string b

        for (int i = 0; i < a.length(); i++) {  //omgjør ordene til array med samme lengde som antall bokstaver
            aArray[i]=a.charAt(i);              //legger string a inn i aArray
        }

        for (int i = 0; i < b.length(); i++) {
            bArray[i]=b.charAt(i);              //legger string b inn i bArray
        }

        Arrays.sort(bArray);                        //sorterer ordene (arrayene)
        Arrays.sort(aArray);

        int e = 0;                                  //setter indeks i bArray til 0

        for (int i = 0; i<a.length(); i++) {        //denne for-løkken går gjennom sortert aArray
            fant = false;

         namedLoop: for ( ; e<b.length(); e++) {    //denne for-løkken går gjennom sortert bArray
                if (aArray[i] == bArray[e]) {       //for hver verdi i aArray
                    bArray[e] = '-';                //fjerner verdien hvis man finner match
                    fant = true;                    //angir at man fant match
                    break namedLoop;                //avslutter letingen for gitt verdi i aArray
                }
            }                                       //siden e er angitt utenfor løkkene vil
                                                    //for-løkken lete der den slapp, i og med at de er sortert
            if (!fant) {
                return false;                       //returnerer false hvis den ikke fant verdi for alle
            }
        }

        return true;
    }
}

