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
<title>Registro Autos</title>
</head>
<body>
<jsp:include page="menu.jsp"/>

<c:if test="${requestScope.MENSAJE!=null}">
	<div class="alert alert-warning alert-dismissible fade show" role="alert">
	  <strong>${requestScope.MENSAJE}</strong>
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	  </button>
	</div>
</c:if>

	<div class="container">
		<h2 class="text-center mt-5 mb-5">Nuevo Auto</h2>
		<form method="post" action="ServletAuto?accion=REGISTRAR" id="registro-form">
		
			<div class="form-group">
      			<label for="marca">Marca</label>
        			<select class="form-control" name="marca" id="marcas">
       		 			<option value="">[Seleccione]</option>
      				</select>
     		</div>
		
			<div class="form-group">
    			<label for="inputDescripcion">Descripción</label>
    			<input type="text" class="form-control" id="inputDescripcion" name="descripcion">
  			</div>
  			<div class="form-row">
  				<div class="form-group col-md-6">
    				<label for="inputStock">Stock</label>
    				<input type="number" class="form-control" id="inputStock" name="stock">
	  			</div>
	  			<div class="form-group col-md-6">
	    			<label for="inputPrecio">Precio</label>
	    			<input type="number" step="any" class="form-control" id="inputPrecio" name="precio">
	  			</div>
  			</div>

  			<div class="text-center mt-5">
     			<button type="submit" class="btn btn-primary pl-5 pr-5">Registrar</button>
    		</div>
		</form>
	</div>

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<!-- jQuery Validation (Minificados) -->
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>
	
	<script>
	
		$.getJSON("ServletAuto?accion=LISTA",{}, function(response) {
			$.each(response, function(index, item) {
				$("#marcas").append("<option value='"+item.codigo+"'>" + item.nombre + "</option");
			});
		});
	
		$("#registro-form").validate({
			rules: {
				marca: {
					required: true
				},
				descripcion: {
					required: true,
					pattern: '.{3,40}'
				},
				stock: {
					required: true,
					pattern: '[1-9][0-9]{1,2}||[1-9]{1}'
				},
				precio: {
					required: true,
					pattern: '\\d{1,2}\\.\\d{1,2}||\\d{1,2}'
				}
			},
			messages: {
				marca: {
					required: 'Seleccione una marca'
				},
				descripcion: {
					required: 'Ingrese una descripción',
					pattern: 'La descripción debe tener entre 3 y 40 caracteres'
				},
				stock: {
					required: 'Ingrese el stock',
					pattern: 'El stock debe ser mayor que cero, y tener entre 1 y 3 dígitos'
				},
				precio: {
					required: 'Ingrese un precio',
					pattern: 'El precio debe tener un máximo de dos dígitos'
				}
			},
			
			errorElement: 'span',
			errorPlacement: function (error, element) {
				error.addClass('invalid-feedback');
				element.closest('.form-group').append(error);
			},
			highlight: function (element, errorClass, validClass) {
				$(element).addClass('is-invalid');
			},
			unhighlight: function (element, errorClass, validaCLass) {
				$(element).removeClass('is-invalid');
			}
			
		});
	</script>
</body>
</html>