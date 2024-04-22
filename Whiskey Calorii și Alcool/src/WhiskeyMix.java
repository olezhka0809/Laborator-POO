import java.util.List;
import java.util.ArrayList;
abstract class whiskey{
    abstract double getNrCalorii(double mili);
    abstract String getConcentratieAlcool();
    abstract  double getConcentratie();
}
class ClasicWhiskey extends whiskey{
    private String nume ;
    private double concentratie;
    public ClasicWhiskey(String nume ,int concentratie){
        this.nume = nume ;
        this.concentratie = concentratie;
    }
    public double getNrCalorii(double mili){
        double result = this.concentratie* mili * 5;
        return result;
    }

    public String getConcentratieAlcool(){
        String result= this.nume + ", Concentratie alcool : " + this.concentratie + " %, Calorii pe100 ml: " +this.getNrCalorii(100)+" kcal"+ "\n";
        return result;
    }
    public double getConcentratie(){
        return this.concentratie;
    }

}
class JackAndHoney extends whiskey {
    private String nume;
    private double concentratie;
    private int cantitateIndulcitor;
    public JackAndHoney(String nume, double concentratie, int cantitateIndulcitor){
        this.nume =nume;
        this.concentratie = concentratie ;
        this. cantitateIndulcitor = cantitateIndulcitor;
    }

    public double getNrCalorii(double mili) {
        double result = this.concentratie * mili * 5 + this.cantitateIndulcitor*mili *15;
         return result;
    }
    public String getConcentratieAlcool(){
        String result = this.nume + ", Concentratie alcool : " + this.concentratie+ " %, Calorii pe 100 ml: "+ this.getNrCalorii(100)+ " kcal , Cantitatea de indulcitor: "+ this.cantitateIndulcitor+ " g \n";
        return result;
    }
    public double getConcentratie(){
        return this.concentratie;
    }


}
class BlendedWhiskey extends whiskey{
    private String nume ;
    private List <whiskey> bauturi;
    private int count =0;
    public BlendedWhiskey(String nume){
        this.nume = nume;
        this.bauturi =new ArrayList<>();
    }
    public void adaugaBauturi(whiskey bautura){
        this.bauturi.add(bautura);
        count++;
    }
    public double getConcentratie(){
        double result =0;
        for(whiskey bautura : this.bauturi){
            result += bautura.getConcentratie() /count;
        }
        return result;
    }
    public double getNrCalorii(double mili){
        double numarCal = mili /count;
        double suma =0;
        for(whiskey bautura :bauturi){
            suma += bautura.getNrCalorii(numarCal);
        }
        return suma;
    }

    public String getConcentratieAlcool(){
        String result = this.nume+" , Concentrtia alcool : "+ this.getConcentratie()+ " % "+ " ,Calorii pe 100 ml: "+ this.getNrCalorii(100)+ " , Compozitie : \n";
        for (whiskey bautura : bauturi){
            result+= bautura.getConcentratieAlcool();
        }
        return  result;
    }
}
class WhiskeyMix{
    public static void  main(String [] args){
        BlendedWhiskey Bleadina = new BlendedWhiskey("Bleadin1");

        ClasicWhiskey soska =new ClasicWhiskey("Genius",40);
        JackAndHoney honey = new JackAndHoney("Penius",  20, 30 );

        BlendedWhiskey Bleadina2 = new BlendedWhiskey("Bleadin2");
        ClasicWhiskey HAHA = new ClasicWhiskey("IAMATA" , 50);
        ClasicWhiskey HUINA = new ClasicWhiskey("KUDASAY" ,45);
        Bleadina2.adaugaBauturi(HAHA);
        Bleadina2.adaugaBauturi(HUINA);

        Bleadina.adaugaBauturi(soska);
        Bleadina.adaugaBauturi(honey);
        Bleadina.adaugaBauturi(Bleadina2);

        System.out.println(Bleadina2.getConcentratieAlcool());

        double oneFIvehUndread = Bleadina2.getNrCalorii(150);
        System.out.println("Numarul de calorii pe 150 de mililitri este : "+ oneFIvehUndread + "\n");



    }
}
