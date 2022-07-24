package hu.wurfel.new_school_reference.diarySubjectTeacherStudent;

import hu.wurfel.new_school_reference.base.Auditable;
import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiarySubjectTeacherStudentService extends CrudService<DiarySubjectTeacherStudent, DiarySubjectTeacherStudentRepository, DiarySubjectTeacherStudentDto> {

	@Autowired
	public DiarySubjectTeacherStudentService(DiarySubjectTeacherStudentRepository repository, ModelMapper mapper) {
		super(repository, mapper);
	}

	//region Student

	List<DiarySubjectTeacherStudent> findAllByStudentId(Long id) {
		return repo.findAllByStudent_IdAndDeletedIsFalse(id);
	}

	List<DiarySubjectTeacherStudent> findAllByStudentCardNumber(Long number) {
		return repo.findAllByStudent_CardNumberAndDeletedIsFalse(number);
	}

	//endregion Student

	//region Diary

	public List<DiarySubjectTeacherStudent> findAllByDiaryId(Long id){
		return repo.findAllByDiary_IdAndDeletedIsFalse(id);
	}

	//endregion Diary

	//region SubjectTeacher

	public List<DiarySubjectTeacherStudent> findAllBySubjectTeacherId(Long id){
		return repo.findAllBySubjectTeacher_IdAndDeletedIsFalse(id);
	}

	public List<DiarySubjectTeacherStudent> findAllBySubjectId(Long id){
		return repo.findAllBySubjectTeacher_Subject_IdAndDeletedIsFalse(id);
	}

	public List<DiarySubjectTeacherStudent> findAllBySubjectName(String name){
		return repo.findAllBySubjectTeacher_Subject_NameAndDeletedIsFalse(name);
	}

	public List<DiarySubjectTeacherStudent> findAllByTeacherId(Long id){
		return repo.findAllBySubjectTeacher_Teacher_IdAndDeletedIsFalse(id);
	}

	public List<DiarySubjectTeacherStudent> findAllByTeacherCardNumber(Long number){
		return repo.findAllBySubjectTeacher_Teacher_CardNumberAndDeletedIsFalse(number);
	}

	@Override
	public List<DiarySubjectTeacherStudentDto> save(DiarySubjectTeacherStudentDto dto) {
		return this.toDtoList(this.repo.save(mapper.map(dto,DiarySubjectTeacherStudent.class)));
	}

	@Override
	public DiarySubjectTeacherStudentDto toDto(DiarySubjectTeacherStudent auditable) {
		return mapper.map(auditable,DiarySubjectTeacherStudentDto.class);
	}

	@Override
	public DiarySubjectTeacherStudent toEntity(DiarySubjectTeacherStudentDto dto) {
		return mapper.map(dto,DiarySubjectTeacherStudent.class);
	}

	@Override
	public List<DiarySubjectTeacherStudentDto> toDtoList(DiarySubjectTeacherStudent auditable) {
		ArrayList<DiarySubjectTeacherStudentDto> list = new ArrayList<>();
		list.add(this.toDto(auditable));
		return list;
	}

	@Override
	public List<DiarySubjectTeacherStudentDto> toDtoList(List<DiarySubjectTeacherStudent> entities) {
		ArrayList<DiarySubjectTeacherStudentDto> list = new ArrayList<>();
		for (DiarySubjectTeacherStudent entity:entities) {
			list.add(this.toDto(entity));
		}
		return list;
	}

	@Override
	public List<DiarySubjectTeacherStudent> toEntityList(DiarySubjectTeacherStudentDto dto) {
		ArrayList<DiarySubjectTeacherStudent> list = new ArrayList<>();
		list.add(this.toEntity(dto));
		return list;
	}

	//endregion SubjectTeacher


}
