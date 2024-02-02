package WCheck.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] data;

    private String type;
    private long fileSize;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

}
