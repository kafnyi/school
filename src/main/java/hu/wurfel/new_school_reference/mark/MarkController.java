package hu.wurfel.new_school_reference.mark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mark")
public class MarkController {

	private final MarkService markService;

	@Autowired
	public MarkController(MarkService markService) {
		this.markService = markService;
	}

	@PostMapping
	public ResponseEntity create(@RequestBody MarkDto markDto) {
		return this.markService.save(markDto);
	}

	@PutMapping
	public ResponseEntity update(@RequestBody MarkDto markDto) {
		return this.markService.save(markDto);
	}

	@DeleteMapping
	public void delete(@RequestBody MarkDto markDto) {
		this.markService.deleteById(markDto.getId());
	}

}
