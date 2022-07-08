package hu.wurfel.new_school_reference.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CrudService<
        ENTITY_TYPE extends Auditable,
        REPO extends JpaRepository<ENTITY_TYPE, Long>
        > {

    protected REPO repo;

    public CrudService(REPO repo) {
        this.repo = repo;
    }

    public List<ENTITY_TYPE> findAll() {
        return repo.findAll();
    }

    public ENTITY_TYPE findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public ENTITY_TYPE save(ENTITY_TYPE entity) {
        return repo.save(entity);
    }

    public void delete(ENTITY_TYPE entity) {
        this.deleteById(entity.getId());
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }


}
