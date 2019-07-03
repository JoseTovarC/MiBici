package uiMain;
import java.util.*;
import BaseDatos.Datos;
import gestorAplicacion.User.Usuario;

public class SignUp extends OpcionDeMenu {

	@Override
	public void ejecutar() {
		Scanner esc = new Scanner(System.in);
		System.out.println("Estas en La opcion de registro de un nuevo ususario, si desea continuar con el registro envie la opcion (1)");
		System.out.println("Si por casualidades de la vida o aleatoriedad llegaste hasta aqui, puedes retroceder con la opcion (2)");
		byte a=0;
		while(a<1 || a>2) {
			a= esc.nextByte();
			if(a==1) {
				
				System.out.println("Ingresa el nombre completo y al final' *'(ESPACIO + *): ");				
				String aux = esc.next();
				String nombre = "";
				while(!(aux.equals("*"))) {
					nombre += aux + " ";
					aux = esc.next();
				}		
				System.out.println("Ingrese su edad:");
				byte edad = esc.nextByte();
				if(edad<18) {
					System.out.println("Eres Menor de edad, no puedes hacer uso del sistema:");
					Main.user = Usuario.getUsuarioPorUsername((long)0);
					break;
				}
				System.out.print("Genero (M/F):");
				String genero = esc.next();
				System.out.print("Ingrese el No. de la cedula(Este sera tu repectivo usuario):");
				long id= esc.nextLong();
				System.out.print("Inserte la contraseña que desee:");
				String contra = esc.next();
				if(Usuario.getUsuarioPorUsername(id) == null) {
					ArrayList<OpcionDeMenu> userOptions = new ArrayList<OpcionDeMenu>(){
						{
							add(BaseDatos.Datos.operations.get("5"));		
							add(BaseDatos.Datos.operations.get("3"));
						}
					};
							
					MenuDeConsola userMenu = new MenuDeConsola(userOptions);
					new Usuario(nombre, edad, id, genero, contra, 0, userMenu);
					System.out.println("Registro realizado exitosamente");					
				}else {
					System.out.println("No se pudo realizar el registro, el usuario ya existe");
				}
				
				Main.user = Usuario.getUsuarioPorUsername((long)0);
			}else if(a==2){
				Main.user = Usuario.getUsuarioPorUsername((long)0);
			}else {
				System.out.println("Inserte un valor valido");
			}
		}
		
	}

	@Override
	public String toString() {
		return "Registrarse";
	}

}
