package DAO;

import Classes.Arbitre;
import Classes.Joueur;
import Classes.Match;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class MatchDAO
{
    private DataSource ds;
    private Connection connexionBD;
    private List<Match> listeMatch;
    private Match match;
    private Statement stmt;
    private Statement _stmt;
    private Statement __stmt;
    private ResultSet resultat;
    
    public List<Match> getMatchsDispos()
    {
        try
        {
            stmt = connexionBD.createStatement();
            resultat = stmt.executeQuery("select nummatch, numtournoi, datematch from match where datematch is null order by nummatch");
            listeMatch = new ArrayList<>();
            
            while(resultat.next())
            {
                match = new Match(resultat.getInt(1), resultat.getInt(2), resultat.getTimestamp(3));
                listeMatch.add(match);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Erreur ! : " + e);
        }
        return listeMatch;
    }
    
    public boolean estBienPlace(Match match, List<Match> listeMatchPlace, String numCourt)
    {
         try
        {
            stmt = connexionBD.createStatement();
            resultat = stmt.executeQuery("select nummatch, numcourt, datematch from match where datematch is not null and numcourt = " + numCourt + " and nummatch <> " + match.getNumMatch() + " order by nummatch");           
            while(resultat.next())
            {
                if(match.getDateMatch().getTime() - resultat.getTimestamp(3).getTime() > -3600000 && match.getDateMatch().getTime() - resultat.getTimestamp(3).getTime() < 3600000)
                {
                    return false;
                }
            }
            return true;
        }
        catch(SQLException e)
        {
            System.out.println("Erreur ! : " + e);
            return false;
        }
         
    }
    
    public Match chercherMatch(String _match, List<Match> listeMatch)
    {
        for(Match match:listeMatch)
        {
            if(_match.equals(match.toString()))
            {
                return match;
            }
        }
        return null;
    }
    
    public boolean placerUnMatch(List<Match> listeMatchDispo, List<Match> listeMatchPlace, Match match, String jour, String mois, String annee, String heure, String minute, Joueur j1, Joueur j2, Arbitre aC,Arbitre a1, Arbitre a2, Arbitre a3, Arbitre a4, Arbitre a5, Arbitre a6, Arbitre a7)
    {
        try
        {
            stmt = connexionBD.createStatement();
            _stmt = connexionBD.createStatement();
            __stmt = connexionBD.createStatement();
            int req = stmt.executeUpdate("update match set datematch = '" + jour + "/" + mois + "/" + annee.substring(2) + " " + heure + ":" + minute + "'" + " where nummatch = " + Integer.toString(match.getNumMatch()));
            req = stmt.executeUpdate("commit");
            resultat = _stmt.executeQuery("select datematch from match where nummatch = " + match.getNumMatch());
            if(resultat.next())
            {
                match.setDateMatch(resultat.getTimestamp(1));
            }
            
            listeMatchDispo.remove(match);
            listeMatchPlace.add(match);
            req = __stmt.executeUpdate("update match set numjoueur1 = " + j1.getNumJoueur() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numjoueur2 = " + j2.getNumJoueur() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitrechaise = " + aC.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre1 = " + a1.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre2 = " + a2.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre3 = " + a3.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre4 = " + a4.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre5 = " + a5.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre6 = " + a6.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre7 = " + a7.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("commit");
            stmt.close();
            _stmt.close();
            __stmt.close();
            return true;
        }
        catch(SQLException e)
        {
            System.out.println("Erreur placer match ! : " + e);
            return false;
        }
        
    }
    
    
    public boolean placerUnMatchDouble(List<Match> listeMatchDispo, List<Match> listeMatchPlace, Match match, String jour, String mois, String annee, String heure, String minute, Joueur j1, Joueur j2, Joueur j3, Joueur j4,Arbitre aC,Arbitre a1, Arbitre a2, Arbitre a3, Arbitre a4, Arbitre a5, Arbitre a6, Arbitre a7)
    {
        try
        {
            stmt = connexionBD.createStatement();
            _stmt = connexionBD.createStatement();
            __stmt = connexionBD.createStatement();
            int req = stmt.executeUpdate("update match set datematch = '" + jour + "/" + mois + "/" + annee.substring(2) + " " + heure + ":" + minute + "'" + " where nummatch = " + Integer.toString(match.getNumMatch()));
            req = stmt.executeUpdate("commit");
            resultat = _stmt.executeQuery("select datematch from match where nummatch = " + match.getNumMatch());
            if(resultat.next())
            {
                match.setDateMatch(resultat.getTimestamp(1));
            }
            
            listeMatchDispo.remove(match);
            listeMatchPlace.add(match);
            req = __stmt.executeUpdate("update match set numjoueur1 = " + j1.getNumJoueur() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numjoueur2 = " + j2.getNumJoueur() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numjoueur3 = " + j3.getNumJoueur() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numjoueur4 = " + j4.getNumJoueur() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitrechaise = " + aC.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre1 = " + a1.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre2 = " + a2.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre3 = " + a3.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre4 = " + a4.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre5 = " + a5.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre6 = " + a6.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("update match set numarbitre7 = " + a7.getNumArbitre() + " where nummatch = " + match.getNumMatch());
            req = __stmt.executeUpdate("commit");
            stmt.close();
            _stmt.close();
            __stmt.close();
            return true;
        }
        catch(SQLException e)
        {
            System.out.println("Erreur placer match ! : " + e);
            return false;
        }
        
    }
    public boolean retirerMatch(List<Match> listeMatchDispo, List<Match> listeMatchPlace, Match match)
    {
        try
        {
            stmt = connexionBD.createStatement();
            int l = stmt.executeUpdate("update match set datematch = null where nummatch = " + Integer.toString(match.getNumMatch()));
            l = stmt.executeUpdate("commit");
            listeMatchPlace.remove(match);
            listeMatchDispo.add(match);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println("Erreur ! : " + e);
            return false;
        }     
    }
    
    public List<Match> getLesMatchsPlace()
    {
        try
        {
            stmt = connexionBD.createStatement();
            resultat = stmt.executeQuery("select nummatch, numtournoi, datematch from match where datematch is not null order by nummatch");
            listeMatch = new ArrayList<>();
            
            while(resultat.next())
            {
                match = new Match(resultat.getInt(1), resultat.getInt(2), resultat.getTimestamp(3));
                listeMatch.add(match);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Erreur get les matchs place! : " + e);
        }
        return listeMatch;
    }
    
    public String afficherHeureMatch(Match match)
    {
        return match.getDateMatch().toString().substring(11, 16);
    }
    
    public String afficherDateMatch(Match match)
    {
        return match.getDateMatch().toString().substring(0, 10);
    }
    
    public String afficherHorraireMatch(Match match)
    {
        return afficherDateMatch(match) + "   à   " + afficherHeureMatch(match);
    }
    
    public void setDataSource(DataSource ds)
    {
        this.ds = ds;
    }

    public void setConnection(Connection c)
    {
        this.connexionBD = c;
    }

    
}
