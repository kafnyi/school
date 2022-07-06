package hu.wurfel.new_school_reference.diary;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Diary")
public class DiaryController {

    DiaryService diaryService;

    @GetMapping
    public ResponseEntity search(){
        return null;
    }

    @PutMapping
    public ResponseEntity update (@RequestBody DiaryDto diaryDto ){
        return null;
    }


}
