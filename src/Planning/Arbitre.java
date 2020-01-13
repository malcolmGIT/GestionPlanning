package Planning;

import java.util.List;

public class Arbitre
{
    private int numArbitre;
    private String nomArbitre;
    private String categorie;
    private String nationalite;
    private String typeArbitre;
    
    public Arbitre(int numArbitre, String nomArbitre, String categorie, String nationalite, String typeArbitre)
    {
        this.categorie = categorie;
        this.nationalite = nationalite;
        this.nomArbitre = nomArbitre;
        this.numArbitre = numArbitre;
    }
    
    public List<String> getTypeArbitre()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
