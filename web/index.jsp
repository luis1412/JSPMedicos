<%-- 
    Document   : Index
    Created on : 14 feb. 2024, 8:59:09
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Usuario"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión Médicos</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-teal-200 flex justify-center items-center h-screen">
    <div class="bg-white rounded-lg flex flex-col items-center md:flex-row">
        <div class="md:w-1/2">
            <img class="h-auto rounded-tl-lg rounded-bl-lg w-full object-cover md:h-full md:w-70" src="medicos.jpg" alt="Imagen medico">
        </div>
        <div class="md:w-1/2 md:ml-8">
            <h1 class="text-2xl font-bold mb-4">Gestión Médicos</h1>
            <form action="./Usuarios?accion=iniciarSesion" method="POST" accept-charset="UTF-8" class="flex flex-col items-center md:items-start">
                <div class="mb-4 w-full md:w-96">
                    <label for="email" class="block">Email</label>
                    <input required="true" type="text" id="email" name="usuario" class="w-full border rounded-md py-2 px-3">
                </div>
                <div class="mb-4 w-full md:w-96">
                    <label for="password" class="block">Contraseña</label>
                    <input required="true" type="password" id="password" name="clave" class="w-full border rounded-md py-2 px-3">
                </div>
                <button id="iniciarSesion" type="submit" class="bg-black text-white py-2 px-4 w-full md:w-96 rounded-md mb-4">Iniciar Sesión</button>
            </form>
            <p class="text-center">¿Aún no tienes cuenta? <a href="./registro.jsp" class="text-blue-500">Regístrate aquí</a></p>
        </div>
    </div>
</body>
</html>

