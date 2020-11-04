package by.bsuir.service.core;

import by.bsuir.service.core.base.FindOperationService;
import by.bsuir.service.core.base.SaveUpdateService;

public interface CustomCrudService<T> extends SaveUpdateService<T>, FindOperationService<T> {

}
