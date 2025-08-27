package modelo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String apelido;
    private String raca;
    private byte[] foto; // NOVO ATRIBUTO

    @ManyToOne
    private Tutor tutor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pet_id")
    private List<Servico> servicos = new ArrayList<>();

    public Pet() {}

    public Pet(String apelido, Tutor tutor, String raca) {
        this.apelido = apelido;
        this.tutor = tutor;
        this.raca = raca;
        this.foto = new byte[0];
    }

    public int getId() { return id; }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public Tutor getTutor() { return tutor; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }

    public List<Servico> getServicos() { return servicos; }
    public void adicionarServico(Servico s) { servicos.add(s); }
    public void removerServico(Servico s) { servicos.remove(s); }
    public int getQuantidadeServicos() { return servicos.size(); }

    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }

    @Override
    public String toString() {
        return apelido + " (" + raca + ") - Tutor: " + (tutor != null ? tutor.getNome() : "Sem tutor") +
                ", Serviços: " + getQuantidadeServicos();
    }
}