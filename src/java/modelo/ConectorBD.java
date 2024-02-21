/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class ConectorBD {
    
    
      private final String bd = "tarea6";
    private final String url = "jdbc:mysql://localhost/";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;
    private  final String login = "root";
    private final String password = "";
     Statement stmt = null;

    public ConectorBD() {
    }

    public Connection getConexion() {
        return conn;
    }

    public boolean conectar() {
        boolean b = false;

        try {
            //Obtenemos el driver de mysql
            Class.forName(driver);
            //Establecemos conexión
            conn = DriverManager.getConnection(url + bd, login, password);

            if (conn != null) {
                System.out.println("Conexión a " + bd + " establecida correctamente.");
                b = true;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b;
    }

    public boolean close() {
        boolean b = false;
        if (this.conn != null) {
            try {
                conn.close();
                conn = null;
                b = true;

                System.out.println("La conexión a la base de datos " + bd + " se ha terminado.");
            } catch (SQLException ex) {
                Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return b;
    }
 
     public boolean altaMedico(String nombre, Short sala, String especialidad, int tarifa ) {
          boolean b = false;
          String query;
          
          query = "INSERT INTO `medico`(`nombre`, `sala`, `especialidad`, `tarifa`) VALUES ('" + nombre + "','"+ sala + "','" + especialidad + "','" + tarifa + "')";
        
        try {
            System.out.println("Conexion: " + conn.getCatalog());
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            b=true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return b;
    }
     public boolean altausuario(String nombre, String usuario, String clave) {
          boolean b = false;
          String query;
          
          query = String.format("INSERT INTO `usuario`(`nombre`, `usuario`, `clave`) VALUES ('%s','%s','%s')", nombre, usuario, clave);
        
        try {
            System.out.println("Conexion: " + conn.getCatalog());
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            b=true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return b;
    }
     
    public boolean comprobarUsuarioExiste(String usuario, String clave) {
        try {
            Statement orden = conn.createStatement();
            String query2 = String.format("SELECT * FROM `usuario` WHERE `usuario` = '%s' AND `clave` = '%s'", usuario, clave);
            ResultSet query = orden.executeQuery(query2);
            if (query.next()) {
               return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
     
     
     
     
     public int numeroMedicos(){
          ArrayList<Medico> medicos =  listar();
          return medicos.size();
         
     }
     
     public int tarifaTotal(){
       ArrayList<Medico> medicos =  listar();
       return medicos.stream().mapToInt(Medico::getTarifa).sum();
     }
     
     
     
     public Medico buscarMedico(int id) {
         Medico a = null;

        try {
            Statement orden = conn.createStatement();
            ResultSet query = orden.executeQuery("select * from medico where `id_medico` = " + id );

            if (query.next()) {
                a = new Medico();

                a.setIdMedico(Integer.parseInt(query.getString("id_medico")));
                a.setSala(Short.parseShort(query.getString("sala")));
                a.setEspecialidad(query.getString("especialidad"));
                a.setTarifa(Integer.parseInt(query.getString("tarifa")));
                a.setNombre(query.getString("nombre"));
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }
     
     public boolean actualizarMedico(Integer i,String nombre, Short sala, String especialidad, int tarifa) {
          boolean b = false;
          String query;
          query = String.format("UPDATE `medico` SET `nombre`='%s',`sala`='%d',`especialidad`='%s',`tarifa`='%d' WHERE `id_medico` = %d", nombre, sala, especialidad, tarifa, i);
        
        try {
            System.out.println("Conexion: " + conn.getCatalog());
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            b=true;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return b;
    }
     
    public boolean eliminarMedico(int id) {
     
        boolean b = false;

        try {
            Statement orden = conn.createStatement();
            orden.executeUpdate("DELETE FROM `medico` WHERE `id_medico` = '" + id + "'");
            orden.close();
            b = true;
        } catch (SQLException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b;
    }
    
     public ArrayList<Medico> listar() {
        ArrayList<Medico> lista = new ArrayList<>();

        try {
       
            Statement orden = conn.createStatement();
            ResultSet query = orden.executeQuery("select * from medico");

            while (query.next()) {
        Medico a = new Medico();
        a.setIdMedico(Integer.parseInt(query.getString("id_medico")));
        a.setNombre(query.getString("nombre"));        
        a.setSala(Short.parseShort(query.getString("sala")));
        a.setTarifa(Integer.parseInt(query.getString("tarifa")));
        a.setEspecialidad(query.getString("especialidad"));
              

                lista.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
}
