package swaglabs.actions.checkout;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;
import swaglabs.model.CustomerDetails;

public class ProvidePersonalDetails {
    public static Performable of(CustomerDetails customerDetails) {
        return Task.where("{0} provides personal details: " + customerDetails,
                Enter.theValue(customerDetails.firstName()).into(InputField.withPlaceholder("First Name")),
                Enter.theValue(customerDetails.lastName()).into(InputField.withPlaceholder("Last Name")),
                Enter.theValue(customerDetails.postCode()).into(InputField.withPlaceholder("Zip/Postal Code")),

                Click.on(Button.withText("Continue"))
        );
    }
}
