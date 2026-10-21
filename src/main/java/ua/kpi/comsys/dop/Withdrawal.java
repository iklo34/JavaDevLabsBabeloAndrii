package ua.kpi.comsys.dop;

import java.math.BigDecimal;

/**
 * Зняття коштів з рахунку.
 *
 * @param accountId ідентифікатор рахунку
 * @param amount    сума зняття (більше 0)
 * @param category  категорія витрати
 */
public record Withdrawal(String accountId, BigDecimal amount, String category) implements Transaction {

    public Withdrawal {
        Validation.requireNonBlank(accountId, "accountId");
        Validation.requirePositive(amount);
        Validation.requireNonBlank(category, "category");
    }
}
