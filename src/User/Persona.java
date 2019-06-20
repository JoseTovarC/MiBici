package User;

public abstract class Persona {
	private String nombre;
	private byte edad;
	private long doc_id;
	private String genero;
	private long clave;
        public Persona(String nombre, byte edad, long id, String genero, long clave){
            this.nombre=nombre;
            this.edad=edad;
            doc_id=id;
            this.genero=genero;
            this.clave=clave;
        }
	public abstract void finalize(); //Abstract, obliga a Usuario y moderador definir destructor
}
