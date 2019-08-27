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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.Control;

public class PanelAgregarDistribuidor extends JPanel {

	public JPanel panel1; //titulo norte
	public JPanel panel2; //Centro
	public JPanel panel3; //Sur

	
	JLabel label1; //titulo Principal
	JLabel label2; //Descripcion
	JLabel label4; //Id del distribuidor
	JLabel label5;// Nombre del distribuidor.

	JButton boton1;
	
	public JTextField texto2;//Id de la Estacion
	public JTextField texto3;//Capacidad maxima
	
	public PanelAgregarDistribuidor() {
		this.setLayout(new BorderLayout());
		
		
		label1 = new JLabel("Agregar un distribuidor.", SwingConstants.CENTER);
		Font auxFont = label1.getFont();
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Ingresar los datos del distribuidor asociado al sistema miBici.", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1, 15, 15));
		panel1.add(label1);
		panel1.add(label2);
		this.add(panel1, BorderLayout.NORTH);

		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(2, 1));
		label4 = new JLabel("Nit [Texto]:");
		texto2 = new JTextField(15);
		JPanel f2 = new JPanel();
		f2.setLayout(new FlowLayout(FlowLayout.CENTER));
		f2.add(label4);
		f2.add(texto2);
		panel3.add(f2);
		label5 = new JLabel("Nombre del distribuidor [Texto]:");
		texto3 = new JTextField(15);
		JPanel f4 = new JPanel();
		f4.setLayout(new FlowLayout(FlowLayout.CENTER));
		f4.add(label5);
		f4.add(texto3);
		panel3.add(f4);
		
		
		this.add(panel3, BorderLayout.CENTER);
		panel2 = new JPanel();
		boton1 = new JButton("Agregar Distribuidor.");
		boton1.setActionCommand("agregarDistribuidor");
		panel2.add(boton1);
		
		this.add(panel2, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	
	public void setControlador(Control c) {
		boton1.addActionListener(c);
	}
}