package hu.wurfel.new_school_reference.base;

import hu.wurfel.new_school_reference.diary.DiaryDto;
import hu.wurfel.new_school_reference.exception.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class CrudService<
        AUDITABLE extends Auditable,
        REPO extends JpaRepository<AUDITABLE, Long>
        > {

    protected ModelMapper mapper;
    protected REPO repo;

    public CrudService(REPO repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<AUDITABLE> findAll() {
        return repo.findAll();
    }

    public AUDITABLE findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public AUDITABLE save(AUDITABLE entity) {
        return this.repo.save(entity);
    }

    public void delete(AUDITABLE entity) {
        this.deleteById(entity.getId());
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public abstract <DTO extends BaseDto> DTO toDto(AUDITABLE auditable, Class<DTO> returnType);

    public abstract AUDITABLE toEntity(BaseDto dto);

    public void validateDtoIsNotEmpty(Dto dto, String message) {
        if (dto.isEmpty()) {
            throw new BadRequestException(message);
        }
    }
}
