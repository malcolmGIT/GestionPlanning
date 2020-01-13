package DAO;

import Planning.Arbitre;
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
    private Arbitre arbitre;
    private Statement stmt;
    private ResultSet resultat;
    
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
    
    public void setDataSource(DataSource ds)
    {
        this.ds = ds;
    }

    public void setConnection(Connection c)
    {
        this.connexionBD = c;
    }
}
