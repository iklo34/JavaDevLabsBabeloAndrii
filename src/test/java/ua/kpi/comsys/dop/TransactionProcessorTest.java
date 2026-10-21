package ua.kpi.comsys.dop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionProcessorTest {

    private TransactionProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new TransactionProcessor();
    }

    @Test
    @DisplayName("Deposit форматується за шаблоном")
    void processDeposit() {
        var deposit = new Deposit("ACC-001", new BigDecimal("150.75"));

        String expected = """
                Transaction Type: DEPOSIT
                Account: ACC-001
                Amount: USD 150.75""";

        assertEquals(expected, processor.process(deposit));
    }

    @Test
    @DisplayName("Withdrawal форматується за шаблоном")
    void processWithdrawal() {
        var withdrawal = new Withdrawal("ACC-002", new BigDecimal("40.00"), "Groceries");

        String expected = """
                Transaction Type: WITHDRAWAL
                Account: ACC-002
                Category: Groceries
                Amount: USD 40.00""";

        assertEquals(expected, processor.process(withdrawal));
    }

    @Test
    @DisplayName("Transfer форматується за шаблоном")
    void processTransfer() {
        var transfer = new Transfer("ACC-001", "ACC-002", new BigDecimal("1000"));

        String expected = """
                Transaction Type: TRANSFER
                From: ACC-001
                To: ACC-002
                Amount: USD 1000""";

        assertEquals(expected, processor.process(transfer));
    }

    @Test
    @DisplayName("Велика сума виводиться без експоненційного запису")
    void amountIsPrintedInPlainNotation() {
        var deposit = new Deposit("ACC-003", new BigDecimal("1E+3"));

        String expected = """
                Transaction Type: DEPOSIT
                Account: ACC-003
                Amount: USD 1000""";

        assertEquals(expected, processor.process(deposit));
    }

    @Test
    @DisplayName("null кидає IllegalArgumentException з точним повідомленням")
    void processNullThrows() {
        var ex = assertThrows(IllegalArgumentException.class, () -> processor.process(null));
        assertEquals("Transaction cannot be null", ex.getMessage());
    }
}
