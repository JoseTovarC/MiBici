package User;
import java.util.ArrayList;

import Work.*;

public class Tarjeta {
	private int saldo;
	private Usuario usuario;
	private ArrayList<Multa> multas = new ArrayList<>();

	public Tarjeta() {}

	public Tarjeta(int saldo, Usuario usuario) {
		this.saldo = saldo;
		this.usuario = usuario;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Multa> getMultas() {
		return multas;
	}

	public void setMultas(ArrayList<Multa> multas) {
		this.multas = multas;
	}

	protected void finalize() {
		// viene desde el borrado de la persona, este método puede ser borrado
	}

	public void recargar(int q$) {
		saldo += q$;
	}

	public boolean pagarM() {
		/*
		 * if(multa>saldo){ return false } else{
		 */return true;
		// }

	}

	public boolean pagarP() {
		// if (valor_prestamo<=saldo){
		return true;
		/*
		 * } else{ return false }
		 */
	}

	public void recibirM(int idM) {
		// se hace la conexión de la tarjeta a la multa y se cambia el estado del
		// usuario de deuda false a true
	}
}
