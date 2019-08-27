package vista;

import javax.swing.*;
import control.*;
import java.awt.*;


public class VentanaLogin extends JFrame implements InterfazVista {
	Container panelventana;
	Control control;
	// Definicion de variables/componentes
	JPanel panelPrincipal;// panel principal = T O D O
	JPanel panel1; // lado izquierdo, contiene al panel3 y panel4
	JPanel panel2; // lado derecho, contiene al panel5 y panel6
	JPanel panel3; // arriba a la izquierda, contiene el texto label1
	JPanel panel4; // centro/abajo a la izquierda, contiene el boton1
	JPanel panel5; // arriba a la derecha, contiene el texto1
	JPanel panel6; // centro/abajo a la derecha, contiene el boton2, boton3, label2, label3,
					// label4, texto2, texto3 y boton4

	public JLabel label1; // bienvenida del sistema, cambia a azul
	JLabel label2; // “Ingrese su código de usuario y su clave”
	JLabel label3; // “Código de usuario”
	JLabel label4; // “Clave”

	private JButton boton1;// boton 1 = “Haga clic para ver fotos de los autores del sistema”
	public JButton boton2; // boton 2 = “Administrador”
	JButton boton3; // boton 3 = “UsuarioComún”
	JButton boton4; // boton 4 = salir
	JButton boton5; // boton invitado

	TextArea texto1; // texto 1 = guia del sistema
	public JTextField texto2; // texto 2 = codigo de usuario
	public JPasswordField texto3; // texto 3 = clave del usuario

	Image imagen;

	public VentanaLogin() {

		super("Log in");

		/*
		 * if(getClass().getResource("images/mibiciicon.png") != null) {
		 * 
		 * }
		 */
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new GridLayout(1, 2));

		// IZQUIERDA
		panel1 = new JPanel();
		// IZQUIERDA ARRIBA
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		label1 = new JLabel("Bienvenido a MiBici");
		panel3.add(label1);
		panel1.setLayout(new BorderLayout());
		panel1.add(panel3, BorderLayout.NORTH);
		//Oyente oyente = new Oyente();
		//label1.addMouseListener(oyente);
		// IZQUIERDA CENTRO/ABAJO
		panel4 = new JPanel();
		boton1 = new JButton("Haga Click aqui para ver fotos de los autores...");
		boton1.setBackground(Color.WHITE);
		boton1.setActionCommand("Cambiar foto");	
		panel4.add(boton1);
		panel1.add(boton1, BorderLayout.CENTER); // REVISAR PARA QUE PUEDA QUEDAR EN UN PANEL
		// TERMINADO DE LA IZQUIERDA
		
		panelPrincipal.add(panel1);

		// DERECHA
		panel2 = new JPanel();
		// DERECHA ARRIBA
		panel5 = new JPanel();
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		texto1 = new TextArea("Esta va a ser la guia. ",0 , 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		texto1.setEditable(false);
		texto1.setBackground(Color.white);
		panel5.add(texto1);
		panel2.setLayout(new BorderLayout());
		panel2.add(panel5, BorderLayout.NORTH);
		// DERECHA CENTRO/ABAJO
		panel6 = new JPanel();
		panel6.setLayout(new GridLayout(5, 1));
		boton2 = new JButton("Administrador");
		boton2.setActionCommand("AdminDatos");
		boton3 = new JButton("Usuario Comun");
		boton5= new JButton("Invitado");
		JPanel f1 = new JPanel();
		f1.setLayout(new FlowLayout());
		f1.add(boton2);
		f1.add(boton3);
		f1.add(boton5);
		panel6.add(f1);
		
		boton4 = new JButton("Salir.");
		panel6.add(boton4);

		panel2.add(panel6, BorderLayout.CENTER);

		panelPrincipal.add(panel2);

		// convertirApesos.setActionCommand(APESOS);
		// convertirAeuros.setActionCommand(AEUROS);
		getContentPane().add(panelPrincipal);
	}

//Métodos de la interfaz InterfazVista:
	public void escribeCambio(String s) {
		// resultado.setText(s);
	}

