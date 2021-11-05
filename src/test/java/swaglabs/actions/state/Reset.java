package swaglabs.actions.state;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Browser;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Link;

public class Reset {
    public static Performable applicationState() {
        return Task.where(
                Click.on(Button.called("Open Menu")),
                Click.on(Link.called("Reset App State")),
                Browser.refreshPage()
        ).withNoReporting();
    }
}
