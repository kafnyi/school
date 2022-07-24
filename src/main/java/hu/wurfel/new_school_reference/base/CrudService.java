package hu.wurfel.new_school_reference.base;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class CrudService<
        AUDITABLE extends Auditable,
        REPO extends JpaRepository<AUDITABLE, Long>,
        DTO extends BaseDto
        > {

    protected ModelMapper mapper;
    protected REPO repo;

    public CrudService(REPO repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<AUDITABLE> findAllAndDeletedIsFalse() {
        return repo.findAll();
    }

    public List<DTO> findById(Long id) {
        return toDtoList((repo.findById(id).orElseThrow()));
    }

    public List<DTO> save(AUDITABLE entity) {
        return toDtoList(repo.save(entity));
    }

    public abstract List<DTO> save(DTO dto);

    public void delete(AUDITABLE entity) {
        this.deleteById(entity.getId());
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public abstract DTO toDto(AUDITABLE auditable);

    public abstract AUDITABLE toEntity(DTO dto);

    public List<AUDITABLE> toList(AUDITABLE auditable) {
        ArrayList<AUDITABLE> list = new ArrayList<>();
        list.add(auditable);
        return list;
    }

    public abstract List<DTO> toDtoList(AUDITABLE auditable);

    public abstract List<DTO> toDtoList(List<AUDITABLE> list);

    public abstract List<AUDITABLE> toEntityList(DTO dto);

}
