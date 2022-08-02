package hu.wurfel.new_school_reference.subject;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService extends CrudService<Subject, SubjectRepository> {

	@Autowired
	public SubjectService(SubjectRepository repository, ModelMapper modelMapper) {
		super(repository, modelMapper);
	}

	public SubjectDto save(CreateSubjectDto dto) {
		this.validateDtoIsNotEmpty(dto, "Create failed due to: Subject has no valid title!");
		Subject subject = new Subject(dto.getTitle().trim());
		return new SubjectDto(this.save(subject));
	}

	@Transactional
	public SubjectDto update(UpdateSubjectDto dto) {
		this.validateDtoIsNotEmpty(dto, "Update failed due to: Subject has no valid title!");
		Subject subject = this.findById(dto.getId());
		subject.setTitle(dto.getTitle());
		return new SubjectDto(this.save(subject));
	}


	@Override
	public <DTO extends BaseDto> DTO toDto(Subject auditable, Class<DTO> returnType) {
		return null;
	}

	@Override
	public Subject toEntity(BaseDto dto) {
		return null;
	}

//	@Override
//	public SubjectDto toDto(Subject auditable) {
//		return this.mapper.map(auditable,SubjectDto.class);
//	}
//	
//	@Override
//	public Subject toEntity(SubjectDto dto) {
//		return this.mapper.map(dto,Subject.class);
//	}

	public List<Subject> findAllByTitleAndDeleted(String name, boolean deleted) {
		return repo.findAllByTitleAndDeleted(name, deleted);
	}

	public SubjectDto getById(Long id) {
		return new SubjectDto(this.findById(id));
	}
}
