package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectTeacherService extends CrudService<SubjectTeacher, SubjectTeacherRepository, SubjectTeacherDto> {

	@Autowired
	public SubjectTeacherService(SubjectTeacherRepository repository, ModelMapper modelMapper) {
		super(repository, modelMapper);
	}

	@Override
	public SubjectTeacherDto toDto(SubjectTeacher auditable) {
		return this.mapper.map(auditable,SubjectTeacherDto.class);
	}

	@Override
	public SubjectTeacher toEntity(SubjectTeacherDto dto) {
		return this.mapper.map(dto,SubjectTeacher.class);
	}

	//region Subject

	public List<SubjectTeacher> findAllBySubject(Long id) {
		return repo.findAllBySubject_IdAndDeletedIsFalse(id);
	}

	public List<SubjectTeacher> findAllBySubjectName(String name) {
		return repo.findAllBySubject_NameAndDeletedIsFalse(name);
	}

	//endregion Subject

	//region Teacher

	public List<SubjectTeacher> findAllByTeacher(Long id) {
		return repo.findAllByTeacher_IdAndDeletedIsFalse(id);
	}

	public List<SubjectTeacher> findAllByTeacherCardNumber(Long number) {
		return repo.findAllByTeacher_CardNumberAndDeletedIsFalse(number);
	}

	//endregion Teacher

	public SubjectTeacher findBySubjectAndTeacher(Long subject, Long teacher) {
		return repo.findBySubject_IdAndTeacher_IdAndDeletedIsFalse(subject, teacher);
	}
}
