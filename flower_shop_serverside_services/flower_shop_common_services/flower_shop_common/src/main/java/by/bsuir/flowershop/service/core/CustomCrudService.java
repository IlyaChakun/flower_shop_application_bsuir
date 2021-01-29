package by.bsuir.flowershop.service.core;

import by.bsuir.flowershop.service.core.base.FindOperationService;
import by.bsuir.flowershop.service.core.base.SaveOperationService;
import by.bsuir.flowershop.service.core.base.UpdateOperationService;

public interface CustomCrudService<T> extends
        SaveOperationService<T>,
        UpdateOperationService<T>,
        FindOperationService<T> {
}
