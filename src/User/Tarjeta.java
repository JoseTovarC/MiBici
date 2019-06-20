package User;

public class Tarjeta{
	  int saldo;
	  Usuario usuario;
	  //multa multas

	  void tarjeta(){
	    //este viene desde el constructor de usuario
	  }
	  protected void finalize(){
	    //viene desde el borrado de la persona, este método puede ser borrado
	  }
	  void recargar(int q$){
	    saldo+=q$;
	  }
	  boolean pagarM(){
	    /*
	    if(multa>saldo){
	      return false
	    }
	    else{
	      */return true;
	    //}
	    
	  }
	  boolean pagarP(){
	    //if (valor_prestamo<=saldo){
	    return true;
	    /*}
	    else{
	      return false
	    }
	    */
	  }
	  void recibirM(int idM){
	    //se hace la conexión de la tarjeta a la multa y se cambia el estado del usuario de deuda false a true
	  }
	}
