package modelo;

import jakarta.persistence.*;

@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String datahora;
    private String tipo;

    public Servico() {}

    public Servico(String datahora, String tipo) {
        this.datahora = datahora;
        this.tipo = tipo;
    }

    public int getId() { return id; }
    public String getDatahora() { return datahora; }
    public void setDatahora(String datahora) { this.datahora = datahora; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return tipo + " em " + datahora;
    }
}