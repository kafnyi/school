package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.Auditable;
import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassService extends CrudService<Class, ClassRepository, ClassDto> {

	@Autowired
	public ClassService(ClassRepository repository, ModelMapper mapper) {
		super(repository, mapper);
	}

	@Override
	public List<ClassDto> save(ClassDto dto) {
		return this.toDtoList(this.save(toEntity(dto)));
	}

	@Override
	public ClassDto toDto(Class auditable) {
		return mapper.map(auditable,ClassDto.class);
	}

	@Override
	public Class toEntity(ClassDto dto) {
		return mapper.map(dto,Class.class);
	}

	@Override
	public List<ClassDto> toDtoList(Class auditable) {
		ArrayList<ClassDto> list = new ArrayList<>();
		list.add(toDto(auditable));
		return list;
	}

	@Override
	public List<ClassDto> toDtoList(List<Class> entities) {
		ArrayList<ClassDto> list = new ArrayList<>();
		for (Class entity:entities) {
			list.add(toDto(entity));
		}
		return list;
	}

	@Override
	public List<Class> toEntityList(ClassDto dto) {
		return null;
	}

	public List<Class> findAllByGrade(short grade){
		return repo.findAllByGradeAndDeletedIsFalse(grade);
	}

	public List<Class> findAllBySign(char sign){
		return repo.findAllBySignAndDeletedIsFalse(sign);
	}

	public List<Class> findByGradeAndSign(short grade, char sign){return this.toList(repo.findByGradeAndSignAndDeletedIsFalse(grade, sign));}

}
