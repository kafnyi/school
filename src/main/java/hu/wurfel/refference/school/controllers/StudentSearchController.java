package hu.wurfel.refference.school.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentSearchController {

    //@Autowired
    //private StudentCrudService scs;
//
    //@PostMapping(value = "/api/v1/searchForStudent")
    //public ResponseEntity<ArrayList<Student>> findStudent(@RequestBody StudentRequestDto requestDto) {
    //	StudentRequestDto ssrd = requestDto;
    //	ArrayList<Student> result = new ArrayList<Student>();
    //	StudentSearchDto searchDto = StudentTranslate.translateToSSDTO(Validate.requestContent(ssrd));
    //	switch (Validate.searchFor(searchDto)) {
    //		case ID -> result.add(scs.getStudentByStudentId(searchDto.getID()));
    //		case Name -> result.addAll(scs.getStudentsByName(searchDto.getName()));
    //		case Birth -> result.addAll(scs.getStudentsByBirth(searchDto.getDate()));
    //		case Empty -> result = scs.getAllStudents();
    //	}
    //	return ResponseEntity.ok(result);
    //}
//
    //@PostMapping(value = "/api/v1/addNewStudent")
    //public void addStudent(@RequestBody StudentDto requestDto) {
    //	System.out.println(requestDto);
    //	Student studentToAdd = StudentTranslate.translateToStudent(requestDto);
    //	scs.saveStudent(studentToAdd);
    //}
//
    //@PostMapping(value = "/api/v1/deleteStudent")
    //public void deleteStudent(@RequestBody StudentRequestDto requestDto) {
    //	StudentRequestDto srd = requestDto;
    //	System.out.println(requestDto.toString());
    //	scs.deleteStudent(scs.getStudentByStudentId(Long.parseLong(srd.getID())));
    //}
}
