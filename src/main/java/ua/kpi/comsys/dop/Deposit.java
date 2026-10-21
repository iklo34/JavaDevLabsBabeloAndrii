package ua.kpi.comsys.dop;

import java.math.BigDecimal;

/**
 * Поповнення рахунку.
 *
 * @param accountId ідентифікатор рахунку
 * @param amount    сума поповнення (більше 0)
 */
public record Deposit(String accountId, BigDecimal amount) implements Transaction {

    public Deposit {
        Validation.requireNonBlank(accountId, "accountId");
        Validation.requirePositive(amount);
    }
}
