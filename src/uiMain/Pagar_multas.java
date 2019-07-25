package uiMain;

import BaseDatos.Datos;
import gestorAplicacion.User.Usuario;
import gestorAplicacion.Work.Multa;

import java.util.*;

public class Pagar_multas extends OpcionDeMenu {

	public Pagar_multas(String key){
		super(key);
	}
	
	@Override
	public void ejecutar() {
		Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());
		if(!aux.isDeuda()) {
			System.out.println("Usted no tiene deudas actualmente.");
		}
		else {
			Scanner ent= new Scanner(System.in);
			if (aux.getMultas().size()==1) {
				System.out.println("Desea pagar su unica multa? (S/N)");
				if(ent.next().equals("S")) {
					System.out.println(aux.pagarM());
				}
			}
			else {
				System.out.println("1 Pagar todas las multas.");
				System.out.println("2 Pagar una unica multa.");
				System.out.println("3 Retroceder.");
				String a= ent.next();
				if(a.contentEquals("1")) {
					System.out.println(aux.pagarM());
				}
				else if(a.contentEquals("2")){
					System.out.println("Por favor escriba cual multa desea pagar: ");
					int i=0;
					for (Multa multa: aux.getMultas()) {
						System.out.print((i+1) + ". ");
						System.out.println(aux.getMulta(i));
						i++;
					}
					System.out.println(aux.pagarM(ent.nextInt()-1));
				}
			}
			
		}
		

	}

	@Override
	public String toString() {		
		return "Pagar Multas";
	}

}
