package Model.Entity;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    private String name;
    private String videoUrl;
    private String picUrl;
    private String categories;
    private String description;

}
