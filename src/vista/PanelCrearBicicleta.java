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
import uiMain.cant_bicicletas_esta;

public class PanelCrearBicicleta extends JPanel {
	JLabel label1; // título
	JLabel label2; // descripcion all
	JLabel label3; // Título estaciones
	JLabel label4; // título distribuidores
	JLabel l1; //escriba estacion
	JLabel l2; //escriba distribuidor

	JPanel panel1; // norte
	JPanel panel2; // oeste
	JPanel panel3; // este
	JPanel panel4; // sur
	JPanel f1; //este, escriba una estacion y texto
	JPanel f2; //oeste, escriba un distribuidor y texto

	TextArea texto1; // estaciones
	TextArea texto2; // distribuidores
	public JTextField texto3; // escoger estacion
	public JTextField texto4; // escoger distribuidor
	
	JButton boton1; //Pedir

	public PanelCrearBicicleta(String e, String d) {
		this.setLayout(new BorderLayout());
		label1 = new JLabel("Solicitar creacion de Bicicleta.", SwingConstants.CENTER);
		Font auxFont = label1.getFont();
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Elija una Estacion y un Distribuidor.", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1, 15, 15));
		panel1.add(label1);
		panel1.add(label2);
		this.add(panel1, BorderLayout.NORTH);

		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(3, 1, 15, 15));
		label3 = new JLabel("Estaciones [Cant. Bicicletas / Cap. Máx.]. ", SwingConstants.CENTER);
		texto1 = new TextArea(e, 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		texto1.setEditable(false);
		texto1.setBackground(Color.white);
		f1= new JPanel();
		f1.setLayout(new FlowLayout(FlowLayout.CENTER,15,15));
		l1= new JLabel("Escriba una estacion [Índice]:");
		texto3 = new JTextField(10);
		f1.add(l1);
		f1.add(texto3);
		panel2.add(label3);
		panel2.add(texto1);
		panel2.add(f1);
		this.add(panel2, BorderLayout.WEST);

		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(3, 1, 15, 15));
		label4 = new JLabel("Distribuidores.", SwingConstants.CENTER);
		texto2 = new TextArea(d, 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		texto2.setEditable(false);
		texto2.setBackground(Color.white);
		f2= new JPanel();
		f2.setLayout(new FlowLayout(FlowLayout.CENTER,15,15));
		l2= new JLabel("Escriba un distribuidor [Índice]:");
		texto4 = new JTextField(10);
		f2.add(l2);
		f2.add(texto4);
		panel3.add(label4);
		panel3.add(texto2);
		panel3.add(f2);
		this.add(panel3, BorderLayout.EAST);

		panel4 = new JPanel();
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		boton1 = new JButton("Pedir.");
		panel4.add(boton1);
		this.add(panel4, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public void setControlador(Control c) {
		boton1.addActionListener(c);
	}

}
