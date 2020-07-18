package net.venta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.venta.entidad.Auto;
import net.venta.interfaces.AutoDAO;
import net.venta.utils.MySqlBDConexion;

public class MySqlAutoDAO implements AutoDAO{

	@Override
	public int insertAuto(Auto bean) {
		int estado = -1;
		
		Connection cn = null;
		PreparedStatement pstm = null;
		
		try {
			cn = MySqlBDConexion.getConexion();
			String sql = "Insert into tb_auto values(null, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);
			
			pstm.setString(1, bean.getDescripcion());
			pstm.setInt(2, bean.getStock());
			pstm.setDouble(3, bean.getPrecio());
			pstm.setInt(4, Integer.parseInt(bean.getMarca()));
			
			estado = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (cn != null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return estado;
	}

	@Override
	public List<Auto> listAllAutos() {
		List<Auto> lista = new ArrayList<Auto>();
		
		Auto bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select A.cod_auto, A.des_auto, A.stock_auto, A.pre_auto, M.nom_marca from tb_auto A inner join tb_marca M on A.cod_marca = M.cod_marca";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Auto();
				bean.setCodigo(rs.getInt(1));
				bean.setDescripcion(rs.getString(2));
				bean.setStock(rs.getInt(3));
				bean.setPrecio(rs.getDouble(4));
				bean.setMarca(rs.getString(5));
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

	@Override
	public List<Auto> listAutosRangoPrecio(double desde, double hasta) {
		List<Auto> lista = new ArrayList<Auto>();
		
		Auto bean = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			cn = MySqlBDConexion.getConexion();
			String sql = "Select A.cod_auto, A.des_auto, A.stock_auto, A.pre_auto, M.nom_marca from tb_auto A inner join tb_marca M on A.cod_marca = M.cod_marca where A.pre_auto between ? and ?";
			pstm = cn.prepareStatement(sql);
			
			pstm.setDouble(1, desde);
			pstm.setDouble(2, hasta);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				bean = new Auto();
				bean.setCodigo(rs.getInt(1));
				bean.setDescripcion(rs.getString(2));
				bean.setStock(rs.getInt(3));
				bean.setPrecio(rs.getDouble(4));
				bean.setMarca(rs.getString(5));
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
