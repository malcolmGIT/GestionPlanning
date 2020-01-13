package OracleDataSource;

import java.sql.*;
import javax.sql.*;
import java.lang.Object;
import oracle.jdbc.pool.OracleDataSource;


public class OracleDataSourceDAO extends OracleDataSource
{
    private static OracleDataSourceDAO ods;
    
    public OracleDataSourceDAO() throws SQLException
    {
        
    }
    
    public static OracleDataSourceDAO getOracleDataSource()
    {
        try
        {
            ods = new OracleDataSourceDAO();
            ods.setDriverType("thin");
            ods.setPortNumber(1521);
            ods.setServiceName("orcl.univ-lyon1.fr");
            ods.setUser("p1809561");
            ods.setPassword("372075");
            ods.setServerName("iutdoua-oracle.univ-lyon1.fr");     
        }
        catch(SQLException e)
        {
            System.out.println("Erreur " + e);
        }
        finally
        {
            
        }
        return ods;
    }

}
