package Model.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Schedule {

    private String scheduleID;
    private String customerID;
    private String coachID;
    private String time;
    private String date;

}
