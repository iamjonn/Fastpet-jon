package aplicacao;

import requisito.Fachada;

public class Consultar {
    public Consultar() {
        Fachada.inicializar();
        try {
            System.out.println("\n--- Pets da raça Labrador");
            Fachada.consultarPetsPorRaca("Labrador").forEach(System.out::println);

            System.out.println("\n--- Pets com mais de 1 serviço");
            Fachada.petsWithMoreThanNservices(1).forEach(System.out::println);

            System.out.println("\n--- Serviços do tipo banho no dia 15/05/2025");
            Fachada.servicesByTipoAndDate("banho", "15/05/2025").forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void main(String[] args) {
        new Consultar();
    }
}