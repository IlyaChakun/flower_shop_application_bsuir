package by.bsuir.service.core;

import by.bsuir.service.core.base.FindAllOperationService;
import by.bsuir.service.core.base.GetOperationService;

public interface SearchCrudService<T> extends
        FindAllOperationService<T>,
        GetOperationService<T> {

}
