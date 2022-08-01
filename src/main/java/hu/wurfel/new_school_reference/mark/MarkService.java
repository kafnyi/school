package hu.wurfel.new_school_reference.mark;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.base.MarkModifier;
import hu.wurfel.new_school_reference.diarySubjectTeacherStudent.DiarySubjectTeacherStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MarkService extends CrudService<Mark,MarkRepository> {

	private final DiarySubjectTeacherStudentService DSTSService;

	@Autowired
	public MarkService(MarkRepository repository, ModelMapper modelMapper, DiarySubjectTeacherStudentService dstsService) {
		super(repository,modelMapper);
		DSTSService = dstsService;
	}

	@Override
	public <DTO extends BaseDto> DTO toDto(Mark auditable, Class<DTO> returnType) {
		return null;
	}

	@Override
	public Mark toEntity(BaseDto dto) {
		return null;
	}

	public MarkDto save(CreateMarkDtoWithConnetctId dto){
		this.validateDtoIsNotEmpty(dto,
				"Create failed due to: Mark has no valid testDate" +
						"/diarySubjectTeacherStudentId" +
						"/value" +
						"/markModifier !");
		Mark mark = new Mark(
				dto.getTestDate(),
				DSTSService.findById(dto.getDiarySubjectTeacherStudentId()),
				dto.getValue(),
				dto.getMarkModifier()
		);
		return new MarkDto(this.save(mark));
	}

	@Transactional
	public MarkDto update(UpdateMarkDtoWithConnectId dto){
		this.validateDtoIsNotEmpty(dto,
				"Create failed due to: Mark has no valid testDate" +
						"/diarySubjectTeacherStudentId" +
						"/value" +
						"/markModifier !");
		Mark mark = this.findById(dto.getId());
		mark.setTestDate(dto.getTestDate());
		mark.setDiarySubjectTeacherStudent(DSTSService
				.findById(dto.getDiarySubjectTeacherStudentId()));
		mark.setValue(dto.getValue());
		mark.setMarkModifier(dto.getMarkModifier());
		return new MarkDto(this.save(mark));
	}



	//region Mark

	public List<Mark> findAllByTestDateAndDeleted(Date date, boolean deleted){
		return repo.findAllByTestDateAndDeleted(date, deleted);
	}

	public List<Mark> findAllByDiarySubjectStudentAndDeleted(Long id, boolean deleted){
		return repo.findAllByDiarySubjectTeacherStudent_IdAndDeleted(id,deleted);
	}

	public List<Mark> findAllByMarkModifierAndDeleted(MarkModifier markModifier, boolean deleted){
		return repo.findAllByMarkModifierAndDeleted(markModifier, deleted);
	}

	//endregion Mark

	//region DiarySubjectTeacherStudent

	//region Diary
	public List<Mark> findAllByDiaryAndDeleted(Long Id, boolean deleted){
		return repo.findAllByDiarySubjectTeacherStudent_Diary_IdAndDeleted(Id, deleted);
	}

	//endregion Diary

	//region SubjectTeacher

	public List<Mark> findAllBySubjectTeacherAndDeleted(Long id, boolean deleted){return repo.findAllByDiarySubjectTeacherStudent_SubjectTeacher_IdAndDeleted(id, deleted);}

	//endregion SubjectTeacher
	//region Student

	public List<Mark> findByStudentAndDeleted(Long id, boolean deleted){return repo.findAllByDiarySubjectTeacherStudent_Student_IdAndDeleted(id,deleted);}


	//endregion Student

	//endregion DiarySubjectTeacherStudent

	public MarkDto getById(Long id){
		return new MarkDto(this.findById(id));
	}

}
