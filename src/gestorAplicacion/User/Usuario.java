package gestorAplicacion.User;

import gestorAplicacion.Bike.*;
import uiMain.*;
import gestorAplicacion.Work.*;
import BaseDatos.*;

import java.util.*;

public class Usuario extends Persona {

	Scanner ent = new Scanner(System.in);

	private Tarjeta tarjeta;
	private Bicicleta bicicleta;
	private boolean deuda = false;
	private ArrayList<Multa> multas = new ArrayList<>();

	// Constructores
	public Usuario() {
		super();
	}
	public Usuario(String nombre, byte edad, long id, String genero, String clave, int saldo) {
		super(nombre, edad, id, genero, clave);
		this.tarjeta= new Tarjeta(saldo, this);
		// Main.addUsuarios(this);
		// main.usuarioh.add(id, this);
		BaseDatos.Datos.hashPersona.put(id, this);
		BaseDatos.Datos.hashUsuario.put(id, this);
	}
	public Usuario(String nombre, byte edad, long id, String genero, String clave, int saldo,String def) {
		super(nombre, edad, id, genero, clave);
		this.tarjeta= new Tarjeta(saldo, this);
		// Main.addUsuarios(this);
		// main.usuarioh.add(id, this);
		BaseDatos.Datos.hashPersona.put(id, this);
	}
	
	public Usuario(String nombre, byte edad, long id, String genero, String clave) {
		super(nombre, edad, id, genero, clave);
		this.tarjeta= new Tarjeta(this);
		// Main.addUsuarios(this);
		// main.usuarioh.add(id, this);
		BaseDatos.Datos.hashPersona.put(id, this);
		BaseDatos.Datos.hashUsuario.put(id, this);
	}
	public Usuario(String nombre, String edad, String iden, String genero, String clave, String sal, ArrayList<Multa> multas) {
		super(nombre, edad, iden, genero, clave);
		int saldo = Integer.parseInt(sal);
		this.tarjeta= new Tarjeta(saldo, this);
		// Main.addUsuarios(this);
		// main.usuarioh.add(id, this);
		long id = (long)Integer.parseInt(iden);
		this.multas = multas;
		BaseDatos.Datos.hashPersona.put(id, this);
		BaseDatos.Datos.hashUsuario.put(id, this);
	}
	public Usuario(String nombre, String edad, String iden, String genero, String clave, String sal, ArrayList<Multa> multas, String ref) {
		super(nombre, edad, iden, genero, clave);
		int saldo = Integer.parseInt(sal);
		this.tarjeta= new Tarjeta(saldo, this);
		// Main.addUsuarios(this);
		// main.usuarioh.add(id, this);
		long id = (long)Integer.parseInt(iden);
		this.multas = multas;
		BaseDatos.Datos.hashPersona.put(id, this);
	}
	public Usuario(String nombre, byte edad, long id, String genero, String clave, int saldo, MenuDeConsola menu) {
		super(nombre, edad, id, genero, clave, menu);
		this.tarjeta= new Tarjeta(saldo, this);
		menu.setUser(this);
		BaseDatos.Datos.menus.put(id, menu);
		BaseDatos.Datos.hashPersona.put(id, this);
		BaseDatos.Datos.hashUsuario.put(id, this);
		
	}

	public Usuario(String nombre, String edad, String iden, String genero, String clave) {
		super(nombre, edad, iden, genero, clave);

		long id = (long)Integer.parseInt(iden);
		BaseDatos.Datos.hashPersona.put(id, this);
		BaseDatos.Datos.hashUsuario.put(id, this);
	}
	public Usuario(MenuDeConsola menu) {
		super("Invitado", (byte) 0, (long) 0, "", "", menu);
		this.nombre = "";
		this.tarjeta= new Tarjeta(this);
		menu.setUser(this);
		BaseDatos.Datos.menus.put((long)0, menu);
		BaseDatos.Datos.hashPersona.put((long) 0, this);
		BaseDatos.Datos.hashUsuario.put((long)0, this);
	}	

	public static Usuario nuevoUsuarioInvitado() {
		ArrayList<OpcionDeMenu> OpcionesInvitado = new ArrayList<OpcionDeMenu>();
		OpcionesInvitado.add(BaseDatos.Datos.operations.get("1"));
		OpcionesInvitado.add(BaseDatos.Datos.operations.get("2"));
		OpcionesInvitado.add(BaseDatos.Datos.operations.get("4"));

		MenuDeConsola InvitadoMenu = new MenuDeConsola(OpcionesInvitado);
		return new Usuario(InvitadoMenu);
	}

	public static String login(long id, String password) {
		Usuario u = Usuario.getUsuarioPorUsername(id);
		if (u != null) {
			if (u.getId() == id && u.getClave().equals(password)) {
				// Seteo el usuario
				Main.user = u;
				return "Bienvenido";
			}
		}
		return "Usuario no encontrado";
	}

	public static Usuario getUsuarioPorUsername(long id) {
		return (Usuario) Datos.hashPersona.get(id);
	}

