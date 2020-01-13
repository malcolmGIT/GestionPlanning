package Planning;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

public class Match
{
    private int numMatch;
    private int numTournoi;
    private String nomMatch;
    private Timestamp dateMatch;
    private List<Arbitre> listeArbitreLigne;
    private List<Joueur> participantMatch;
    private Arbitre arbitreChaise;
    private Joueur gagnant;
    private Joueur perdant;
    private List<EquipeRamasseur> listeEquipeRamasseur;

    public Match(int numMatch, int numTournoi, String typeMatch, Timestamp dateMatch, Arbitre arbitreChaise, Joueur gagnant, Joueur perdant)
    {
        this.numMatch = numMatch;
        this.numTournoi = numTournoi;
        this.dateMatch = dateMatch;
        this.listeArbitreLigne = new ArrayList<>();
        this.participantMatch = new ArrayList<>();
        this.nomMatch = participantMatch.get(0).getNomJoueur() + " VS " + participantMatch.get(0).getNomJoueur();
        this.arbitreChaise = arbitreChaise;
    }
    
    public Match(int numMatch, int numTournoi, Timestamp dateMatch)
    {
        this.numMatch = numMatch;
        this.numTournoi = numTournoi;
        this.dateMatch = dateMatch;
        this.listeArbitreLigne = new ArrayList<>();
        this.participantMatch = new ArrayList<>();
        this.listeEquipeRamasseur = new ArrayList<>();
    }
    
    public void setGagnant(Joueur modGagnant)
    {
        gagnant = modGagnant;
    }
    
    public void setPerdant(Joueur modPerdant)
    {
        perdant = modPerdant;
    }

    public void ajouterListeArbitreLigne(Arbitre arbitre)
    {
        listeArbitreLigne.add(arbitre);
    }

    public void setArbitreChaise(Arbitre arbitre)
    {
        this.arbitreChaise = arbitre;
    }

    public String getNom()
    {
        return nomMatch;
    }

    public Timestamp getDateMatch()
    {
        return dateMatch;
    }

    public int getNumMatch()
    {
        return numMatch;
    }  

    public void setDateMatch(Timestamp dateMatch) {
        this.dateMatch = dateMatch;
    }
    
    
    
    @Override
    public String toString()
    {
        return "match n°"+ Integer.toString(numMatch);
    }
}
