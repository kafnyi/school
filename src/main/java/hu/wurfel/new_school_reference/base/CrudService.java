package hu.wurfel.new_school_reference.base;

import org.springframework.data.jpa.repository.JpaRepository;

public class CrudService <E, R extends JpaRepository<E, Long>>{
    R repository;

    private E save(E entity){
        return repository.save(entity);
    }

    private E delete(E entity){
        repository.delete(entity);
        return entity;
    }
}
