package hu.wurfel.new_school_reference.diary;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diary")
public class DiaryController {

    private final DiaryService diaryService;
    private final ModelMapper modelMapper;

    @Autowired
    public DiaryController(DiaryService diaryService, ModelMapper modelMapper) {
        this.diaryService = diaryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<List<DiaryDto>> create(@RequestBody DiaryDto diaryDto) {
        return ResponseEntity.ok(this.diaryService.save(diaryDto));
    }

    @PutMapping
    public ResponseEntity<List<DiaryDto>> update(@RequestBody DiaryDto diaryDto) {
        return ResponseEntity.ok(this.diaryService.save(diaryDto));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody DiaryDto diaryDto) {
        this.diaryService.deleteById(diaryDto.getId());
        return ResponseEntity.noContent().build();
    }
}
