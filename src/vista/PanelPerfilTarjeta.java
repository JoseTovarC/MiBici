package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.Control;
import gestorAplicacion.User.*;

public class PanelPerfilTarjeta extends JPanel {

	public JPanel panel1; //titulo norte
	public JPanel panel2; //Centro
	public JPanel panel3; //Sur
	public JPanel panel4; //menu central
	
	JLabel label1; //titulo Principal
	JLabel label2; //Descripcion
	JLabel label3; //Saldo
	JLabel label4; //Multas
	
	JButton boton1; //Recargar
	JButton boton2; //Pagar multas
	
	public PanelPerfilTarjeta(int sal, String mul) {
		this.setLayout(new BorderLayout());
		label1 = new JLabel("Perfil de la Tarjeta.", SwingConstants.CENTER);
		Font auxFont=label1.getFont(); 
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Esta es la información sobre la tarjeta personal.", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1, 15,15));
		panel1.add(label1);
		panel1.add(label2);
		this.add(panel1, BorderLayout.NORTH);
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,1,50, 15));
		label3 = new JLabel("Saldo: $"+ sal, SwingConstants.CENTER);
		label3.setFont(auxFont);
		boton1= new JButton("Recargar Tarjeta");
		label4 = new JLabel("Multas: "+ mul, SwingConstants.CENTER);
		boton2= new JButton("Pagar Multas");
		panel4 = new JPanel();
		panel4.setLayout(new GridLayout(2, 2, 15, 15));
		panel4.add(label3);
		panel4.add(boton1);
		panel4.add(label4);
		panel4.add(boton2);
		panel2.add(panel4);
		
		this.add(panel2);
		
		this.setVisible(true);
	}
	public void setControlador(Control c) {
		boton1.addActionListener(c);
		boton2.addActionListener(c);
	}
}