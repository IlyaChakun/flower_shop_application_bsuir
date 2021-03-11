package by.bsuir.service.report.api;

import by.bsuir.service.report.dto.Report;

public interface FloristReportService {

    /**
     *  получить отчет по месячной зарплате флориста С изветсным месяцев: взять его оклад, посчитать колво заказов за этот месяц
     *  и если колво заказов Больше чем допустим 50, то умножить оклад на 0,5  и добавить как премию (статискику в базу сохранить)
     * @param email
     * @Param monthNumber мб не интр а че ни ть другое
     * @return
     */
    Report getFloristMonthSalaryReport(String email, Integer monthNumber);

    /**
     * Отчет по годовой зарплате
     * @param email
     * @return
     */
    Report getFloristYearSalaryReport(String email);

    /**
     * Отчет по заказам МБ Сделать за определннывй период!
     * @param email
     * @return
     */
    Report getFloristOrdersReport(String email);
}
