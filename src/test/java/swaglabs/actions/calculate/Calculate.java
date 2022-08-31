package swaglabs.actions.calculate;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.ui.Button;

public class Calculate {

    public static Performable theAnswerTo(String firstNumber, String secondNumber, String operation) {
        return Task.where("{0} calculates " + firstNumber + " " + operation + " " + secondNumber,
                Enter.theValue(firstNumber).into("#number1Field"),
                Enter.theValue(secondNumber).into("#number2Field"),
                SelectFromOptions.byVisibleText(operation).from("#selectOperationDropdown"),
                Click.on(Button.withText("Calculate"))
        );
    }
}
