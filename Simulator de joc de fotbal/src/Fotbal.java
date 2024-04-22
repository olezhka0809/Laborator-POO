import java.util.Random;
import java.util.Date;

class ClasaExceptie extends Exception {
    static class Out extends Exception {
        public Out(String mesaj) {
            super(mesaj);
        }
    }

    static class Gol extends Exception {
        public Gol(String mesaj) {
            super(mesaj);
        }
    }

    static class Corner extends Exception {
        public Corner(String mesaj) {
            super(mesaj);
        }
    }
}

class CoordinateGenerator {
    private Random randomGenerator;

    public CoordinateGenerator() {
        randomGenerator = new Random();
    }

    public int generateX() {
        int x = randomGenerator.nextInt(101);
        if (x < 5) {
            return 0;
        } else if (x > 95) {
            return 100;
        } else {
            return randomGenerator.nextInt(99) + 1;
        }
    }

    public int generateY() {
        int y = randomGenerator.nextInt(101);
        if (y < 5) {
            return 0;
        } else if (y > 95) {
            return 50;
        } else {
            return randomGenerator.nextInt(49) + 1;
        }
    }
}

class Minge {
    private int X;
    private int Y;

    public Minge(int pozX, int pozY) {
        this.X = pozX;
        this.Y = pozY;
    }

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public void setX(int newX) {
        this.X = newX;
    }

    public void setY(int newY) {
        this.Y = newY;
    }

    public void suteaza(CoordinateGenerator generator) throws ClasaExceptie.Out, ClasaExceptie.Gol, ClasaExceptie.Corner {
        this.X = generator.generateX();
        this.Y = generator.generateY();

        if (Y == 0 || Y == 50) {
            throw new ClasaExceptie.Out("Mingea se afla in Out !");
        } else if ((X == 0 || X == 100) && (Y >= 20 && Y <= 30)) {
            throw new ClasaExceptie.Gol("S-a marcat un gol !");
        } else if ((X == 0 || X == 100) && ((Y > 0 && Y < 20) || (Y > 30 && Y > 50))) {
            throw new ClasaExceptie.Corner("Mingea se afla in corner !");
        }

        System.out.println("Mingea se afla la cordonatele ( " + getX() + ", " + getY() + " )");
    }
}

class Joc {
    private String numeleEchipelor[] = new String[2];
    private int nrGoluriEchipa1 = 0;
    private int nrGoluriEchipa2 = 0;
    private int nrOut = 0;
    private int nrCornere = 0;

    public Joc(String echipa1, String echipa2) {
        if (!echipa1.equals(echipa2)) {
            numeleEchipelor[0] = echipa1;
            numeleEchipelor[1] = echipa2;
        } else {
            System.out.println("Introdu nume de echipe diferite!");
        }
    }

    public String toString() {
        String reprezentare = "Echipa 1 : " + this.numeleEchipelor[0] + "\n" + "A marcat gol de " + this.nrGoluriEchipa1
                + "\n";
        String reprezentare2 = "Echipa 2 : " + this.numeleEchipelor[1] + "\n" + "A marcat gol de " + this.nrGoluriEchipa2
                + "\n";
        return reprezentare + reprezentare2 + " \n Iar numarul de out-uri este de " + this.nrOut
                + " iar nr de corneruri este de " + this.nrCornere + "\n";

    }

    public void simuleaza() {
        CoordinateGenerator generator = new CoordinateGenerator();
        Minge minge1 = new Minge(50, 25);
        String reprezentare = this.numeleEchipelor[0] + " - " + this.numeleEchipelor[1] + " : \n";
        for (int i = 0; i < 1000; i++) {
            try {
                minge1.suteaza(generator);
                System.out.println(reprezentare + "Mingea se afla la cordonatele ( " + minge1.getX() + ", "
                        + minge1.getY() + " )");
            } catch (ClasaExceptie.Gol e) {
                System.out.println(reprezentare + "Mingea se afla la cordonatele ( " + minge1.getX() + ", "
                        + minge1.getY() + " )");
                if (minge1.getX() == 0 && (minge1.getY() < 30 && minge1.getY() > 20)) {
                    this.nrGoluriEchipa2++;
                }
                if (minge1.getX() == 100 && (minge1.getY() < 30 && minge1.getY() > 20)) {
                    this.nrGoluriEchipa1++;
                }
                Minge mingeNoua = new Minge(50, 25);
                mingeNoua = minge1;
            } catch (ClasaExceptie.Out e) {
                System.out.println(reprezentare + "Mingea se afla la cordonatele ( " + minge1.getX() + ", "
                        + minge1.getY() + " )");
                this.nrOut++;

                int newX = minge1.getX();
                int newY = minge1.getY();
                Minge mingeOut = new Minge(newX, newY);

            } catch (ClasaExceptie.Corner e) {
                System.out.println(reprezentare + "Mingea se afla la cordonatele ( " + minge1.getX() + ", "
                        + minge1.getY() + " )");
                this.nrCornere++;
                if (minge1.getY() < 20 && minge1.getY() > 0) {
                    if (minge1.getX() == 0) {
                        Minge mingeNoua = new Minge(0, 0);
                        mingeNoua = minge1;
                    } else if (minge1.getX() == 100) {
                        Minge mingeNoua = new Minge(100, 0);
                        mingeNoua = minge1;
                    }
                } else if (minge1.getY() < 50 && minge1.getY() > 30) {
                    if (minge1.getX() == 0) {
                        Minge mingeNoua = new Minge(0, 50);
                        mingeNoua = minge1;
                    } else if (minge1.getX() == 100) {
                        Minge mingeNoua = new Minge(100, 50);
                        mingeNoua = minge1;
                    }
                }

            }
        }
    }
}

class Fotbal {
    public static void main(String[] args) {
        Joc joc1 = new Joc("Liverpool", "Barcelona");
        Joc joc2 = new Joc("Real-Madrid", "Manchester-United");
        joc1.simuleaza();
        joc2.simuleaza();
        System.out.println("-----------------------------Inceputul primului joc -----------------------------------");
        System.out.println(joc1.toString());
        System.out.println("-----------------------------Inceputul la al doilea joc -----------------------------------");
        System.out.println(joc2.toString());
    }
}
