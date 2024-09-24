/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cajeros;
import Vista.jfrCajero;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

/**
 *
 * @author Estudiante
 */
public class ctrlCajeros implements MouseListener, KeyListener {

    private Cajeros modelo;
    private jfrCajero vista;
   
    //2- Crear el constructor
    public ctrlCajeros(Cajeros modelo, jfrCajero vista){
        this.modelo = modelo;
        this.vista = vista;

        vista.btnAgregar.addMouseListener(this);   
        modelo.Mostrar(vista.jtbCajeros);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbCajeros.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar1.addMouseListener(this);
    
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource() == vista.btnAgregar){
            modelo.setNombreCajero(vista.txtNombre.getText());
            modelo.setEdadCajero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPesoCajero(new BigDecimal(vista.txtPeso1.getText())); //
            modelo.setCorreoCajero(vista.txtCorreo.getText());
            
            
            modelo.Guardar();
            modelo.Mostrar(vista.jtbCajeros);
        }
        
        if(e.getSource() == vista.btnEliminar){
            modelo.Eliminar(vista.jtbCajeros);
            modelo.Mostrar(vista.jtbCajeros);
        }
        
        if(e.getSource() == vista.jtbCajeros){
            modelo.cargarDatosTabla(vista);
        }
        
        if(e.getSource() == vista.btnActualizar){
            modelo.setNombreCajero(vista.txtNombre.getText());
            modelo.setEdadCajero(Integer.parseInt(vista.txtEdad.getText()));
            modelo.setPesoCajero(new BigDecimal(vista.txtPeso1.getText()));
            modelo.setCorreoCajero(vista.txtCorreo.getText());
            
            modelo.Actualizar(vista.jtbCajeros);
            modelo.Mostrar(vista.jtbCajeros);
        }
        
        if(e.getSource() == vista.btnLimpiar1){
            modelo.limpiar(vista);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == vista.txtBuscar){
            modelo.Buscar(vista.jtbCajeros, vista.txtBuscar);
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
