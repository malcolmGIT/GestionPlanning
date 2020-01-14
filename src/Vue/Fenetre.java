package Vue;

import DAO.ArbitreDAO;
import DAO.CourtDAO;
import DAO.EquipeRamasseurDAO;
import DAO.JoueurDAO;
import DAO.MatchDAO;
import DAO.RamasseurDAO;
import DAO.ReservationDAO;
import OracleDataSource.OracleDataSourceDAO;
import Planning.Arbitre;
import Planning.Court;
import Planning.EquipeRamasseur;
import Planning.Joueur;
import Planning.Match;
import Planning.Ramasseur;
import Planning.Reservation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Fenetre extends javax.swing.JFrame
{
    private static OracleDataSourceDAO ods;
    private static Connection c;
    private static ArbitreDAO arbitre;
    private static CourtDAO court;
    private static EquipeRamasseurDAO equipe;
    private static JoueurDAO joueur;
    private static MatchDAO match;
    private static RamasseurDAO ramasseur;
    private static ReservationDAO reservation;
    private static List<Arbitre> listeArbitreChaise;
    private static List<Arbitre> listeArbitreLigne;
    private static List<Arbitre> listeArbitre;
    private static List<Court> listeCourt;
    private static List<EquipeRamasseur> listeEquipeRamasseur;
    private static List<Joueur> listeJoueurDispo;
    private static List<Joueur> listeJoueurPlace;
    private static List<Match> listeMatchDispo;
    private static List<Match> listeMatchPlace;
    private static List<Ramasseur> listeRamasseur;
    private static List<Reservation> listeReservation;
    
    public Fenetre()
    {
        initComponents();
        arbitre = new ArbitreDAO();
        court = new CourtDAO();
        equipe = new EquipeRamasseurDAO();
        joueur = new JoueurDAO();
        match = new MatchDAO();
        ramasseur = new RamasseurDAO();
        reservation = new ReservationDAO();
        listeArbitreChaise = new ArrayList<>();
        listeArbitreLigne = new ArrayList<>();
        listeArbitre = new ArrayList<>();
        listeCourt = new ArrayList<>();
        listeEquipeRamasseur = new ArrayList<>();
        listeJoueurDispo = new ArrayList<>();
        listeJoueurPlace = new ArrayList<>();
        listeMatchDispo = new ArrayList<>();
        listeMatchPlace = new ArrayList<>();
        listeRamasseur = new ArrayList<>();
        listeReservation = new ArrayList<>();
        ods = OracleDataSourceDAO.getOracleDataSource();
        try
        {
            c = ods.getConnection();
            arbitre.setConnection(c);
            arbitre.setDataSource(ods);
            court.setConnection(c);
            court.setDataSource(ods);
            equipe.setConnection(c);
            equipe.setDataSource(ods);
            joueur.setConnection(c);
            joueur.setDataSource(ods);
            match.setConnection(c);
            match.setDataSource(ods);
            ramasseur.setConnection(c);
            ramasseur.setDataSource(ods);
            reservation.setConnection(c);
            reservation.setDataSource(ods);         
        }
        catch(SQLException e)
        {
            System.out.println("Erreur !! " + e);
        }
        listeArbitre = arbitre.getLesArbitres();
        listeArbitreChaise = arbitre.getLesArbitresChaise();
        listeArbitreLigne = arbitre.getLesArbitresLigne();
        listeCourt = court.getLesCours();
        listeEquipeRamasseur = equipe.getLesEquipes();
        listeMatchDispo = match.getMatchsDispos();
        listeMatchPlace = match.getLesMatchsPlace();
        listeRamasseur = ramasseur.getLesRamasseurs();
        listeReservation = reservation.getLesReservations();
        panConfirmationJoueur.setVisible(false);
        panResponsable.setVisible(false);
        panLancement.setVisible(true);
        panMatchDouble.setVisible(false);
        for(Match m:listeMatchDispo)
        {
            comboNumMatch.addItem(m.toString());
        }
        comboMinute.setEnabled(false);
        actualiseListeJoueur();
    }
    
    private void actualiseListeJoueur()
    {
        recupListeJoueur();
        jComboBox7.removeAllItems();
        for(Joueur j:listeJoueurDispo)
        {         
            jComboBox7.addItem(j.toString());
        }
        jComboBox8.removeAllItems();
        for(Joueur j:listeJoueurDispo)
        {
            if(!j.toString().equals(jComboBox7.getSelectedItem().toString()))
                jComboBox8.addItem(j.toString());
        }
    }
    
    private void recupListeJoueur()
    {
        joueur.getLesJoueurs(comboJour.getSelectedItem().toString(), comboMois.getSelectedItem().toString(), comboAnnee.getSelectedItem().toString(), comboHeure.getSelectedItem().toString(), comboMinute.getSelectedItem().toString());
        listeJoueurPlace = joueur.getListeJoueurPlace();
        listeJoueurDispo = joueur.getListeJoueurDispo();
    }
    
    private void recupListeArbitre()
    {
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panConfirmationJoueur = new javax.swing.JPanel();
        butValider1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        panResponsable = new javax.swing.JPanel();
        butValider = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        comboNumMatch = new javax.swing.JComboBox<>();
        comboJour = new javax.swing.JComboBox<>();
        comboMois = new javax.swing.JComboBox<>();
        comboAnnee = new javax.swing.JComboBox<>();
        comboHeure = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        comboMinute = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        comboCourt = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboArbitre1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        comboArbitre2 = new javax.swing.JComboBox<>();
        comboArbitre3 = new javax.swing.JComboBox<>();
        comboArbitre4 = new javax.swing.JComboBox<>();
        comboArbitre5 = new javax.swing.JComboBox<>();
        comboArbitre6 = new javax.swing.JComboBox<>();
        comboArbitre7 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        panMatchDouble = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        panLancement = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        butJoueur = new javax.swing.JButton();
        butResponsable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panConfirmationJoueur.setPreferredSize(new java.awt.Dimension(400, 300));

        butValider1.setText("OK");
        butValider1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butValider1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panConfirmationJoueurLayout = new javax.swing.GroupLayout(panConfirmationJoueur);
        panConfirmationJoueur.setLayout(panConfirmationJoueurLayout);
        panConfirmationJoueurLayout.setHorizontalGroup(
            panConfirmationJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panConfirmationJoueurLayout.createSequentialGroup()
                .addGroup(panConfirmationJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panConfirmationJoueurLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel7))
                    .addGroup(panConfirmationJoueurLayout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(butValider1)))
                .addContainerGap(742, Short.MAX_VALUE))
        );
        panConfirmationJoueurLayout.setVerticalGroup(
            panConfirmationJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panConfirmationJoueurLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(butValider1)
                .addContainerGap(413, Short.MAX_VALUE))
        );

        butValider.setText("Valider");
        butValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butValiderActionPerformed(evt);
            }
        });

        jLabel3.setText("N ° Match :");

        comboNumMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNumMatchActionPerformed(evt);
            }
        });

        comboJour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        comboJour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboJourActionPerformed(evt);
            }
        });

        comboMois.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        comboMois.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMoisActionPerformed(evt);
            }
        });

        comboAnnee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2021" }));
        comboAnnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAnneeActionPerformed(evt);
            }
        });

        comboHeure.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "11", "12" }));
        comboHeure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboHeureActionPerformed(evt);
            }
        });

        jLabel6.setText(":");

        comboMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "30" }));
        comboMinute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMinuteActionPerformed(evt);
            }
        });

        jLabel1.setText("Joueur 1 :");

        jLabel2.setText("Joueur 2 :");

        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel8.setText("N ° Court :");

        comboCourt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        comboCourt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCourtActionPerformed(evt);
            }
        });

        jLabel10.setText("Date :");

        jLabel11.setText("Heure : ");

        jLabel4.setText("Affecter des joueurs et arbitres à un match");

        jLabel9.setText("Arbitre 1 : ");

        jLabel12.setText("Arbitre 3 :");

        jLabel13.setText("Arbitre 2 :");

        jLabel14.setText("Arbitre 4 :");

        jLabel15.setText("Arbitre 5 :");

        jLabel16.setText("Arbitre 6 :");

        jLabel17.setText("Arbitre 7:");

        jCheckBox1.setText("Match Double");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel18.setText("Joueur 3 :");

        jLabel19.setText("Joueur 4 :");

        javax.swing.GroupLayout panMatchDoubleLayout = new javax.swing.GroupLayout(panMatchDouble);
        panMatchDouble.setLayout(panMatchDoubleLayout);
        panMatchDoubleLayout.setHorizontalGroup(
            panMatchDoubleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMatchDoubleLayout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );
        panMatchDoubleLayout.setVerticalGroup(
            panMatchDoubleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMatchDoubleLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panMatchDoubleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panResponsableLayout = new javax.swing.GroupLayout(panResponsable);
        panResponsable.setLayout(panResponsableLayout);
        panResponsableLayout.setHorizontalGroup(
            panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResponsableLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panResponsableLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(butValider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panResponsableLayout.createSequentialGroup()
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addGroup(panResponsableLayout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jLabel4))
                            .addGroup(panResponsableLayout.createSequentialGroup()
                                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(panMatchDouble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboCourt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panResponsableLayout.createSequentialGroup()
                                                .addComponent(comboJour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(comboMois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(comboAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panResponsableLayout.createSequentialGroup()
                                                .addComponent(comboHeure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                                        .addGap(13, 13, 13)
                                                        .addComponent(comboMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(comboNumMatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(92, 92, 92)
                                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboArbitre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboArbitre4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboArbitre5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panResponsableLayout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(comboArbitre7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panResponsableLayout.createSequentialGroup()
                                            .addComponent(jLabel16)
                                            .addGap(18, 18, 18)
                                            .addComponent(comboArbitre6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboArbitre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboArbitre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(443, Short.MAX_VALUE))))
        );
        panResponsableLayout.setVerticalGroup(
            panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResponsableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(25, 25, 25)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panResponsableLayout.createSequentialGroup()
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addComponent(panMatchDouble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(comboNumMatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboJour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboMois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(38, 38, 38)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboHeure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addGap(35, 35, 35)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(comboCourt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(butValider, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panResponsableLayout.createSequentialGroup()
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(comboArbitre1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(comboArbitre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(comboArbitre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(comboArbitre4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(comboArbitre5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(comboArbitre6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(comboArbitre7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel5.setText("Vous êtes : ");

        butJoueur.setText("Joueur");
        butJoueur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butJoueurActionPerformed(evt);
            }
        });

        butResponsable.setText("Responsable planning");
        butResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butResponsableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panLancementLayout = new javax.swing.GroupLayout(panLancement);
        panLancement.setLayout(panLancementLayout);
        panLancementLayout.setHorizontalGroup(
            panLancementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panLancementLayout.createSequentialGroup()
                .addGroup(panLancementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panLancementLayout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jLabel5))
                    .addGroup(panLancementLayout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addGroup(panLancementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(butJoueur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        panLancementLayout.setVerticalGroup(
            panLancementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panLancementLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel5)
                .addGap(59, 59, 59)
                .addComponent(butJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(butResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panConfirmationJoueur, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(238, 238, 238)
                    .addComponent(panLancement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(239, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panResponsable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panConfirmationJoueur, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(panLancement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(130, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void butValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butValiderActionPerformed
        
        panResponsable.setVisible(false);
        panConfirmationJoueur.setVisible(true);       
        if(match.placerUnMatch(listeMatchDispo, listeMatchPlace, match.chercherMatch(comboNumMatch.getSelectedItem().toString(), listeMatchDispo), comboJour.getSelectedItem().toString(), comboMois.getSelectedItem().toString(), comboAnnee.getSelectedItem().toString(), comboHeure.getSelectedItem().toString(), comboMinute.getSelectedItem().toString()))
        {    
            if(match.estBienPlace(match.chercherMatch(comboNumMatch.getSelectedItem().toString(), listeMatchPlace), listeMatchPlace, comboCourt.getSelectedItem().toString()))
            {            
                jLabel7.setText("                                           " + comboNumMatch.getSelectedItem().toString() + " placé ");
                comboNumMatch.removeItem(comboNumMatch.getSelectedItem());            
            }
            else
            {
                jLabel7.setText("Erreur : Il faut un intervalle d'au moins une heure avec les autres match du court.");
                match.retirerMatch(listeMatchDispo, listeMatchPlace, match.chercherMatch(comboNumMatch.getSelectedItem().toString(), listeMatchPlace));
            }
        }
        actualiseListeJoueur();
    }//GEN-LAST:event_butValiderActionPerformed

    private void butValider1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butValider1ActionPerformed
        panConfirmationJoueur.setVisible(false);
        panResponsable.setVisible(true);
        
    }//GEN-LAST:event_butValider1ActionPerformed

    private void comboNumMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNumMatchActionPerformed
        
    }//GEN-LAST:event_comboNumMatchActionPerformed

    private void comboAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAnneeActionPerformed
        actualiseListeJoueur();
    }//GEN-LAST:event_comboAnneeActionPerformed

    private void comboMinuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMinuteActionPerformed
        actualiseListeJoueur();
    }//GEN-LAST:event_comboMinuteActionPerformed

    private void comboHeureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboHeureActionPerformed
        if(comboHeure.getSelectedItem().toString().equals("12"))
        {
            comboMinute.setEnabled(true);
        }
        else
        {
            comboMinute.setSelectedItem("00");
            comboMinute.setEnabled(false);
        }
        actualiseListeJoueur();
    }//GEN-LAST:event_comboHeureActionPerformed

    private void comboJourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboJourActionPerformed
        actualiseListeJoueur();
    }//GEN-LAST:event_comboJourActionPerformed

    private void comboMoisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMoisActionPerformed
        actualiseListeJoueur();
    }//GEN-LAST:event_comboMoisActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        jComboBox8.removeAllItems();
        for(Joueur j:listeJoueurDispo)
        {
            if(jComboBox7.getSelectedItem() == null)
                return;
            else if(!j.toString().equals(jComboBox7.getSelectedItem().toString()))
                jComboBox8.addItem(j.toString());
        }
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void comboCourtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCourtActionPerformed

    }//GEN-LAST:event_comboCourtActionPerformed

    private void butJoueurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butJoueurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butJoueurActionPerformed

    private void butResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butResponsableActionPerformed
        // TODO add your handling code here:
        panConfirmationJoueur.setVisible(false);
        panResponsable.setVisible(true);
        panLancement.setVisible(false);
    }//GEN-LAST:event_butResponsableActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()){
            panMatchDouble.setVisible(true);
        }
        else{
            panMatchDouble.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                new Fenetre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butJoueur;
    private javax.swing.JButton butResponsable;
    private javax.swing.JButton butValider;
    private javax.swing.JButton butValider1;
    private javax.swing.JComboBox<String> comboAnnee;
    private javax.swing.JComboBox<String> comboArbitre1;
    private javax.swing.JComboBox<String> comboArbitre2;
    private javax.swing.JComboBox<String> comboArbitre3;
    private javax.swing.JComboBox<String> comboArbitre4;
    private javax.swing.JComboBox<String> comboArbitre5;
    private javax.swing.JComboBox<String> comboArbitre6;
    private javax.swing.JComboBox<String> comboArbitre7;
    private javax.swing.JComboBox comboCourt;
    private javax.swing.JComboBox<String> comboHeure;
    private javax.swing.JComboBox<String> comboJour;
    private javax.swing.JComboBox<String> comboMinute;
    private javax.swing.JComboBox<String> comboMois;
    private javax.swing.JComboBox<String> comboNumMatch;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel panConfirmationJoueur;
    private javax.swing.JPanel panLancement;
    private javax.swing.JPanel panMatchDouble;
    private javax.swing.JPanel panResponsable;
    // End of variables declaration//GEN-END:variables

    
}
