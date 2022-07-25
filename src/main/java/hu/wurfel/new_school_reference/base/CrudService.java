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
        return this.toDtoList(this.repo.save(entity));
    }

    public List<DTO> save(DTO dto){
        return this.save(this.toEntity(dto));
    }

    public void delete(AUDITABLE entity) {
        this.deleteById(entity.getId());
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public abstract DTO toDto(AUDITABLE auditable);

    public abstract AUDITABLE toEntity(DTO dto);

    public List<DTO> toDtoList(AUDITABLE auditable){
        ArrayList<DTO> list = new ArrayList<>();
        list.add(this.toDto(auditable));
        return list;
    }

    public List<DTO> toDtoList(List<AUDITABLE> list){
        ArrayList<DTO> dtos = new ArrayList<>();
        for (AUDITABLE auditable:list) {
            dtos.add(this.toDto(auditable));
        }
        return dtos;
    }

    public List<DTO> toDtoList(DTO dto){
        ArrayList<DTO> list = new ArrayList<>();
        list.add(dto);
        return list;
    }

    public List<AUDITABLE> toEntityList(DTO dto){
        ArrayList<AUDITABLE> list= new ArrayList<>();
        list.add(this.toEntity(dto));
        return list;
    }

    public List<AUDITABLE> toEntityList(List<DTO> list){
        ArrayList<AUDITABLE> auditables = new ArrayList<>();
        for (DTO dto:list) {
            auditables.add(this.toEntity(dto));
        }
        return auditables;
    }

    public List<AUDITABLE> toEntityList(AUDITABLE auditable) {
        ArrayList<AUDITABLE> list = new ArrayList<>();
        list.add(auditable);
        return list;
    }

}
