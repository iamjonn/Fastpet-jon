package aplicacao;

import requisito.Fachada;

public class Deletar {
    public Deletar() {
        Fachada.inicializar();
        try {
            // Supondo que o ID do pet 'Mimi' seja 2
            int idMimi = 2;
            Fachada.excluirPet(idMimi);
            System.out.println("Pet 'Mimi' e seus serviços foram apagados.");
        } catch (Exception e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void main(String[] args) {
        new Deletar();
    }
}