	public double getCantidad() {
		try {
			return 0.0;// Double.parseDouble(cantidad.getText())
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}

	public void setControlador(Control c) {
		label1.addMouseListener(c);
		boton1.addActionListener(c);
		boton2.addActionListener(c);
		boton3.addActionListener(c);
		boton4.addActionListener(c);
		boton5.addActionListener(c);
	}

	public void arranca() {

		pack();// coloca los componentes
		setSize(1000, 500);// Da el tamaño inicial de la ventana
		Dimension d= new Dimension(910,400);
		setMinimumSize(d);
		setLocationRelativeTo(null); // centra la ventana en la pantalla
		String ruta = System.getProperty("user.dir") + "\\src\\images\\";
		ImageIcon ImageIcono = new ImageIcon(ruta + "mibiciicon.png");
		imagen = ImageIcono.getImage();
		setIconImage(imagen);
		setVisible(true);// visualiza la ventana
	}

	/*class Oyente implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			label1.setForeground(Color.BLUE);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			label1.setForeground(Color.BLACK);

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}*/
	
	
	public void cambiarazul() {	
		label1.setForeground(Color.BLUE);
		setVisible(true);
			
	}
	
	public void cambiarnegro() {	
		label1.setForeground(Color.BLACK);
		setVisible(true);

			
	}
	public void ingresardatosUser() {	
		panel6.removeAll();
		panel6.setLayout(new GridLayout(5, 1));
		boton2.setText("Usuario Complete y Nuevamente Clic");
		boton2.setActionCommand("IngresarUsuario");
		//boton3 = new JButton("Usuario Comun");
		JPanel f1 = new JPanel();
		f1.setLayout(new FlowLayout());
		f1.add(boton2);
		//f1.add(boton3);
		panel6.add(f1);
		label2 = new JLabel("Ingrese su código de usuario y su clave.");
		label2.setHorizontalAlignment(JLabel.CENTER);
		panel6.add(label2);
		label3 = new JLabel("        Codigo de usuario: ");
		texto2 = new JTextField(15);
		JPanel f2 = new JPanel();
		f2.setLayout(new FlowLayout(FlowLayout.LEFT));
		f2.add(label3);
		f2.add(texto2);
		panel6.add(f2);
		label4 = new JLabel("        Clave:                        ");
		texto3 = new JPasswordField(15);
	    texto3.setToolTipText("Ingrese Contraseña");
		JPanel f3 = new JPanel();
		f3.setLayout(new FlowLayout(FlowLayout.LEFT));
		f3.add(label4);
		f3.add(texto3);
		panel6.add(f3);
		panel6.add(boton4);
		
		panel2.add(panel6, BorderLayout.CENTER);
		panelPrincipal.add(panel2);
		
		setVisible(true);
	}
	public void ingresardatosAdmin() {	
		panel6.removeAll();
		panel6.setLayout(new GridLayout(5, 1));
		boton2.setText("Administrador Complete y Nuevamente Clic");
		boton2.setActionCommand("Ingresar");
		//boton3 = new JButton("Usuario Comun");
		JPanel f1 = new JPanel();
		f1.setLayout(new FlowLayout());
		f1.add(boton2);
		//f1.add(boton3);
		panel6.add(f1);
		label2 = new JLabel("Ingrese su código de usuario y su clave.");
		label2.setHorizontalAlignment(JLabel.CENTER);
		panel6.add(label2);
		label3 = new JLabel("        Codigo de usuario: ");
		texto2 = new JTextField(15);
		JPanel f2 = new JPanel();
		f2.setLayout(new FlowLayout(FlowLayout.LEFT));
		f2.add(label3);
		f2.add(texto2);
		panel6.add(f2);
		label4 = new JLabel("        Clave:                        ");
		texto3 = new JPasswordField(15);
	    texto3.setToolTipText("Ingrese Contraseña");
		JPanel f3 = new JPanel();
		f3.setLayout(new FlowLayout(FlowLayout.LEFT));
		f3.add(label4);
		f3.add(texto3);
		panel6.add(f3);
		panel6.add(boton4);
		
		panel2.add(panel6, BorderLayout.CENTER);
		panelPrincipal.add(panel2);
		
		setVisible(true);
	}
	
	public void checkLogIn() {
		
		panel6.removeAll();
		boton2.setText("Administrador");
		boton2.setActionCommand("AdminDatos");
		JPanel f1 = new JPanel();
		f1.setLayout(new FlowLayout());
		f1.add(boton2);
		f1.add(boton3);
		f1.add(boton5);
		panel6.add(f1);
		boton4.setText("Salir.");
		panel6.add(boton4);
		setVisible(true);
	}
public void cambiarFondoBoton(String ruta) {
	ImageIcon ImageIcono  = new ImageIcon(ruta);
	boton1.setIcon(ImageIcono);
	if(ruta==null) {
		boton1.setText("Haga Click aqui para ver fotos de los autores...");
	}else {
		boton1.setText(null);
	}
			
		setVisible(true);
	}

public void cerrarventana() {
	setVisible(false);
	
}
	
}