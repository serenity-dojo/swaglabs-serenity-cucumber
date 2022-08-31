package swaglabs.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.CheckboxValue;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.waits.WaitUntil;
import swaglabs.actions.calculate.Calculate;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorStepDefinitions {

    @Given("{actor} has opened the calculator")
    public void has_opened_the_calculator(Actor actor) {
        actor.attemptsTo(
                Open.url("https://testsheepnz.github.io/BasicCalculator.html")
        );
    }

    @When("{actor} performs a {string} operation on {string} and {string}")
    public void performs_a_operation_on_and(Actor actor,
                                            String operation,
                                            String firstNumber,
                                            String secondNumber) {
        actor.attemptsTo(
                Calculate.theAnswerTo(firstNumber, secondNumber, operation),
                WaitUntil.the("#waitGraphic", isNotVisible()).forNoMoreThan(30).seconds()
        );
    }

    @When("{actor} tries to perform a {string} operation on {string} and {string}")
    public void tries_to_performs_a_operation_on_and(Actor actor,
                                            String operation,
                                            String firstNumber,
                                            String secondNumber) {
        actor.attemptsTo(
                Calculate.theAnswerTo(firstNumber, secondNumber, operation)
        );
    }

    @Then("the result should be {string}")
    public void the_result_should_be(String expectedResult) {
        Actor actor = theActorInTheSpotlight();

        String result = actor.asksFor(Value.of("#numberAnswerField"));

        assertThat(result).isEqualTo(expectedResult);
    }

    @When("{actor} activates \"Integers Only\"")
    public void activatesIntegersOnly(Actor actor) {
        boolean integerSelected = actor.asksFor(CheckboxValue.of("#integerSelect"));
        if (!integerSelected) {
            actor.attemptsTo(
                    Click.on("#integerSelect")
            );
        }
    }

    @Then("a {string} error message should be reported")
    public void aErrorMessageShouldBeReported(String expectedError) {
        String actualError = theActorInTheSpotlight().asksFor(Text.of("#errorMsgField"));
        assertThat(actualError).isEqualTo(expectedError);
    }
}
