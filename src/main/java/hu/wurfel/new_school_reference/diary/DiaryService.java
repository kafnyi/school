package hu.wurfel.new_school_reference.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {

	private DiaryRepository diaryRepository;

	@Autowired
	public DiaryService(DiaryRepository diaryRepository) {
		this.diaryRepository = diaryRepository;
	}
}
