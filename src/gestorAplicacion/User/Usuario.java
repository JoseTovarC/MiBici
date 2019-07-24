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
		BaseDatos.Datos.hashUsoP.put(id, 0);
	}
	public Usuario(String nombre, byte edad, long id, String genero, String clave, int saldo, MenuDeConsola menu, String def) {
		super(nombre, edad, id, genero, clave);
		this.tarjeta= new Tarjeta(saldo, this);
		// Main.addUsuarios(this);
		// main.usuarioh.add(id, this);
		menu.setUser(this);
		BaseDatos.Datos.menus.put(id, menu);
		BaseDatos.Datos.hashPersona.put(id, this);
		BaseDatos.Datos.hashUsoP.put(id, 0);
	}
	
	public Usuario(String nombre, byte edad, long id, String genero, String clave) {
		super(nombre, edad, id, genero, clave);
		this.tarjeta= new Tarjeta(this);
		// Main.addUsuarios(this);
		// main.usuarioh.add(id, this);
		BaseDatos.Datos.hashPersona.put(id, this);
		BaseDatos.Datos.hashUsuario.put(id, this);
		BaseDatos.Datos.hashUsoP.put(id, 0);
	}
	public Usuario(String nombre, String edad, String iden, String genero, String clave, String sal) {
		super(nombre, edad, iden, genero, clave);
		int saldo = Integer.parseInt(sal);
		this.tarjeta= new Tarjeta(saldo, this);
		// Main.addUsuarios(this);
		// main.usuarioh.add(id, this);
		long id = (long)Integer.parseInt(iden);
		BaseDatos.Datos.hashPersona.put(id, this);
		BaseDatos.Datos.hashUsuario.put(id, this);
		BaseDatos.Datos.hashUsoP.put(id, 0); 
	}
	public Usuario(String nombre, String edad, String iden, String genero, String clave, String sal, String ref) { //Constructor moderador
		super(nombre, edad, iden, genero, clave);
		int saldo = Integer.parseInt(sal);
		this.tarjeta= new Tarjeta(saldo, this);
		// Main.addUsuarios(this);
		// main.usuarioh.add(id, this);
		long id = (long)Integer.parseInt(iden);
		BaseDatos.Datos.hashPersona.put(id, this);
		BaseDatos.Datos.hashUsoP.put(id, 0);
	}
	public Usuario(String nombre, byte edad, long id, String genero, String clave, int saldo, MenuDeConsola menu) {
		super(nombre, edad, id, genero, clave, menu);
		this.tarjeta= new Tarjeta(saldo, this);
		menu.setUser(this);
		BaseDatos.Datos.menus.put(id, menu);
		BaseDatos.Datos.hashPersona.put(id, this);
		BaseDatos.Datos.hashUsuario.put(id, this);
		BaseDatos.Datos.hashUsoP.put(id, 0);
		
	}

	
	public Usuario(MenuDeConsola menu) {
		super("Invitado", (byte) 0, (long) 0, "", "", menu);
		this.nombre = "";
		this.tarjeta= new Tarjeta(this);
		menu.setUser(this);
		BaseDatos.Datos.menus.put((long)0, menu);
		BaseDatos.Datos.hashPersona.put((long) 0, this);
		BaseDatos.Datos.hashUsuario.put((long)0, this);
		BaseDatos.Datos.hashUsoP.put(this.getId(), 0);
	}	

	public static Usuario nuevoUsuarioInvitado() {
		ArrayList<OpcionDeMenu> OpcionesInvitado = new ArrayList<OpcionDeMenu>();
		OpcionesInvitado.add(BaseDatos.Datos.operations.get("1")); //Iniciar Sesion
		OpcionesInvitado.add(BaseDatos.Datos.operations.get("2")); //Registrarse
		OpcionesInvitado.add(BaseDatos.Datos.operations.get("4")); //Salir

		MenuDeConsola InvitadoMenu = new MenuDeConsola(OpcionesInvitado);
		return new Usuario(InvitadoMenu);
	}

	

	// Getters & Setters
	public Tarjeta getTarjeta() {return tarjeta;}

	public Bicicleta getBicicleta() {return bicicleta;}
	public void setBicicleta(Bicicleta bicicleta) {this.bicicleta = bicicleta;}

	public boolean isDeuda() { //se chequea si hay una deuda (si es que hay multas), cambia el atributo "deuda" y ademas retorna true si se tienen multas o false en caso contrario
		if(multas.size() == 0) {
			this.deuda = false;
		}else if(multas == null) {
			this.deuda = false;
		}else {
			this.deuda = true;
		}
		return deuda;
	}
	public void setDeuda(boolean deuda) {this.deuda = deuda;} //se puede modificar la deuda manualmente
	public long getDeuda() { //se devuelve el precio de la deuda actual (todas las multas sumadas) y se cambia el atributo booleano "deuda"
		long deuda = 0;
		for (Multa m : multas) {
			deuda += m.getPrecio();
		}
		if (multas == null) {
			this.deuda = false;
		} else {
			this.deuda = true;
		}
		return deuda;
	}

	public ArrayList<Multa> getMultas() {return multas;} //devuelve todas las multas
	public Multa getMulta(int id) { //devuelve la multa en cierta posicion
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
		if (multas == null) {
			deuda = false;
		} else {
			deuda = true;
		}
	}
	public void addMulta(Multa multa) {//agregar una multa
		if (BaseDatos.Datos.hashCantM.containsKey(this.getId())) {
			BaseDatos.Datos.hashCantM.put(this.getId(),BaseDatos.Datos.hashCantM.get(this.getId())+1);
		}
		else {
			BaseDatos.Datos.hashCantM.put(this.getId(), 1);
		}
		this.multas.add(multa);
		if (multas == null) {
			deuda = false;
		} else {
			deuda = true;
		}
	}
	public void delMultas() { //borrar todas las multas
		multas.clear();
		deuda = false;
	}
	public void delMulta(int id) { //borrar una sola multa
		multas.remove(id);
		if (multas == null) {
			deuda = false;
		} else {
			deuda = true;
		}
	}
	
	//FUNCIONALIDADES/METODOS DEL USUARIO
	public void prestar(Estacion estacion) {
//Se le presta una bicicleta al usuario, primero se ingresa una estacion y se chequea todo (que no tenga un prestamo ni deudas actualmente, que la estacion tenga bicicletas y estÃ© abierta, que el usuario tenga saldo suficiente para prestar una bicicleta)
//Luego se imprime por pantalla las bicicletas que se encuentran en dicha estacion y se le pide al usuario que ingrese un numero para elegir la bicicleta
//Despues, se confirma que este numero sea un valor valido para prestar una bicicleta.
//Al final, se llama a la estacion para que haga el prestamo de la bicicleta, se anexa la bicicleta al usuario, se le disminuye el saldo a su tarjeta por el prestamo y se devuelve un mensaje diciendo los datos de la persona que ha prestado la bicicleta y los datos de la bicicleta
		StringBuffer r = new StringBuffer("Prestamo no aceptado. ");
		if ((this.bicicleta == null) && !this.isDeuda()) {
			if (estacion.getCantBicis() > 0 && tarjeta.getSaldo() >= 500 && estacion.isEstado()) {
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
					System.out.println("Prestamo no aceptado. ");
					System.out.println("No Hay Bicicletas en la posicion elegida, ingrese una posicion valida.");
					idB = ent.nextInt() - 1;
					while (idB < 0 || idB > estacion.getCap_max()) {
						System.out.println(
								"Por favor ingrese un valor valido. Entre 1 y " + (estacion.getCap_max()) + ".");
						idB = ent.nextInt() - 1;
					}
				}
				r = new StringBuffer("Prestamo aceptado. ");
				BaseDatos.Datos.hashUsoP.put(this.getId(), BaseDatos.Datos.hashUsoP.get(this.getId())+1);
				this.setBicicleta(bicicleta);
				BaseDatos.Datos.hashUsoB.put(bicicleta.getId(), BaseDatos.Datos.hashUsoB.get(bicicleta.getId())+1);
				BaseDatos.Datos.hashUsoE.put(estacion.getIde(), BaseDatos.Datos.hashUsoE.get(estacion.getIde())+1);
				r.append("\n" + super.nombre + " posee la bicicleta " + this.bicicleta.toString() /* +". Estaba en la estacion " + estacion.getId()+" posicion "+idB */);
				tarjeta.pagarP();
			} else if (tarjeta.getSaldo() < 500) {
				r.append("\n Saldo Insuficiente.");
			} else if (!estacion.isEstado()) {
				r.append("\n Estacion cerrada.");
			} else {
				r.append("\n Estacion vacia.");
			}
		} else if (this.bicicleta != null) {
			r.append("\n Tiene un prestamo vigente");
		} else {
			r.append("\n Tiene una deuda pendiente");
		}
		System.out.println(r);
	}

	public StringBuffer devolver(Estacion estacion/* , Date initialtime */) {
//Se devuelve la bicicleta anexada del usuario a la estacion que se ha ingresado.
//Se revisa si si se tiene un prestamo, si la estacion esta abierta y tiene cupos disponibles. De lo contrario le comenta al usuario si no se puede devolver la bicicleta y dice por que.
//En caso contrario, si todo esta en orden se le notifica al usuario, se hace la devolucion a la estacion y se desconecta del usuario. 

		StringBuffer r = new StringBuffer("Bicicleta no devuelta.");
		if (bicicleta == null) {
			r.append("\n Usted no tiene un prestamo actualmente.");
		} else if (!estacion.isEstado()) {
			r.append("\n Estacion cerrada.");
		} else if (estacion.getCantBicis() == estacion.getCap_max()) {
			r.append("\n Estacion llena.");
		} else {
			if (estacion.recibir(bicicleta)) {
				r = new StringBuffer("Bicicleta devuelta.");
				this.bicicleta.setUsuario(null);
				this.bicicleta = null;

			}
			else{
				r.append("\n Error desconocido");			
			}
		}
		return r;
	}

	public String recargarT(int q$) { //Se recarga la tarjeta del usuario con el saldo que se le indique
		tarjeta.recargar(q$);
		return "Recarga de: $" + q$ + " realizada correctamente.";
	}

	public String pagarM() { //El usuario paga todas las multas, se revisa si tiene saldo suficiente en su tarjeta y se devuelve el correspondiente mensaje
		if (this.isDeuda()) {
			if (tarjeta.pagarM()) {
				return "Pago realizado.";
			} else {
				return "Pago no realizado. \n Saldo insuficiente";
			}
		} else {
			return "Pago no realizado. \n Usted no tiene multas o deudas actualmente.";
		}
	}
	
	public String pagarM(String id){ //El usuario paga una multa, se revisa si tiene saldo suficiente en su tarjeta y se devuelve el correspondiente mensaje
		if (this.isDeuda()) {
			if (tarjeta.pagarM(id)) {
				return "Pago realizado.";
			} else {
				return "Pago no realizado. \n Saldo insuficiente";
			}
		} else {
			return "Pago no realizado. \n Usted no tiene multas o deudas actualmente.";
		}
	}
	
	public String toString() { //Mensaje que se devuelve al imprimir un usuario
		return "Nombre= " + super.getNombre() + ". Id= " + super.getId();
	}
	
	@Override
	public void finalize() { //Mensaje que se devuelve al eliminar un usuario
		System.out.println("Se ha eliminado el usuario " + nombre);
		BaseDatos.Datos.hashPersona.remove(super.getId());
	}
}
