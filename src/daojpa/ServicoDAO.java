package daojpa;

import java.util.List;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Servico;

public class ServicoDAO extends DAO<Servico> {
    public Servico read(Object chave) {
        try {
            int id = (int) chave;
            TypedQuery<Servico> q = manager.createQuery("select s from Servico s where s.id = :n", Servico.class);
            q.setParameter("n", id);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Consulta JPQL 3: Serviços por tipo e data
    public List<Servico> servicesByTipoAndDate(String tipo, String data) {
        TypedQuery<Servico> q = manager.createQuery("select s from Servico s where s.tipo = :tipo and s.datahora like :data", Servico.class);
        q.setParameter("tipo", tipo);
        q.setParameter("data", data + "%");
        return q.getResultList();
    }
}