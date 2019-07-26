package uiMain;
import java.util.Scanner;

import gestorAplicacion.User.Persona;
import gestorAplicacion.User.Usuario;


public class Login extends OpcionDeMenu{

	public Login(String key){
		super(key);
	}
	@Override
	public void ejecutar() {
		Scanner esc = new Scanner(System.in);
		System.out.print("Ingrese su usuario: ");
		long id = esc.nextLong();
		System.out.print("Ingrese su contrase�a: ");
		String password = esc.next();
		
		System.out.println(Persona.login(id, password));
		
		
		
	}

	@Override
	public String toString() {
		return "Iniciar sesi�n";
	}

}