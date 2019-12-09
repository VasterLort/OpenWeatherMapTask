package by.jnetworks.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sys {
    private Long type;
    private Long id;
    private String country;
    private Long sunrise;
    private Long sunset;
}
