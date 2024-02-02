package WCheck.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class  PhotoDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("data")
    private byte[] data;
    @JsonProperty("type")
    private String type;
    @JsonProperty("fileSize")
    private Long fileSize;
    @JsonProperty("uploadDate")
    private Date uploadDate;
}
