package User;

public class Tarjeta{
	  private int saldo;
	  private Usuario usuario;
	  //private multa multas

	  public void tarjeta(){
	    //este viene desde el constructor de usuario
	  }
	  protected void finalize(){
	    //viene desde el borrado de la persona, este método puede ser borrado
	  }
	  public void recargar(int q$){
	    saldo+=q$;
	  }
	  public boolean pagarM(){
	    /*
	    if(multa>saldo){
	      return false
	    }
	    else{
	      */return true;
	    //}
	    
	  }
	  public boolean pagarP(){
	    //if (valor_prestamo<=saldo){
	    return true;
	    /*}
	    else{
	      return false
	    }
	    */
	  }
	  public void recibirM(int idM){
	    //se hace la conexión de la tarjeta a la multa y se cambia el estado del usuario de deuda false a true
	  }
	}
