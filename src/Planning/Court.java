package Planning;

import java.sql.Date;

public class Court
{
    private int numCourt;
    private boolean estLibre;

    public Court(int numCourt)
    {
        this.numCourt = numCourt;
        estLibre = true;
    }
    
    public boolean estLibre(Date horaire)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