	// Getters & Setters
	public Tarjeta getTarjeta() {return tarjeta;}

	public Bicicleta getBicicleta() {return bicicleta;}
	public void setBicicleta(Bicicleta bicicleta) {this.bicicleta = bicicleta;}

	public boolean isDeuda() {return deuda;}
	public void setDeuda(boolean deuda) {this.deuda = deuda;}
	public long getDeuda() { //se devuelve el precio de la deuda actual (todas las multas sumadas)
		long deuda = 0;
		for (Multa m : multas) {
			deuda += m.getPrecio();
		}
		return deuda;
	}

	public ArrayList<Multa> getMultas() {return multas;}
	public Multa getMulta(int id) {
		try {
			Multa a=multas.get(id);
			return a;
		}catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
		}
	public void setMultas(ArrayList<Multa> multas) { //cambiar todas las multas
		this.multas = multas;
		this.tarjeta.setMultas(multas);
		if (multas != null) {
			deuda = false;
		} else {
			deuda = true;
		}
	}
	public void addMulta(Multa multa) {//agregar una multa
		this.multas.add(multa);
		if(multa!=null) {
		deuda=true;}
		}
	public void delMultas() {multas.clear();}//borrar todas las multas
	public void delMulta(int id) {multas.remove(id);}//borrar una sola multa


	

	public void prestar(Estacion estacion) {
		StringBuffer r;
		if ((this.bicicleta == null) && !deuda) {
			if (estacion.getCantBicis() > 0 && tarjeta.getSaldo() >= 500 && estacion.isEstado()) {
				for (int i = 0; i < estacion.getCap_max(); i++) {
					System.out.print((i + 1) + ". " + estacion.getBicicletas()[i]);
					System.out.println(" ");
				}
				int idB = ent.nextInt() - 1;
				while (idB < 0 || idB >= estacion.getCantBicis()) {
					System.out.println(
							"Por favor ingrese un valor valido. Entre 1 y " + (estacion.getCantBicis()) + ".");
					idB = ent.nextInt() - 1;
				}
				while (!estacion.prestar(idB, this)) {
					System.out.println("Prestamo no aceptado.");
					System.out.println("No Hay Bicicletas en la posicion elegida, ingrese una posicion valida.");
					idB = ent.nextInt() - 1;
				}
				r = new StringBuffer("Prestamo aceptado.");
				this.setBicicleta(bicicleta);
				r.append("\n" + super.nombre + " posee la bicicleta " + this.bicicleta.toString() /* +". Estaba en la estacion " + estacion.getId()+" posicion "+idB */);
				tarjeta.pagarP();
			} else if (tarjeta.getSaldo() < 500) {
				r = new StringBuffer("Saldo Insuficiente.");
			} else if (!estacion.isEstado()) {
				r = new StringBuffer("Estacion cerrada.");
			} else {
				r = new StringBuffer("Estacion vacia.");
			}
		} else if (this.bicicleta != null) {
			r = new StringBuffer("Tiene un prestamo vigente");
		} else {
			r = new StringBuffer("Tiene una deuda pendiente");
		}

		// return r;
		System.out.println(r);
	}

	public StringBuffer devolver(Estacion estacion/* , Date initialtime */) {
		StringBuffer r;
		if (bicicleta == null) {
			r = new StringBuffer("Usted no tiene un prestamo actualmente.");
		} else if (!estacion.isEstado()) {
			r = new StringBuffer("Estacion cerrada.");
		} else if (estacion.getCantBicis() == estacion.getCap_max()) {
			r = new StringBuffer("Estacion llena.");
		} else {
			if (estacion.recibir(bicicleta)) {
				r = new StringBuffer("Bicicleta devuelta.");
				this.bicicleta = null;
				/*
				 * if (initialtime-bicicleta.time()>2 horas){ usuario.setMulta(tiempo);
				 * r.append(" Ha sido multado por tiempo excedido"); }
				 */
			} else {
				r = new StringBuffer("Bicicleta no devuelta.");
			}
		}
		return r;
	}

	public String recargarT(int q$) {
		tarjeta.recargar(q$);
		return "Recarga de: $" + q$ + " realizada correctamente.";
	}

	public String pagarM() {
		if (deuda) {
			if (tarjeta.pagarM()) {
				return "Pago realizado.";
			} else {
				return "Saldo insuficiente";
			}
		} else {
			return "Usted no tiene multas o deudas actualmente.";
		}
	}
	public String pagarM(int id) {
		if (deuda){
			if (tarjeta.pagarM(id)) {
				return "Se ha pagado la multa";
			}
			else {
				return "Saldo insuficiente";
			}
		}
		else {
			return "Usted no tiene multas o deudas actualmente";
		}
	}

	public String toString() {
		return "Nombre= " + super.getNombre() + ". Id= " + super.getId();
	}
	
	@Override
	public void finalize() {
		System.out.println("Se ha eliminado el usuario " + nombre);
		BaseDatos.Datos.hashPersona.remove(super.getId());
	}
}