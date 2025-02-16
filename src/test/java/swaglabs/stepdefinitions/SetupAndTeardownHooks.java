package swaglabs.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import swaglabs.actions.state.Reset;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class SetupAndTeardownHooks {

    @After
    public void clearSession() {
        theActorInTheSpotlight().attemptsTo(
                Reset.applicationState()
        );
    }
}
