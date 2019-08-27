package vista;

import javax.swing.*;
import javax.swing.event.MenuListener;

import control.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import images.Imagen;

public class VentanaUsuarioInvitado extends JFrame implements InterfazVista {
	Container panelventana;
	Control control;
	// Definicion de variables/componentes
	JPanel panelPrincipal;// panel principal = T O D O
	JPanel panel1; // Panel Norte, contiene el label1
	JPanel panel2; // Panel Sur, alias botonera.
	JPanel panel3; // Panel centro, ingresar datos.

	public JLabel label1; // Bienvenida del sistema a la opcion del sistema
	public JLabel label2; // Mensaje de ayuda
	public JLabel label3; // Boton para el registro
	public JLabel label4; // Ingrese su código de usuario y su clave.
	public JLabel label5; // Codigo de usuario
	public JLabel label6; // Clave

	private JButton boton1;// boton 1 = “Login Usuario”
	public JButton boton2; // boton 2 = “Inicio”

	TextArea texto1; // texto 1 = guia del sistema
	public JTextField texto2; // texto 2 = codigo de usuario
	public JPasswordField texto3; // texto 3 = clave del usuario

	//Variables solo para registro
	public JTextField texto4; //Id
	public JTextField texto5; //Edad
	public JTextField texto6; //Genero
	public JLabel label7; //Edad
	public JLabel label8; //Genero
	
	Image imagen;
	JMenuBar arri;
	JMenuItem UsuarioComun;
	JMenu Consultas;
	JMenuItem Bicixestaciones;
	Imagen imagenpresentacion;

	public VentanaUsuarioInvitado() {

		super("Usuario: Invitado");

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// MENU
		arri = new JMenuBar();
		UsuarioComun = new JMenuItem("Usuario Comun");
		Consultas = new JMenu("Consultas");
		Bicixestaciones = new JMenuItem("Bicicletas por estacion");
		Consultas.add(Bicixestaciones);
		arri.add(Consultas);
		setJMenuBar(arri);

		// CREACIONDE PANELES QUE USARE DESPUES
		panel3 = new JPanel();

		// DISEÑO PANEL
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());

		imagenpresentacion = new Imagen();

