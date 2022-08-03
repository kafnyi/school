package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.diary.DiaryService;
import hu.wurfel.new_school_reference.student.StudentService;
import hu.wurfel.new_school_reference.subject_teacher.SubjectTeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiarySubjectTeacherStudentService extends CrudService<DiarySubjectTeacherStudent, DiarySubjectTeacherStudentRepository> {

	private final DiaryService diaryService;
	private final SubjectTeacherService subjectTeacherService;
	private  final StudentService studentService;
	@Autowired
	public DiarySubjectTeacherStudentService(DiarySubjectTeacherStudentRepository repository, ModelMapper mapper, DiaryService diaryService, SubjectTeacherService subjectTeacherService, StudentService studentService) {
		super(repository,mapper);
		this.diaryService = diaryService;
		this.subjectTeacherService = subjectTeacherService;
		this.studentService = studentService;
	}

	public DiarySubjectTeacherStudentDto save(CreateDiarySubjectTeacherStudentDto dto){
		this.validateDtoIsNotEmpty(dto,"Create failed due to: DiarySubjectTeacherStudent has no valid " +
				"DiaryId/" +
				"SubjectTeacherId/" +
				"Student!");
		DiarySubjectTeacherStudent entity = new DiarySubjectTeacherStudent(
				diaryService.findById(dto.getDiaryId()),
				subjectTeacherService.findById(dto.getSubjectTeacherId()),
				studentService.findById(dto.getStudentId()));
		return new DiarySubjectTeacherStudentDto(this.save(entity));
	}

	@Transactional
	public DiarySubjectTeacherStudentDto save(UpdateDiarySubjectTeacherStudentDto dto){
		this.validateDtoIsNotEmpty(dto,"Create failed due to: DiarySubjectTeacherStudent has no valid " +
				"DiaryId/" +
				"SubjectTeacherId/" +
				"Student!");
		DiarySubjectTeacherStudent entity = this.findById(dto.getId());
		entity.setDiary(this.diaryService.findById(dto.getDiaryId()));
		entity.setSubjectTeacher(this.subjectTeacherService.findById(dto.getSubjectTeacherId()));
		entity.setStudent(studentService.findById(dto.getStudentId()));
		return new DiarySubjectTeacherStudentDto(this.save(entity));
	}

	@Override
	public <DTO extends BaseDto> DTO toDto(DiarySubjectTeacherStudent auditable, Class<DTO> returnType) {
		return null;
	}

	@Override
	public DiarySubjectTeacherStudent toEntity(BaseDto dto) {
		return null;
	}

	//region Student

	List<DiarySubjectTeacherStudent> findAllByStudentIdAndDeleted(Long id, boolean deleted) {
		return repo.findAllByStudent_IdAndDeleted(id, deleted);
	}

	List<DiarySubjectTeacherStudent> findAllByStudentCardNumberAndDeleted(Long number, boolean deleted) {
		return repo.findAllByStudent_CardNumberAndDeleted(number,deleted);
	}

	//endregion Student

	//region Diary

	public List<DiarySubjectTeacherStudent> findAllByDiaryIdAndDeleted(Long id, boolean deleted){
		return repo.findAllByDiary_IdAndDeleted(id,deleted);
	}

	//endregion Diary

	//region SubjectTeacher

	public List<DiarySubjectTeacherStudent> findAllBySubjectTeacherIdAndDeleted(Long id, boolean deleted){
		return repo.findAllBySubjectTeacher_IdAndDeleted(id,deleted);
	}

	public List<DiarySubjectTeacherStudent> findAllBySubjectIdAndDeleted(Long id, boolean deleted){
		return repo.findAllBySubjectTeacher_Subject_IdAndDeleted(id,deleted);
	}

	public List<DiarySubjectTeacherStudent> findAllBySubjectNameAndDeleted(String name, boolean deleted){
		return repo.findAllBySubjectTeacher_Subject_NameAndDeleted(name, deleted);
	}

	public List<DiarySubjectTeacherStudent> findAllByTeacherIdAndDeleted(Long id, boolean deleted){
		return repo.findAllBySubjectTeacher_Teacher_IdAndDeleted(id, deleted);
	}

	public List<DiarySubjectTeacherStudent> findAllByTeacherCardNumberAndDeleted(Long number, boolean deleted){
		return repo.findAllBySubjectTeacher_Teacher_CardNumberAndDeleted(number, deleted);
	}

	//endregion SubjectTeacher


}
