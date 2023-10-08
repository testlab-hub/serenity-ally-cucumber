package starter.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import starter.pages.LoginPage;

public class NavigateTo {
    public static Performable theLoginPage() {
        return Task.where("{0} opens the OreangeHRM home page",
                Open.browserOn().the(LoginPage.class));

    }
}
