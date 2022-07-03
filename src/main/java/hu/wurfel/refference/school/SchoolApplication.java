package hu.wurfel.refference.school;

import hu.wurfel.refference.school.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.ParseException;

@EnableJpaRepositories("hu.wurfel.refference.school")
@EntityScan(basePackages = {"hu.wurfel.refference.school"})
@SpringBootApplication
public class SchoolApplication {
    @Autowired
    static StudentRepository studentRepository;

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(SchoolApplication.class, args);
    }
}
