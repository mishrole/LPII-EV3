package net.venta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.venta.entidad.Marca;
import net.venta.interfaces.MarcaDAO;
import net.venta.utils.MySqlBDConexion;

public class MySqlMarcaDAO implements MarcaDAO{

	@Override
	public List<Marca> listMarcas() {
		List<Marca> lista = new ArrayList<Marca>();
		
		Marca bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Select cod_marca, nom_marca from tb_marca";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Marca();
				bean.setCodigo(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				lista.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm != null) pstm.close();
				if (cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}

}
