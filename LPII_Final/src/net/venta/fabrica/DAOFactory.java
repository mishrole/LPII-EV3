package net.venta.fabrica;

import net.venta.interfaces.AutoDAO;
import net.venta.interfaces.MarcaDAO;

public abstract class DAOFactory {
	public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int DB2 = 3;
    public static final int SQLSERVER = 4;
    public static final int XML = 5;
    
    // Registro de DAOs
    
    public abstract AutoDAO getAutoDAO();
    public abstract MarcaDAO getMarcaDAO();

    public static DAOFactory getDAOFactory(int whichFactory){
        switch(whichFactory){
       	case MYSQL:
        	   return new MySqlDAOFactory();
        	case XML:
        	    //return new XmlDAOFactory();
        	case ORACLE:
        	    return new OracleDAOFactory();
        	/*case DB2:
        	    return new Db2DAOFactory();*/
        	case SQLSERVER:
        	    return new SqlServerDAOFactory();
        	/*case XML:
        	    return new XmlDAOFactory();*/
        	default:
        	    return null;
        }
     }
}
