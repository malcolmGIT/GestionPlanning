package Classes;

import java.util.List;

public class Arbitre
{
    private int numArbitre;
    private String nomArbitre;
    private String categorie;
    private String nationalite;
    private String typeArbitre;
    
    public Arbitre(int numArbitre, String typeArbitre, String nomArbitre, String nationalite, String categorie)
    {
        this.categorie = categorie;
        this.nationalite = nationalite;
        this.nomArbitre = nomArbitre;
        this.numArbitre = numArbitre;
        this.typeArbitre = typeArbitre;
    }

    public Arbitre(int numArbitre) {
    this.numArbitre=numArbitre;
    }
    
    public List<String> getTypeArbitre()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getNumArbitre(){
        return numArbitre;
    }

    @Override
    public String toString()
    {
        return "arbitre n°" + numArbitre;
    }
    
    
}
