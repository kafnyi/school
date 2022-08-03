package hu.wurfel.new_school_reference.subject_teacher;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.subject.SubjectService;
import hu.wurfel.new_school_reference.teacher.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectTeacherService extends CrudService<SubjectTeacher, SubjectTeacherRepository> {

	private final SubjectService subjectService;
	private final TeacherService teacherService;

	@Autowired
	public SubjectTeacherService(SubjectTeacherRepository repository, ModelMapper modelMapper, SubjectService subjectService, TeacherService teacherService) {
		super(repository, modelMapper);
		this.subjectService = subjectService;
		this.teacherService = teacherService;
	}

	public SubjectTeacherDto save(CreateSubjectTeacherDto dto){
		this.validateDtoIsNotEmpty(dto, "Create failed due to: Student has no valid " +
				"subjectId/" +
				"teacherId!");
		SubjectTeacher subjectTeacher = new SubjectTeacher(
				subjectService.findById(dto.getSubjectId()),
				teacherService.findById(dto.getTeacherId())
		);
		return new SubjectTeacherDto(this.save(subjectTeacher));
	}

	@Transactional
	public SubjectTeacherDto update(UpdateSubjectTeacherDto dto){
		this.validateDtoIsNotEmpty(dto, "Create failed due to: Student has no valid " +
				"subjectId/" +
				"teacherId!");
		SubjectTeacher subjectTeacher = this.findById(dto.getId());
		subjectTeacher.setSubject(this.subjectService.findById(dto.getSubjectId()));
		subjectTeacher.setTeacher(this.teacherService.findById(dto.getTeacherId()));
		return new SubjectTeacherDto(this.save(subjectTeacher));
	}

	@Override
	public <DTO extends BaseDto> DTO toDto(SubjectTeacher auditable, Class<DTO> returnType) {
		return null;
	}

	@Override
	public SubjectTeacher toEntity(BaseDto dto) {
		return null;
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

	public SubjectTeacherDto getById(Long id){
		return new SubjectTeacherDto(this.findById(id));
	}

}
