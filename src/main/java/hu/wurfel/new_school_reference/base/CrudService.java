package hu.wurfel.new_school_reference.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CrudService <EntityType, RepositoryType extends JpaRepository<EntityType, Long>>{

    public RepositoryType repository;

    public CrudService(RepositoryType repository) {
        this.repository = repository;
    }

    public List<EntityType> findAll(){
        return repository.findAll();
    }

    private EntityType findById(Long id){
        return repository.findById(id).get();
    }

    public EntityType save(EntityType entity){
        return repository.save(entity);
    }

    public EntityType delete(EntityType entity){
        repository.delete(entity);
        return entity;
    }

    public EntityType deleteById(Long id){
        EntityType entity = repository.findById(id).get();
        repository.deleteById(id);
        return entity;
    }


}
