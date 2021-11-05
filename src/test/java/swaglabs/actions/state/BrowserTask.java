package swaglabs.actions.state;

import net.serenitybdd.markers.CanBeSilent;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import java.util.function.Consumer;

/**
 * Perform an action directly with the Serenity WebDriver API
 */
public class BrowserTask implements Performable, CanBeSilent {

    private final Consumer<BrowseTheWeb> action;
    private boolean isSilent = false;

    public BrowserTask(Consumer<BrowseTheWeb> action) {
        this.action = action;
    }

    public static BrowserTask perform(Consumer<BrowseTheWeb> action) {
        return new BrowserTask(action);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        action.accept(BrowseTheWeb.as(actor));
    }

    @Override
    public boolean isSilent() {
        return isSilent;
    }

    public Performable withNoReporting() {
        this.isSilent = true;
        return this;
    }
}
