package uiMain;
import java.util.Scanner;

import gestorAplicacion.User.Usuario;


public class Login extends OpcionDeMenu{

	@Override
	public void ejecutar() {
		Scanner esc = new Scanner(System.in);
		System.out.print("Ingrese su usuario: ");
		long id = esc.nextLong();
		System.out.print("Ingrese su contrase�a: ");
		String password = esc.next();
		
		System.out.println(Usuario.login(id, password));
		
	}

	@Override
	public String toString() {
		return "Iniciar sesi�n";
	}

}