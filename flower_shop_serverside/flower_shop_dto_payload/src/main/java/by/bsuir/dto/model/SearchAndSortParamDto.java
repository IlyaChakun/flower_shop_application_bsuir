package by.bsuir.dto.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
public class SearchAndSortParamDto extends AbstractDTO {

    @Size(min = 3, max = 30, message = "Name could not be empty! Should be not empty and in 4-30 characters range!")
    private String name;

    @Size(min = 3, max = 30, message = "Surname could not be empty! Should be not empty and in 4-30 characters range!")
    private String surname;

    @Valid
    private Set<
            @Size(min = 3, max = 30,
                    message = "Tag is not valid. Should be not empty and in 3-30 characters range!")
                    String> tags;

    @Valid
    private Set<
            @Size(min = 4, max = 30,
                    message = "SorBy is not valid. Should be not empty and in 4-30 characters range!")
                    String> sortBy;

    @Pattern(regexp = "(asc|ASC|desc|DESC)", message = "sortType param could not be null or blank. Only ASC or DESC!")
    private String sortType;

}
