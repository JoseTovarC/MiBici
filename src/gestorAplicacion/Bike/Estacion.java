package gestorAplicacion.Bike;
import gestorAplicacion.User.*;
//import java.util.ArrayList;


public class Estacion {
	private String id;
	private String tipo;
	private boolean estado = true;
	private static boolean estadogeneral= true;
	private int cap_max;
	private Bicicleta[] bicicletas;
	private Moderador moderador;
	private byte cantBicis=0;
	
	public Estacion(String id, String tipo, boolean estado, int cap_max, Moderador moderador) {
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.cap_max = cap_max;
		this.bicicletas =  new Bicicleta[cap_max];
		this.moderador = moderador;
		BaseDatos.Datos.hashUsoE.put(id, 0);
	}
	
	public Estacion(String id, String tipo, boolean estado, int cap_max) {
		
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.cap_max = cap_max;
		this.bicicletas =  new Bicicleta[cap_max];
		BaseDatos.Datos.hashUsoE.put(id, 0);
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public boolean isEstado() {
		if(estadogeneral) {
			estado=true;
		}
		else {
			estado=false;
		}
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public int getCap_max() {
		return cap_max;
	}


	public void setCap_max(int cap_max) {
		this.cap_max = cap_max;
	}


	public Bicicleta[] getBicicletas() {
		return bicicletas;
	}


	public void setBicicletas(Bicicleta[] bicicletas) {
		this.bicicletas = bicicletas;
	}
	public void addBicicleta(Bicicleta bicicleta) {
		int i=0;
		System.out.println(bicicletas);
		while(i<cap_max-1) {
			if (bicicletas[i]==null) {
				bicicletas[i]=bicicleta;
				cantBicis++;
				System.out.println("Bicicleta agregada");
				break;
			}
		}
	}


	public Moderador getModerador() {
		return moderador;
	}


	public void setModerador(Moderador moderador) {
		this.moderador = moderador;
	}
	
	public byte getCantBicis() {
		return cantBicis;
	}

	public static void cerrar_estaciones() {
		estadogeneral = false;
	}
	public static void abrir_estaciones() {
		estadogeneral = true;
	}
	
	public boolean prestar(int idb, Usuario usuario){
		if((idb) <0 || (idb-1)>= cap_max || cantBicis==0 || !this.isEstado()) {
			return false;
		}
		else if(bicicletas[idb] == null) {
			return false;
		}
		else {
			usuario.setBicicleta(bicicletas[idb]);
			bicicletas[idb].setUsuario(usuario);
			bicicletas[idb] = null;
			for (int i = 0; i < bicicletas.length; i++) {
				System.out.print((i+1) + ". " + bicicletas[i]);
				System.out.println(" ");
			}
			cantBicis--;
            return true;
		}
	}
	
	public boolean recibir(Bicicleta bicicleta){
        if((tipo. equals("Automatica") && cantBicis==cap_max) || !this.isEstado()) {
        	return false;
        }
        else {
        	cantBicis++;
        	//ver nuevo método addBicicletas()
        	return true;
        }
		
	}
	
	public String toString() {
		return "Estacion \n [id = " + id + "\n Tipo = " + tipo+ "\n moderador=" + moderador + "\n Bicis= "+ cantBicis+"] \n";
	}
}
