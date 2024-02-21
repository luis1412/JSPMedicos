/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ConectorBD;
import modelo.Medico;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "Medicos", urlPatterns = {"/Medicos"})
public class Medicos extends HttpServlet {

    private ConectorBD bd;

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
            out.println("<title>Servlet Medicos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Medicos at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarMedico(request, response);
                    break;

                case "eliminar":
                    this.eliminarMedico(request, response);
                    break;

                default:
                    this.cargarPagina(request, response);
            }
        } else {
            this.cargarPagina(request, response);
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
          bd=new ConectorBD();
        String accion=request.getParameter("accion");
        if (accion !=null) {
            switch(accion){
                case "insertar":
                    this.insertarMedico(request, response); break;
                case "modificar":
                       this.modificarMedico(request, response); break;
                 case "editar":
                       this.editarMedico(request, response); break;
                case "eliminar":
                      this.eliminarMedico(request, response); break;
                default:
                       this.cargarPagina(request, response);
            }
            
        } else {
                 this.cargarPagina(request, response);
        }
        
        
       
    }

    private void cargarPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        bd = new ConectorBD();
        if (bd.conectar()) {
            List<Medico> medicos = bd.listar();
            System.out.println("medico" + medicos);
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setAttribute("medicos", medicos);
            request.setAttribute("totalMedico", bd.numeroMedicos());
            request.setAttribute("totalTarifa", bd.tarifaTotal());

            request.getRequestDispatcher("./medicos.jsp").forward(request, response);
            return;
        }
    }

    protected void modificarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer i = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        Short sala = Short.parseShort(request.getParameter("sala"));
        String especialidad = request.getParameter("especialidad");
        int tarifa = Integer.parseInt(request.getParameter("tarifa"));

        if (bd.conectar()) {
            if (bd.actualizarMedico(i, nombre, sala, especialidad, tarifa)) {
                System.out.println("modificado");
            }

        }
        this.cargarPagina(request, response);

    }

    protected void editarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Medico a = new Medico();
        if (bd.conectar()) {
            a = bd.buscarMedico(id);
        }

        request.setAttribute("amod", a);
        String jspEditar = "./editarmedicos.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
        return;

    }

    protected void eliminarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        if (bd.conectar()) {
            if (bd.eliminarMedico(Integer.parseInt(id))) {
                this.cargarPagina(request, response);
            }
        }

        this.cargarPagina(request, response);

    }

    protected void insertarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        Short sala = Short.parseShort(request.getParameter("sala"));
        String especialidad = request.getParameter("especialidad");
        int tarifa = Integer.parseInt(request.getParameter("tarifa"));

        if (bd.conectar()) {
            if (bd.altaMedico(nombre, sala, especialidad, tarifa)) {
                this.cargarPagina(request, response);
            }
        }

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
