package uiMain;

import java.util.*;

import BaseDatos.*;
import gestorAplicacion.Bike.Estacion;
import gestorAplicacion.User.*;
import gestorAplicacion.Work.Multa;


public class Multar extends OpcionDeMenu {
	
	public static ArrayList<Multa> multas = new ArrayList<>();
	public Multar(String key) {
		super(key);
	}

	@Override
	public void ejecutar() {
		Scanner ent=new Scanner(System.in);
		System.out.println("Por favor ingrese el id del usuario que va a multar:");
		Usuario a = BaseDatos.Datos.hashUsuario.get(ent.nextLong());
		if (a!=null) {
			System.out.println("Ingrese el id de la multa (0 para cancelar):");
			byte i= ent.nextByte();
			Multa multa=Datos.hashMulta.get(i);
			
			while (multa==null) {
				if (i==0) {
					System.out.println("Se ha cancelado la multa.");
					return;
				}
				System.out.println("Id invalido.");
				System.out.println("Ingrese el id de la multa (0 para cancelar):");
				i= ent.nextByte();
				multa= Datos.hashMulta.get(i);
			}
			a.addMulta(multa);
			System.out.println("Se ha multado al usuario "+a.getNombre()+", por "+multa.getDescripcion());
		}
		else {
			System.out.println("Usuario no encontrado.");
		}
	}
	
	public static String getMultas() {
		String r;
		r=new String();
		if(BaseDatos.Datos.hashMulta.isEmpty()){
			r= new String("No hay multas creadas.");
			return r;
		}
		int i=1;
		multas.clear();
		for (Map.Entry<Byte, Multa> entry : BaseDatos.Datos.hashMulta.entrySet()) {
			r += ""+ i +". Id: "+entry.getValue().getId() + ", Descripcion: "+ entry.getValue().getDescripcion()+"\n";
			multas.add(entry.getValue());
			i++;
		}
		
		return r;
	}
	public static  Multa getMulta( String r) {
		int index = Integer.parseInt(r);
		return multas.get(index - 1);
	}
	
	public static String getUsuarios() {
		String r;
		r=new String();
		if(BaseDatos.Datos.hashUsuario.isEmpty()){
			r= new String("No hay usuarios registados.");
			return r;
		}
		
		for (Map.Entry<Long, Usuario> entry : BaseDatos.Datos.hashUsuario.entrySet()) {
			r += "* "+". Documento: "+entry.getValue().getId() + ", Nombre: "+ entry.getValue().getNombre()+".\n";
			
		}
		
		return r;
	}

	@Override
	public String toString() {
		return "Multar";
	}

}
