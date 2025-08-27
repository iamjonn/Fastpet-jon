package requisito;

import java.util.List;

import daojpa.DAO;
import daojpa.PetDAO;
import daojpa.ServicoDAO;
import daojpa.TutorDAO;
import modelo.Pet;
import modelo.Servico;
import modelo.Tutor;

public class Fachada {
    private Fachada() {}

    private static PetDAO petDAO = new PetDAO();
    private static TutorDAO tutorDAO = new TutorDAO();
    private static ServicoDAO servicoDAO = new ServicoDAO();

    public static void inicializar() {
        DAO.open();
    }
    public static void finalizar() {
        DAO.close();
    }

    // --- MÉTODOS DE CADASTRO ---
    public static void cadastrarTutor(String cpf, String nome, String telefone) throws Exception {
        DAO.begin();
        Tutor t = tutorDAO.read(cpf);
        if (t != null) {
            DAO.rollback();
            throw new Exception("Tutor ja cadastrado: " + cpf);
        }
        tutorDAO.create(new Tutor(cpf, nome, telefone));
        DAO.commit();
    }

    public static void cadastrarPet(String apelido, String raca, String cpfTutor) throws Exception {
        DAO.begin();
        Tutor tutor = tutorDAO.read(cpfTutor);
        if (tutor == null) {
            DAO.rollback();
            throw new Exception("Tutor nao encontrado: " + cpfTutor);
        }
        Pet pet = new Pet(apelido, tutor, raca);
        tutor.adicionarPet(pet);
        petDAO.create(pet);
        DAO.commit();
    }

    public static void adicionarServicoAoPet(int idPet, String datahora, String tipo) throws Exception {
        DAO.begin();
        Pet pet = petDAO.read(idPet);
        if (pet == null) {
            DAO.rollback();
            throw new Exception("Pet nao encontrado: " + idPet);
        }
        Servico servico = new Servico(datahora, tipo);
        pet.adicionarServico(servico);
        servicoDAO.create(servico);
        DAO.commit();
    }

    // --- MÉTODOS DE LISTAGEM ---
    public static List<Tutor> listarTutores() {
        return tutorDAO.readAll();
    }
    public static List<Pet> listarPets() {
        return petDAO.readAll();
    }
    public static List<Servico> listarServicos() {
        return servicoDAO.readAll();
    }

    // --- MÉTODOS DE ALTERAÇÃO ---
    public static void alterarApelidoPet(int id, String novoApelido) throws Exception {
        DAO.begin();
        Pet pet = petDAO.read(id);
        if (pet == null) {
            DAO.rollback();
            throw new Exception("Pet nao encontrado: " + id);
        }
        pet.setApelido(novoApelido);
        petDAO.update(pet);
        DAO.commit();
    }

    public static void removerUltimoServico(int idPet) throws Exception {
        DAO.begin();
        Pet pet = petDAO.read(idPet);
        if (pet == null) {
            DAO.rollback();
            throw new Exception("Pet nao encontrado: " + idPet);
        }
        if (pet.getServicos().isEmpty()) {
            DAO.rollback();
            throw new Exception("Pet nao possui servicos para remover.");
        }
        Servico removido = pet.getServicos().getLast();
        pet.removerServico(removido);
        servicoDAO.delete(removido);
        DAO.commit();
    }

    // --- MÉTODOS DE EXCLUSÃO ---
    public static void excluirPet(int id) throws Exception {
        DAO.begin();
        Pet pet = petDAO.read(id);
        if (pet == null) {
            DAO.rollback();
            throw new Exception("Pet nao encontrado: " + id);
        }
        // Os servicos são apagados automaticamente por causa do orphanRemoval
        Tutor tutor = pet.getTutor();
        if (tutor != null) {
            tutor.removerPet(pet);
        }
        petDAO.delete(pet);
        DAO.commit();
    }

    // --- MÉTODOS DE CONSULTA JPQL ---
    public static List<Pet> consultarPetsPorRaca(String raca) {
        return petDAO.petsByRaca(raca);
    }
    public static List<Pet> petsWithMoreThanNservices(int n) {
        return petDAO.petsWithMoreThanNservices(n);
    }
    public static List<Servico> servicesByTipoAndDate(String tipo, String data) {
        return servicoDAO.servicesByTipoAndDate(tipo, data);
    }
}