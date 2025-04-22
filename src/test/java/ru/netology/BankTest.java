package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {
    Bank bank = new Bank();

    @Test
    public void shouldTransferNegativeAmount() {
        SavingAccount account1 = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);
        CreditAccount account2 = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertFalse(bank.transfer(account1, account2, -3_000));
    }

    @Test
    public void shouldTransferZeroAmount() {
        SavingAccount account1 = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);
        CreditAccount account2 = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertFalse(bank.transfer(account1, account2, 0));
    }

    @Test
    public void shouldTransferPositiveAmount() {
        SavingAccount account1 = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5);
        CreditAccount account2 = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertTrue(bank.transfer(account1, account2, 3_000));
    }

    @Test
    public void shouldTransferAmountWithBalanceLimit() { // оплата в сумме начального баланса и кредитного лимита
        CreditAccount account1 = new CreditAccount(
                1_000,
                5_000,
                15
        );
        SavingAccount account2 = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5);
        bank.transfer(account1, account2, 6_000);
        int expected = - 5_000;
        Assertions.assertEquals(expected, account1.getBalance());
    }

    @Test
    public void shouldTransferAmountToBalance() { // поступление в пределах баланса
        CreditAccount account1 = new CreditAccount(
                1_000,
                5_000,
                15
        );
        SavingAccount account2 = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5);
        bank.transfer(account1, account2, 6_000);
        int expected = 7_000;
        Assertions.assertEquals(expected, account2.getBalance());
    }

    @Test
    // оплата в сумме, большей начального баланса и кредитного лимита
    public void shouldTransferAmountAboveBalanceLimitFrom() {
        CreditAccount account1 = new CreditAccount(
                1_000,
                5_000,
                15
        );
        SavingAccount account2 = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5);
        bank.transfer(account1, account2, 7_000);
        int expected = 1_000;
        Assertions.assertEquals(expected, account1.getBalance());
    }

    @Test
    // поступление после оплаты в сумме, большей начального баланса и кредитного лимита
    public void shouldTransferAmountAboveBalanceLimitTo() {
        CreditAccount account1 = new CreditAccount(
                1_000,
                5_000,
                15
        );
        SavingAccount account2 = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5);
        bank.transfer(account1, account2, 7_000);
        int expected = 1_000;
        Assertions.assertEquals(expected, account2.getBalance());
    }

    @Test
    // поступление после оплаты в сумме, большей максимального баланса
    public void shouldTransferAmountAboveMaxBalanceTo() {
        CreditAccount account1 = new CreditAccount(
                5_000,
                5_000,
                15
        );
        SavingAccount account2 = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5);
        bank.transfer(account1, account2, 10_000);
        int expected = 1_000;
        Assertions.assertEquals(expected, account2.getBalance());
    }

    @Test
    // состояние счета отправителя после оплаты в сумме, большей максимального баланса получателя
    public void shouldTransferAmountAboveMaxBalanceFrom() {
        CreditAccount account1 = new CreditAccount(
                5_000,
                5_000,
                15
        );
        SavingAccount account2 = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5);
        bank.transfer(account1, account2, 10_000);
        int expected = 5_000;
        Assertions.assertEquals(expected, account1.getBalance());
    }
}
