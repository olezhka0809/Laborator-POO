import java.util.ArrayList;
import java.util.List;

abstract class Test {
    abstract public int getNumarTeste();
}

class WrongQualityIndicatorException extends Exception {
    public WrongQualityIndicatorException(String mesaj) {
        super(mesaj);
    }
}

class WrongComponentComplexityIndicatorException extends Exception {
    public WrongComponentComplexityIndicatorException(String mesaj) {
        super(mesaj);
    }
}

class IntegrationTest extends Test {
    private String nume;
    private int indicator;

    public IntegrationTest(String nume, int indicator) throws WrongQualityIndicatorException {
        this.nume = nume;
        verificaIndicator(indicator);
        this.indicator = indicator;
    }

    public void verificaIndicator(int indicator) throws WrongQualityIndicatorException {
        if (!(indicator < 10 && indicator > 1)) {
            throw new WrongQualityIndicatorException("Ati introdus un indicator care nu corespunde conditiilor impuse !");
        }
    }

    public int getNumarTeste() {
        return 1;
    }

    public String toString() {
        return "IntegrationTest [name: " + this.nume + ", quality indicator: " + this.indicator + "]";
    }
}

class ComponentTest extends Test {
    private String nume;
    private int indicator;
    private int complexitate;

    public ComponentTest(String nume, int indicator, int complex) throws WrongQualityIndicatorException, WrongComponentComplexityIndicatorException {
        this.nume = nume;
        verificaComponentTest(indicator, complex);

        this.indicator = indicator;
        this.complexitate = complex;
    }

    public void verificaComponentTest(int indicator, int complexitate) throws WrongQualityIndicatorException, WrongComponentComplexityIndicatorException {
        if (complexitate < 0) {
            throw new WrongComponentComplexityIndicatorException("Ati introdus complexitatea componentelor testate care nu corespunde conditiilor impuse ! ");
        } else if (!(indicator < 10 && indicator > 1)) {
            throw new WrongQualityIndicatorException("Ati introdus un indicator care nu corespunde conditiilor impuse !");
        }
    }

    public int getNumarTeste() {
        return 1;
    }

    public String toString() {
        return "ComponentTest: [" + this.nume + "], quality indicator: " + this.indicator + ", component complexity indicator: " + this.complexitate;
    }
}

class TestSuite extends Test {
    private List<Test> teste;

    public TestSuite() {
        this.teste = new ArrayList<>();
    }

    public int getNumarTeste() {
        int sum = 0;
        for (Test test : teste) {
            sum += test.getNumarTeste();
        }
        return sum;
    }

    public boolean addNewIntegrationTest(String name, int indicator)  {
        try {
            IntegrationTest test = new IntegrationTest(name, indicator);
            this.teste.add(test);
            return true;
        } catch (WrongQualityIndicatorException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean addNewComponentTest(String name, int indicator, int complexity) throws WrongComponentComplexityIndicatorException {
        try {
            ComponentTest test = new ComponentTest(name, indicator, complexity);
            this.teste.add(test);
            return true;
        } catch (WrongQualityIndicatorException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (WrongComponentComplexityIndicatorException e) {
            System.out.println(e.getMessage());
            throw new WrongComponentComplexityIndicatorException("A apărut o eroare de complexitate a componentelor!");
        }
    }


    public String toString() {
        StringBuilder representation = new StringBuilder("Test Suite [");
        for (Test test : this.teste) {
            representation.append(test.toString()).append(", ");
        }
        representation.append("]");
        return representation.toString();
    }
}

class Main {
    public static void main(String[] args) {
        try {
            TestSuite teste = new TestSuite();

            teste.addNewIntegrationTest("IntegrareTest", 7);
            teste.addNewComponentTest("ComponentTest", 5, 78);

            // Aici se adaugă un test cu complexitate incorectă pentru a genera excepția
            teste.addNewComponentTest("ComponentTest2", 11, -56);

            teste.addNewIntegrationTest("IntegrationTest2", 5);

            System.out.println(teste.toString());
            System.out.println("Numarul de teste totale este: " + teste.getNumarTeste());
        } catch (WrongComponentComplexityIndicatorException e) {
            System.out.println("A aparut o eroare de complexitate a componentelor!");
        }
    }
}