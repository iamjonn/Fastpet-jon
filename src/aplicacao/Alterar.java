package aplicacao;

import requisito.Fachada;

public class Alterar {
    public Alterar() {
        Fachada.inicializar();
        try {
            System.out.println("Alterando: Pet 'Rex'");
            // Supondo que você sabe o ID do pet 'Rex'
            int idRex = 1;

            Fachada.alterarApelidoPet(idRex, "Rexão");
            System.out.println("Apelido alterado para: Rexão");
            
            try {
                Fachada.removerUltimoServico(idRex);
                System.out.println("Último serviço de Rex removido.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            Fachada.adicionarServicoAoPet(idRex, "20/06/2025 10:00", "vacina");
            System.out.println("Novo serviço adicionado.");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        Fachada.finalizar();
    }

    public static void main(String[] args) {
        new Alterar();
    }
}