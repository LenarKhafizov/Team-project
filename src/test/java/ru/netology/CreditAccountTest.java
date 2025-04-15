package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.add(3_000);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToInitialBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.add(3_000);
        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void shouldAccountNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    5_000,
                    -15
            );
        });
    }

    @Test
    public void shouldAccountNegativeInitialBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -1_000,
                    5_000,
                    15
            );
        });
    }

    @Test
    public void shouldAccountNegativeCreditLimit() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    1_000,
                    -5_000,
                    15
            );
        });
    }

    @Test
    public void shouldPayBollWithPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertTrue(account.pay(3_000));
    }

    @Test
    public void shouldPayBollNegativeAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertFalse(account.pay(- 3_000));
    }

    @Test
    public void shouldPayBollAmountAboveLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertFalse(account.pay(7_000));
    }

    @Test
    public void shouldPayBollAmountWithBalanceLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertTrue(account.pay(6_000));
    }

    @Test
    public void shouldPayWithPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(3_000);
        Assertions.assertEquals(- 2_000, account.getBalance());
    }

    @Test
    public void shouldPayWithZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(3_000);
        Assertions.assertEquals(-3_000, account.getBalance());
    }

    @Test
    public void shouldPayAmountAboveLimit() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(6_000);
        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldPayAmountWithBalanceLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(6_000);
        Assertions.assertEquals(- 5_000, account.getBalance());
    }

    @Test
    public void shouldPayAmountAboveBalanceLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        account.pay(7_000);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldYearChangePositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );
        Assertions.assertEquals(0, account.yearChange());

    }

    @Test
    public void shouldYearChangeNegativeBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(1_000);
        Assertions.assertEquals(- 150, account.yearChange());
    }
}
