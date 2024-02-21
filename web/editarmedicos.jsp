<%-- 
    Document   : productos
    Created on : 06-ene-2022, 16:17:27
    Author     : javier
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="modelo.Medico"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" charset="UTF-8">
        <title>EDITAR Medicos</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    
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
        <div class="container">
            <h1 class="text-center">Editar Medicos</h1>

            <div class="row">
                <section class="col-md-3">
                    <form action="./Medicos?accion=modificar&id=${amod.getIdMedico()}" method="POST" accept-charset="UTF-8">
                        <div><input type="hidden" name="id" value="${amod.getIdMedico()}"></div>
                        <div class="form-group"><label for="nombre">Nombre:</label><input required="true" class="form-control" type="text" name="nombre" id="nombre" value="${amod.getNombre()}"> </div>
                        <div class="form-group"><label for="sala">Sala:</label><input required="true" class="form-control" type="number" name="sala" value="${amod.getSala()}"> </div>
                        <div class="form-group"><label for="especialidad">Especialidad:</label><input required="true" class="form-control" type="text" id="especialidad" name="especialidad" value="${amod.getEspecialidad()}"> </div>
                        <div class="form-group"><label for="tarifa">Tarifa:</label><input required="true" class="form-control" type="number" name="tarifa" value="${amod.getTarifa()}"> </div>
                     <input type="submit" value="modificar" class="btn btn-outline-primary" >
                    </form>
                </section>

            </div>

        </div>

    </body>
</html>
