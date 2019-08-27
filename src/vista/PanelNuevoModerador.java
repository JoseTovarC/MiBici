package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.Control;

public class PanelNuevoModerador extends JPanel {
	JPanel panel1; // Titulo Norte
	JPanel panel2; // Centro
	JPanel panel3; // Sur
	JPanel f1; // contiene nombre
	JPanel f2; // contiene id
	JPanel f3; // contiene clave
	JPanel f4; // contiene edad
	JPanel f5; // contiene genero

	JLabel label1; // Titulo
	JLabel label2; // Descripción
	JLabel label3; // Ingrese nombre
	JLabel label4; // Ingrese id
	JLabel label5; // Ingrese clave
	JLabel label6; // Ingrese edad
	JLabel label7; // Ingrese genero

	public JTextField texto1; // Nombre
	public JTextField texto2; // Id
	public JTextField texto3; // Clave
	public JTextField texto4; // Edad
	public JTextField texto5; // Genero
	
	JButton boton1; //CrearAdmin

	public PanelNuevoModerador() {
		this.setLayout(new BorderLayout());

		label1 = new JLabel("Nuevo Moderador.", SwingConstants.CENTER);
		Font auxFont = label1.getFont();
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Registrar un nuevo Moderador, rellene el formulario como se le pide.",
				SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1, 15, 15));
		panel1.add(label1);
		panel1.add(label2);
		this.add(panel1, BorderLayout.NORTH);
		
		panel2= new JPanel();
		panel2.setLayout(new GridLayout(5, 1));
		
		label3 = new JLabel("Nombre [Texto]:");
		texto1 = new JTextField(15);
		f1 = new JPanel();
		f1.setLayout(new FlowLayout(FlowLayout.CENTER));
		f1.add(label3);
		f1.add(texto1);
		panel2.add(f1);
		
		label4 = new JLabel("Id [Numero]:");
		texto2 = new JTextField(15);
		f2 = new JPanel();
		f2.setLayout(new FlowLayout(FlowLayout.CENTER));
		f2.add(label4);
		f2.add(texto2);
		panel2.add(f2);
		
		label5 = new JLabel("Clave [Texto] :");
		texto3 = new JPasswordField(15);
		texto3.setToolTipText("Ingrese Contraseña");
		f3 = new JPanel();
		f3.setLayout(new FlowLayout(FlowLayout.CENTER));
		f3.add(label5);
		f3.add(texto3);
		panel2.add(f3);
		
		label6 = new JLabel("Edad [Numero]:");
		texto4 = new JTextField(15);
		f4 = new JPanel();
		f4.setLayout(new FlowLayout(FlowLayout.CENTER));
		f4.add(label6);
		f4.add(texto4);
		panel2.add(f4);
		
		label7 = new JLabel("Genero [M/F]:");
		texto5 = new JTextField(15);
		f5 = new JPanel();
		f5.setLayout(new FlowLayout(FlowLayout.CENTER));
		f5.add(label7);
		f5.add(texto5);
		panel2.add(f5);
		this.add(panel2, BorderLayout.CENTER);

		panel3= new JPanel();
		boton1=new JButton("Crear Nuevo Moderador.");
		boton1.setActionCommand("crearModerador");
		panel3.add(boton1);
		this.add(panel3, BorderLayout.SOUTH);

		setVisible(true);
		panel2 = new JPanel();
	}

	public void setControlador(Control c) {
		boton1.addActionListener(c);
	}
}
