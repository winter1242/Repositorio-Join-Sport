package pe.edu.upc.joinsports.models.respositories;

import java.util.List;
import java.util.Optional;

public interface JpaRepository<T, ID> {
	T save(T entity) throws Exception;
	T update(T entity) throws Exception;
	void deleteById(ID id) throws Exception;
	Optional<T> findById(ID id) throws Exception;
	List<T> findAll() throws Exception;
}
