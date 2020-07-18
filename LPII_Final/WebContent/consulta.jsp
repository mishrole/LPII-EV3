<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap 4.5 CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">
<!-- <link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"> -->

<title>Consulta</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	
	<div class="container">
		<h2 class="text-center mt-5 mb-5">Consulta por precio</h2>
		<span class="mb-5"></span>
		<form id="consulta-form">
			<div class="form-group row">
	    		<label for="inputDesde" class="col-sm-2 col-form-label">Precio mínimo</label>
	    		<div class="col-sm-2">
	      			<input type="text" class="form-control" id="inputDesde" name="desde">  
	    		</div>
	    		
	    		<label for="inputHasta" class="col-sm-2 col-form-label">Precio máximo</label>
	    		<div class="col-sm-2">
	      			<input type="text" class="form-control" id="inputHasta" name="hasta">  
	    		</div>
	    		<div class="col-sm-2">
	    			<button type="button" class="btn btn-primary">Consulta</button><p>
	    		</div>
  			</div>
		</form>
					
		<table id="table_id" class="table table-striped table-bordered">
		    <thead>
		        <tr>
		            <th>Código</th>
		            <th>Descripción</th>
		            <th>Stock</th>
		            <th>Precio</th>
		            <th>Marca</th>
		        </tr>
		    </thead>
		    <tbody>
				<c:forEach items="${requestScope.normativas}" var="item">
					<tr>
			         	<td>${item.normativa_id}</td>
			         	<td>${item.normativa_nombre}</td>
			         	<!-- <td><a href="ServletNormativa?accion=buscar&codigo=${row.normativa_id}">Editar</a></td> -->
			        </tr>
				</c:forEach>
		    </tbody>
		</table>
	</div>
	

	<!-- Optional JavaScript -->
    <!-- jQuery first, then DataTables for Bootstrap 4, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/v/bs4/jq-3.3.1/dt-1.10.21/datatables.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

	<script type="text/javascript">
	$(document).ready( function () {
	    $('#table_id').DataTable( {
	    	searching: false
	    });
	} );
	
	$(".btn-primary").click(function() {
		var desde = $("#inputDesde").val();
		var hasta = $("#inputHasta").val();
		
		$("#table_id tbody").empty();
		
		$.getJSON("ServletAuto?accion=CONSULTA_AUTO", {desde:desde, hasta:hasta}, function(response) {
			$.each(response, function(index, item) {
				$("#table_id").append(
						"<tr><td>" + item.codigo + "</td><td>" + item.descripcion +
						"</td><td>" + item.stock + "</td><td>" + item.precio +
						"</td><td>" + item.marca + "</td></tr>"
				);
			});
		});
	});
	
	</script>

</body>
</html>