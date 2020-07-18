package net.venta.service;

import java.util.List;

import net.venta.entidad.Marca;
import net.venta.fabrica.DAOFactory;
import net.venta.interfaces.MarcaDAO;

public class MarcaService {
	DAOFactory fabrica = DAOFactory.getDAOFactory(1);
	MarcaDAO daoMarca = fabrica.getMarcaDAO();
	
	public List<Marca> listarMarcas() {
		return daoMarca.listMarcas();
	}
}
