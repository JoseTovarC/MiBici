package uiMain;

import BaseDatos.Datos;
import gestorAplicacion.User.Usuario;

public class ConsultarSaldo extends OpcionDeMenu {
	public ConsultarSaldo(String key) {
		super(key);
	}

	@Override
	public void ejecutar() {
		// TODO Auto-generated method stub
		System.out.println(((Usuario) Datos.hashPersona.get(Main.user.getId())).getTarjeta());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Saldo Tarjeta.";
	}

}
