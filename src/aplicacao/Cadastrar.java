package aplicacao;

import requisito.Fachada;

public class Cadastrar {
    public Cadastrar() {
        Fachada.inicializar();
        try {
            System.out.println("Cadastrando tutores, pets e serviços...");
            Fachada.cadastrarTutor("11111111111", "José", "83990000001");
            Fachada.cadastrarTutor("22222222222", "Maria", "83990000002");
            Fachada.cadastrarTutor("33333333333", "Pedro", "83990000003");

            // Exemplo de como cadastrar pets e serviços agora
            Fachada.cadastrarPet("Rex", "Labrador", "11111111111");
            Fachada.adicionarServicoAoPet(1, "15/05/2025 08:00", "banho");
            Fachada.adicionarServicoAoPet(1, "15/05/2025 09:00", "tosa");
            
            Fachada.cadastrarPet("Mimi", "Persa", "22222222222");
            Fachada.adicionarServicoAoPet(2, "14/05/2025 08:30", "banho");
            Fachada.adicionarServicoAoPet(2, "15/05/2025 10:00", "vacina");
            
            Fachada.cadastrarPet("Miau", "Siames", "33333333333");
            Fachada.adicionarServicoAoPet(3, "15/05/2025 11:00", "hospedagem");
            Fachada.adicionarServicoAoPet(3, "15/05/2025 09:30", "banho");
            
            Fachada.cadastrarPet("Bolt", "Golden", "11111111111");
            Fachada.adicionarServicoAoPet(4, "15/05/2025 08:00", "consulta");

            Fachada.cadastrarPet("Luna", "Poodle", "22222222222");
            Fachada.adicionarServicoAoPet(5, "15/05/2025 09:30", "banho");
            
            Fachada.cadastrarPet("Thor", "Labrador", "11111111111");

            System.out.println("Cadastros realizados com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
        Fachada.finalizar();
        System.out.println("\nFim do programa!");
    }

    public static void main(String[] args) {
        new Cadastrar();
    }
}