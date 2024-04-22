
class Birou{
    private int l, L, H;
    public void Tipareste_birou(Sertare sert1, Sertare sert2){
        System.out.println("Biroul contine " +sert1.Tipareste()+"\n"+sert2.Tipareste()+ " Lungime: " +L +" latime: "+l +" Inaltimea: "+H);
    }

}
class Sertare{
    private int l, L, H;
    public void Setare_sertar(int latime ,int lungime, int inaltime){
        l= latime;
        L= lungime;
        H = inaltime;
    }
    public String Tipareste( ){
        return  ("" +
                "Sertar: latime "+l+" Lungime "+ L+ " inaltime "+H);
    }

}
public class App {
    public static void main(String[] args) {
        Sertare sert1 = new Sertare();
        Sertare sert2 = new Sertare();
        Birou birou1 = new Birou();
        sert1.Setare_sertar(10, 20, 5);
        sert2.Setare_sertar(15, 25, 6);
        birou1.Tipareste_birou(sert1, sert2);
    }

}
