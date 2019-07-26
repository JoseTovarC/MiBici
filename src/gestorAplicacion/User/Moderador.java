package gestorAplicacion.User;
//import java.util.ArrayList;

import BaseDatos.Datos;
//import Work.*;
import gestorAplicacion.Bike.*;
import uiMain.MenuDeConsola;

public class Moderador extends Usuario{
	private Estacion estacionM;


	public Moderador(String nombre, byte edad, long id, String genero, String clave, int saldo, MenuDeConsola menu, Estacion estacion) {
		super(nombre, edad, id, genero, clave, saldo, menu, "No Agregar a la hash de Usuarios");
		this.estacionM = estacion;
		menu.setUser(this);
		BaseDatos.Datos.menus.put(id, menu);
		BaseDatos.Datos.hashModerador.put(id,this);
		
	}

	public Moderador(String nombre, byte edad, long id, String genero, String clave, int saldo, Estacion estacion) {
		super(nombre, edad, id, genero, clave, saldo, null, "No Agregar a la hash de Usuarios");
		this.estacionM = estacion;
		BaseDatos.Datos.hashModerador.put(id,this);
		
	}
	public Moderador(String nombre, byte edad, long id, String genero, String clave, int saldo, MenuDeConsola menu) {
		super(nombre, edad, id, genero, clave, saldo, menu, "No Agregar a la hash de Usuarios");
		BaseDatos.Datos.hashModerador.put(id,this);
		menu.setUser(this);
		BaseDatos.Datos.menus.put(id, menu);
		
	}
	public Moderador(String nombre, String edad, String iden, String genero, String clave, String saldo) {
		super(nombre, edad, iden, genero, clave, saldo, "No Agregar a la hash de Usuarios");
		long id = (long)Integer.parseInt(iden);
		BaseDatos.Datos.hashModerador.put(id,this);
	}
	
	
	

	public void multar(Usuario usuario, String tipo) {
		
		
		
		/*
		usuario.setDeuda(true);
		Tarjeta a = new Tarjeta();
		a = usuario.getTarjeta();
		ArrayList<Multa> b = new ArrayList<>();
		b = a.getMultas();
		b.add(null);
		a.setMultas(b);
		usuario.setTarjeta(a);
		System.out.println("Se ha multado al usuario: " + usuario.getNombre());
		*/
	}

	public void danioB(Usuario usuario, Bicicleta bicicleta/*, Distribuidor distribuidor*/) {
		this.multar(usuario, "danio");
		//distribuidor.arreglar(bicicleta);
		System.out.println("Se ha detectado un daño en la bicicleta " +  bicicleta.getId()
				+ " ,se ha multado al usuario "/* + usuario.getNombre()*/
				+ " y se ha solicitado arreglo de la bicileta con el distribuidor: "/* + distribuidor.getNombre()*/);
	}

	public Estacion getEstacion() {
		return estacionM;
	}
	public void setEstacion(Estacion estacion) {
		this.estacionM = estacion;
		estacion.setModerador(this);
	}
	public void prestar(Estacion estacion) {
		StringBuffer r;
		if ((this.getBicicleta() == null)) {
			if (estacion.getCantBicis() > 0  && estacion.isEstado()) {
				for (int i = 0; i < estacion.getCap_max(); i++) {
					System.out.print((i + 1) + ". " + estacion.getBicicletas()[i]);
					System.out.println(" ");
				}
				System.out.print("Elija una posición valida en la que halla una bicicleta: ");
				int idB = ent.nextInt() - 1;
				while (idB < 0 || idB > estacion.getCap_max()) {
					System.out.println(
							"Por favor ingrese un valor valido. Entre 1 y " + (estacion.getCap_max()) + ".");
					idB = ent.nextInt() - 1;
				}
				while (!estacion.prestar(idB, this)) {
					System.out.println("Prestamo no aceptado.");
					System.out.println("No Hay Bicicletas en la posicion elegida, ingrese una posicion valida.");
					idB = ent.nextInt() - 1;
					while (idB < 0 || idB > estacion.getCap_max()) {
						System.out.println(
								"Por favor ingrese un valor valido. Entre 1 y " + (estacion.getCap_max()) + ".");
						idB = ent.nextInt() - 1;
					}
				}
				r = new StringBuffer("Prestamo aceptado.");
				BaseDatos.Datos.hashUsoP.put(this.getId(), BaseDatos.Datos.hashUsoP.get(this.getId())+1);
				BaseDatos.Datos.hashUsoB.put(this.getBicicleta().getId(), BaseDatos.Datos.hashUsoB.get(this.getBicicleta().getId())+1);
				BaseDatos.Datos.hashUsoE.put(estacion.getIde(), BaseDatos.Datos.hashUsoE.get(estacion.getIde())+1);
				r.append("\n" + super.nombre + " posee la bicicleta " + this.getBicicleta().toString() /* +". Estaba en la estacion " + estacion.getId()+" posicion "+idB */);
				
			}  else if (!estacion.isEstado()) {
				r = new StringBuffer("Estacion cerrada.");
			} else {
				r = new StringBuffer("Estacion vacia.");
			}
		} else if (this.getBicicleta() != null) {
			r = new StringBuffer("Tiene un prestamo vigente");
		} else {
			r = new StringBuffer("Tiene una deuda pendiente");
		}

		System.out.println(r);
		
	}

	@Override
	public void finalize() {
		// TODO Auto-generated method stub
		
	}



}
