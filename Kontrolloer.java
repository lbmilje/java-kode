public class Kontrolloer {
    GameOfLifeModell modell;
    GameOfLifeView gui;
    int rad, kol;

    public Kontrolloer(int rad, int kol){
        modell = new GameOfLifeModell(rad, kol); 
        gui = new GameOfLifeView(this, rad, kol); 
    }    

    public boolean harStartet = false; 

    public Celle[][] hentRutenett(){
        return modell.rutene;
    }

    public int hentAntallLevende(){
        return modell.antallLevende();
    }

    public int hentGenerasjonsNr(){
        return modell.genNr;
    }

    public void oppdatering(){
        modell.oppdatering();
    }

    public Celle hentCelle(int rad, int kol){
        return modell.hentCelle(rad, kol);
    }

}
