import java.util.LinkedList;
import java.util.Queue;

public class Fronta {
    private Queue<Zakaznik> fronta = new LinkedList<>();

    public void pridejZakaznika(Zakaznik zakaznik) {
        fronta.add(zakaznik);
    }

    public Zakaznik odeberZakaznikaZFronty() {
        return fronta.remove();
    }

    public boolean jePrazdna() {
        return fronta.isEmpty();
    }

    public int size() {
        return fronta.size();
    }
}
