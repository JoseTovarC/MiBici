package control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import BaseDatos.Datos;
import gestorAplicacion.User.*;
import uiMain.*;
import vista.*;

public class ControlVentanaAdmin implements Control {

	private InterfazVista vista;
	String ruta = System.getProperty("user.dir") + "\\src\\images\\";
	ImageIcon ImageIconoerror = new ImageIcon(ruta + "iconfinder_Error_381599.png");
	ImageIcon ImageIconoerrorsign = new ImageIcon(ruta + "iconfinder_sign-error_299045.png");
	ImageIcon ImageIconowarning = new ImageIcon(ruta + "iconfinder_101_Warning_183416.png");
	ImageIcon ImageIconocheck = new ImageIcon(ruta + "iconfinder_sign-check_299110.png");

	// private ConversorEurosPesos modelo;
	public ControlVentanaAdmin(InterfazVista vista) {
		this.vista = vista;

	}

	public void actionPerformed(ActionEvent evento) {

		if (evento.getActionCommand().equals("UsuarioDatos")) {
			// ((VentanaAdmin) vista).ingresardatos();

		} else if (evento.getActionCommand().equals("Añadir/Quitar opciones de menu de un usuario")) {
			((VentanaAdmin) vista).panelPrincipal.removeAll();
			JPanel j = new PanelModificar(Modificar.getUsuarios());
			((VentanaAdmin) vista).setPanelPrincipal(j);
			((PanelModificar) j).setControlador(new ControlVentanaAdmin(vista));

		} else if (evento.getActionCommand().equals("Escogerusuario")) {
			try {
				String u = ((PanelModificar) ((VentanaAdmin) vista).panelPrincipal).texto2.getText();
				long id = (long) Integer.parseInt(u);
				if (Persona.getUsuarioPorUsername(id) == null) {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado.", "Modificar.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);

				} else if ((Persona.getUsuarioPorUsername(id) instanceof Admin)) {
					JOptionPane.showMessageDialog(null,
							"El documento pertenece a otro tipo de usuario, el cual no se le puede modificar las opciones",
							"Modificar.", JOptionPane.ERROR_MESSAGE, ImageIconowarning);

				} else {
					MenuDeConsola MenuDefault;

					if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

						ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
							{
								add(BaseDatos.Datos.operations.get("13"));
								add(BaseDatos.Datos.operations.get("9"));
								add(BaseDatos.Datos.operations.get("10"));
								add(BaseDatos.Datos.operations.get("11"));
								add(BaseDatos.Datos.operations.get("14"));
								add(BaseDatos.Datos.operations.get("18"));
								add(BaseDatos.Datos.operations.get("32"));
							}
						};

						MenuDefault = new MenuDeConsola(DefaultOptions);
					} else {
						ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
							{
								add(BaseDatos.Datos.operations.get("5"));
								add(BaseDatos.Datos.operations.get("9"));
								add(BaseDatos.Datos.operations.get("10"));
								add(BaseDatos.Datos.operations.get("11"));
								add(BaseDatos.Datos.operations.get("12"));
							}

						};

						MenuDefault = new MenuDeConsola(DefaultOptions);
					}

					((VentanaAdmin) vista).panelPrincipal.removeAll();
					JPanel j = new PanelOpciones(u);
					// Parte que podemos pasar a controlador

