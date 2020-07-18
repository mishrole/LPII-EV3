package net.venta.interfaces;

import java.util.List;
import net.venta.entidad.Auto;

public interface AutoDAO {
	public int insertAuto(Auto bean);
	public List<Auto> listAllAutos();
	public List<Auto> listAutosRangoPrecio(double desde, double hasta);
}
