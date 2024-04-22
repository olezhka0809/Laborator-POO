import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

abstract class Utilizator {
    abstract double CalculeazaVenit(int ore);
}

class Subscriber extends Utilizator{
    private String nume;
    private int nivel ;
    public Subscriber(String nume, int numar){
        this.nume = nume;
        this.nivel = numar;
    }
    public double CalculeazaVenit(int nivel){
        double result = 1.5 * nivel;
        return result;
    }
    public String toString(){
        String represent= "   Nume Subscriber : "+ this.nume + " - "+ "subscriber" + " - " + "nivel : "+ this.nivel + "\n";
        return  represent;
    }


}
class Creator extends Utilizator {
    private String nume;
    private List<Subscriber> subscribers;
    public Creator(String nume) {
        this.nume = nume;
        this.subscribers= new ArrayList<>();
    }



    public void adaugaSubscriber(Subscriber sub) {
        subscribers.add(sub);
    }

    public double CalculeazaVenit(int minute) {
        double suma = 0;
        for (Subscriber sub : subscribers) {
            suma += sub.CalculeazaVenit(minute);
        }
        return suma;
    }

    public String toString() {

        String result = "Nume Creator: " + this.nume + " - " + "creator" + " - \n";
        for (Subscriber sub : subscribers) {
            result += sub.toString();
        }
        return result;
    }
}
class Platforma{
        private Utilizator  utilizatori[] = new Utilizator[1000];
        private int count = 0;
        public boolean aduagaUtilizator(Utilizator utilizator){
            if(count< utilizatori.length ){
                utilizatori[count] = utilizator;
                count++;
                return true;
            }else{
                return false;
            }

        }
        public Utilizator determinaVip(int ore){
            if(count ==0 ){
                return null;
            }
            Utilizator utilizatorVip = utilizatori[0];
            for(int i = 1; i<count ; i++){
                if(utilizatori[i].CalculeazaVenit(ore) > utilizatorVip.CalculeazaVenit(ore)){
                    utilizatorVip = utilizatori[i];
                }
            }
            return utilizatorVip;
        }


}
class Twich{
    public static void main(String[] args){
        Platforma Twich = new Platforma();
        Creator creator1 = new Creator("IwanWhite");
        Creator creator2 = new Creator("BlackDick");

        Subscriber sub1= new Subscriber("Jony", 20);
        Subscriber sub2= new Subscriber("Hana", 24);
        Subscriber sub3= new Subscriber("Luisa", 10);

        Subscriber sub4= new Subscriber("Alin", 30);
        Subscriber sub5= new Subscriber("Chris", 12);

        creator1.adaugaSubscriber(sub1);
        creator1.adaugaSubscriber(sub2);
        creator1.adaugaSubscriber(sub3);

        creator2.adaugaSubscriber(sub4);
        creator2.adaugaSubscriber(sub5);

        Twich.aduagaUtilizator(creator1);
        Twich.aduagaUtilizator(creator2);



        Utilizator util = Twich.determinaVip(30);


        System.out.println(util.toString());
   }
}