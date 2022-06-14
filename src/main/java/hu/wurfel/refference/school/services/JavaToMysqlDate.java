package hu.wurfel.refference.school.services;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@NoArgsConstructor
public class JavaToMysqlDate {
    private static final String PATTERN = "yyyy-MM-dd";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);

    public String format(Date dateToFormat) {
        return simpleDateFormat.format(dateToFormat);
    }

}
