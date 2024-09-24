package Modelo;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorFondo extends JPanel {

    private Color color1 = Color.WHITE; 
    private Color color2 = Color.BLACK; 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        Graphics2D g2d = (Graphics2D) g;

        int w = getWidth(); // obtiene la anchura del panel
        int h = getHeight(); // obtiene la altura del panell

        // crea una pintura de gradient a partir del color 1 y el 2
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp); //hacemos un setPaint e imprimimos nuestra variable gp
        g2d.fillRect(0, 0, w, h); // llena le rectangulo con el gradient
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gradient Background Example"); 
        ColorFondo mColor = new ColorFondo(); 
        frame.add(mColor); // agregar el panel a el frame
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
    }
}