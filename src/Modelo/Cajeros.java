/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.jfrCajero;
import java.math.BigDecimal;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Estudiante
 */
public class Cajeros {
    
  private String uuidCajero;
    private String nombreCajero;
    private int edadCajero;
    private BigDecimal pesoCajero; 
    private String correoCajero;

   
    public String getUuidCajero() {
        return uuidCajero;
    }

    public void setUuidCajero(String uuidCajero) {
        this.uuidCajero = uuidCajero;
    }

   
    public String getNombreCajero() {
        return nombreCajero;
    }

    public void setNombreCajero(String nombreCajero) {
        this.nombreCajero = nombreCajero;
    }

 
    public int getEdadCajero() {
        return edadCajero;
    }

    public void setEdadCajero(int edadCajero) {
        this.edadCajero = edadCajero;
    }

   
    public BigDecimal getPesoCajero() {
        return pesoCajero;
    }

    public void setPesoCajero(BigDecimal pesoCajero) {
        this.pesoCajero = pesoCajero;
    }


    public String getCorreoCajero() {
        return correoCajero;
    }

    public void setCorreoCajero(String correoCajero) {
        this.correoCajero = correoCajero;
    }
    
    
     public void Guardar() {
         
         
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addCajero = conexion.prepareStatement("INSERT INTO tbCajero(UUID_Cajero, Nombre_Cajero, Edad_Cajero, Peso_Cajero, Correo_Cajero) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addCajero.setString(1, UUID.randomUUID().toString());
            addCajero.setString(2, getNombreCajero());
            addCajero.setInt(3, getEdadCajero());
            addCajero.setBigDecimal(4, getPesoCajero());
            addCajero.setString(5, getCorreoCajero());
 
            
            //Ejecutar la consulta
            addCajero.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo: metodo guardar " + ex);
        }
    }
     
      public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Cajero", "Nombre_Cajero", "Edad_Cajero", "Peso_Cajero, Correo_Cajero"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbCajero");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Cajero"), 
                    rs.getString("Nombre_Cajero"), 
                    rs.getInt("Edad_Cajero"), 
                    rs.getBigDecimal("Peso_Cajero"), 
                    rs.getString("Correo_Cajero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
      
       public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbCajero where UUID_Cajero = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("Este es el error metodo de Eliminar" + e);
        }
    }
       
        public void cargarDatosTabla(jfrCajero vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbCajeros.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbCajeros.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbCajeros.getValueAt(filaSeleccionada, 1).toString();
            String EdadDeTb = vista.jtbCajeros.getValueAt(filaSeleccionada, 2).toString();
            String PesodeTB = vista.jtbCajeros.getValueAt(filaSeleccionada, 3).toString();
            String CorreodeTB = vista.jtbCajeros.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadDeTb);
            vista.txtPeso1.setText(PesodeTB);
            vista.txtCorreo.setText(CorreodeTB);
        }
    }
    
        
        public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("UPDATE tbCajero SET Nombre_Cajero= ?, Edad_Cajero = ?, Peso_Cajero = ?, Correo_Cajero WHERE UUID_Cajero = ?");

                updateUser.setString(1, getNombreCajero());
                updateUser.setInt(2, getEdadCajero());
                updateUser.setBigDecimal(3, getPesoCajero());
                updateUser.setString(4, getCorreoCajero());
                updateUser.setString(5, miUUId);
                updateUser.executeUpdate();
            } catch (Exception e) {
                System.out.println("Este es el error en el metodo de Actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
        
         public void limpiar(jfrCajero vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso1.setText("");
        vista.txtCorreo.setText("");
    }
         
         public void Buscar(JTable tabla, JTextField txtBuscar) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Cajero", "Nombre_Cajero", "Edad_Cajero", "Peso_Cajero", "Correo_Cajero"});
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("SELECT * FROM tbCajero WHERE Nombre LIKE ? || '%'");
            deleteEstudiante.setString(1, txtBuscar.getText());
            ResultSet rs = deleteEstudiante.executeQuery();
 
             while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Cajero"), 
                    rs.getString("Nombre_Cajero"), 
                    rs.getInt("Edad_Cajero"), 
                    rs.getInt("Peso_Cajero"), 
                    rs.getString("Correo_Cajero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
 
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
    
}
