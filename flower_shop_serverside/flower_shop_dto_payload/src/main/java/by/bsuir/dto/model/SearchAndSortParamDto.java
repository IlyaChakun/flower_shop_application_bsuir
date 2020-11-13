package by.bsuir.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class SearchAndSortParamDto extends AbstractDTO {//TODO а куда делся lombok?))

    @Size(min = 3, max = 30, message = "Name could not be empty! Should be not empty and in 4-30 characters range!")
    private String flowerType;

    @Valid
    private Set<
            @Size(min = 4, max = 30,
                    message = "SorBy is not valid. Should be not empty and in 4-30 characters range!")
                    String> sortBy;

    @Pattern(regexp = "(asc|ASC|desc|DESC)", message = "sortType param could not be null or blank. Only ASC or DESC!")
    private String sortType;

}
