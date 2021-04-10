package Model.Entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    String customerID;
    String goal;
    String gender;
    String sex;
    String age;
    String membershipTime;
    String height;
    String weight;
}
