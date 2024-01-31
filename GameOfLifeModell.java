import java.lang.Math;

public class GameOfLifeModell {
        int genNr;
        int antRader;
        int antKolonner;
        Celle[][] rutene; 
    
        
        public GameOfLifeModell(int antRader, int antKolonner){
            this.antRader = antRader;
            this.antKolonner = antKolonner;
            genNr = 0; 
            rutene = new Celle[antRader][antKolonner];
            fyllMedTilfeldigeCeller();
            kobleAlleCeller();
        }
    

        public void tegn(){
            tegnRutenett();
            System.out.println("Generasjonsnummer: " + genNr);
            System.out.println("Antall levende: " + antallLevende());
            
        }
    

        public void oppdatering(){
            for (int rad = 0; rad < antRader; rad++){
                for (int kol = 0; kol < antKolonner; kol++){
                    hentCelle(rad, kol).tellLevendeNaboer();
                }
            }
                for (int rad = 0; rad < antRader; rad++){
                    for (int kol = 0; kol < antKolonner; kol++){
                        hentCelle(rad, kol).oppdaterStatus();
                    }
    
                }  genNr++;
        }
        

        public void lagCelle(int antRader, int antKolonner){
            Celle celle = new Celle();
            rutene[antRader][antKolonner] = celle;
            if (Math.random()>0.33) {
                celle.settLevende();}
        }
        

        public void fyllMedTilfeldigeCeller(){
            for (int rad = 0; rad < antRader; rad++){
                for (int kol = 0; kol < antKolonner; kol++){
                    lagCelle(rad, kol);
                }
    
            }
        }
    

        public Celle hentCelle(int rader, int kolonner){
            if (rader < 0 || rader >= antRader){
                return null;
            }
            if (kolonner < 0 || kolonner >= antKolonner){
                 return null;   
            }
            return rutene[rader][kolonner];
        }
    

        public void tegnRutenett(){
            for (Celle[] rader : rutene){
                for (Celle kolonner : rader){
                    System.out.print(kolonner.hentStatusTegn());
                }
                System.out.println();
            }   
        }
        

        public void settNaboer(int rader, int kolonner){
            Celle mincelle = hentCelle(rader, kolonner);
            for (int rad = -1; rad < 2; rad++ ){
                for (int kol = -1; kol < 2; kol++){
                    if (rad == 0 && kol == 0){
                        continue; 
                    } Celle nabocelle = this.hentCelle(rader + rad, kolonner + kol);
                    if (nabocelle == null) {
                        continue;
                    }
                    mincelle.leggTilNabo(nabocelle);
                }
            }
        }
    

        public void kobleAlleCeller(){
            for (int rad = 0; rad < antRader; rad++){
                for (int kol = 0; kol < antKolonner; kol++){
                    settNaboer(rad, kol);
                }
    
            }
        }
    

        public int antallLevende(){
            int levendeNaboer = 0;
            for (Celle[] rader : rutene){
                for (Celle kolonner : rader){
                    if (kolonner.erLevende()){
                        levendeNaboer++;
                    }
                }
            }
            return levendeNaboer;
        }
        
}
    
    

