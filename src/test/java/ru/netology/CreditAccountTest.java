package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() { // пополнение счета с неотрицательным балансом
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(3_000);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToInitialBalance() { // пополнение счета с положительным балансом
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.add(3_000);
        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void shouldAccountNegativeRate() { // создание счета с отрицательной ставкой кредитования
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    5_000,
                    -15
            );
        });
    }

    @Test
    public void shouldAccountNegativeInitialBalance() { // создание счета с отрицательным начальным балансом
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -1_000,
                    5_000,
                    15
            );
        });
    }

    @Test
    public void shouldAccountNegativeCreditLimit() { // создание счета с отрицательным кредитным лимитом
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    -5_000,
                    15
            );
        });
    }

    @Test
    public void shouldPayBollWithPositiveBalance() { // оплата с начальным балансом в пределах кредитного лимита
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertTrue(account.pay(3_000));
    }

    @Test
    public void shouldPayBollNegativeAmount() { // оплата отрицательной суммы
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertFalse(account.pay(- 3_000));
    }

    @Test
    public void shouldPayBollAmountAboveLimit() { // оплата с начальным балансом сверх кредитного лимита
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertFalse(account.pay(7_000));
    }

    @Test
    public void shouldPayBollAmountWithBalanceLimit() { // оплата в сумме начального баланса и кредитного лимита
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertTrue(account.pay(6_000));
    }

    @Test
    public void shouldPayWithPositiveBalance() { // оплата с начальным балансом в пределах кредитного лимита
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(3_000);
        Assertions.assertEquals(- 2_000, account.getBalance());
    }

    @Test
    public void shouldPayWithZeroBalance() { // оплата с нулевым балансом в пределах кредитного лимита
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(3_000);
        Assertions.assertEquals(-3_000, account.getBalance());
    }

    @Test
    public void shouldPayAmountAboveLimit() { // оплата в сумме, превышающей кредитный лимит
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(6_000);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldPayAmountWithBalanceLimit() { // оплата в сумме начального баланса и кредитного лимита
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(6_000);
        Assertions.assertEquals(- 5_000, account.getBalance());
    }

    @Test
    public void shouldPayAmountAboveBalanceLimit() { // оплата в сумме, превышающей начальный баланс и кредитный лимит
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(7_000);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldYearChangePositiveBalance() { // проценты на положительный остаток на счете
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertEquals(0, account.yearChange());

    }

    @Test
    public void shouldYearChangeNegativeBalance() {   //  проценты на отрицательный остаток на счете
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(1_000);

        Assertions.assertEquals(- 150, account.yearChange());
    }

    @Test
    public void shouldAddToNegativeBalance() { // пополнение счета с отрицательным балансом
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(1000);
        account.add(3_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }

}
