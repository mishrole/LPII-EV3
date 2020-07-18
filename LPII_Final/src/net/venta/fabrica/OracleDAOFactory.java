package net.venta.fabrica;

import net.venta.dao.MySqlAutoDAO;
import net.venta.dao.MySqlMarcaDAO;
import net.venta.interfaces.AutoDAO;
import net.venta.interfaces.MarcaDAO;

public class OracleDAOFactory extends DAOFactory {

	@Override
	public AutoDAO getAutoDAO() {
		return new MySqlAutoDAO();
	}

	@Override
	public MarcaDAO getMarcaDAO() {
		return new MySqlMarcaDAO();
	}
}
