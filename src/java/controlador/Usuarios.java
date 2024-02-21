/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ConectorBD;

/**
 *
 * @author luish
 */
public class Usuarios extends HttpServlet {

    ConectorBD bd;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Usuarios</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Usuarios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "iniciarSesion":
                    this.existeUsuario(request, response);
                    break;
                default:
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        bd = new ConectorBD();
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarUsuario(request, response);
                    break;
                    
                case "iniciarSesion":
                    this.existeUsuario(request, response);
                    break;
                    
            }
        }
    }
    
    private static final byte[] SALT = {
        // Puedes definir tu propio valor de salt aquí
        (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78,
        (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF
    };
    

    
    protected static String encriptar(String password) {
        String generatedSecuredPasswordHash = null;
        int iterations = 10000;
        char[] chars = password.toCharArray();
        byte[] salt = SALT;
        
        KeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            generatedSecuredPasswordHash = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return generatedSecuredPasswordHash;
    }
    
    protected void existeUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        
        clave = encriptar(clave);
        
        if (bd.conectar()) {
            if (bd.comprobarUsuarioExiste(usuario, clave)) {
                this.cargarPagina(request, response, "./Medicos");
            }
            else{
                this.cargarPagina(request, response, "./index.jsp");
            }
        }
        
    }
    
    //TODO crear pagina registro y poner campos
    protected void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
       
        clave = encriptar(clave);
        
        if (bd.conectar()) {
            if (bd.altausuario(nombre, usuario, clave)) {
                cargarPagina(request, response, "./index.jsp");
            }
        }
        
    }
    
    
    
    protected void cargarPagina(HttpServletRequest request, HttpServletResponse response, String ruta) throws ServletException, IOException {
        request.getRequestDispatcher(ruta).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
