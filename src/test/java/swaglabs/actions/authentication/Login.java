package swaglabs.actions.authentication;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.InputField;
import swaglabs.model.Customer;

public class Login {
    public static Performable withCredentials(String username, String password) {
        return Task.called("{0} logs in with username " + username + " and password " + password)
                .whereTheActorAttemptsTo(
                        Enter.theValue(username).into(InputField.called("Username")),
                        Enter.theValue(password).into(InputField.called("Password")),
                        Click.on(Button.called("Login"))
                );
    }

    public static Performable as(String customerName) {
        Customer customer = Customer.withName(customerName);
        return withCredentials(customer.getUsername(), customer.getPassword());
    }

}
