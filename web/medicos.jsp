<%-- 
    Document   : productos
    Created on : 06-ene-2022, 16:17:27
    Author     : javier
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="modelo.Medico"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" charset="UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
       <script src="https://cdn.tailwindcss.com"></script>
       <script>
function validarFormulario() {
    var nombre = document.getElementById('nombre').value;
    var especialidad = document.getElementById("especialidad").value;
    if (nombre.trim() === '' || especialidad.trim() === '') {
        alert('No dejes espacios en blanco');
        return false;
    }
    return true;
}
</script>
    </head>
    <body>
        <!<!-- comment -->
        <div class="container">
            <h1 class="text-center">Gestión de Médicos</h1>

            <div class="row">
                <section class="col-md-3">
                    <form action="./Medicos?accion=insertar" onsubmit="return validarFormulario()" method="POST" accept-charset="UTF-8">

                        <div><input type="hidden" name="id" value="${a.getIdMedico()}"></div>
                        <div class="form-group"><label for="nombre">Nombre</label><input required="true" class="form-control" type="text" id="nombre" name="nombre" value="" > </div>
                        <div class="form-group"><label for="sala">Sala</label><input required="true" class="form-control" type="number" name="sala" value="" > </div>
                        <div class="form-group"><label for="especialidad">Especialidad</label><input required="true" class="form-control" id="especialidad" type="text" name="especialidad" value="" ></div>
                        <div class="form-group"><label for="tarifa">Tarifa</label><input required="true" class="form-control" type="number" name="tarifa" value="" > </div>
                        <input type="submit" value="insertar" class="btn btn-outline-primary">
                    </form>
                </section>



                </section>
                <section class="col-md-9">
                    <table class="table table-sm table-hover">
                        <thead class="thead-dark">
                        <th>id</th>
                        <th>nombre</th>
                        <th>sala</th>
                        <th>especialidad</th>
                        <th>tarifa</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${medicos}" var="lista">
                                <tr>
                                    <td>${lista.getIdMedico()}</td>
                                    <td>${lista.getNombre()}</td>
                                    <td>${lista.getSala()}</td>
                                    <td>${lista.getEspecialidad()}</td>
                                    <td>${lista.getTarifa()}</td>
                                    <td class="row"> 
                                        <a class="btn btn-outline-warning"  href="./Medicos?accion=editar&id=${lista.getIdMedico()}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a> &nbsp;
                                        <a class="btn btn-outline-danger" href="./Medicos?accion=eliminar&id=${lista.getIdMedico()}"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
                                </tr>
                            </c:forEach>

                                            
                        </tbody>
                    </table>
                </section>
            </div>
         <div class="bg-red-500 w-40 h-40 text-white text-2xl flex justify-center items-center flex-column mt-4">
             <p>Tarifa Total:</p>
             <p>${totalTarifa}€</p>
         </div>
         <div class="bg-green-500 w-40 h-40 text-white text-2xl flex justify-center items-center flex-column mt-4">
             <p>Total Medicos:</p>
             <p>${totalMedico}</p>
         </div>
        </div>
                        

    </body>
</html>
