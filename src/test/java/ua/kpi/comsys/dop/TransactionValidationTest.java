package ua.kpi.comsys.dop;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionValidationTest {

    private static final BigDecimal VALID_AMOUNT = new BigDecimal("10.00");

    @Nested
    class DepositValidation {

        @Test
        void validDepositIsCreated() {
            assertDoesNotThrow(() -> new Deposit("ACC-1", VALID_AMOUNT));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void blankAccountIdThrows(String accountId) {
            assertThrows(IllegalArgumentException.class, () -> new Deposit(accountId, VALID_AMOUNT));
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"0", "0.00", "-1", "-0.01"})
        void nonPositiveAmountThrows(BigDecimal amount) {
            assertThrows(IllegalArgumentException.class, () -> new Deposit("ACC-1", amount));
        }
    }

    @Nested
    class WithdrawalValidation {

        @Test
        void validWithdrawalIsCreated() {
            assertDoesNotThrow(() -> new Withdrawal("ACC-1", VALID_AMOUNT, "Food"));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t"})
        void blankAccountIdThrows(String accountId) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Withdrawal(accountId, VALID_AMOUNT, "Food"));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t"})
        void blankCategoryThrows(String category) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Withdrawal("ACC-1", VALID_AMOUNT, category));
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"0", "-5"})
        void nonPositiveAmountThrows(BigDecimal amount) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Withdrawal("ACC-1", amount, "Food"));
        }
    }

    @Nested
    class TransferValidation {

        @Test
        void validTransferIsCreated() {
            assertDoesNotThrow(() -> new Transfer("ACC-1", "ACC-2", VALID_AMOUNT));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t"})
        void blankFromAccountThrows(String from) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Transfer(from, "ACC-2", VALID_AMOUNT));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t"})
        void blankToAccountThrows(String to) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Transfer("ACC-1", to, VALID_AMOUNT));
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {"0", "-100"})
        void nonPositiveAmountThrows(BigDecimal amount) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Transfer("ACC-1", "ACC-2", amount));
        }
    }
}
