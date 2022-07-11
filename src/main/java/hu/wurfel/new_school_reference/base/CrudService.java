package hu.wurfel.new_school_reference.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class CrudService<
        AUDITABLE extends Auditable,
        REPO extends JpaRepository<AUDITABLE, Long>
        > {

    protected REPO repo;

    public CrudService(REPO repo) {
        this.repo = repo;
    }

    public List<AUDITABLE> findAll() {
        return repo.findAll();
    }

    public AUDITABLE findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public AUDITABLE save(AUDITABLE entity) {
        return repo.save(entity);
    }

    public void delete(AUDITABLE entity) {
        this.deleteById(entity.getId());
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }


}
