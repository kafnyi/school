package hu.wurfel.refference.school.services.requestServices;

import hu.wurfel.refference.school.diary.DiaryService;
import hu.wurfel.refference.school.division.ClassService;
import hu.wurfel.refference.school.mark.MarkService;
import hu.wurfel.refference.school.model.DeleteRequest;
import hu.wurfel.refference.school.student.StudentService;
import hu.wurfel.refference.school.subject.SubjectService;
import hu.wurfel.refference.school.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteRequestService {
	@Autowired
	StudentService studentService;
	@Autowired
	DiaryService diaryService;
	@Autowired
	SubjectService subjectService;
	@Autowired
	ClassService classService;
	@Autowired
	MarkService markService;
	@Autowired
	TeacherService teacherService;

	public void create(DeleteRequest deleteRequest) {
		switch (deleteRequest.getType()) {
			case Student -> {
				studentService.deleteStudent(studentService.getStudentByStudentId(Long.parseLong(deleteRequest.getId())));
				break;
			}
			case Diary -> {
				diaryService.deleteDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(deleteRequest.getId())));
				break;
			}
			case Class -> {
				classService.deleteClass(classService.getClassByClassId(Integer.parseInt(deleteRequest.getId())));
				break;
			}
			case Subject -> {
				subjectService.delete(subjectService.getSubjectBySubjectId(Integer.parseInt(deleteRequest.getId())));
				break;
			}
			case Mark -> {
				markService.deleteMark(markService.getMarkByMarkid(Long.parseLong(deleteRequest.getId())));
				break;
			}
			case Teacher -> {
				teacherService.deleteTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(deleteRequest.getId())));
				break;
			}
			default -> {
				break;
			}
		}


	}
}
