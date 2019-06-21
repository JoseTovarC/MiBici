package Bike;
import User.*;
import java.util.ArrayList;


public class Estacion {
	private String id;
	private String tipo;
	private boolean estado = false;
	private static boolean estadogeneral= true;
	private int cap_max;
	private Bicicleta[] bicicletas;
	private Moderador moderadores;
	
	
	
	public Estacion(String id, String tipo, boolean estado, int cap_max, Moderador moderadores) {
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.cap_max = cap_max;
		this.bicicletas =  new Bicicleta[cap_max];
		this.moderadores = moderadores;
	}
	
	public Estacion(String id, String tipo, boolean estado, int cap_max) {
		this.id = id;
		this.tipo = tipo;
		this.estado = estado;
		this.cap_max = cap_max;
		this.bicicletas =  new Bicicleta[cap_max];		
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


	public Moderador getModeradores() {
		return moderadores;
	}


	public void setModeradores(Moderador moderadores) {
		this.moderadores = moderadores;
	}


	public static void cerrar_estaciones() {
		estadogeneral = false;
	}
	public static void abrir_estaciones() {
		estadogeneral = true;
	}	
	public boolean prestar(int idb, Usuario usuario){
		if((idb-1) <0) {
			return false;
		}else if((idb-1)>= cap_max){
				
				return false;
		
		}else if(bicicletas[idb-1] == null) {
			return false;
		}else {
			usuario.setBicicleta(bicicletas[idb-1]);
			bicicletas[idb-1] = null;
			for (int i = 0; i < bicicletas.length; i++) {
				System.out.print((i+1) + ". " + bicicletas[i]);
				System.out.println(" ");
			}
            return true;
		}
	}
	public void devolver(Bicicleta bicicleta){
            
	}
}
