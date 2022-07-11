package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.division.ClassDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

//    @GetMapping("/{searchEntity}/{entityField}/{value}")
//    public ResponseEntity search(@PathVariable EntityNames searchEntity,
//                                 @PathVariable EntityFieldName entityField,
//                                 @PathVariable String value){
//        return null;
//    }




    @PutMapping
    public ResponseEntity update (@RequestBody DiaryDto diaryDto ){
        return null;
    }


}
