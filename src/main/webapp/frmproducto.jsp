<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Punto de venta</title>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="WEB-INF/menu.jsp">
            <jsp:param name="opcion" value="productos"/>
        </jsp:include>

        <div class="container">
            <h1>Formulario de clientes</h1>



            <br/>

            <form action="ProductoControlador" method="post">

                <input type="hidden" name="id" value="${producto.id}"/>   <!-- cliente es lo que se envia desde el controlador-->




                <div class="mb-3">
                    <label for="" class="form-label">Nombre</label>
                    <input type="text" class="form-control"  name="nombre" value="${producto.nombre}" placeholder="Escriba el nombre del producto" >

                </div>
                <div class="mb-3">
                    <label  class="form-label">Descripcion</label>
                    <input type="text" class="form-control" name="descripcion"  value="${producto.descripcion}" placeholder="Escriba la descripcion">
                </div>
                <div class="mb-3 ">
                    <label  class="form-label">Precio</label>
                    <input type="text" class="form-control" name="precio" value="${producto.precio}" placeholder="Escriba el precio">
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>


        </div>
        <script src="https://kit.fontawesome.com/a2dd6045c4.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
