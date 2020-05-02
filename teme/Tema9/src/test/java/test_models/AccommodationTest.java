package test_models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccommodationTest {
    private int id;
    private String type;
    private String bed_type;
    private int max_guests;
    private String description;
}
