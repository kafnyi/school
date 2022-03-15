package hu.wurfel.refference.school.controll;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CrudRepository <T, ID> extends Repository<T,ID> {
	<S extends T> S save(S entity);
	Optional<T> FindByScid(ID primaryKey);
	Iterable<T> FindAll();
	long count();
	void delete(T entity);
	boolean existsById(ID primaryKey);

}
