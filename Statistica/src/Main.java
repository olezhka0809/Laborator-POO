import java.util.List;
import java.util.ArrayList;
abstract class statistica{
    abstract void calculeaza(List<String> String );
    abstract String getJurnal();
}
class StatisticaNumarAparitii extends statistica{
    private List<String> cautate;
    private String jurnal;
    public StatisticaNumarAparitii(List<String> secventa){
        this.cautate =secventa;
    }

    public void calculeaza(List<String> segment ){
        int count =0;
        String sir= " ";
        this.jurnal = "In secventa { ";
        for (String secv: segment){
            this.jurnal+= secv+" , ";
        }
        for (int i=0; i<cautate.size();i++){
                if(this.cautate.get(i).equals(segment.get(i))){
                    sir+= this.cautate.get(i);
                    count++;
                }
        }
        this.jurnal += " } apar "+ count +" siruri din secventa "+ sir;

    }
    public String getJurnal(){
        return this.jurnal+"\n";
    }

}
class StatisticaNumeraleReale extends statistica{

    private String jurnal;
    private int cout =0;
    public void calculeaza(List<String> secventa ){
        for (String str : secventa){
            try{
                Double.parseDouble(str);
            }catch(NumberFormatException e){
                this.cout++;
            }
        }
        int numar = secventa.size()-cout;
        this.jurnal= "In secventa { ";
        for (String str: secventa){
            this.jurnal  += str ;
        }
        this.jurnal+=" } avem " +numar+" siruri ce nu sunt numere reale  \n";
    }
    public String getJurnal(){
        return this.jurnal;
    }
}
class Executor{
    private List<statistica> statistici;
    public Executor(List <statistica> stat){
        this.statistici = stat;
    }
    public void executa(List <String> secventa){
        for (statistica stat :statistici){
            stat.calculeaza(secventa);
            System.out.println(stat.getJurnal());
        }
    }
}
class Main {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("mere");
        list1.add("banane");
        list1.add("pere");

        List<String> list2 = new ArrayList<>();
        list2.add("Ana");
        list2.add("are");
        list2.add("mere");

        StatisticaNumarAparitii statistica = new StatisticaNumarAparitii(list1);
        StatisticaNumeraleReale statistica2 = new StatisticaNumeraleReale();

        List<statistica> stat = new ArrayList<>();
        stat.add(statistica);
        stat.add(statistica2);

        Executor executor = new Executor(stat);

        executor.executa(list2); 
    }
}
