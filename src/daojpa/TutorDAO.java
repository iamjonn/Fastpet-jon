package daojpa;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Tutor;

public class TutorDAO extends DAO<Tutor> {
    public Tutor read(Object chave) {
        try {
            String cpf = (String) chave;
            TypedQuery<Tutor> q = manager.createQuery("select t from Tutor t where t.cpf = :n", Tutor.class);
            q.setParameter("n", cpf);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}