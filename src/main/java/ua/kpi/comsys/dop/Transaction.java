package ua.kpi.comsys.dop;

/**
 * Фінансова транзакція. Закрита ієрархія: допустимі лише
 * {@link Deposit}, {@link Withdrawal} та {@link Transfer}.
 */
public sealed interface Transaction permits Deposit, Withdrawal, Transfer {
}
