package uiMain;

import BaseDatos.Datos;
import gestorAplicacion.User.Usuario;

public class Deuda extends OpcionDeMenu {

	public Deuda(String key){
		super(key);
	}
	@Override
	public void ejecutar() {
		Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());
		if(aux.isDeuda()) {
			System.out.println(Main.user.getNombre()+ ", tiene una deuda en MiBici con valor de $" + aux.getDeuda() );
		}else {
			System.out.println( "Usted esta libre de deudas con MiBici");
		}
		

	}

	@Override
	public String toString() {
		return "Deuda de Multas";
	}

}
