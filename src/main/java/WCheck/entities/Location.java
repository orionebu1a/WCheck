package WCheck.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double latitude;
    private double longitude;
    private Integer votes;
    private String hashtag;

    @OneToMany
    @JoinColumn(name = "location_id")
    private List<UserName> upvoters = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "location_id")
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "location_id")
    private List<Photo> photos = new ArrayList<>();
}