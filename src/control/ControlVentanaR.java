package control;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BaseDatos.Datos;
import gestorAplicacion.Bike.Distribuidor;
import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.Admin;
import gestorAplicacion.User.Moderador;
import gestorAplicacion.User.Persona;
import gestorAplicacion.User.Usuario;
import uiMain.CrearBicicleta;
import uiMain.MenuDeConsola;
import uiMain.Multar;
import uiMain.OpcionDeMenu;
import uiMain.cant_bicicletas_esta;
import vista.InterfazVista;
import vista.PanelAgregarDistribuidor;
import vista.PanelCrearBicicleta;
import vista.PanelCrearEstacion;
import vista.PanelDevolverBicicleta;
import vista.PanelFuncionalidades;
import vista.PanelMultar;
import vista.PanelPedirBicicleta;
import vista.PanelPerfilTarjeta;
import vista.PanelRecargarTarjeta;
import vista.VentanaLogin;
import vista.VentanaUsuarioInvitado;
import vista.VentanaUsuarioR;

public class ControlVentanaR implements Control {

	private InterfazVista vista;
	String ruta = System.getProperty("user.dir") + "\\src\\images\\";
	ImageIcon ImageIconoerror = new ImageIcon(ruta + "iconfinder_Error_381599.png");
	ImageIcon ImageIconoerrorsign = new ImageIcon(ruta + "iconfinder_sign-error_299045.png");
	ImageIcon ImageIconowarning = new ImageIcon(ruta + "iconfinder_101_Warning_183416.png");
	ImageIcon ImageIconocheck = new ImageIcon(ruta + "iconfinder_sign-check_299110.png");
	ImageIcon ImageIconohelp = new ImageIcon(ruta + "iconfinder_Help_1493288.png");

	// private ConversorEurosPesos modelo;
	public ControlVentanaR(InterfazVista vista) {
		this.vista = vista;

	}

