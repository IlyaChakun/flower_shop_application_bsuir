package by.bsuir.service.core;

import by.bsuir.service.core.base.DeleteOperationService;
import by.bsuir.service.core.base.SaveUpdateService;

public interface CrudService<T> extends SaveUpdateService<T>, DeleteOperationService<T>, SearchCrudService<T> {

}
