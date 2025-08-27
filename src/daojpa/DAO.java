package daojpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public abstract class DAO<T> implements InterfaceDAO<T> {
	protected static EntityManager manager;

	public static void open() {
		manager = Util.conectarBanco();
	}

	public static void close() {
		Util.fecharBanco();
	}

	public void create(T obj) {
		manager.persist(obj);
	}

	public abstract T read(Object chave);

	public T update(T obj) {
		return manager.merge(obj);
	}

	public void delete(T obj) {
		manager.remove(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		TypedQuery<T> query = manager.createQuery("select x from " + type.getSimpleName() + " x", type);
		return query.getResultList();
	}

	public static void begin() {
		if (!manager.getTransaction().isActive())
			manager.getTransaction().begin();
	}

	public static void commit() {
		if (manager.getTransaction().isActive()) {
			manager.getTransaction().commit();
			manager.clear();
		}
	}

	public static void rollback() {
		if (manager.getTransaction().isActive())
			manager.getTransaction().rollback();
	}
}