package es.s2o.selenium.core.pages;

import es.s2o.selenium.core.domain.User;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends PageObjectBase {

    @FindBy(id = "username")
    WebElementFacade usernameTxt;
    WebElementFacade password;

    @FindBy(css = "#login > button > i")
    WebElementFacade button;

    public void login(User user){
        typeInto(usernameTxt, user.getUserName());
        typeInto(password, user.getPassword());
        button.click();
    }

}
