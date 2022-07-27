package hu.wurfel.new_school_reference.subject;

import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService extends CrudService<Subject,SubjectRepository, SubjectDto> {

	@Autowired
	public SubjectService(SubjectRepository repository, ModelMapper modelMapper) {
		super(repository, modelMapper);
	}

	@Override
	public SubjectDto toDto(Subject auditable) {
		return this.mapper.map(auditable,SubjectDto.class);
	}

	@Override
	public Subject toEntity(SubjectDto dto) {
		return this.mapper.map(dto,Subject.class);
	}

	public List<Subject> findAllByName(String name, boolean deleted){
		return repo.findAllByNameAndDeleted(name, deleted);
	}
}
