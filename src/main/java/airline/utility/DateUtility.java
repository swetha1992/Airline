package airline.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtility {
    public LocalDate convertStringToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
