package hu.wurfel.new_school_reference.base;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@MappedSuperclass
public class Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime UpdatedAt;

	private boolean deleted;
}