					JPanel panel2 = new JPanel();
					panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
					JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
					Font auxFont = opcion.getFont();
					JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
					opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
					botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
					panel2.add(opcion);
					panel2.add(botonanaqui);
					for (byte i = 0; i < MenuDefault.options.size(); i++) {
						String r = MenuDefault.options.get((int) i).toString();
						panel2.add(new JLabel(r, SwingConstants.CENTER));
						if (Persona.getUsuarioPorUsername(id).getMenu()
								.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
							JButton anaqui = new JButton("Quitar.");
							anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
							anaqui.addActionListener(new ControlVentanaAdmin(vista));
							panel2.add(anaqui);
						} else {
							JButton anaqui = new JButton("Añadir.");
							anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
							anaqui.addActionListener(new ControlVentanaAdmin(vista));
							panel2.add(anaqui);
						}

					}
					// (panel2, BorderLayout.CENTER)
					j.add(panel2, BorderLayout.CENTER);
					((VentanaAdmin) vista).setPanelPrincipal(j);

				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Se ha ingresado un dato incorrecto", "Tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se ha elegido el usuario", "Modificar",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			}
		}

		// Añadir
		else if (evento.getActionCommand().equals("Anadir;5")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().añadirOpcion("5");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le añadio la opcion - "
								+ Datos.operations.get("5").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Anadir;9")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().añadirOpcion("9");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le añadio la opcion - "
								+ Datos.operations.get("9").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Anadir;10")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().añadirOpcion("10");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le añadio la opcion - "
								+ Datos.operations.get("10").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Anadir;11")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().añadirOpcion("11");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le añadio la opcion - "
								+ Datos.operations.get("11").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Anadir;12")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().añadirOpcion("12");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le añadio la opcion - "
								+ Datos.operations.get("12").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Anadir;13")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().añadirOpcion("13");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le añadio la opcion - "
								+ Datos.operations.get("13").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Anadir;14")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().añadirOpcion("14");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le añadio la opcion - "
								+ Datos.operations.get("14").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Anadir;18")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().añadirOpcion("18");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le añadio la opcion - "
								+ Datos.operations.get("18").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Anadir;32")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().añadirOpcion("32");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le añadio la opcion - "
								+ Datos.operations.get("32").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		}

		// Quitar

		else if (evento.getActionCommand().equals("Quitar;5")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().quitarOpcion("5");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le quito la opcion - "
								+ Datos.operations.get("5").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Quitar;9")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().quitarOpcion("9");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le quito la opcion - "
								+ Datos.operations.get("9").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		}

		else if (evento.getActionCommand().equals("Quitar;10")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().quitarOpcion("10");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le quito la opcion - "
								+ Datos.operations.get("10").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Quitar;11")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().quitarOpcion("11");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le quito la opcion - "
								+ Datos.operations.get("11").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Quitar;12")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().quitarOpcion("12");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le quito la opcion - "
								+ Datos.operations.get("12").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Quitar;13")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().quitarOpcion("13");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le quito la opcion - "
								+ Datos.operations.get("13").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Quitar;14")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().quitarOpcion("14");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le quito la opcion - "
								+ Datos.operations.get("14").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				((VentanaAdmin) vista).inicio();

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Quitar;18")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().quitarOpcion("18");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le quito la opcion - "
								+ Datos.operations.get("18").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();
				MenuDeConsola MenuDefault;

				if (Persona.getUsuarioPorUsername(id) instanceof Moderador) {

					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("13"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("14"));
							add(BaseDatos.Datos.operations.get("18"));
							add(BaseDatos.Datos.operations.get("32"));
						}
					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				} else {
					ArrayList<OpcionDeMenu> DefaultOptions = new ArrayList<OpcionDeMenu>() {
						{
							add(BaseDatos.Datos.operations.get("5"));
							add(BaseDatos.Datos.operations.get("9"));
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("11"));
							add(BaseDatos.Datos.operations.get("12"));
						}

					};

					MenuDefault = new MenuDeConsola(DefaultOptions);
				}

				((VentanaAdmin) vista).panelPrincipal.removeAll();
				JPanel j = new PanelOpciones(u);
				// Parte que podemos pasar a controlador

				JPanel panel2 = new JPanel();
				panel2.setLayout(new GridLayout((MenuDefault.options.size() + 1), 2, 15, 15));
				JLabel opcion = new JLabel("Nombre de la opción:", SwingConstants.CENTER);
				Font auxFont = opcion.getFont();
				JLabel botonanaqui = new JLabel("Boton(Añadir/Quitar):", SwingConstants.CENTER);
				opcion.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				botonanaqui.setFont(new Font(auxFont.getFontName(), Font.BOLD, 16));
				panel2.add(opcion);
				panel2.add(botonanaqui);
				for (byte i = 0; i < MenuDefault.options.size(); i++) {
					String r = MenuDefault.options.get((int) i).toString();
					panel2.add(new JLabel(r, SwingConstants.CENTER));
					if (Persona.getUsuarioPorUsername(id).getMenu()
							.contieneOpcion(MenuDefault.options.get((int) i).getKey())) {
						JButton anaqui = new JButton("Quitar.");
						anaqui.setActionCommand("Quitar;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					} else {
						JButton anaqui = new JButton("Añadir.");
						anaqui.setActionCommand("Anadir;" + MenuDefault.options.get((int) i).getKey());
						anaqui.addActionListener(new ControlVentanaAdmin(vista));
						panel2.add(anaqui);
					}

				}
				// (panel2, BorderLayout.CENTER)
				j.add(panel2, BorderLayout.CENTER);
				((VentanaAdmin) vista).setPanelPrincipal(j);

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Quitar;32")) {
			try {
				String u = ((PanelOpciones) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				long id = (long) Integer.parseInt(u);
				Persona.getUsuarioPorUsername(id).getMenu().quitarOpcion("32");
				JOptionPane.showMessageDialog(null,
						"A " + Persona.getUsuarioPorUsername(id).getNombre() + " se le quito la opcion - "
								+ Datos.operations.get("32").toString(),
						"Modificar.", JOptionPane.OK_OPTION, ImageIconocheck);
				Datos.guardarDatos();

				((VentanaAdmin) vista).inicio();

			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			}
		} else if (evento.getActionCommand().equals("Nuevo Administrador.")) {
			((VentanaAdmin) vista).panelPrincipal.removeAll();
			JPanel j = new PanelNuevoAdmin();
			((VentanaAdmin) vista).setPanelPrincipal(j);
			((PanelNuevoAdmin) j).setControlador(new ControlVentanaAdmin(vista));
		} else if (evento.getActionCommand().equals("crearAdmin")) {
			try {
				String nombre = ((PanelNuevoAdmin) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				int id = Integer.parseInt(((PanelNuevoAdmin) ((VentanaAdmin) vista).panelPrincipal).texto2.getText());
				String contra = ((PanelNuevoAdmin) ((VentanaAdmin) vista).panelPrincipal).texto3.getText();
				int edad = Integer.parseInt(((PanelNuevoAdmin) ((VentanaAdmin) vista).panelPrincipal).texto4.getText());
				String genero = ((PanelNuevoAdmin) ((VentanaAdmin) vista).panelPrincipal).texto5.getText();
				if (nombre.isEmpty() || contra.isEmpty() || genero.isEmpty()) {
					// error dato vacio
					JOptionPane.showMessageDialog(null, "Se han dejado las casillas vacias.", "Registro Administrador.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else if (edad < 18) {
					JOptionPane.showMessageDialog(null, "Debe ser mayor de edad.", "Registro Administrador.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else if ((!genero.equals("F")) && (!genero.equals("M"))) {
					JOptionPane.showMessageDialog(null, "Ingrese un género válido (M/F).", "Registro Administrador.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else {
					if (!BaseDatos.Datos.hashPersona.containsKey((long) id)) {
						Admin.newAdminUser(nombre, (byte) edad, (long) id, genero, contra);

						JOptionPane.showMessageDialog(null, "Registro realizado exitosamente",
								"Registro Administrador.", JOptionPane.OK_OPTION, ImageIconocheck);
						Datos.guardarDatos();
						((VentanaAdmin) vista).inicio();
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo realizar el registro, el usuario ya existe",
								"Registro Administrador", JOptionPane.ERROR_MESSAGE, ImageIconoerror);
					}
				}
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Ha ingresado un tipo de dato erróneo", "Tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ha ingresado un tipo de dato erróneo", "Tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			}
		} 
		else if (evento.getActionCommand().equals("Registrar un moderador.")) {
			((VentanaAdmin) vista).panelPrincipal.removeAll();
			JPanel j = new PanelNuevoModerador();
			((VentanaAdmin) vista).setPanelPrincipal(j);
			((PanelNuevoModerador) j).setControlador(new ControlVentanaAdmin(vista));
		}
		else if (evento.getActionCommand().equals("crearModerador")) {
			try {
				String nombre = ((PanelNuevoModerador) ((VentanaAdmin) vista).panelPrincipal).texto1.getText();
				int id = Integer.parseInt(((PanelNuevoModerador) ((VentanaAdmin) vista).panelPrincipal).texto2.getText());
				String contra = ((PanelNuevoModerador) ((VentanaAdmin) vista).panelPrincipal).texto3.getText();
				int edad = Integer.parseInt(((PanelNuevoModerador) ((VentanaAdmin) vista).panelPrincipal).texto4.getText());
				String genero = ((PanelNuevoModerador) ((VentanaAdmin) vista).panelPrincipal).texto5.getText();
				if (nombre.isEmpty() || contra.isEmpty() || genero.isEmpty()) {
					// error dato vacio
					JOptionPane.showMessageDialog(null, "Se han dejado las casillas vacias.", "Registro Moderador.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else if (edad < 18) {
					JOptionPane.showMessageDialog(null, "Debe ser mayor de edad.", "Registro Moderador.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else if ((!genero.equals("F")) && (!genero.equals("M"))) {
					JOptionPane.showMessageDialog(null, "Ingrese un género válido (M/F).", "Registro Moderador.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else {
					if (Moderador.getUsuarioPorUsername(id) == null) {
						ArrayList<OpcionDeMenu> userOptions = new ArrayList<OpcionDeMenu>() {
							{
								add(BaseDatos.Datos.operations.get("13"));
								add(BaseDatos.Datos.operations.get("9"));
								add(BaseDatos.Datos.operations.get("10"));
								add(BaseDatos.Datos.operations.get("11"));
								add(BaseDatos.Datos.operations.get("14"));
								add(BaseDatos.Datos.operations.get("18"));
								add(BaseDatos.Datos.operations.get("32"));								
							}
						};

						MenuDeConsola userMenu = new MenuDeConsola(userOptions);
						new Moderador(nombre,(byte) edad, (long)id, genero, contra, (int)0, userMenu);
						//BaseDatos.Datos.hashModerador.get(id).setEstacion(e);
						JOptionPane.showMessageDialog(null, "Registro realizado exitosamente",
								"Registro Moderador.", JOptionPane.OK_OPTION, ImageIconocheck);
						Datos.guardarDatos();
						((VentanaAdmin) vista).inicio();
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo realizar el registro, el usuario ya existe",
								"Registro Moderador.", JOptionPane.ERROR_MESSAGE, ImageIconoerror);
					}
					if (!BaseDatos.Datos.hashPersona.containsKey((long) id)) {
						Admin.newAdminUser(nombre, (byte) edad, (long) id, genero, contra);

						
					} else {
						
					}
				}
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Ha ingresado un tipo de dato erróneo", "Tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Ha ingresado un tipo de dato erróneo", "Tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			}
		} 
		else if (evento.getActionCommand().equals("Inicio.")) {
			Datos.guardarDatos();
			((VentanaAdmin) vista).inicio();
		} else if (evento.getActionCommand().equals("Cerrar sesión.")) {
			Datos.guardarDatos();
			// la vista:
			InterfazVista vistainicio = new VentanaLogin();
			// y el control:
			Control controlinicio = new ControlPFicticio(vistainicio);
			// configura la vista
			vistainicio.setControlador(controlinicio);
			// y arranca la interfaz (vista):
			((VentanaAdmin) vista).cerrarventana();
			Persona.currentUser = null;
			vistainicio.arranca();
		}

		else
			vista.escribeCambio("ERROR");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
