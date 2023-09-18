package awanmr.restful.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMovieRequest {
    
    private Long movieId;

    @Size(max = 100)
    private String title;
    
    private String description;
    
    private Double rating;
    
    @Size(max = 255)
    private String image;
}
