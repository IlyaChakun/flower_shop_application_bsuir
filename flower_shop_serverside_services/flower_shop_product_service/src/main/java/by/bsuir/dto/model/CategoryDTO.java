package by.bsuir.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO extends BaseAbstractDTO {

    private String name;

    private Integer parentId;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryDTO> children = new ArrayList<>();
}
