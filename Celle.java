
public class Celle {
    boolean levende; 
    int antNaboer;
    int antLevendeNaboer;
    Celle naboer[];

    public Celle() {
        levende = false;
        antNaboer = 0;
        antLevendeNaboer = 0;
        naboer = new Celle[8];

    }

    public void settDoed(){
        levende = false;
    }
    
    public void settLevende(){
        levende = true;
    }

    public boolean erLevende(){
        if (levende == true){
            return true;
    }   else {
        return false;
    } 
    }

    public char hentStatusTegn() {
        if (levende == true){
            return 'O';
    }   else {
        return '.';
    }
    }

    public void leggTilNabo(Celle nabo){
        naboer[antNaboer] = nabo;
        antNaboer++;
           
    }

    public void tellLevendeNaboer(){
        antLevendeNaboer = 0;
        for (Celle nabo : naboer){
            if (nabo != null && nabo.erLevende()){
                antLevendeNaboer++;
            }
           
        }
    }


    public void oppdaterStatus(){
        tellLevendeNaboer();
        if (levende == true){
            if (antLevendeNaboer < 2){
                this.settDoed();
            }
            else if (antLevendeNaboer == 2 || antLevendeNaboer ==3){
                this.settLevende();
            }
            else if (antLevendeNaboer > 3){
                this.settDoed();
            }
        } else {
            if (antLevendeNaboer == 3){
                this.settLevende();
            }
        }
    }
}