		String ruta = System.getProperty("user.dir") + "\\src\\images\\";
		imagenpresentacion.setImage(ruta + "presentacion.png");
		panelPrincipal.add(imagenpresentacion, BorderLayout.CENTER);
		label1 = new JLabel("Bienvenido,", SwingConstants.CENTER);
		Font auxFont = label1.getFont();
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel(
				"Este es la ventana de usuario invitado, si desea puede loguearse o registrarse en la Opcion login",
				SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1, 15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 1, 50, 0));
		boton2 = new JButton("Volver a inicio.");
		boton1 = new JButton();
		label3 = new JLabel("Registrarme en el sistema", SwingConstants.CENTER);
		panel2.add(boton2);
		panel2.add(label3);

		panelPrincipal.add(panel2, BorderLayout.SOUTH);
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
		boton1.addActionListener(c);
		boton2.addActionListener(c);
		label3.addMouseListener(c);
		Bicixestaciones.addActionListener(c);
		UsuarioComun.addActionListener(c);

	}

	public void arranca() {

		pack();// coloca los componentes
		setSize(1000, 500);// Da el tamaño inicial de la ventana
		Dimension d = new Dimension(910, 400);
		setMinimumSize(d);
		setLocationRelativeTo(null); // centra la ventana en la pantalla
		String ruta = System.getProperty("user.dir") + "\\src\\images\\";
		ImageIcon ImageIcono = new ImageIcon(ruta + "mibiciicon.png");
		imagen = ImageIcono.getImage();
		setIconImage(imagen);
		setVisible(true);// visualiza la ventana
	}

	public void cambiarazul() {
		label3.setForeground(Color.BLUE);
		Font font = label3.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		label3.setFont(font.deriveFont(attributes));
		setVisible(true);

	}

	public void cambiarnegro() {
		label3.setForeground(Color.BLACK);
		Font font = label3.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, -1);
		label3.setFont(font.deriveFont(attributes));
		setVisible(true);

	}



	public void Usuariocomun() {
		arri.removeAll();
		arri.add(Consultas);
		setJMenuBar(arri);

		panelPrincipal.removeAll();
		panel3.removeAll();
		panel2.removeAll();
		panel1.removeAll();

		label1 = new JLabel("Bienvenido,", SwingConstants.CENTER);
		Font auxFont = label1.getFont();
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel(
				"Este es la ventana de usuario invitado, si desea puede loguearse o registrarse en la Opcion login",
				SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1, 15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);

		panelPrincipal.add(imagenpresentacion, BorderLayout.CENTER);

		boton1.setText("Iniciar Sesión");
		boton1.setActionCommand("UsuarioDatos");
		panel2.setLayout(new GridLayout(2, 1, 50, 0));
		panel2.add(boton2);
		panel2.add(label3);
		panelPrincipal.add(panel2, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}


	public void cerrarventana() {
		setVisible(false);

	}

	public void mostrartexto(String muestra) {
		arri.removeAll();
		arri.add(Consultas);
		arri.add(UsuarioComun);
		setJMenuBar(arri);

		panelPrincipal.removeAll();
		panel3.removeAll();
		panel1.removeAll();
		panel2.removeAll();

		label1 = new JLabel("Bicicletas por estacion [ cantidad / capacidad maxima ]:", SwingConstants.CENTER);
		Font auxFont = label1.getFont();
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("La cantidad de Bicicletas por Estación.", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1, 15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);

		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		texto1 = new TextArea(muestra, 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		texto1.setEditable(false);
		texto1.setBackground(Color.white);
		panel3.add(texto1);
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		
		panelPrincipal.add(panel2, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	public void registro() {
		
		arri.removeAll();
		arri.add(Consultas);
		arri.add(UsuarioComun);
		setJMenuBar(arri);
		
		panelPrincipal.removeAll();
		panel1.removeAll();
		panel2.removeAll();
		panel3.removeAll();
	
		label1 = new JLabel("Registro nuevo usuario.", SwingConstants.CENTER);
		Font auxFont = label1.getFont();
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Ingrese sus datos:", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1, 15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);

		panel3.setLayout(new GridLayout(5, 1));
		label4 = new JLabel("Nombre [Texto]:");
		texto2 = new JTextField(15);
		JPanel f2 = new JPanel();
		f2.setLayout(new FlowLayout(FlowLayout.CENTER));
		f2.add(label4);
		f2.add(texto2);
		panel3.add(f2);
		label5 = new JLabel("Id [Numero]:");
		texto4 = new JTextField(15);
		JPanel f4 = new JPanel();
		f4.setLayout(new FlowLayout(FlowLayout.CENTER));
		f4.add(label5);
		f4.add(texto4);
		panel3.add(f4);
		label6 = new JLabel("Clave [Texto] :");
		texto3 = new JPasswordField(15);
		texto3.setToolTipText("Ingrese Contraseña");
		JPanel f3 = new JPanel();
		f3.setLayout(new FlowLayout(FlowLayout.CENTER));
		f3.add(label6);
		f3.add(texto3);
		panel3.add(f3);
		label7 = new JLabel("Edad [Numero]:");
		texto5 = new JTextField(15);
		JPanel f5 = new JPanel();
		f5.setLayout(new FlowLayout(FlowLayout.CENTER));
		f5.add(label7);
		f5.add(texto5);
		panel3.add(f5);
		label8 = new JLabel("Genero [M/F]:");
		texto6 = new JTextField(15);
		JPanel f6 = new JPanel();
		f6.setLayout(new FlowLayout(FlowLayout.CENTER));
		f6.add(label8);
		f6.add(texto6);
		panel3.add(f6);
		
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		
		boton1.setText("Crear.");
		boton1.setActionCommand("crearUsuario");
		panel2.add(boton1);
		
		panelPrincipal.add(panel2, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		
	}
}