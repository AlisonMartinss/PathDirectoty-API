package PathCarrer.API.Model.User;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Note {
    private LocalDate date;
    private String message;
    private String key;

    public void BuildNote (String message){
        this.date = LocalDate.now();
        this.message = message;
        this.key = date + message.substring(0,5);
    }

    // ==== Getters ==== //
    public LocalDate getDate() {
        return date;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }
}
