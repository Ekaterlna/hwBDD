package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private final SelenideElement amountField = $("[data-test-id='amount'] input");
    private final SelenideElement transferFromCardField = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement errorMessageInsufficientFunds = $("[data-test-id='error-notification-insufficient-funds']");
    private final SelenideElement errorMessageMatch = $("[data-test-id='error-notification-match']");



    public TransferPage() {
        transferButton.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardsInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeTransfer(String sumTransfer, DataHelper.CardsInfo cardInfo) {
        amountField.setValue(sumTransfer);
        transferFromCardField.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public void errorMessageInsufficientFunds(String expectedText){
        errorMessageInsufficientFunds.shouldHave(exactText(expectedText), Duration.ofSeconds(10)).shouldBe(visible);
    }

    public void errorMessageMatch(String expectedText){
        errorMessageMatch.shouldHave(exactText(expectedText), Duration.ofSeconds(10)).shouldBe(visible);
    }
}
