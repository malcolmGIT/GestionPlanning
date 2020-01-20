package DAO;

import Classes.Arbitre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ArbitreDAO
{
    private DataSource ds;
    private Connection connexionBD;
    private List<Arbitre> listeArbitre;
    private List<Arbitre> listeArbitreLigne;
    private List<Arbitre> listeArbitreChaise;
    private List<Arbitre> listeArbitreChaiseDispo;
    private List<Arbitre> listeArbitreChaisePlace;
    private List<Arbitre> listeArbitreDispo;
    private List<Arbitre> listeArbitrePlace;
    private Arbitre arbitre;
    private Statement stmt;
    private Statement _stmt;
    private ResultSet resultat;
    private ResultSet _resultat;
    private ResultSet __resultat;
    private ResultSet ___resultat;
    private String ts;
    private int i;
    
    public List<Arbitre> getLesArbitres()
    {
        try
        {
            stmt = connexionBD.createStatement();
            resultat = stmt.executeQuery("select * from arbitre");
            listeArbitre = new ArrayList<>();
            
            while(resultat.next())
            {
                arbitre = new Arbitre(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getString(4), resultat.getString(5));
                listeArbitre.add(arbitre);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Erreur ! : " + e);
        }
        return listeArbitre;
    }
    
    public void getArbitresLigne(String jour, String mois, String annee, String heure, String minute)
    {
        try
        {
            ts = jour + "/" + mois + "/" + annee.substring(2) + " " + heure + ":" + minute;
            stmt = connexionBD.createStatement();
            _stmt = connexionBD.createStatement();
            resultat = stmt.executeQuery("select * from arbitre where numarbitre in(select  numarbitre1 from (match join arbitre a on numarbitre1 = a.numarbitre) join arbitre b on numarbitre2 = b.numarbitre where datematch = '" + ts + "') and typearbitre='ligne' order by numarbitre");
            listeArbitreLigne = new ArrayList<>();
            i = _stmt.executeUpdate("delete from arbitreligneplace");    
            while(resultat.next())
            {
                arbitre = new Arbitre(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getString(4), resultat.getString(5));        
                i = _stmt.executeUpdate("insert into arbitreligne place values(" + arbitre.getNumArbitre() + "')");
            }
            resultat.close();
            _resultat = stmt.executeQuery("select * from arbitre where numarbitre in(select numarbitre2 from (match join arbitre a on numarbitre1 = a.numarbitre) join arbitre b on numarbitre2 = b.numarbitre where datematch = '" + ts + "') order by numarbitre");
            while(_resultat.next())
            {
                arbitre = new Arbitre(_resultat.getInt(1), _resultat.getString(2), _resultat.getString(3), _resultat.getString(4), _resultat.getString(5));        
                i = _stmt.executeUpdate("insert into arbitreligne place values(" + arbitre.getNumArbitre() + "')");
            }
            _resultat.close();
            __resultat = stmt.executeQuery("select * from arbitreligneplace order by numarbitre");
            while(__resultat.next())
            {
                arbitre = new Arbitre(__resultat.getInt(1));
                if(!listeArbitreLigne.contains(arbitre))
                    listeArbitrePlace.add(arbitre);
            }
            __resultat.close();
            listeArbitreDispo = listeArbitreLigne;
            listeArbitreLigne.clear();
            ___resultat = stmt.executeQuery("select * from arbitre where numarbitre not in(select numarbitre from arbitreligneplace) order by numarbitre");
            while(___resultat.next())
            {
                arbitre = new Arbitre(___resultat.getInt(1), ___resultat.getString(2), ___resultat.getString(3), ___resultat.getString(4), ___resultat.getString(5));
                if(!listeArbitreLigne.contains(arbitre))
                    listeArbitreLigne.add(arbitre);
            }
            ___resultat.close();
            listeArbitreDispo = listeArbitreLigne;
        }
        catch(SQLException e)
        {
            System.out.println("Erreur ! : " + e);
        }
    }
    
    public void getArbitresChaise(String jour, String mois, String annee, String heure, String minute)
    {
        try
        {
            ts = jour + "/" + mois + "/" + annee.substring(2) + " " + heure + ":" + minute;
            stmt = connexionBD.createStatement();
            _stmt = connexionBD.createStatement();
            resultat = stmt.executeQuery("select * from arbitre where numarbitre in(select  numarbitre1 from (match join arbitre a on numarbitre1 = a.numarbitre) join arbitre b on numarbitre2 = b.numarbitre where datematch = '" + ts + "') and typearbitre='chaise' order by numarbitre");
            listeArbitreChaise = new ArrayList<>();
            i = _stmt.executeUpdate("delete from arbitrechaiseplace");    
            while(resultat.next())
            {
                arbitre = new Arbitre(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getString(4), resultat.getString(5));        
                i = _stmt.executeUpdate("insert into arbitrechaise place values(" + arbitre.getNumArbitre() + "')");
            }
            resultat.close();
            _resultat = stmt.executeQuery("select * from arbitre where numarbitre in(select numarbitre2 from (match join arbitre a on numarbitre1 = a.numarbitre) join arbitre b on numarbitre2 = b.numarbitre where datematch = '" + ts + "') and typearbitre='chaise' order by numarbitre");
            while(_resultat.next())
            {
                arbitre = new Arbitre(_resultat.getInt(1), _resultat.getString(2), _resultat.getString(3), _resultat.getString(4), _resultat.getString(5));        
                i = _stmt.executeUpdate("insert into arbitrechaise place values(" + arbitre.getNumArbitre() + "')");
            }
            _resultat.close();
            __resultat = stmt.executeQuery("select * from arbitrechaiseplace order by numarbitre");
            while(__resultat.next())
            {
                arbitre = new Arbitre(__resultat.getInt(1));
                if(!listeArbitreChaise.contains(arbitre))
                    listeArbitreChaise.add(arbitre);
            }
            __resultat.close();
            listeArbitreDispo = listeArbitreChaise;
            listeArbitreChaise.clear();
            ___resultat = stmt.executeQuery("select * from arbitre where numarbitre not in(select numarbitre from arbitreligneplace) order by numarbitre");
            while(___resultat.next())
            {
                arbitre = new Arbitre(___resultat.getInt(1), ___resultat.getString(2), ___resultat.getString(3), ___resultat.getString(4), ___resultat.getString(5));
                if(!listeArbitreChaise.contains(arbitre))
                    listeArbitreChaise.add(arbitre);
            }
            ___resultat.close();
            listeArbitreDispo = listeArbitreChaise;
        }
        catch(SQLException e)
        {
            System.out.println("Erreur ! : " + e);
        }
    }
    
    public List<Arbitre> getLesArbitresLigne()
    {
        try
        {
            stmt = connexionBD.createStatement();
            resultat = stmt.executeQuery("select * from arbitre where typearbitre = lower('ligne')");
            listeArbitre = new ArrayList<>();
            
            while(resultat.next())
            {
                arbitre = new Arbitre(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getString(4), resultat.getString(5));
                listeArbitre.add(arbitre);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Erreur ! : " + e);
        }
        return listeArbitre;
    }
    
    public List<Arbitre> getLesArbitresChaise()
    {
        try
        {
            stmt = connexionBD.createStatement();
            resultat = stmt.executeQuery("select * from arbitre where typearbitre = lower('chaise')");
            listeArbitre = new ArrayList<>();
            
            while(resultat.next())
            {
                arbitre = new Arbitre(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getString(4), resultat.getString(5));
                listeArbitre.add(arbitre);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Erreur ! : " + e);
        }
        return listeArbitre;
    }
    
    public List<Arbitre> getListeArbitreDispo(){
        return listeArbitreDispo;
    }
    
    public List<Arbitre> getListeArbitrePlace(){
        return listeArbitrePlace;
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