	public void actionPerformed(ActionEvent evento) {

		if (evento.getActionCommand().equals("UsuarioDatos")) {
			((VentanaUsuarioR) vista).ingresardatos();

		} else if (evento.getActionCommand().equals("Perfil de la Tarjeta")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			JPanel j = new PanelPerfilTarjeta(((Usuario) Persona.currentUser).getTarjeta().getSaldo(),
					((Usuario) Persona.currentUser).getMultas().toString());
			((VentanaUsuarioR) vista).setPanelPrincipal(j);
			((PanelPerfilTarjeta) j).setControlador(new ControlVentanaR(vista));

		} else if (evento.getActionCommand().equals("Pedir Bicicleta")) {

			try {
				if (Persona.currentUser instanceof Moderador) {
					if (((Moderador) Persona.currentUser).getBicicleta() != null) {
						JOptionPane.showMessageDialog(null, "Usted tiene un prestamo vigente.", "Error para prestar",
								JOptionPane.ERROR_MESSAGE, ImageIconowarning);
					} else {
						((VentanaUsuarioR) vista).panelPrincipal.removeAll();
						PanelPedirBicicleta j = new PanelPedirBicicleta(cant_bicicletas_esta.getEstacioness());
						((VentanaUsuarioR) vista).setPanelPrincipal(j);
						j.setControlador(new ControlVentanaR(vista));
					}
				} else if (Persona.currentUser instanceof Usuario) {
					if (((Usuario) Persona.currentUser).getBicicleta() != null) {
						JOptionPane.showMessageDialog(null, "Usted tiene un prestamo vigente.", "Error para prestar",
								JOptionPane.ERROR_MESSAGE, ImageIconowarning);
					} else if (((Usuario) Persona.currentUser).getTarjeta().getSaldo() < 500) {
						JOptionPane.showMessageDialog(null, "Saldo insuficiente", "Error para prestar",
								JOptionPane.ERROR_MESSAGE, ImageIconowarning);
					} else if (((Usuario) Persona.currentUser).isDeuda()) {
						JOptionPane.showMessageDialog(null, "Posee una Deuda", "Error para prestar",
								JOptionPane.ERROR_MESSAGE, ImageIconowarning);
					} else {
						((VentanaUsuarioR) vista).panelPrincipal.removeAll();
						PanelPedirBicicleta j = new PanelPedirBicicleta(cant_bicicletas_esta.getEstacioness());
						((VentanaUsuarioR) vista).setPanelPrincipal(j);
						j.setControlador(new ControlVentanaR(vista));

					}
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error.", "Error para el prestamo", JOptionPane.ERROR_MESSAGE,
						ImageIconowarning);
			}

		} else if (evento.getActionCommand().equals("Escogerestacionp")) {
			try {
				String ide = ((PanelPedirBicicleta) ((VentanaUsuarioR) vista).panelPrincipal).texto2.getText();
				String r = "";
				for (int i = 0; i < cant_bicicletas_esta.getEstacion(ide).getCap_max(); i++) {
					r += (i + 1) + ". " + cant_bicicletas_esta.getEstacion(ide).getBicicletas()[i] + "\n";
				}
				((PanelPedirBicicleta) ((VentanaUsuarioR) vista).panelPrincipal).bicicletas(ide, r);
				((VentanaUsuarioR) vista).pack();
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Ingresaste un dato incorrecto", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingreso un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {

			}

		} else if (evento.getActionCommand().equals("Escogerbicicletap")) {
			try {
				String ib = ((PanelPedirBicicleta) ((VentanaUsuarioR) vista).panelPrincipal).texto2.getText();
				int idb = Integer.parseInt(ib);
				String ie = ((PanelPedirBicicleta) ((VentanaUsuarioR) vista).panelPrincipal).texto3.getText();
				if (Persona.currentUser instanceof Moderador) {
					if(((Moderador) Persona.currentUser).prestarPanel(cant_bicicletas_esta.getEstacion(ie), idb)) {
						JOptionPane.showMessageDialog(null, "Prestamo realizado exitosamente", "Prestamo Bicicleta",
								JOptionPane.OK_OPTION, ImageIconocheck);
						Datos.guardarDatos();
					} else {
						JOptionPane.showMessageDialog(null, "Prestamo no aceptado", "Prestamo",
								JOptionPane.ERROR_MESSAGE, ImageIconowarning);
						Datos.guardarDatos();
					}
				} else if (Persona.currentUser instanceof Usuario) {
					if(((Usuario) Persona.currentUser).prestarPanel(cant_bicicletas_esta.getEstacion(ie), idb)) {
						JOptionPane.showMessageDialog(null, "Prestamo realizado exitosamente", "Prestamo Bicicleta",
								JOptionPane.OK_OPTION, ImageIconocheck);
						Datos.guardarDatos();
					} else {
						JOptionPane.showMessageDialog(null, "Prestamo no aceptado", "Prestamo",
								JOptionPane.ERROR_MESSAGE, ImageIconowarning);
						Datos.guardarDatos();
					}

				}
				((VentanaUsuarioR) vista).inicio();
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingreso un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Ingresaste un dato incorrecto", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {

			} finally {
				Estacion.nivelar();
			}

		} else if (evento.getActionCommand().equals("Devolver Bicicleta")) {
			try {
				if (((Usuario) Persona.currentUser).getBicicleta() == null) {
					JOptionPane.showMessageDialog(null, "Usted no tiene un prestamo vigente.", "Error para devolver",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else {
					((VentanaUsuarioR) vista).panelPrincipal.removeAll();
					JPanel j = new PanelDevolverBicicleta(cant_bicicletas_esta.getEstacioness());
					((VentanaUsuarioR) vista).setPanelPrincipal(j);
					((PanelDevolverBicicleta) j).setControlador(new ControlVentanaR(vista));
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error.", "Error para devolver", JOptionPane.ERROR_MESSAGE,
						ImageIconowarning);
			}

		} else if (evento.getActionCommand().equals("Recargar Tarjeta")) {
			try {
				if (!((Usuario) Persona.currentUser).getMenu().contieneOpcion("11") ) {
					throw new opcionNoValidaException();
				}
				((VentanaUsuarioR) vista).panelPrincipal.removeAll();
				// ((VentanaUsuarioR) vista).recargarTarjeta();
				JPanel j = new PanelRecargarTarjeta();
				((VentanaUsuarioR) vista).setPanelPrincipal(j);
				((PanelRecargarTarjeta) j).setControlador(new ControlVentanaR(vista));

			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Ingresaste un dato incorrecto", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			}catch (opcionNoValidaException e) {
				JOptionPane.showMessageDialog(null, "El usuario tiene la opcion bloqueada", "Opcion no valida",
						JOptionPane.ERROR_MESSAGE, ImageIconoerror);
			} 
			catch (Exception e) {

			}

		} else if (evento.getActionCommand().equals("Recargar la Tarjeta")) {
			try {
				int q = Integer
						.parseInt(((PanelRecargarTarjeta) ((VentanaUsuarioR) vista).panelPrincipal).texto1.getText());
				if (q <= 300000) {
					((Usuario) Persona.currentUser).getTarjeta().recargar(q);
					JOptionPane.showMessageDialog(null, "La recarga a la tarjeta de $" + q + " fue exitosa.",
							"Recarga Tarjeta", JOptionPane.OK_OPTION, ImageIconocheck);
					Datos.guardarDatos();
				} else {
					JOptionPane.showMessageDialog(null, "Recarga fallida, recarga máxima $300000.", "Recarga Tarjeta",
							JOptionPane.ERROR_MESSAGE, ImageIconoerror);
					Datos.guardarDatos();
				}
				((VentanaUsuarioR) vista).inicio();

			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Se ha ingresado un dato incorrecto.", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingreso un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} 
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error en la recarga.", "Recarga Tarjeta",
						JOptionPane.ERROR_MESSAGE, ImageIconoerror);
			}
		} else if (evento.getActionCommand().equals("Escoger Estacion.")) {
			try {
				String id = ((PanelDevolverBicicleta) ((VentanaUsuarioR) vista).panelPrincipal).texto2.getText();
				if (((Usuario) Persona.currentUser).devolver(cant_bicicletas_esta.getEstacion(id))) {
					JOptionPane.showMessageDialog(null, "Devolución realizada exitosamente", "Devolver Bicicleta",
							JOptionPane.OK_OPTION, ImageIconocheck);
				} else {
					JOptionPane.showMessageDialog(null, "Devolucion no realizada", "Devolver Bicicleta",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				}

				((VentanaUsuarioR) vista).inicio();
				Datos.guardarDatos();
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Ingresaste un dato incorrecto", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
			} finally {
				Estacion.nivelar();
			}

		} else if (evento.getActionCommand().equals("Pagar Multas")) {

			try {
				if (!((Usuario) Persona.currentUser).getMenu().contieneOpcion("12")) {
					throw new opcionNoValidaException();
				} else {
					String r = ((Usuario) Persona.currentUser).pagarM();
					if (r.equals("Pago no realizado. \n Saldo insuficiente")) {
						JOptionPane.showMessageDialog(null, "Pago no realizado. \n Saldo insuficiente", "Pagar Multas",
								JOptionPane.ERROR_MESSAGE, ImageIconoerror);
					} else if (r.equals("Pago no realizado. \n Usted no tiene multas o deudas actualmente.")) {
						JOptionPane.showMessageDialog(null,
								"Pago no realizado. \n Usted no tiene multas o deudas actualmente.", "Pagar Multas",
								JOptionPane.ERROR_MESSAGE, ImageIconowarning);
					} else {
						JOptionPane.showMessageDialog(null, "El valor de las multas o multa han sido pagadas",
								"Pagar Multas", JOptionPane.OK_OPTION, ImageIconocheck);
					}
					((VentanaUsuarioR) vista).inicio();
					Datos.guardarDatos();
				}
			} catch (IndexOutOfBoundsException e) {

				JOptionPane.showMessageDialog(null, "Ingresaste un dato incorrecto", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			}catch (opcionNoValidaException e) {
				JOptionPane.showMessageDialog(null, "El usuario tiene la opcion bloqueada", "Opcion no valida",
						JOptionPane.ERROR_MESSAGE, ImageIconoerror);
			}  
			catch (Exception e) {

			}

		} else if (evento.getActionCommand().equals("Cantidad de bicicletas por estación")) {
			((VentanaUsuarioR) vista).mostrartexto(cant_bicicletas_esta.getEstacioness());

		} else if (evento.getActionCommand().equals("Deuda de Multas")) {
			String r = "";
			for (int i = 0; i < ((Usuario) Persona.currentUser).getMultas().size(); i++) {
				r += ((Usuario) Persona.currentUser).getMultas().get(i).descrip();
			}
			((VentanaUsuarioR) vista).mostrarmultas(r + "Total: " + ((Usuario) Persona.currentUser).getDeuda());
		} else if (evento.getActionCommand().equals("Inicio")) {
			((VentanaUsuarioR) vista).inicio();
		} else if (evento.getActionCommand().equals("Cerrar sesión")) {
			// la vista:
			InterfazVista vistainicio = new VentanaLogin();
			// y el control:
			Control controlinicio = new ControlPFicticio(vistainicio);
			// configura la vista
			vistainicio.setControlador(controlinicio);
			// y arranca la interfaz (vista):
			((VentanaUsuarioR) vista).cerrarventana();
			Persona.currentUser = null;
			vistainicio.arranca();
		}

		// OPCIONES DE MODERADOR
		else if (evento.getActionCommand().equals("Solicitar creacion de bicicleta")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			JPanel j = new PanelCrearBicicleta(cant_bicicletas_esta.getEstacioness(),
					CrearBicicleta.getDistribuidores());
			((VentanaUsuarioR) vista).setPanelPrincipal(j);
			((PanelCrearBicicleta) j).setControlador(new ControlVentanaR(vista));
		} else if (evento.getActionCommand().equals("Pedir.")) {
			try {
				String e = ((PanelCrearBicicleta) ((VentanaUsuarioR) vista).panelPrincipal).texto3.getText();
				String d = ((PanelCrearBicicleta) ((VentanaUsuarioR) vista).panelPrincipal).texto4.getText();
				if (cant_bicicletas_esta.getEstacion(e).isFull()) {
					JOptionPane.showMessageDialog(null, "Estacion llena.", "Creacion Bicicleta.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);

				} else {
					String s = CrearBicicleta.getDistribuidor(d).crearB(cant_bicicletas_esta.getEstacion(e));
					JOptionPane.showMessageDialog(null, s, "Creacion Bicicleta", JOptionPane.OK_OPTION,
							ImageIconocheck);
				}
				Datos.guardarDatos();
				((VentanaUsuarioR) vista).inicio();

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Se ha ingresado un dato incorrecto", "Tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se han creado Bicicleta", "Creacion Bicicleta.",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			}
		} else if (evento.getActionCommand().equals("Crear Estacion.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			JPanel j = new PanelCrearEstacion();
			((VentanaUsuarioR) vista).setPanelPrincipal(j);
			((PanelCrearEstacion) j).setControlador(new ControlVentanaR(vista));

		} else if (evento.getActionCommand().equals("crearEstacion")) {
			try {
				if (((PanelCrearEstacion) ((VentanaUsuarioR) vista).panelPrincipal).texto2.getText().isEmpty()
						|| ((PanelCrearEstacion) ((VentanaUsuarioR) vista).panelPrincipal).texto4.getText().isEmpty()
						|| ((PanelCrearEstacion) ((VentanaUsuarioR) vista).panelPrincipal).texto3.getText().isEmpty()) {
					// error dato vacio
					JOptionPane.showMessageDialog(null, "Has dejado alguna casilla vacia.", "Crear Estación.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else if (!((PanelCrearEstacion) ((VentanaUsuarioR) vista).panelPrincipal).texto4.getText()
						.equals("Automatica")
						&& !((PanelCrearEstacion) ((VentanaUsuarioR) vista).panelPrincipal).texto4.getText()
								.equals("Manual")) {
					JOptionPane.showMessageDialog(null, "Ingrese un tipo válido (Automatica/Manual).",
							"Crear Estación.", JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else {
					String id = ((PanelCrearEstacion) ((VentanaUsuarioR) vista).panelPrincipal).texto2.getText();
					if (Datos.hashEstacion.get(id) == null) {

						String tipo = ((PanelCrearEstacion) ((VentanaUsuarioR) vista).panelPrincipal).texto4.getText();
						int cap_max = Integer.parseInt(
								((PanelCrearEstacion) ((VentanaUsuarioR) vista).panelPrincipal).texto3.getText());

						new Estacion(id, tipo, true, cap_max);
						JOptionPane.showMessageDialog(null,
								"Se ha creado la estación " + Datos.hashEstacion.get(id).toString(), "Crear Estación.",
								JOptionPane.OK_OPTION, ImageIconocheck);
						((VentanaUsuarioR) vista).inicio();
						Datos.guardarDatos();
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo realizar el registro, la estación ya existe.",
								"Crear Estación.", JOptionPane.ERROR_MESSAGE, ImageIconoerror);
					}
				}
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Ha ingresado un tipo de dato erróneo", "Tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {

			}
		} else if (evento.getActionCommand().equals("Agregar Distribuidor.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			JPanel j = new PanelAgregarDistribuidor();
			((VentanaUsuarioR) vista).setPanelPrincipal(j);
			((PanelAgregarDistribuidor) j).setControlador(new ControlVentanaR(vista));

		} else if (evento.getActionCommand().equals("agregarDistribuidor")) {
			try {
				if (((PanelAgregarDistribuidor) ((VentanaUsuarioR) vista).panelPrincipal).texto2.getText().isEmpty()
						|| ((PanelAgregarDistribuidor) ((VentanaUsuarioR) vista).panelPrincipal).texto3.getText()
								.isEmpty()) {
					// error dato vacio
					JOptionPane.showMessageDialog(null, "Has dejado alguna casilla vacia.", "Agregar distribuidor.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else {
					String id = ((PanelAgregarDistribuidor) ((VentanaUsuarioR) vista).panelPrincipal).texto2.getText();
					if (Datos.hashDistribuidor.get(id) == null) {

						String name = ((PanelAgregarDistribuidor) ((VentanaUsuarioR) vista).panelPrincipal).texto3
								.getText();

						new Distribuidor(name, id);
						JOptionPane.showMessageDialog(null, "Se ha agregado el distribuidor.", "Agregar distribuidor.",
								JOptionPane.OK_OPTION, ImageIconocheck);
						((VentanaUsuarioR) vista).inicio();
						Datos.guardarDatos();
					} else {
						JOptionPane.showMessageDialog(null,
								"No se pudo realizar el registro, el distribuidor ya esta agregado.", "Crear Estación.",
								JOptionPane.ERROR_MESSAGE, ImageIconoerror);
					}
				}
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Ha ingresado un tipo de dato erróneo", "Agregar distribuidor.",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {

			}
		} else if (evento.getActionCommand().equals("Multar")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			JPanel j = new PanelMultar(Multar.getUsuarios(), Multar.getMultas());

			((VentanaUsuarioR) vista).setPanelPrincipal(j);
			((PanelMultar) j).setControlador(new ControlVentanaR(vista));

		} else if (evento.getActionCommand().equals("Multar.")) {
			try {
				String u = ((PanelMultar) ((VentanaUsuarioR) vista).panelPrincipal).texto3.getText();
				long id = (long) Integer.parseInt(u);
				if (Persona.getUsuarioPorUsername(id) == null) {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado.", "Multar.", JOptionPane.ERROR_MESSAGE,
							ImageIconowarning);

				} else if ((Persona.getUsuarioPorUsername(id) instanceof Moderador)
						|| (Persona.getUsuarioPorUsername(id) instanceof Admin)) {
					JOptionPane.showMessageDialog(null,
							"El documento pertenece a otro tipo de usuario, el cual no se le ponen multas", "Multar.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);

				} else {

					String m = ((PanelMultar) ((VentanaUsuarioR) vista).panelPrincipal).texto4.getText();
					((Usuario) Persona.getUsuarioPorUsername(id)).addMulta(Multar.getMulta(m));
					JOptionPane.showMessageDialog(
							null, "El Usuario " + Persona.getUsuarioPorUsername(id).getNombre()
									+ " ha sido multado con:" + Multar.getMulta(m).descripm(),
							"Multar.", JOptionPane.OK_OPTION, ImageIconocheck);
					Datos.guardarDatos();
					((VentanaUsuarioR) vista).inicio();
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Se ha ingresado un dato incorrecto", "Tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se han creado Bicicleta", "Creacion Bicicleta.",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			}
		}
		else if (evento.getActionCommand().equals("El usuario que mas usos ha hecho.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			String a="El usuario que más usos ha hecho.";
			StringBuffer b=BaseDatos.Datos.getMayorUsoP();
			((VentanaUsuarioR) vista).setPanelPrincipal(new PanelFuncionalidades(a,b));
		}
		else if (evento.getActionCommand().equals("El usuario mas viejo.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			String a="El usuario de mayor edad.";
			StringBuffer b=BaseDatos.Datos.getMayorEdad();
			((VentanaUsuarioR) vista).setPanelPrincipal(new PanelFuncionalidades(a,b));
		}
		else if (evento.getActionCommand().equals("El usuario mas joven.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			String a="El usuario de menor edad.";
			StringBuffer b=BaseDatos.Datos.getMenorEdad();
			((VentanaUsuarioR) vista).setPanelPrincipal(new PanelFuncionalidades(a,b));
		}
		else if (evento.getActionCommand().equals("Promedio de edad de todos los usuarios.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			String a="El promedio de edad de todos los usuarios.";
			StringBuffer b=BaseDatos.Datos.getPromEdad();
			((VentanaUsuarioR) vista).setPanelPrincipal(new PanelFuncionalidades(a,b));
		}
		else if (evento.getActionCommand().equals("Porcentaje de genero de los usuarios.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			String a="El porcentaje de usuarios por género.";
			StringBuffer b=BaseDatos.Datos.getPorcGen();
			((VentanaUsuarioR) vista).setPanelPrincipal(new PanelFuncionalidades(a,b));
		}
		else if (evento.getActionCommand().equals("El porcentaje de uso por genero.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			String a="El porcentaje de uso de todos los usuarios según su género.";
			StringBuffer b=BaseDatos.Datos.getPorcUsoGen();
			((VentanaUsuarioR) vista).setPanelPrincipal(new PanelFuncionalidades(a,b));
		}
		else if (evento.getActionCommand().equals("El usuario que tiene mas multas actualmente.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			String a="El usuario que tiene mas multas actualmente.";
			StringBuffer b=BaseDatos.Datos.getMayorCantM();
			((VentanaUsuarioR) vista).setPanelPrincipal(new PanelFuncionalidades(a,b));
		}
		else if (evento.getActionCommand().equals("Las bicicletas mas usadas.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			String a="Las bicicletas que han sido mas usadas.";
			StringBuffer b=BaseDatos.Datos.getMayorUsoB();
			((VentanaUsuarioR) vista).setPanelPrincipal(new PanelFuncionalidades(a,b));
		}
		else if (evento.getActionCommand().equals("Las estaciones mas usadas.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			String a="Las estaciones que han sido mas usadas.";
			StringBuffer b=BaseDatos.Datos.getMayorUsoE();
			((VentanaUsuarioR) vista).setPanelPrincipal(new PanelFuncionalidades(a,b));
		}
		else if (evento.getActionCommand().equals("El promedio de la cantidad de bicicletas en todas las estaciones.")) {
			((VentanaUsuarioR) vista).panelPrincipal.removeAll();
			String a="El promedio de la cantidad de bicicletas que hay en cada estación actualmente.";
			StringBuffer b=BaseDatos.Datos.getPromCantB();
			((VentanaUsuarioR) vista).setPanelPrincipal(new PanelFuncionalidades(a,b));
		}
		else if (evento.getActionCommand().equals("Ayuda")) {
			if(Persona.currentUser instanceof Moderador) {
				JOptionPane.showMessageDialog(null, "Ayuda Moderador registrado \n Creado por el grupo 8, conformado por Juan José Lopera Duque, Jhon Freddy Guerra Martinez, José Orlando Tovar Cano y Luis Felipe Moreno Chamorro. \n Para mayor información consulte el manual de usuario.", "Ayuda.",
						JOptionPane.INFORMATION_MESSAGE, ImageIconohelp);
			}else {
				JOptionPane.showMessageDialog(null, "Ayuda Usuario registrado \n Creado por el grupo 8, conformado por Juan José Lopera Duque, Jhon Freddy Guerra Martinez, José Orlando Tovar Cano y Luis Felipe Moreno Chamorro. \n Para mayor información consulte el manual de usuario.", "Ayuda.",
						JOptionPane.INFORMATION_MESSAGE, ImageIconohelp);
			}
			
		}
		

		else {
			JOptionPane.showMessageDialog(null, "Error.", "Error.", JOptionPane.ERROR_MESSAGE, ImageIconowarning);
		}
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
