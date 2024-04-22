import java.util.ArrayList;
import java.util.List;

abstract class Destinar{
    abstract void receptioneaza(Utilizator utilizator , String argument);

}
class Utilizator extends Destinar{
    private String nume;
    private List<String> jurnal ;
    public Utilizator(String string){
        this.nume = string;
        this.jurnal = new ArrayList<>();
    }
    public String getNume(){
        return this.nume;
    }
    public  boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(o instanceof Utilizator){
            return ((Utilizator)o).nume.equals(this.nume);
        }else{
            return false;
        }
    }
    public void trimite(Destinar destinatar, String string){
         String loc = "Trimis catre "+ destinatar.toString()+ " mesajul : "+ string;
         jurnal.add(loc);
         receptioneaza(this, string);

    }
    public void receptioneaza(Utilizator destinatar , String mesaj){
        String call ="Primit de la "+ destinatar.getNume()+ " mesajul : "+ mesaj;
        jurnal.add(call);
    }
    public String toString(){
        String result= this.getNume()+ " : ";
        for(String call : this.jurnal){
            result+= call +"\n";
        }
        return result;
    }

}
class DestinatarDublu extends Exception{
    public DestinatarDublu (String mesaj){
        super(mesaj);
    }
}

class Grup extends Destinar{
    private List<Destinar> destinatari;
    private String nume;
    public Grup(String nume){
        this.nume = nume;
        this.destinatari = new ArrayList<>();
    }
    public void inscrie(Destinar destinatar) throws DestinatarDublu{
        for (Destinar membru:destinatari){
            if(!(destinatar instanceof Utilizator) || ((Utilizator)membru).equals((Utilizator) destinatar)){
                throw new DestinatarDublu("Acest destinatar exista deja exista in destinatari !");
            }
        }
        this.destinatari.add(destinatar);
    }
    public void receptioneaza(Utilizator utilizator, String mesaj){
        for (Destinar destinar: destinatari){
            if(!(destinar instanceof Utilizator ) || !((Utilizator)destinar).equals(utilizator)){
                destinar.receptioneaza(utilizator, mesaj);
            }
        }
    }
    public String toString() {
        return "Grup: " + nume; 
    }
}
class Mesagerie{
    public static void main(String[]  args){
        try {
            Utilizator utilizator1 = new Utilizator("Dan");
            Utilizator utilizator2 = new Utilizator("Marius");
            Utilizator utilizator3 = new Utilizator("Alex");
            Grup grup1 = new Grup("Carnivotii");
            grup1.inscrie(utilizator1);
            grup1.inscrie(utilizator2);
            grup1.inscrie(utilizator3);
            grup1.inscrie(utilizator3);

            utilizator3.trimite(grup1, "Am deschis magazinul");
            utilizator2.trimite(grup1, "Vin acuma");

            System.out.println(utilizator1.toString());
            System.out.println(utilizator2.toString());
            System.out.println(utilizator3.toString());

        }catch (DestinatarDublu e){
            System.out.println(e.getMessage());
        }
        try{
            Utilizator utilizator1 = new Utilizator("Dan");
            Utilizator utilizator2 = new Utilizator("Marius");
            Utilizator utilizator3 = new Utilizator("Alex");
            Grup grup1 = new Grup("Carnivotii");
            grup1.inscrie(utilizator1);
            grup1.inscrie(utilizator2);
            grup1.inscrie(utilizator3);

            utilizator3.trimite(grup1, "Am deschis magazinul");
            utilizator2.trimite(grup1, "Vin acuma");

            System.out.println(utilizator1.toString());
            System.out.println(utilizator2.toString());
            System.out.println(utilizator3.toString());
        }catch (DestinatarDublu e){
            System.out.println(e.getMessage());
        }

    }
}
