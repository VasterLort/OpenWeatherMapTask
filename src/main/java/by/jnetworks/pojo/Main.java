package by.jnetworks.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Main {
    private Double temp;
    private Long pressure;
    private Long humidity;
    private Double temp_min;
    private Double temp_max;
}
