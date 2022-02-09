package swaglabs.actions.checkout;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ui.Button;

public class Confirm {
    public static Performable order() {
        return Click.on(Button.withText("Finish"));
    }
}
