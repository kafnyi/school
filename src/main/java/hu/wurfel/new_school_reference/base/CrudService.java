package hu.wurfel.new_school_reference.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class CrudService <E, R extends JpaRepository<E, Long>>{
    protected R repository;

    public CrudService(R repository) {
        this.repository = repository;
    }

    public E save(E entity){
        return repository.save(entity);
    }

    public E delete(E entity){
        repository.delete(entity);
        return entity;
    }
}
