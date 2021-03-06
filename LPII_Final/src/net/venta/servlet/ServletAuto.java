package net.venta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.venta.entidad.Auto;
import net.venta.entidad.Marca;
import net.venta.service.AutoService;
import net.venta.service.MarcaService;

/**
 * Servlet implementation class ServletAuto
 */
@WebServlet("/ServletAuto")
public class ServletAuto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AutoService serviceAuto;
	private MarcaService serviceMarca;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAuto() {
        super();
        serviceAuto = new AutoService();
        serviceMarca = new MarcaService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("accion");
		
		if (action.equals("REGISTRAR")) {
			registraAuto(request, response);
		}
		else if(action.equals("CONSULTA")) {
			consultaAuto(request, response);
		}
		else if (action.equals("LISTA")) {
			listarTodasMarcas(request, response);
		}
	}

	private void listarTodasMarcas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Marca> lista = serviceMarca.listarMarcas();
		
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

	private void registraAuto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descripcion, stock, precio, marca;
		
		descripcion = request.getParameter("descripcion");
		stock = request.getParameter("stock");
		precio = request.getParameter("precio");
		marca = request.getParameter("marca");
		
		Auto bean = new Auto();
		bean.setDescripcion(descripcion);
		bean.setStock(Integer.parseInt(stock));
		bean.setPrecio(Double.parseDouble(precio));
		bean.setMarca(marca);
		
		int salida = serviceAuto.guardarAuto(bean);
		
		if(salida != -1) {
			request.setAttribute("MENSAJE", "Se registr� correctamente");
		} else {
			request.setAttribute("MENSAJE", "Error en el registro");
		}
		
		request.getRequestDispatcher("/registro.jsp").forward(request, response);
	}

	private void consultaAuto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Double desde, hasta;
		desde = Double.parseDouble(request.getParameter("desde"));
		hasta = Double.parseDouble(request.getParameter("hasta"));
		
		List<Auto> lista = serviceAuto.listarAutosRangoPrecio(desde, hasta);
		
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter salida = response.getWriter();
		salida.println(json);
		
	}

}
