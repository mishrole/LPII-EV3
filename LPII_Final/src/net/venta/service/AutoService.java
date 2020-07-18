package net.venta.service;

import java.util.List;
import net.venta.entidad.Auto;
import net.venta.fabrica.DAOFactory;
import net.venta.interfaces.AutoDAO;

public class AutoService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	AutoDAO daoAuto = fabrica.getAutoDAO();
	
	public int guardarAuto(Auto bean) {
		return daoAuto.insertAuto(bean);
	}
	
	public List<Auto> listarTodosAutos() {
		return daoAuto.listAllAutos();
	}
	
	public List<Auto> listarAutosRangoPrecio(double desde, double hasta) {
		return daoAuto.listAutosRangoPrecio(desde, hasta);
	}

}
