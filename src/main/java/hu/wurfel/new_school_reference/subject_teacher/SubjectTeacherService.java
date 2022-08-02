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

	public List<SubjectTeacher> findAllBySubjectAndDeleted(Long id, boolean deleted) {
		return repo.findAllBySubject_IdAndDeleted(id, deleted);
	}

	public List<SubjectTeacher> findAllBySubjectNameAndDeleted(String name, boolean deleted) {
		return repo.findAllBySubject_NameAndDeleted(name, deleted);
	}

	//endregion Subject

	//region Teacher

	public List<SubjectTeacher> findAllByTeacherAndDeleted(Long id, boolean deleted) {
		return repo.findAllByTeacher_IdAndDeleted(id, deleted);
	}

	public List<SubjectTeacher> findAllByTeacherCardNumberAndDeleted(Long number, boolean deleted) {
		return repo.findAllByTeacher_CardNumberAndDeleted(number, deleted);
	}

	//endregion Teacher

	public SubjectTeacher findBySubjectAndTeacherAndDeleted(Long subject, Long teacher, boolean deleted) {
		return repo.findBySubject_IdAndTeacher_IdAndDeleted(subject, teacher, deleted);
	}
}
