package swaglabs.stepdefinitions;

import io.cucumber.java.After;
import swaglabs.actions.state.Reset;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class SetupAndTeardownHooks {

    @After("@resetappstate")
    public void clearSession() {
        theActorInTheSpotlight().attemptsTo(
                Reset.applicationState()
        );
    }
}
