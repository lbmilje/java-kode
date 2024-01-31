import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GameOfLifeView {
    private Kontrolloer kontroll;
    private int antRad, antKol;
    private JFrame vindu;
    private JPanel rutenett; // tegneflate
    private JPanel info;
    private JLabel tekstGenNr;
    private JLabel antallLevendeTekst;
    private JButton avsluttknapp;
    public Thread traad;
    public CelleKnapp[][] celleKnappene;

    public GameOfLifeView(Kontrolloer kontroll, int rad, int kol) {
        this.kontroll = kontroll;
        antRad = rad;
        antKol = kol;

        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.exit(1);
        }

        vindu = new JFrame("Game Of Life!");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindu.setSize(1000, 700);
        vindu.setLayout(new BorderLayout());

        leggTilInfo();
        leggTilKnapper();
        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);

        this.traad = new Thread(() -> { // lager en traad
            while (true) {
                try {
                    Thread.sleep(2000); // 2 sekunders intervaller
                } catch (Exception e) {
                    e.printStackTrace();
                }
                kontroll.oppdatering();
                lagKnapper();
                OppdaterGenerasjon();
            }
        });
    }


    public void OppdaterGenerasjon() {
        tekstGenNr.setText("Generasjon: " + kontroll.hentGenerasjonsNr());
        antallLevendeTekst.setText("Antall levende: " + kontroll.hentAntallLevende());
    }


    public void leggTilInfo() {
        info = new JPanel();
        info.setLayout(new GridLayout(1, 4)); // panelet skal ha 1 rad og 3 kolonner

        antallLevendeTekst = new JLabel("Antall levende: " + kontroll.hentAntallLevende());
        antallLevendeTekst.setFont(new Font("Arial", Font.BOLD, 25));
        info.add(antallLevendeTekst); // legger til antall levende

        tekstGenNr = new JLabel("Generasjon: " + kontroll.hentGenerasjonsNr());
        tekstGenNr.setFont(new Font("Arial", Font.BOLD, 25));
        info.add(tekstGenNr);

        JButton startknapp = new StartKnappHandling("Start");
        startknapp.setFont(new Font("Arial", Font.BOLD, 25));
        info.add(startknapp);

        avsluttknapp = new AvsluttKnappHandling("Avslutt");
        avsluttknapp.setFont(new Font("Arial", Font.BOLD, 25));
        info.add(avsluttknapp);

        vindu.add(info, BorderLayout.NORTH); // legger infoen oeverst

    }


    class CelleKnapp extends JButton {
        Celle verdi;

        public CelleKnapp(Celle verdi) {
            super(" ");
            this.verdi = verdi;
            this.addActionListener(new KnappBehandler());
            knappStatus(this);
        }

        class KnappBehandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verdi.erLevende()) {
                    verdi.settDoed();
                    setText(".");
                    antallLevendeTekst.setText("Antall levende: " + kontroll.hentAntallLevende());
                } else {
                    verdi.settLevende();
                    setText("O");
                    antallLevendeTekst.setText("Antall levende: " + kontroll.hentAntallLevende());
                }

            }
        }
    }


    class StartKnappHandling extends JButton {
        public StartKnappHandling(String string) {
            super(string);
            super.addActionListener(new StartKnappBehandler());
        }

        class StartKnappBehandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           traad.start();
            }
        }   
    }


    class AvsluttKnappHandling extends JButton{
        public AvsluttKnappHandling(String string) {
            super(string);
            super.addActionListener(new AvsluttKnappBehandler());  
        }
    
        class AvsluttKnappBehandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(1);
            }
        }
    }


    public void leggTilKnapper() {
        rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(antRad, antKol));

        celleKnappene = new CelleKnapp[antRad][antKol];

        for (int rad = 0; rad < antRad; rad++) {
            for (int kol = 0; kol < antKol; kol++) {
                CelleKnapp knappCelle = new CelleKnapp(kontroll.hentCelle(rad, kol));
                celleKnappene[rad][kol] = knappCelle;
                
                rutenett.add(knappCelle);

            }
        }
        vindu.add(rutenett, BorderLayout.CENTER);
    }


    public void knappStatus(CelleKnapp knapp) {
        if (knapp.verdi.erLevende()) {
            knapp.setText("O");

        } else if (!knapp.verdi.erLevende()){
            knapp.setText(".");
        }

    }


    public void lagKnapper() {
        for (int rad = 0; rad < antRad; rad++) {
            for (int kol = 0; kol < antKol; kol++) {
                knappStatus(celleKnappene[rad][kol]);
            }
        }
    }
}