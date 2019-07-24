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
		BaseDatos.Datos.hashEstacion.put(id, this);
	}
	
	public Estacion(String id, String tipo, boolean estado, int cap_max) {
		
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.cap_max = cap_max;
		this.bicicletas =  new Bicicleta[cap_max];
		BaseDatos.Datos.hashUsoE.put(id, 0);
		BaseDatos.Datos.hashEstacion.put(id, this);
	}
	
	public Estacion(String id, String tipo, String esta, String cap_max) {
		this.id = id;
		this.tipo = tipo;
		boolean estado = Boolean.parseBoolean(esta);
		this.estado = estado;
		int aux = Integer.parseInt(cap_max);
		this.cap_max = aux;		
		this.bicicletas =  new Bicicleta[aux];
		BaseDatos.Datos.hashUsoE.put(id, 0);
		BaseDatos.Datos.hashEstacion.put(id, this);
	}
	
	public static Estacion getEstacionporId(String id) {
		
		return BaseDatos.Datos.hashEstacion.get(id);
	}
	
	public String getIde() {
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
		
			while(i<cap_max) {
				if (bicicletas[i]==null) {
					bicicletas[i]=bicicleta;
					cantBicis++;
					
					break;
				}
				i++;
			}
		
		
	}


	public Moderador getModerador() {
		return moderador;
	}


	public void setModerador(Moderador moderador) {
		this.moderador = moderador;
	}
	
	public byte getCantBicis() {
		int i=0;
		
		for(int y=0;y<bicicletas.length;y++) {
			if (bicicletas[i]!=null) {
				i++;
			}
	
		}
		this.cantBicis=(byte)i;
	
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
			bicicletas[idb].setEstacion(null);
			bicicletas[idb] = null;
			
			cantBicis--;
            return true;
		}
	}
	
	public boolean recibir(Bicicleta bicicleta){
        if((this.getCantBicis()==cap_max) || !this.isEstado()) {
        	return false;
        }
        else {
        	this.addBicicleta(bicicleta);
        	
        	System.out.println("Bicicleta agregada");
        	return true;
        }
		
	}
	
	public String toString() {
		return "Estacion \n [id = " + id + "\n Tipo = " + tipo+ "\n moderador=" + moderador + "\n Bicis= "+ cantBicis+"] \n";
	}
}
