package WCheck.entities;

import jakarta.persistence.*;

import java.util.Date;

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

}
