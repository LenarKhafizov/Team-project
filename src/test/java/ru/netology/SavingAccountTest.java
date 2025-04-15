package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // pay

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

    // add

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
    public void addingMoreThanTheMaximumAmountToYourBalance() { //пополнение баланса превышающий мах баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        account.add(15_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    // конструтор

    @Test
    public void shouldThrowExceptionForNegativeRate() { //Отрицательная ставка
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    -5);
        });
    }

    @Test
    public void ItShouldQenerateAnExceptionIfThereIsABalanceOfValidValues() { // баланс допустимых значений.
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldThrowExceptionIfMinBalanceMoreThanMaxBalance() { // Минимальный баланс больше максимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    2_000,
                    12_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldThrowExceptionIfInitialBalanceLessThanMin() { // Начальный баланс меньше минимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    500,
                    1_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldThrowExceptionIfInitialBalanceGreaterThanMax() { // Начальный баланс больше максимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    15_000,
                    1_000,
                    10_000,
                    5);
        });
    }

    // yearChange

    @Test
    public void shouldCalculateYearlyInterestCorrectly5Percent() { // Проверка расчёта процентов
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        Assertions.assertEquals(100, account.yearChange());
    }

    @Test
    public void shouldCalculateYearlyInterestCorrectly() { // Проверка расчёта процентов c другой % ставкой
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                10);

        Assertions.assertEquals(200, account.yearChange());
    }

    // гетторы

    @Test
    public void checkingTheCurrentBalance() {  // проверка текущего баланса
        SavingAccount account = new SavingAccount(
                7_000,
                1_000,
                10_000,
                5);

        Assertions.assertEquals(7_000, account.getBalance());
    }


    @Test
    public void shouldReturnMinBalance() { //проверка корректного мин значения баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        Assertions.assertEquals(1_000, account.getMinBalance());
    }

    @Test
    public void shouldReturnMaxBalance() { //проверка корректного макс значения баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5);

        Assertions.assertEquals(10_000, account.getMaxBalance());
    }
}


