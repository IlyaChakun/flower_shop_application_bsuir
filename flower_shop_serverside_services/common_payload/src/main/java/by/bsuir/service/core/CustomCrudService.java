package by.bsuir.service.core;

import by.bsuir.service.core.base.FindOperationService;
import by.bsuir.service.core.base.SaveOperationService;
import by.bsuir.service.core.base.UpdateOperationService;

public interface CustomCrudService<T> extends
        SaveOperationService<T>,
        UpdateOperationService<T>,
        FindOperationService<T> {
}
