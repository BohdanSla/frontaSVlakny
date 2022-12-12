public class Prichod implements Runnable {

    private Fronta fronta;
    private boolean konec = false;

    public Prichod(Fronta fronta) {
        this.fronta = fronta;
    }

    public void zastavit() {
        konec = true;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (!konec) {
            Zakaznik zakaznik = new Zakaznik();
            fronta.pridejZakaznika(zakaznik);
            System.out.println("Přišel zákazník do fronty s číslem " + zakaznik.getId());
            synchronized(fronta) {
                fronta.notify();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
}
