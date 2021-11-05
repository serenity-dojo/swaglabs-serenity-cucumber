package swaglabs.actions.state;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Browser;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Link;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;

public class Reset {
    private static final Target RESET_APP_STATE = Link.called("Reset App State");
    private static final Target MENU_BUTTON = Button.called("Open Menu");

    public static Performable applicationState() {
        return Task.where(
                Click.on(MENU_BUTTON),
                WaitUntil.the(RESET_APP_STATE, isPresent()),
                Click.on(RESET_APP_STATE),
                Browser.refreshPage()
        ).withNoReporting();
    }
}
