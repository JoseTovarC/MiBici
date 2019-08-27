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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.Control;

public class PanelDevolverBicicleta extends JPanel {
	public JPanel panel1; //titulo norte
	public JPanel panel2; //Centro
	public JPanel panel3; //Sur
	public JPanel panel4; //titulo bicicletas centr
	
	JLabel label1; //titulo Principal
	JLabel label2; //Descripcion
	JLabel label3; //Titulo Bicicletas
	JLabel label4; //Descripcion Bicicletas

	JButton boton1;
	JButton boton2;
	
	TextArea texto1;
	
	public JTextField texto2;
	public JTextField texto3;//para obtener ie
	
	public PanelDevolverBicicleta(String r) {
		this.setLayout(new BorderLayout());
		label1 = new JLabel("Devolver Bicicleta.", SwingConstants.CENTER);
		Font auxFont=label1.getFont(); 
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Escoger una estacion.", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1, 15,15));
		panel1.add(label1);
		panel1.add(label2);
		this.add(panel1, BorderLayout.NORTH);
		
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		
		
		texto1 = new TextArea(r, 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		texto1.setEditable(false);
		texto1.setBackground(Color.white);
		panel2.add(texto1);
		this.add(panel2, BorderLayout.CENTER);
		
		panel3= new JPanel();
		panel3.setLayout(new GridLayout(1,2, 15,15));
		texto2= new JTextField(10);
		boton1 = new JButton("Escoger Estacion.");
		panel3.add(texto2);
		panel3.add(boton1);
		this.add(panel3, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void setControlador(Control c) {
		boton1.addActionListener(c);
	}
	
}
