package daojpa;

import java.util.List;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Pet;

public class PetDAO extends DAO<Pet> {
    public Pet read(Object chave) {
        try {
            int id = (int) chave;
            TypedQuery<Pet> q = manager.createQuery("select p from Pet p where p.id = :n", Pet.class);
            q.setParameter("n", id);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Consulta JPQL 1: Pets da raça X
    public List<Pet> petsByRaca(String raca) {
        TypedQuery<Pet> q = manager.createQuery("select p from Pet p where p.raca = :raca", Pet.class);
        q.setParameter("raca", raca);
        return q.getResultList();
    }

    // Consulta JPQL 2: Pets com mais de N serviços
    public List<Pet> petsWithMoreThanNservices(int n) {
        TypedQuery<Pet> q = manager.createQuery("select p from Pet p where size(p.servicos) > :n", Pet.class);
        q.setParameter("n", n);
        return q.getResultList();
    }
}