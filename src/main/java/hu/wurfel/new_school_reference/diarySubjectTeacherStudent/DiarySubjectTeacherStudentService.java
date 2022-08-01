package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiarySubjectTeacherStudentService extends CrudService<DiarySubjectTeacherStudent, DiarySubjectTeacherStudentRepository, DiarySubjectTeacherStudentDto> {

	@Autowired
	public DiarySubjectTeacherStudentService(DiarySubjectTeacherStudentRepository repository, ModelMapper mapper) {
		super(repository, mapper);
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

	@Override
	public DiarySubjectTeacherStudentDto toDto(DiarySubjectTeacherStudent auditable) {
		return this.mapper.map(auditable,DiarySubjectTeacherStudentDto.class);
	}

	@Override
	public DiarySubjectTeacherStudent toEntity(DiarySubjectTeacherStudentDto dto) {
		return this.mapper.map(dto,DiarySubjectTeacherStudent.class);
	}


}
