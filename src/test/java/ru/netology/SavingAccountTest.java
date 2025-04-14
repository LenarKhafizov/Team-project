package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldReturnCorrectBalanceAfterPayment() { //уменьшение баланса на сумму покупки
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        account.pay(500);

        Assertions.assertEquals(1_500, account.getBalance());
    }

    @Test
    public void purchaseForAnAmountExceedingTheMinimumBalance() { // покупка на сумму превышающий минимальный баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        account.pay(5_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void purchaseForAnAmountEqualToTheBalance() { // покупка на сумму равная балансу
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        account.pay(2_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldReturnCorrectBalanceAfterAddition() {  //пополнение баланса на сумму пополнения
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        account.add(4_000);

        Assertions.assertEquals(6_000, account.getBalance());
    }


    @Test
    public void addingMoreThanTheMaximumAmountToYourBalance() { //пополнение баланса ПРЕВЫШАЮЩИЙ МАХ БАЛАНС
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        account.add(10_000);

        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test
    public void shouldAllowAddingUpToMaxBalance() {  //пополнение баланса до мах значения
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        Assertions.assertEquals(10_000, account.getBalance());
    }

    @Test
    public void shouldThrowExceptionForNegativeRate() { //Проверка инициализации с отрицательной ставкой
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    5_000,
                    1_000,
                    10_000,
                    -5);
        });
    }

    @Test
    public void shouldCalculateYearlyInterestCorrectly() { // Проверка расчёта процентов
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                10);

        Assertions.assertEquals(200, account.yearChange());
    }

    @Test
    public void shouldReturnMinBalance() { //проверка корректного мин баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        Assertions.assertEquals(1_000, account.getMinBalance());
    }

    // Проверяет, что геттер возвращает корректный maxBalance
    @Test
    public void shouldReturnMaxBalance() { //проверка корректного макс баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        Assertions.assertEquals(10_000, account.getMaxBalance());
    }

}


