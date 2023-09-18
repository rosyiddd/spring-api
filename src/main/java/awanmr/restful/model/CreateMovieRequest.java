package awanmr.restful.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMovieRequest {

    @NotBlank
    @Size(max = 100)
    private String title;
    
    @NotBlank
    private String description;
    
    @NotNull
    private Double rating;

    @Size(max = 255)
    private String image;
}
