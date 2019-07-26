package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.Moderador;
import gestorAplicacion.User.Usuario;

public class SignUpM extends OpcionDeMenu {

	public SignUpM(String key) {
		super(key);
	}

	@Override
	public void ejecutar() {
		Scanner esc = new Scanner(System.in);
		System.out.println("1. Registrar un nuevo moderador.");
		System.out.println("2. Retroceder.");
		byte a = 0;
		Estacion e;
		while (a < 1 || a > 2) {
			a = esc.nextByte();
			if (a == 1) {
				System.out.println("Ingrese su nombre y luego ' *' (ESPACIO + *): ");
				String aux = esc.next();
				String nombre = "";
				while (!(aux.equals("*"))) {
					nombre += aux + " ";
					aux = esc.next();
				}
				nombre = nombre.substring(0, nombre.length() - 1);
				System.out.println("Ingrese su edad: ");
				byte edad = esc.nextByte();
				if (edad < 18) {
					System.out.println("Es Menor de edad, no puede hacer uso del sistema.");
					Main.user = Usuario.getUsuarioPorUsername((long) 0);
					break;
				}
				System.out.print("Ingrese su genero (M/F): ");
				String genero = esc.next();
				System.out.print("Ingrese el No. de su cedula (Este sera su usuario): ");
				long id = esc.nextLong();
				System.out.print("Inserte una contraseña: ");
				String contra = esc.next();
				System.out.print("Ingrese un saldo inicial: ");
				int saldo= esc.nextInt();
				/*System.out.print("Desea dejarlo en una estacion? (S/N);");
				if (esc.next().equals("S")) {
					 BaseDatos.Datos.operations.get("8").ejecutar();
					 System.out.println("Escriba el id de una estacion: (Cadena String)");
					 e=BaseDatos.Datos.hashEstacion.get(esc.next());
				}*/
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
							add(BaseDatos.Datos.operations.get("3"));
							
						}
					};

					MenuDeConsola userMenu = new MenuDeConsola(userOptions);
					new Moderador(nombre, edad, id, genero, contra, saldo, userMenu);
					//BaseDatos.Datos.hashModerador.get(id).setEstacion(e);
					System.out.println("Registro realizado exitosamente.");
				} else {
					System.out.println("No se pudo realizar el registro, el usuario ya existe.");
				}

				Main.user = Usuario.getUsuarioPorUsername((long) 0);
			} else if (a == 2) {
				Main.user = Usuario.getUsuarioPorUsername((long) 0);
			} else {
				System.out.println("Inserte un valor valido.");
			}
		}
		esc.close();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Crear Moderador.";
	}
}
