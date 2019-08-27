package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.Control;
import gestorAplicacion.User.Persona;
import gestorAplicacion.User.Usuario;

public class PanelRecargarTarjeta extends JPanel  {


	public JPanel panel1; //titulo norte
	public JPanel panel2; //Centro
	public JPanel panel3; //Sur
	JPanel panel4; //escoger cantidad centro
	
	JLabel label1; //titulo Principal
	JLabel label2; //Descripcion
	JLabel label3; //Saldo actual
	JLabel label4; //Ingrese su saldo

	JButton boton1; //recargar
	
	public JTextField texto1;
	
	public PanelRecargarTarjeta() {
		this.setLayout(new BorderLayout());
		label1 = new JLabel("Recargar Tarjeta (max. $300000).", SwingConstants.CENTER);
		Font auxFont=label1.getFont(); 
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Esta es la pantalla para recargar la tarjeta personal.", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1, 15,15));
		panel1.add(label1);
		panel1.add(label2);
		this.add(panel1, BorderLayout.NORTH);
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,1,50, 15));
		label3 = new JLabel("Saldo actual: $"+((Usuario)Persona.currentUser).getTarjeta().getSaldo(), SwingConstants.CENTER);
		label3.setFont(auxFont);
		panel2.add(label3);
		panel3= new JPanel();
		panel3.setLayout(new GridLayout(1,2, 15,15));
		texto1= new JTextField(10);
		boton1= new JButton("Recargar la Tarjeta");
		panel3.add(texto1);
		panel3.add(boton1);
		this.add(panel3, BorderLayout.SOUTH);
		
		
		
		this.add(panel2, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	public void setControlador(Control c) {
		boton1.addActionListener(c);
	}
}