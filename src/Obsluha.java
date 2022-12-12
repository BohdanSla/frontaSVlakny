import java.util.LinkedList;
import java.util.List;

public class Obsluha implements Runnable {

    private static List<Zakaznik> obslouzenyZakaznici = new LinkedList<>();
    private Long casyZakazniku = 0L;

    public Long getCasyZakazniku() {
        return casyZakazniku;
    }

    public static List<Zakaznik> getObslouzenyZakazniky() {
        return obslouzenyZakaznici;
    }

    private Fronta fronta;
    private Zakaznik obsluhovanyZakaznik;

    private static int poradi = 0;
    private final int cisloObsluhy;
    private boolean konec = false ;

    public Obsluha(Fronta fronta) {
        this.fronta = fronta;
        cisloObsluhy = ++poradi;
    }

    public void zastavit() {
        konec = true;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                synchronized(fronta) {
                    if(konec && fronta.jePrazdna()) {
                        break;
                    }
                    while(fronta.jePrazdna()) {
                        fronta.wait();
                    }
                    obsluhovanyZakaznik = fronta.odeberZakaznikaZFronty();
                }
                obsluhovanyZakaznik.prichodZakaznikaKObsluze();
                System.out.println("Přišel zákazník s číslem " + obsluhovanyZakaznik.getId() + " k obsluze s číslem " + cisloObsluhy + " a " + fronta.size() +  " ve frontě");
                long nahodnyCasObsluhy = (long) (Math.random() * (3000 - 1000)) + 1000;
                Thread.sleep(nahodnyCasObsluhy);
                obsluhovanyZakaznik.odchodZakaznikaZObsuhy();
                synchronized(obslouzenyZakaznici) {
                    obslouzenyZakaznici.add(obsluhovanyZakaznik);
                }
                casyZakazniku = casyZakazniku + obsluhovanyZakaznik.casUObsluhy();
                System.out.println("Odešel zákazník s číslem " + obsluhovanyZakaznik.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
