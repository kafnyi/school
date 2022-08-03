package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassService extends CrudService<Class, ClassRepository> {

	@Autowired
	public ClassService(ClassRepository repository, ModelMapper mapper) {
		super(repository, mapper);
	}

	public ClassDto save(CreateClassDto dto){
		this.validateDtoIsNotEmpty(dto, "Create failed due to: Student has no valid name/birthdate/cardNumber!");
		Class division = new Class(dto.getGrade(), dto.getSign());
		return new ClassDto(this.save(division));
	}

	@Transactional
	public ClassDto update(UpdateClassDto dto) {
		this.validateDtoIsNotEmpty(dto, "Update failed due to: Student has no valid name/birthdate/cardNumber!");
		Class division = this.findById(dto.getId());
		division.setGrade(dto.getGrade());
		division.setSign(dto.getSign());
		return new ClassDto(this.save(division));
	}

	@Override
	public <DTO extends BaseDto> DTO toDto(Class auditable, java.lang.Class<DTO> returnType) {
		return null;
	}

	@Override
	public Class toEntity(BaseDto dto) {
		return null;
	}

	public List<Class> findAllByGradeAndDeleted(short grade, boolean deleted){
		return repo.findAllByGradeAndDeleted(grade, deleted);
	}

	public List<Class> findAllBySignAndDeleted(char sign, boolean deleted){
		return repo.findAllBySignAndDeleted(sign, deleted);
	}

	public Class findByGradeAndSignAndDeleted(short grade, char sign, boolean deleted){return repo.findByGradeAndSignAndDeleted(grade, sign, deleted);}

	public ClassDto getById(Long id) {
		return new ClassDto(this.findById(id));
	}

}
