package ua.kpi.comsys.dop;

import java.math.BigDecimal;

/**
 * Переказ між рахунками.
 *
 * @param fromAccount рахунок-відправник
 * @param toAccount   рахунок-отримувач
 * @param amount      сума переказу (більше 0)
 */
public record Transfer(String fromAccount, String toAccount, BigDecimal amount) implements Transaction {

    public Transfer {
        Validation.requireNonBlank(fromAccount, "fromAccount");
        Validation.requireNonBlank(toAccount, "toAccount");
        Validation.requirePositive(amount);
    }
}
