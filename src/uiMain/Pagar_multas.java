package uiMain;

import BaseDatos.Datos;
import gestorAplicacion.User.Usuario;

public class Pagar_multas extends OpcionDeMenu {

	public Pagar_multas(String key){
		super(key);
	}
	
	@Override
	public void ejecutar() {
		Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());
		System.out.println(aux.pagarM());

	}

	@Override
	public String toString() {		
		return "Pagar Multas";
	}

}
