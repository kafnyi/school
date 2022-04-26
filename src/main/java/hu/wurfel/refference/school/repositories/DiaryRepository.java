package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Diary;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface DiaryRepository extends CrudRepository<Diary, Integer> {
	ArrayList<Diary> findAll();

	Optional<Diary> findById(Integer id);

	ArrayList<Diary> findAllByScid(Long scid);

	ArrayList<Diary> findAllByClassID(Integer classId);

	Optional<Diary> findByScidAndClassID(Long scid, Integer classId);
}
