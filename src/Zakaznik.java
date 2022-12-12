public class Zakaznik {
    private long casPrichoduDoFronty,casPrichoduKObsluze,casOdchoduZObsluhy;
    private static int poradi = 0;
    private final int id;

    public int getId() {
        return id;
    }

    public Zakaznik() {
        id = ++poradi;
        casPrichoduDoFronty = System.currentTimeMillis();
    }

    public void prichodZakaznikaKObsluze() {
        casPrichoduKObsluze = System.currentTimeMillis();
    }
    
    public void odchodZakaznikaZObsuhy() {
        casOdchoduZObsluhy = System.currentTimeMillis();
    }
    
    public long getCasPrichoduDoFronty() {
        return this.casPrichoduDoFronty;
    }

    public long getCasPrichoduKObsluze() {
        return this.casPrichoduKObsluze;
    }

    public long getCasOdchoduZObsluhy() {
        return this.casOdchoduZObsluhy;
    }

    public long casVeFronte() {
        return casPrichoduKObsluze - casPrichoduDoFronty;
    }

    public long casUObsluhy() {
        return casOdchoduZObsluhy - casPrichoduKObsluze;
    }

    public long casVSystemu() {
        return casOdchoduZObsluhy - casPrichoduDoFronty;
    }

}
