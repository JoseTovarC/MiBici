package gestorAplicacion.User;

import java.util.ArrayList;
import gestorAplicacion.Work.*;

public class Tarjeta {
	private int saldo;
	private Usuario usuario;
	private ArrayList<Multa> multas = new ArrayList<>();

	//Constructores
	public Tarjeta(int saldo, Usuario usuario) {
		this.saldo = saldo;
		this.usuario = usuario;
	}
	public Tarjeta(Usuario usuario) {
		this(0, usuario);
	}

	//Getters & Setters
	public int getSaldo() {return saldo;}
	public void setSaldo(int saldo) {this.saldo = saldo;}

	public Usuario getUsuario() {return usuario;}
	public void setUsuario(Usuario usuario) {this.usuario = usuario;}

	public ArrayList<Multa> getMultas() {return multas;}
	public void setMultas(ArrayList<Multa> multas) {this.multas = multas;}

	protected void finalize() {}

	public void recargar(int q$) {
		saldo += q$;
	}

	public boolean pagarM() {
		if(usuario.isDeuda()) {
			if(usuario.getDeuda() <= saldo) {
				return true;
			}
		}
		return false;
	}

	public boolean pagarP() {
		if (500<=saldo){
			saldo-=500;
			return true;
		}
		return false;
	}

	public void recibirM(int idM) {
		// se hace la conexión de la tarjeta a la multa y se cambia el estado del
		// usuario de deuda false a true
	}
}
