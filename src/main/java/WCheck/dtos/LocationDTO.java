package WCheck.dtos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationDTO {
    private long id;
    private long latitude;
    private long longitude;
    private List<Long> listFeedbackIds;
}