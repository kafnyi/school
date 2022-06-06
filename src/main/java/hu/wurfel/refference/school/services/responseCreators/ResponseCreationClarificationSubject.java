package hu.wurfel.refference.school.services.responseCreators;

import hu.wurfel.refference.school.model.Request;

import java.time.Year;
import java.util.ArrayList;

public class ResponseCreationClarificationSubject extends responseCreationTemplate {
	public static ArrayList create(Request request) {
		switch (request.getRWith()) {
			case Student -> {
				sjWStudent(request);
			}
			case Diary -> {
				sjWDiary(request);
			}
			case Class -> {
				sjWClass(request);
			}
			case Subject -> {
				sjWSubject(request);
			}
			case Mark -> {
				sjWMark(request);
			}
			case Teacher -> {
				sjWTeacher(request);
			}
			default -> {
			}
		}
		return rContent;
	}

	private static void sjWStudent(Request request) {
		switch (request.getRBy()) {
			case StudentId -> {
				rContent = subjectService.getSubjectsByStudent(studentService.getStudentByStudentId(Long.parseLong(request.getRValue())));
			}
			case Name -> {
				rContent = subjectService.getSubjectsByStudents(studentService.getStudentsByName(request.getRValue()));
			}
			case Date -> {
				rContent = subjectService.getSubjectsByStudents(studentService.getStudentsByBirth(request.getRValDate()));
			}
			default -> {
			}
		}
	}

	private static void sjWDiary(Request request) {
		switch (request.getRBy()) {
			case DiaryId -> {
				rContent = subjectService.getSubjectsByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getRValue())));
			}
			case StudentId -> {
				rContent = subjectService.getSubjectsByDiaries(diaryService.getDiariesByScid(Long.parseLong(request.getRValue())));
			}
			case ClassId -> {
				rContent = subjectService.getSubjectsByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(request.getRValue())));
			}
			default -> {
				;
			}
		}
	}

	private static void sjWClass(Request request) {
		switch (request.getRBy()) {
			case ClassId -> {
				rContent = subjectService.getSubjectsByClass(classService.getClassByClassId(Integer.parseInt(request.getRValue())));
			}
			case Grade -> {
				rContent = subjectService.getSubjectsByClasses(classService.getClassesByGrade(Short.parseShort(request.getRValue())));
			}
			case Sign -> {
				rContent = subjectService.getSubjectsByClasses(classService.getClassesBySign(request.getRValue().strip().charAt(0)));
			}
			case Year -> {
				rContent = subjectService.getSubjectsByClasses(classService.getClassesByYear(Year.parse(request.getRValue())));
			}
			case TeacherId -> {
				rContent = subjectService.getSubjectsByClasses(classService.getClassesByTid(Long.parseLong(request.getRValue())));
			}
			default -> {
			}
		}
	}

	private static void sjWSubject(Request request) {
		switch (request.getRBy()) {
			case SubjectId -> {
				rContent.add(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getRValue())));
			}
			case Name -> {
				rContent = subjectService.getSubjectsByName(request.getRValue());
			}
			case TeacherId -> {
				rContent = subjectService.getSubjectsByTid(Long.parseLong(request.getRValue()));
			}
			default -> {
			}
		}
	}

	private static void sjWMark(Request request) {
		switch (request.getRBy()) {
			case MarkId -> {
				rContent.add(subjectService.getSubjectByMark(markService.getMarkByMarkid(Long.parseLong(request.getRValue()))));
			}
			case DiaryId -> {
				rContent = subjectService.getSubjectsByMarks(markService.getMarksByDiaryid(Integer.parseInt(request.getRValue())));
			}
			case Date -> {
				rContent = subjectService.getSubjectsByMarks(markService.getMarksByDate(request.getRValDate()));
			}
			case SubjectId -> {
				rContent.add(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getRValue())));
			}
			case Mark -> {
				rContent = subjectService.getSubjectsByMarks(markService.getMarksByMark(Byte.parseByte(request.getRValue())));
			}
			default -> {
			}
		}
	}

	private static void sjWTeacher(Request request) {
		switch (request.getRBy()) {
			case TeacherId -> {
				rContent = subjectService.getSubjectsByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(request.getRValue())));
			}
			case Name -> {
				rContent = subjectService.getSubjectsByTeachers(teacherService.getTeacherByName(request.getRValue()));
			}
			case Date -> {
				rContent = subjectService.getSubjectsByTeachers(teacherService.getTeacherByBirth(request.getRValDate()));
			}
			default -> {
			}
		}
	}
}
