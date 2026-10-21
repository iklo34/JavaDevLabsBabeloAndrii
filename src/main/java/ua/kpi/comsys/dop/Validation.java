package ua.kpi.comsys.dop;

import java.math.BigDecimal;

/**
 * Спільні перевірки для конструкторів записів.
 */
final class Validation {

    private Validation() {
    }

    static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be null or blank");
        }
    }

    static void requirePositive(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("amount must be greater than 0");
        }
    }
}
