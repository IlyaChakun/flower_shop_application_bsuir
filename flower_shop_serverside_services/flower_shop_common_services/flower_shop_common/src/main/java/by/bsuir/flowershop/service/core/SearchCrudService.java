package by.bsuir.flowershop.service.core;

import by.bsuir.flowershop.service.core.base.FindAllOperationService;
import by.bsuir.flowershop.service.core.base.GetOperationService;

public interface SearchCrudService<T> extends
        FindAllOperationService<T>,
        GetOperationService<T> {

}
