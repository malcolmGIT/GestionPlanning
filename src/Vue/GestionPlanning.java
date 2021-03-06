package Vue;

import DAO.ArbitreDAO;
import DAO.CourtDAO;
import DAO.EquipeRamasseurDAO;
import DAO.JoueurDAO;
import DAO.MatchDAO;
import DAO.RamasseurDAO;
import DAO.ReservationDAO;
import OracleDataSource.OracleDataSourceDAO;
import Classes.Arbitre;
import Classes.Court;
import Classes.EquipeRamasseur;
import Classes.Joueur;
import Classes.Match;
import Classes.Ramasseur;
import Classes.Reservation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionPlanning extends javax.swing.JFrame
{
    private static OracleDataSourceDAO ods;
    private static Connection c;
    private static Statement stmt;
    private static ResultSet res;
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
    private List<Arbitre> listeArbitreDispo;
    private List<Arbitre> listeArbitrePlace;
    private static List<Match> listeMatchDispo;
    private static List<Match> listeMatchPlace;
    private static List<Ramasseur> listeRamasseur;
    private static List<Reservation> listeReservation;
    
    public GestionPlanning()
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
        listeArbitreDispo = new ArrayList<>();
        listeArbitrePlace = new ArrayList<>();
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
        
        listeArbitreChaise = arbitre.getLesArbitresChaise();
        listeArbitreLigne = arbitre.getLesArbitresLigne();
        listeCourt = court.getLesCours();//
        listeEquipeRamasseur = equipe.getLesEquipes();
        listeMatchDispo = match.getMatchsDispos();
        listeMatchPlace = match.getLesMatchsPlace();
        listeRamasseur = ramasseur.getLesRamasseurs();//
        listeReservation = reservation.getLesReservations();//
        panConfirmationResponsable.setVisible(false);
        panResponsable.setVisible(false);
        panLancement.setVisible(true);
        panMatchDouble.setVisible(false);
        panJoueur.setVisible(false);
        panConfirmationReservation.setVisible(false);
        comboMinute.setEnabled(false);
        for(Match m:listeMatchDispo)
        {
            comboNumMatch.addItem(m.toString());
        }
        
        actualiseListeJoueur();
        actualiseListeArbitre();
    }
    
    private void actualiseListeJoueur()
    {
        recupListeJoueur();
        comboJ1.removeAllItems();
        for(Joueur j:listeJoueurDispo)
        {         
            comboJ1.addItem(j.toString());
        }
        comboJ2.removeAllItems();
        for(Joueur j:listeJoueurDispo)
        {
            if(!j.toString().equals(comboJ1.getSelectedItem().toString()))
                comboJ2.addItem(j.toString());
        }
        comboJ3.removeAllItems();
        for(Joueur j:listeJoueurDispo)
        {
            if(!j.toString().equals(comboJ1.getSelectedItem().toString()))
                if(!j.toString().equals(comboJ2.getSelectedItem().toString()))
                    comboJ3.addItem(j.toString());
                
            }
        comboJ4.removeAllItems();
        for(Joueur j:listeJoueurDispo)
        {
            if(!j.toString().equals(comboJ1.getSelectedItem().toString()))
                if(!j.toString().equals(comboJ2.getSelectedItem().toString()))
                    if(!j.toString().equals(comboJ3.getSelectedItem().toString()))
                        comboJ4.addItem(j.toString());
                
            }
    }
    
    private void actualiseListeArbitre()
    {
        recupListeArbitre();
        comboArbitre1.removeAllItems();
        for(Arbitre a : listeArbitreDispo)
        {         
            comboArbitre1.addItem(a.toString());
        }
        comboArbitre2.removeAllItems();
        for(Arbitre j:listeArbitreDispo)
        {
            if(!j.toString().equals(comboArbitre1.getSelectedItem().toString()))
                comboArbitre2.addItem(j.toString());
        }
        comboArbitre3.removeAllItems();
        for(Arbitre j:listeArbitreDispo)
        {
            if(!j.toString().equals(comboArbitre1.getSelectedItem().toString()))
                if(!j.toString().equals(comboArbitre2.getSelectedItem().toString()))
                    comboArbitre3.addItem(j.toString());
        }
        comboArbitre4.removeAllItems();
        for(Arbitre j:listeArbitreDispo)
        {
            if(!j.toString().equals(comboArbitre1.getSelectedItem().toString()))
                if(!j.toString().equals(comboArbitre2.getSelectedItem().toString()))
                    if(!j.toString().equals(comboArbitre3.getSelectedItem().toString()))
                        comboArbitre4.addItem(j.toString());
        }
        comboArbitre5.removeAllItems();
        for(Arbitre j:listeArbitreDispo)
        {
            if(!j.toString().equals(comboArbitre1.getSelectedItem().toString()))
                if(!j.toString().equals(comboArbitre2.getSelectedItem().toString()))
                    if(!j.toString().equals(comboArbitre3.getSelectedItem().toString()))
                        if(!j.toString().equals(comboArbitre4.getSelectedItem().toString()))
                            comboArbitre5.addItem(j.toString());
        }
        comboArbitre6.removeAllItems();
        for(Arbitre j:listeArbitreDispo)
        {
            if(!j.toString().equals(comboArbitre1.getSelectedItem().toString()))
                if(!j.toString().equals(comboArbitre2.getSelectedItem().toString()))
                    if(!j.toString().equals(comboArbitre3.getSelectedItem().toString()))
                        if(!j.toString().equals(comboArbitre4.getSelectedItem().toString()))
                            if(!j.toString().equals(comboArbitre5.getSelectedItem().toString()))
                                comboArbitre6.addItem(j.toString());
        }
        comboArbitre7.removeAllItems();
        for(Arbitre j:listeArbitreDispo)
        {
            if(!j.toString().equals(comboArbitre1.getSelectedItem().toString()))
                if(!j.toString().equals(comboArbitre2.getSelectedItem().toString()))
                    if(!j.toString().equals(comboArbitre3.getSelectedItem().toString()))
                        if(!j.toString().equals(comboArbitre4.getSelectedItem().toString()))
                            if(!j.toString().equals(comboArbitre5.getSelectedItem().toString()))
                                if(!j.toString().equals(comboArbitre6.getSelectedItem().toString()))
                                    comboArbitre7.addItem(j.toString());
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
        arbitre.getArbitresLigne(comboJour.getSelectedItem().toString(), comboMois.getSelectedItem().toString(), comboAnnee.getSelectedItem().toString(), comboHeure.getSelectedItem().toString(), comboMinute.getSelectedItem().toString());
        listeArbitrePlace = arbitre.getListeArbitrePlace();
        listeArbitreDispo = arbitre.getListeArbitreDispo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panConfirmationResponsable = new javax.swing.JPanel();
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
        comboJ1 = new javax.swing.JComboBox<>();
        comboJ2 = new javax.swing.JComboBox<>();
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
        comboJ4 = new javax.swing.JComboBox<>();
        comboJ3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        panLancement = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        butJoueur = new javax.swing.JButton();
        butResponsable = new javax.swing.JButton();
        panJoueur = new javax.swing.JPanel();
        TFnom = new javax.swing.JTextField();
        CBHeureDébutReservation = new javax.swing.JComboBox<>();
        CBJourReservation = new javax.swing.JComboBox<>();
        SNombrePersonnesReservation = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        BValider = new javax.swing.JButton();
        panConfirmationReservation = new javax.swing.JPanel();
        LblTextConfirmation = new javax.swing.JLabel();
        BLancement = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panConfirmationResponsable.setPreferredSize(new java.awt.Dimension(400, 300));

        butValider1.setText("OK");
        butValider1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butValider1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panConfirmationResponsableLayout = new javax.swing.GroupLayout(panConfirmationResponsable);
        panConfirmationResponsable.setLayout(panConfirmationResponsableLayout);
        panConfirmationResponsableLayout.setHorizontalGroup(
            panConfirmationResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panConfirmationResponsableLayout.createSequentialGroup()
                .addGroup(panConfirmationResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panConfirmationResponsableLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel7))
                    .addGroup(panConfirmationResponsableLayout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(butValider1)))
                .addContainerGap(740, Short.MAX_VALUE))
        );
        panConfirmationResponsableLayout.setVerticalGroup(
            panConfirmationResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panConfirmationResponsableLayout.createSequentialGroup()
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

        comboJ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboJ1ActionPerformed(evt);
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

        comboArbitre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboArbitre1ActionPerformed(evt);
            }
        });

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
                .addComponent(comboJ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboJ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );
        panMatchDoubleLayout.setVerticalGroup(
            panMatchDoubleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMatchDoubleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panMatchDoubleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(comboJ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboJ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton1.setText("< Retour");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel20.setText("Equipe de ramasseur :");

        jLabel21.setText("Arbitres de ligne :");

        jLabel22.setText("Arbitre de chaise :");

        javax.swing.GroupLayout panResponsableLayout = new javax.swing.GroupLayout(panResponsable);
        panResponsable.setLayout(panResponsableLayout);
        panResponsableLayout.setHorizontalGroup(
            panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResponsableLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(butValider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panResponsableLayout.createSequentialGroup()
                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panResponsableLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panResponsableLayout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addComponent(jLabel4))
                            .addGroup(panResponsableLayout.createSequentialGroup()
                                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                            .addComponent(comboNumMatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jCheckBox1))
                                .addGap(92, 92, 92)
                                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                            .addComponent(jLabel13)
                                            .addGap(18, 18, 18)
                                            .addComponent(comboArbitre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panResponsableLayout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(18, 18, 18)
                                            .addComponent(comboArbitre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel21))
                                    .addComponent(jLabel20))
                                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panResponsableLayout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(panResponsableLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addGap(202, 359, Short.MAX_VALUE))
        );
        panResponsableLayout.setVerticalGroup(
            panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResponsableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addGap(25, 25, 25)
                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel20)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(comboJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel22)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(comboJ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(panResponsableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panResponsableLayout.createSequentialGroup()
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
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
                            .addComponent(butResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
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

        TFnom.setText("Entrez votre nom");
        TFnom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFnomActionPerformed(evt);
            }
        });

        CBHeureDébutReservation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBHeureDébutReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBHeureDébutReservationActionPerformed(evt);
            }
        });

        CBJourReservation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel20.setText("Votre nom");

        jLabel21.setText("Heure de début");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel22.setText("Jour");

        jLabel23.setText("Court");

        jLabel24.setText("Nombre de personnes");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel25.setText("Réserver un créneau d'entraînement");

        BValider.setText("Réserver avec ces informations");
        BValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BValiderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panJoueurLayout = new javax.swing.GroupLayout(panJoueur);
        panJoueur.setLayout(panJoueurLayout);
        panJoueurLayout.setHorizontalGroup(
            panJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panJoueurLayout.createSequentialGroup()
                .addGroup(panJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panJoueurLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(panJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23)
                            .addComponent(jLabel20)
                            .addComponent(SNombrePersonnesReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panJoueurLayout.createSequentialGroup()
                                .addGroup(panJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(TFnom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                        .addComponent(CBHeureDébutReservation, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel21))
                                .addGap(18, 18, 18)
                                .addGroup(panJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(CBJourReservation, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panJoueurLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel25)))
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panJoueurLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BValider)
                .addGap(247, 247, 247))
        );
        panJoueurLayout.setVerticalGroup(
            panJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panJoueurLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel25)
                .addGap(29, 29, 29)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TFnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(2, 2, 2)
                .addGroup(panJoueurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBHeureDébutReservation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBJourReservation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addGap(5, 5, 5)
                .addComponent(SNombrePersonnesReservation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BValider)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        LblTextConfirmation.setText("jLabel26");

        BLancement.setText("Retourner à l'écran de lancement");
        BLancement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLancementActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panConfirmationReservationLayout = new javax.swing.GroupLayout(panConfirmationReservation);
        panConfirmationReservation.setLayout(panConfirmationReservationLayout);
        panConfirmationReservationLayout.setHorizontalGroup(
            panConfirmationReservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panConfirmationReservationLayout.createSequentialGroup()
                .addGroup(panConfirmationReservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panConfirmationReservationLayout.createSequentialGroup()
                        .addGap(402, 402, 402)
                        .addComponent(LblTextConfirmation))
                    .addGroup(panConfirmationReservationLayout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(BLancement)))
                .addContainerGap(347, Short.MAX_VALUE))
        );
        panConfirmationReservationLayout.setVerticalGroup(
            panConfirmationReservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panConfirmationReservationLayout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(LblTextConfirmation)
                .addGap(73, 73, 73)
                .addComponent(BLancement)
                .addContainerGap(236, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panConfirmationResponsable, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(238, 238, 238)
                    .addComponent(panLancement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(239, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panConfirmationReservation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panResponsable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panConfirmationJoueur, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(panLancement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(130, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panConfirmationReservation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void butValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butValiderActionPerformed
        
        panResponsable.setVisible(false);
        panConfirmationJoueur.setVisible(true);  
        panMatchDouble.setVisible(false);
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
        int nj1 =joueur.chercherNumJoueur(comboJ1.getSelectedItem().toString(), listeJoueurDispo);
        int nj2 =joueur.chercherNumJoueur(comboJ2.getSelectedItem().toString(), listeJoueurDispo);
        try {
            joueur.placerJoueur(nj1,nj2,match.chercherMatch(comboNumMatch.getSelectedItem().toString(), listeMatchDispo).getNumMatch());
        } catch (SQLException ex) {
            System.out.println("erreur valider : " + ex);
        }
        actualiseListeJoueur();
        actualiseListeArbitre();
    }//GEN-LAST:event_butValiderActionPerformed

    private void butValider1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butValider1ActionPerformed
        panConfirmationResponsable.setVisible(false);
        panResponsable.setVisible(true);
        
    }//GEN-LAST:event_butValider1ActionPerformed

    private void comboNumMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNumMatchActionPerformed
        
    }//GEN-LAST:event_comboNumMatchActionPerformed

    private void comboAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAnneeActionPerformed
        actualiseListeJoueur();
       actualiseListeArbitre();
    }//GEN-LAST:event_comboAnneeActionPerformed

    private void comboMinuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMinuteActionPerformed
        actualiseListeJoueur();
      actualiseListeArbitre();
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

    private void comboJ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboJ1ActionPerformed
        comboJ2.removeAllItems();
        for(Joueur j:listeJoueurDispo)
        {
            if(comboJ1.getSelectedItem() == null)
                return;
            else if(!j.toString().equals(comboJ1.getSelectedItem().toString()))
                comboJ2.addItem(j.toString());
        }
    }//GEN-LAST:event_comboJ1ActionPerformed

    
    
    private void comboCourtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCourtActionPerformed

    }//GEN-LAST:event_comboCourtActionPerformed

    private void butJoueurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butJoueurActionPerformed
        panLancement.setVisible(false);
        panJoueur.setVisible(true);
        
        CBHeureDébutReservation.removeAllItems();
        for(String heure:reservation.getHeureDebut())
        {
            CBHeureDébutReservation.addItem(heure);
        }
        
        CBJourReservation.removeAllItems();
        for(String jour:reservation.getJours())
        {
            CBJourReservation.addItem(jour);
        }
        
        jComboBox3.removeAllItems();
        for(String typeCourt:reservation.getTypeCourts())
        {
            jComboBox3.addItem(typeCourt);
        }
    }//GEN-LAST:event_butJoueurActionPerformed

    private void butResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butResponsableActionPerformed
        // TODO add your handling code here:
        panConfirmationResponsable.setVisible(false);
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

    private void comboArbitre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboArbitre1ActionPerformed
    comboArbitre2.removeAllItems();
            for(Arbitre a:listeArbitreDispo)
            {
                if(comboArbitre2.getSelectedItem() == null)
                    return;
                else if(!a.toString().equals(comboArbitre1.getSelectedItem().toString()))
                    comboArbitre2.addItem(a.toString());
            }        // TODO add your handling code here:
    }//GEN-LAST:event_comboArbitre1ActionPerformed

    private void CBHeureDébutReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBHeureDébutReservationActionPerformed
        // TODO add your handling code here:
        
        //for()
    }//GEN-LAST:event_CBHeureDébutReservationActionPerformed

    private void TFnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFnomActionPerformed

    private void BValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BValiderActionPerformed
        boolean reservationReussie = false;
        try {
            // TODO add your handling code here:
            reservation.ajouterReservation(TFnom.getText(), (String)CBHeureDébutReservation.getSelectedItem(), (String)CBJourReservation.getSelectedItem(), (int) SNombrePersonnesReservation.getValue(), (String)jComboBox3.getSelectedItem());
            reservationReussie = true;
        } catch (SQLException ex) {
            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        } 
        panJoueur.setVisible(false);
        panConfirmationReservation.setVisible(true);
        if(reservationReussie)
        {
            LblTextConfirmation.setText("La réservation a bien été effectuée !");
        }
        else
        {
            LblTextConfirmation.setText("Une erreur est survenue !");
        }
    }//GEN-LAST:event_BValiderActionPerformed

    private void BLancementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLancementActionPerformed
        // TODO add your handling code here:
        panConfirmationReservation.setVisible(false);
        panLancement.setVisible(true);
    }//GEN-LAST:event_BLancementActionPerformed

    
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
            java.util.logging.Logger.getLogger(GestionPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionPlanning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                new GestionPlanning().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BLancement;
    private javax.swing.JButton BValider;
    private javax.swing.JComboBox<String> CBHeureDébutReservation;
    private javax.swing.JComboBox<String> CBJourReservation;
    private javax.swing.JLabel LblTextConfirmation;
    private javax.swing.JSpinner SNombrePersonnesReservation;
    private javax.swing.JTextField TFnom;
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
    private javax.swing.JComboBox<String> comboJ1;
    private javax.swing.JComboBox<String> comboJ2;
    private javax.swing.JComboBox<String> comboJ3;
    private javax.swing.JComboBox<String> comboJ4;
    private javax.swing.JComboBox<String> comboJour;
    private javax.swing.JComboBox<String> comboMinute;
    private javax.swing.JComboBox<String> comboMois;
    private javax.swing.JComboBox<String> comboNumMatch;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel panConfirmationJoueur;
    private javax.swing.JPanel panConfirmationReservation;
    private javax.swing.JPanel panJoueur;
    private javax.swing.JPanel panLancement;
    private javax.swing.JPanel panMatchDouble;
    private javax.swing.JPanel panResponsable;
    // End of variables declaration//GEN-END:variables

    
}
