package swaglabs.actions.state;

import net.serenitybdd.screenplay.Performable;

public class Reset {
    public static Performable applicationState() {
        return BrowserTask.perform(
                browser -> {
                    browser.evaluateJavascript("window.localStorage.clear()");
                    browser.getDriver().navigate().refresh();
                }
        ).withNoReporting();
    }
}
