package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.BaseDto;
import org.modelmapper.ModelMapper;
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

    @PostMapping
    public ResponseEntity<? extends BaseDto> create(@RequestBody CreateDiaryDtoWithConnectIds dto) {
        return ResponseEntity.ok(this.diaryService.save(dto));
    }

    @PostMapping("/withClassCreation")
    public ResponseEntity<? extends BaseDto> createWithClassCreation(@RequestBody CreateDiaryDtoWithConnectIds dto) {
        return ResponseEntity.ok(this.diaryService.save(dto));
    }

    @PutMapping
    public ResponseEntity<? extends BaseDto> update(@RequestBody UpdateDiaryDtoWithConnectIds dto) {
        return ResponseEntity.ok(this.diaryService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@RequestParam Long id) {
        this.diaryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
