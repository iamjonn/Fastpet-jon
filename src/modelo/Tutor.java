package modelo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tutor {

    @Id
    private String cpf;

    private String nome;
    private String telefone;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();

    public Tutor() {}

    public Tutor(String cpf, String nome, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public List<Pet> getPets() { return pets; }
    public void adicionarPet(Pet pet) {
        pets.add(pet);
        pet.setTutor(this);
    }

    public void removerPet(Pet pet) {
        pets.remove(pet);
        pet.setTutor(null);
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ")";
    }
}