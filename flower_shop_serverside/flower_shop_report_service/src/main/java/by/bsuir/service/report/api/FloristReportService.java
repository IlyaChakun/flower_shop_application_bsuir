package by.bsuir.service.report.api;

import by.bsuir.service.report.dto.Report;

public interface FloristReportService {

    /**
     *  получить отчет по месячной зарплате флориста С изветсным месяцев: взять его оклад, посчитать колво заказов за этот месяц
     *  и если колво заказов Больше чем допустим 50, то умножить оклад на 0,5  и добавить как премию (статискику в базу сохранить)
     * @param floristId
     * @Param monthNumber мб не интр а че ни ть другое
     * @return
     */
    Report getFloristMonthSalaryReport(Long floristId, Integer monthNumber);

    /**
     * Отчет по годовой зарплате
     * @param floristId
     * @return
     */
    Report getFloristAnnualSalaryReport(Long floristId);

    /**
     * Отчет по заказам МБ Сделать за определннывй период!
     * @param floristId
     * @return
     */
    Report getFloristOrdersReport(Long floristId);
}
