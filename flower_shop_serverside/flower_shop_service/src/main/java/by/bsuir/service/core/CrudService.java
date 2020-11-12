package by.bsuir.service.core;

import by.bsuir.service.core.base.DeleteOperationService;
import by.bsuir.service.core.base.SaveOperationService;
import by.bsuir.service.core.base.UpdateOperationService;

public interface CrudService<T> extends SaveOperationService<T>, UpdateOperationService<T>,
        DeleteOperationService<T>, SearchCrudService<T> {

}
