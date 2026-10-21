package ua.kpi.comsys.dop;

/**
 * Обробник транзакцій. Містить лише алгоритм перетворення даних у текст;
 * самі дані описані незмінними записами ієрархії {@link Transaction}.
 */
public class TransactionProcessor {

    public String process(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null");
        }

        return switch (transaction) {
            case Deposit(String accountId, var amount) -> """
                    Transaction Type: DEPOSIT
                    Account: %s
                    Amount: USD %s""".formatted(accountId, amount.toPlainString());

            case Withdrawal(String accountId, var amount, String category) -> """
                    Transaction Type: WITHDRAWAL
                    Account: %s
                    Category: %s
                    Amount: USD %s""".formatted(accountId, category, amount.toPlainString());

            case Transfer(String from, String to, var amount) -> """
                    Transaction Type: TRANSFER
                    From: %s
                    To: %s
                    Amount: USD %s""".formatted(from, to, amount.toPlainString());
        };
    }
}
