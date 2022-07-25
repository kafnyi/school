package hu.wurfel.new_school_reference.teacher;

import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeacherService extends CrudService<Teacher,TeacherRepository, TeacherDto> {

	public TeacherService(TeacherRepository repository, ModelMapper modelMapper) {
		super(repository, modelMapper);
	}

	@Override
	public TeacherDto toDto(Teacher auditable) {
		return this.mapper.map(auditable,TeacherDto.class);
	}

	@Override
	public Teacher toEntity(TeacherDto dto) {
		return this.mapper.map(dto,Teacher.class);
	}

	public List<Teacher> findAllByName(String name){
		return repo.findAllByNameAndDeletedIsFalse(name);
	}

	public List<Teacher> findAllByBirthDate(Date date){
		return repo.findAllByBirthDateAndDeletedIsFalse(date);
	}

	public List<Teacher> findByTeacherCardNumber(long number){
		return this.toEntityList(repo.findAllByCardNumberAndDeletedIsFalse(number));
	}
}
