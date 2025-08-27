package aplicacao;

import requisito.Fachada;
import modelo.Pet;
import modelo.Servico;
import modelo.Tutor;
import java.util.List;

public class Listar {
    public Listar() {
        Fachada.inicializar();
        try {
            System.out.println("\n--- Tutores ---");
            List<Tutor> tutores = Fachada.listarTutores();
            tutores.forEach(System.out::println);

            System.out.println("\n--- Pets ---");
            List<Pet> pets = Fachada.listarPets();
            pets.forEach(System.out::println);

            System.out.println("\n--- Serviços ---");
            List<Servico> servicos = Fachada.listarServicos();
            servicos.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void main(String[] args) {
        new Listar();
    }
}