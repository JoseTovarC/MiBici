package uiMain;
import java.util.*;
import BaseDatos.Datos;
import gestorAplicacion.User.Admin;
import gestorAplicacion.User.Usuario;

public class SignUpU extends OpcionDeMenu {

	public SignUpU(String key){
		super(key);
	}
	@Override
	public void ejecutar() {
		Scanner esc = new Scanner(System.in);
		System.out.println("1. Registrar un nuevo usuario.");
		System.out.println("2. Retroceder.");
		byte a=0;
		while(a<1 || a>2) {
			a= esc.nextByte();
			if(a==1) {
				System.out.println("Ingrese su nombre y luego ' *' (ESPACIO + *): ");				
				String aux = esc.next();
				String nombre = "";
				while(!(aux.equals("*"))) {
					nombre += aux + " ";
					aux = esc.next();
				}
				nombre = nombre.substring(0,nombre.length()-1);
				System.out.println("Ingrese su edad: ");
				byte edad = esc.nextByte();
				if(edad<18) {
					System.out.println("Es Menor de edad, no puede hacer uso del sistema.");
					Main.user = Usuario.getUsuarioPorUsername((long)0);
					break;
				}
				System.out.print("Ingrese su genero (M/F): ");
				String genero = esc.next();
				System.out.print("Ingrese el No. de su cedula (Este sera su usuario): ");
				long id= esc.nextLong();
				System.out.print("Inserte una contraseña: ");
				String contra = esc.next();
				if(Usuario.getUsuarioPorUsername(id) == null) {
					ArrayList<OpcionDeMenu> userOptions = new ArrayList<OpcionDeMenu>(){
						{
							add(BaseDatos.Datos.operations.get("5"));		
							add(BaseDatos.Datos.operations.get("9"));	
							add(BaseDatos.Datos.operations.get("10"));
							add(BaseDatos.Datos.operations.get("12"));
							add(BaseDatos.Datos.operations.get("3"));

						}
					};
							
					MenuDeConsola userMenu = new MenuDeConsola(userOptions);
					new Usuario(nombre, edad, id, genero, contra, 0, userMenu);
					System.out.println("Registro realizado exitosamente.");					
				}else {
					System.out.println("No se pudo realizar el registro, el usuario ya existe.");
				}
				
				Main.user = Usuario.getUsuarioPorUsername((long)0);
			}else if(a==2){
				Main.user = Usuario.getUsuarioPorUsername((long)0);
			}else {
				System.out.println("Inserte un valor valido.");
			}
		}
		esc.close();
	}

	@Override
	public String toString() {
		if (Main.user instanceof Admin) {
			return "Crear Usuario.";
		}
		return "Registrarse.";
	}

}
