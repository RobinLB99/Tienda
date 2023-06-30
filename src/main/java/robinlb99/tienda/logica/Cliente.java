package robinlb99.tienda.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String consumidorFinal;

    public Cliente() {
    }
    
    public Cliente(String consumidorFinal, long id, String cedula, String nombres, String apellidos) {
        super(cedula, nombres, apellidos);
        this.id = id;
        this.consumidorFinal = consumidorFinal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConsumidorFinal() {
        return consumidorFinal;
    }

    public void setConsumidorFinal(String consumidorFinal) {
        this.consumidorFinal = consumidorFinal;
    }    

    @Override
    public String toString() {
        return "Cliente{" + "consumidorFinal=" + consumidorFinal + '}';
    }
    
    
    
}
