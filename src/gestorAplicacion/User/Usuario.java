package gestorAplicacion.User;

import gestorAplicacion.Bike.*;
import uiMain.MenuDeConsola;
import uiMain.*;
import gestorAplicacion.Work.*;
import BaseDatos.*;

import java.util.*;

public class Usuario extends Persona {
    
	Scanner ent=new Scanner(System.in);
    
    private Tarjeta tarjeta;
    private Bicicleta bicicleta;
    private boolean deuda = false;
    private ArrayList<Multa> multas = new ArrayList<>();
    private MenuDeConsola menu;

    //Constructores
    public Usuario(String nombre, byte edad, long id, String genero, String clave, int saldo) {
    	super(nombre, edad, id, genero, clave);
        Tarjeta tarjeta = new Tarjeta(saldo, this);
        this.tarjeta = tarjeta;
        //Main.addUsuarios(this);
        //main.usuarioh.add(id, this);
        BaseDatos.Datos.hashPersona.put(id, this);
    }
    
    public Usuario(String nombre, byte edad, long id, String genero, String clave, int saldo, MenuDeConsola menu) {
    	super(nombre, edad, id, genero, clave);
        Tarjeta tarjeta = new Tarjeta(saldo, this);
        this.tarjeta = tarjeta;
        this.menu = menu;
        //Main.addUsuarios(this);
        //main.usuarioh.add(id, this);
        BaseDatos.Datos.hashPersona.put(id, this);
    }
    public Usuario(MenuDeConsola menu) {		
        super("",(byte) 0,(long) 0, "", "");
    	this.nombre = "";
        Tarjeta tarjeta = new Tarjeta(0, this);
        this.tarjeta = tarjeta;
        this.menu = menu;
        BaseDatos.Datos.hashPersona.put((long)0, this);
    }
    
    public static Usuario nuevoUsuarioInvitado(){
		ArrayList<OpcionDeMenu> OpcionesInvitado = new ArrayList<OpcionDeMenu>();			
                OpcionesInvitado.add(BaseDatos.Datos.operations.get("1"));
                OpcionesInvitado.add(BaseDatos.Datos.operations.get("2"));
                OpcionesInvitado.add(BaseDatos.Datos.operations.get("4"));

		
		MenuDeConsola InvitadoMenu = new MenuDeConsola(OpcionesInvitado);
		return new Usuario(InvitadoMenu);
   }
    public static String login(long id, String password){
        Usuario u = Usuario.getUsuarioPorUsername(id);
        if (u != null){
            if(u.getDoc_id() == id && u.getClave().equals(password)){
            	//Seteo el usuario
            	Main.user = u;
                return "Bienvenido";
            }
        }
        return "Usuario no encontrado";
    }
    public static Usuario getUsuarioPorUsername(long id){
        return (Usuario) Datos.hashPersona.get(id);
    }
    
    //Getters & Setters
    public Tarjeta getTarjeta() {return tarjeta;}
    public void setTarjeta(Tarjeta tarjeta) {this.tarjeta = tarjeta;}
        
    public Bicicleta getBicicleta() {return bicicleta;}
    public void setBicicleta(Bicicleta bicicleta) {this.bicicleta = bicicleta;}
    
    public boolean isDeuda() {return deuda;}
    public void setDeuda(boolean deuda) {this.deuda = deuda;}
    public int getDeuda() {
    	return multas.stream().mapToInt(o -> o.getPrecio()).sum();
    }
    
    public ArrayList<Multa> getMultas() {return multas;}
    public void setMultas(ArrayList<Multa> multas) {
    	this.multas = multas;
    	if(multas!=null) {
    		deuda=false;
    	}
    	else {
    		deuda=true;
    	}
    }
    public void addMultas(Multa multa) {
    	this.multas.add(multa);
    }
    public void delMultas() {
    	multas.clear();
    }
    public void delMultas(int id) {
    	multas.remove(id);
    }

    public MenuDeConsola getMenu() {
		return menu;
	}
    public void setMenu(MenuDeConsola menu) {
		this.menu = menu;
	}

    @Override
    public void finalize() {
        System.out.println("Se ha eliminado el usuario " + nombre);
        // borrar tarjeta
    }
    
    
    public void prestar(Estacion estacion) {
		StringBuffer r;
		Bicicleta[] bicis;
		if ((this.bicicleta==null) && !deuda) {
			if (estacion.getCantBicis()>0 && tarjeta.getSaldo()>=500 && estacion.isEstado()) {
				for (int i = 0; i < estacion.getCap_max(); i++) {
					System.out.print((i+1) + ". " + estacion.getBicicletas()[i]);
					System.out.println(" ");
				}
				int idB = ent.nextInt()-1;
				while(idB<0 || idB>=estacion.getCantBicis()) {
					System.out.println("Por favor ingrese un valor válido. Entre 1 y "+(estacion.getCantBicis())+".");
					idB=ent.nextInt()-1;
				}
				while(!estacion.prestar(idB, this)) {
					System.out.println("Prestamo no aceptado.");
					System.out.println("No Hay Bicicletas en la posición elegida, ingrese una posición válida.");
					idB=ent.nextInt()-1;
				}
				r= new StringBuffer("Prestamo aceptado.");
				this.setBicicleta(bicicleta);
				r.append("\n"+super.nombre + " posee la bicicleta " + this.bicicleta.toString() /*+". Estaba en la estación " + estacion.getId()+" posición "+idB*/);
				tarjeta.pagarP();
			}
			else if(tarjeta.getSaldo() < 500){
				r= new StringBuffer("Saldo Insuficiente.");
			}
			else if(!estacion.isEstado()) {
				r= new StringBuffer("Estación cerrada.");
			}
			else {
				r= new StringBuffer("Estación vacía.");
			}
		}
		else if(this.bicicleta != null) {
			r= new StringBuffer("Tiene un prestamo vigente");
		}
		else {
			r= new StringBuffer("Tiene una deuda pendiente");
		}

		//return r;
		System.out.println(r);
	}

    public StringBuffer devolver(Estacion estacion/*, Date initialtime*/) {
    	StringBuffer r;
    	if(bicicleta==null) {
    		r= new StringBuffer("Usted no tiene un préstamo actualmente.");
    	}
    	else if(!estacion.isEstado()) {
    		r= new StringBuffer("Estación cerrada.");
    	}
    	else if(estacion.getCantBicis()==estacion.getCap_max()) {
    		r= new StringBuffer("Estación llena.");
    	}
    	else{
            if(estacion.recibir(bicicleta)) {
            	r=new StringBuffer("Bicicleta devuelta.");
            	this.bicicleta= null;
            	/*
            	 * if (initialtime-bicicleta.time()>2 horas){
            	 * 		usuario.setMulta(tiempo);
             	 * 		r.append(" Ha sido multado por tiempo excedido");
             	 * }
             	*/
            }
            else {
            	r=new StringBuffer("Bicicleta no devuelta.");
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
        	if(tarjeta.pagarM()) {
        		return "Pago realizado.";
        	}
        	else {
        		return "Saldo insuficiente";
        	}
        }
        else {
            return "Usted no tiene multas o deudas actualmente.";
        }
    }
    public String toString() {
    	return "Nombre= "+super.getNombre()+". Id= "+super.getDoc_id();
    }

}