import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        long casZacatkuSimulace = System.currentTimeMillis();
        Fronta fronta = new Fronta();

        Prichod prichod = new Prichod(fronta);

        Thread vlaknoPrichod = new Thread(prichod);
        
        Obsluha obsluhaJedna = new Obsluha(fronta);
        Obsluha obsluhaDva = new Obsluha(fronta);
        
        Thread vlaknoObsluhaJedna =  new Thread(obsluhaJedna);
        Thread vlaknoObsluhaDva =  new Thread(obsluhaDva);
        
        vlaknoPrichod.start();
        vlaknoObsluhaJedna.start();
        vlaknoObsluhaDva.start();

        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        prichod.zastavit();
        obsluhaJedna.zastavit();
        obsluhaDva.zastavit();

        vlaknoObsluhaJedna.join();
        vlaknoObsluhaDva.join();

        Long casKonecSimulace =  System.currentTimeMillis();
        System.out.println("Počet Obsloužených zákazníků: " + Obsluha.getObslouzenyZakazniky().stream().count());

        double prumernyCasVeFronte = Obsluha.getObslouzenyZakazniky().stream().collect(Collectors.averagingDouble(c -> c.casVeFronte()));
        double prumernyCasUObsluhy = Obsluha.getObslouzenyZakazniky().stream().collect(Collectors.averagingDouble(c -> c.casUObsluhy() / 1000));
        double prumernyCasVSystemu = Obsluha.getObslouzenyZakazniky().stream().collect(Collectors.averagingDouble(c -> c.casVSystemu() / 1000));
        int procentoJedna = (int) ((obsluhaJedna.getCasyZakazniku() * 100) / (casKonecSimulace - casZacatkuSimulace));
        int procentoDva = (int) ((obsluhaDva.getCasyZakazniku()*100) / (casKonecSimulace - casZacatkuSimulace));

        System.out.println(String.format("Průměrný čas v systému: %.1f s %nPrůměrný čas ve frontě: %.1f ms %nPrůměrný čas u obsluhy: %.1f s %nVytížení první přepážky: %d %% %nVytížení druhé přepážky: %d %% ",prumernyCasVSystemu,prumernyCasVeFronte,prumernyCasUObsluhy,procentoJedna,procentoDva));
    }
}
