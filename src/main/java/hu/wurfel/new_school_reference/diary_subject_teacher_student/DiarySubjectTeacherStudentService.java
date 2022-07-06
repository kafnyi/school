package hu.wurfel.new_school_reference.diary_subject_teacher_student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiarySubjectTeacherStudentService {

	private final DiarySubjectTeacherStudentRepository diarySubjectTeacherStudentRepository;

	@Autowired
	public DiarySubjectTeacherStudentService(DiarySubjectTeacherStudentRepository diarySubjectTeacherStudentRepository) {
		this.diarySubjectTeacherStudentRepository = diarySubjectTeacherStudentRepository;
	}
}
