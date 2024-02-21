<%-- 
    Document   : productos
    Created on : 06-ene-2022, 16:17:27
    Author     : javier
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="modelo.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" charset="UTF-8">
        <title>Registrar Usuario</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://cdn.tailwindcss.com"></script>
        <script>
function validarFormulario() {
    var nombre = document.getElementById('nombre').value;
    var usuario = document.getElementById("usuario").value;
    var password = document.getElementById("password").value;
    if (nombre.trim() === '' || usuario.trim() === '' || password.trim() === '') {
        alert('No dejes espacios en blanco');
        return false;
    }
    return true;
}
</script>

    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Registo de usuario</h1>

            <div class="row">
                <section class="col-md-3">
                    <form action="./Usuarios?accion=insertar" onsubmit="return validarFormulario()" method="POST" accept-charset="UTF-8">
                        <div><input type="hidden" name="id" ></div>
                        <div class="form-group"><label for="nombre">Nombre:</label><input required="true" class="form-control" type="text" id="nombre" name="nombre"> </div>
                        <div class="form-group"><label for="usuario">Usuario:</label><input required="true" class="form-control" type="text" id="usuario" name="usuario"> </div>
                        <div class="form-group"><label for="clave">Contraseña:</label><input required="true" class="form-control" type="password" id="password" name="clave"> </div>
                        <input type="submit" value="Registrarse" class="btn btn-outline-primary">
                        
                    </form>
                <a href="./index.jsp"><button id="Volver" class="btn btn-outline-primary">Volver</button></a> 
                </section>
            </div>

        </div>

    </body>
</html>
