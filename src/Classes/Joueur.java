package Classes;

public class Joueur
{
    private int numJoueur;
    private String nomJoueur;
    private boolean estElimine;
    private boolean qualifDirect;
    private String statut;

    public Joueur(int numJoueur, String nomJoueur, boolean estElimine, boolean qualifDirect, String statut)
    {
        this.numJoueur = numJoueur;
        this.nomJoueur = nomJoueur;
        this.estElimine = estElimine;
        this.qualifDirect = qualifDirect;
        this.statut = statut;
    }
    
    public Joueur(int numJoueur, String nomJoueur, String statut)
    {
        this.numJoueur = numJoueur;
        this.nomJoueur = nomJoueur;
        this.estElimine = false;
        this.qualifDirect = false;
        this.statut = statut;
    }

    public String getNomJoueur()
    {
        return nomJoueur;
    }

    public int getNumJoueur() {
        return numJoueur;
    }

    public String getStatut() {
        return statut;
    }
    
    

    @Override
    public String toString()
    {
        return "Joueur n°" + numJoueur;
    }
   
}
