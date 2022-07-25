package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diarySubjectTeacherStudent.DiarySubjectTeacherStudent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarkService extends CrudService<Mark,MarkRepository, MarkDto> {

	@Autowired
	public MarkService(MarkRepository repository, ModelMapper modelMapper) {
		super(repository,modelMapper);
	}

	@Override
	public MarkDto toDto(Mark auditable) {
		return this.mapper.map(auditable,MarkDto.class);
	}

	@Override
	public Mark toEntity(MarkDto dto) {
		return this.mapper.map(dto,Mark.class);
	}

	//region Mark

	public List<Mark> findAllByTestDate(Date date){
		return repo.findAllByTestDateAndDeletedIsFalse(date);
	}

	public List<Mark> findAllByDiarySubjectStudent (DiarySubjectTeacherStudent diarySubjectTeacherStudent){
		return repo.findAllByDiarySubjectTeacherStudentAndDeletedIsFalse(diarySubjectTeacherStudent);
	}

	public List<Mark> findAllByMarkModifier(MarkModifier markModifier){
		return repo.findAllByMarkModifierAndDeletedIsFalse(markModifier);
	}

	//endregion Mark

	//region DiarySubjectTeacherStudent

	//region Diary
	public List<Mark> findAllByDiary(Long Id){
		return repo.findAllByDiarySubjectTeacherStudent_Diary_IdAndDeletedIsFalse(Id);
	}

	//endregion Diary

	//region SubjectTeacher

	public List<Mark> findAllBySubjectTeacher(Long id){return repo.findAllByDiarySubjectTeacherStudent_SubjectTeacher_IdAndDeletedIsFalse(id);}

	//endregion SubjectTeacher
	//region Student

	public List<Mark> findByStudent(Long id){return repo.findAllByDiarySubjectTeacherStudent_Student_IdAndDeletedIsFalse(id);}


	//endregion Student

	//endregion DiarySubjectTeacherStudent

}
