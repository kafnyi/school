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

	public List<Mark> findAllByTestDate(Date date, boolean deleted){
		return repo.findAllByTestDateAndDeleted(date, deleted);
	}

	public List<Mark> findAllByDiarySubjectStudent (Long id, boolean deleted){
		return repo.findAllByDiarySubjectTeacherStudent_IdAndDeleted(id,deleted);
	}

	public List<Mark> findAllByMarkModifier(MarkModifier markModifier, boolean deleted){
		return repo.findAllByMarkModifierAndDeleted(markModifier, deleted);
	}

	//endregion Mark

	//region DiarySubjectTeacherStudent

	//region Diary
	public List<Mark> findAllByDiary(Long Id, boolean deleted){
		return repo.findAllByDiarySubjectTeacherStudent_Diary_IdAndDeleted(Id, deleted);
	}

	//endregion Diary

	//region SubjectTeacher

	public List<Mark> findAllBySubjectTeacher(Long id, boolean deleted){return repo.findAllByDiarySubjectTeacherStudent_SubjectTeacher_IdAndDeleted(id, deleted);}

	//endregion SubjectTeacher
	//region Student

	public List<Mark> findByStudent(Long id, boolean deleted){return repo.findAllByDiarySubjectTeacherStudent_Student_IdAndDeleted(id,deleted);}


	//endregion Student

	//endregion DiarySubjectTeacherStudent

}
