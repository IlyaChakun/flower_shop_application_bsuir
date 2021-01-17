package by.bsuir.service.core;

import by.bsuir.service.core.base.FindAllOperationService;
import by.bsuir.service.core.base.FindOptionalOperationService;
import by.bsuir.service.core.base.GetOperationService;

public interface SearchCrudService<T> extends FindOptionalOperationService<T>, FindAllOperationService<T>, GetOperationService<T> {

}
