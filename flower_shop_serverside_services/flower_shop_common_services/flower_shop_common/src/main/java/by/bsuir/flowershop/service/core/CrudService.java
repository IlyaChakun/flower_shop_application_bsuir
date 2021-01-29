package by.bsuir.flowershop.service.core;

import by.bsuir.flowershop.service.core.base.DeleteOperationService;
import by.bsuir.flowershop.service.core.base.SaveOperationService;
import by.bsuir.flowershop.service.core.base.UpdateOperationService;

public interface CrudService<T> extends SaveOperationService<T>, UpdateOperationService<T>,
        DeleteOperationService<T>, SearchCrudService<T> {

}
