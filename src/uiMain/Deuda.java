package uiMain;

import BaseDatos.Datos;
import gestorAplicacion.User.Usuario;
import java.util.*;

public class Deuda extends OpcionDeMenu {

	public Deuda(String key){
		super(key);
	}
	@Override
	public void ejecutar() {
		Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());
		if(aux.isDeuda()) {
			System.out.println(Main.user.getNombre()+ ", tiene una deuda en MiBici con valor de $" + aux.getDeuda() );
			Scanner ent= new Scanner(System.in);
			System.out.println("Desea usted ir al menu de pagar multa?.(S/N)");
			if((ent.next()).equals("S")) {
				BaseDatos.Datos.operations.get("12").ejecutar();
			}
		}else {
			System.out.println( "Usted esta libre de deudas con MiBici");
		}
		
		

	}

	@Override
	public String toString() {
		return "Deuda de Multas";
	}

}
