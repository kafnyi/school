package hu.wurfel.new_school_reference.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody DiaryDto diaryDto) {
        return this.diaryService.save(diaryDto);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody DiaryDto diaryDto) {
        return this.diaryService.save(diaryDto);
    }

    @DeleteMapping
    public void delete(@RequestBody DiaryDto diaryDto) {
        this.diaryService.deleteById(diaryDto.getId());
    }
}
