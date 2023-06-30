package robinlb99.tienda.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nombres;
    private String apellidos;
    private String consumidorFinal;
    private String CI;

    public Cliente() {
    }

    public Cliente(long id, String nombres, String apellidos, String consumidorFinal, String CI) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.consumidorFinal = consumidorFinal;
        this.CI = CI;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getConsumidorFinal() {
        return consumidorFinal;
    }

    public void setConsumidorFinal(String consumidorFinal) {
        this.consumidorFinal = consumidorFinal;
    }    

    public String getCI() {
        return CI;
    }

    public void setCI(String CI) {
        this.CI = CI;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + 
                ", nombres=" + nombres + 
                ", apellidos=" + apellidos + 
                ", consumidorFinal=" + consumidorFinal + 
                ", CI=" + CI + '}';
    }
    
}
