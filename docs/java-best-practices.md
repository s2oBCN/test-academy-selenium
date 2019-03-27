# Java Best practices

* Download [JDK](https://drive.google.com/open?id=1vKeS7olEr9nmoA3m31gmEhNoa8kztyI5).
* Read the [Java Naming Conventions]:http://www.oracle.com/technetwork/java/codeconventions-135099.html
* Read [the Coding style guide](style-guide.md)

And take care of:
* Methods in well-designed object-oriented code are short. Strive to keep methods less than 10 lines. Reconsider methods that are over a page in length, breaking them into several methods representing smaller blocks of functionality.
* Break up long methods into small methods. This promotes code reuse and allows for more combinations of methods. If the number of methods grows to be difficult to understand, then look at decomposing the class into more than one class.
* Follow the 30-second rule. Another programmer should be able to look at your method and fully understand what it does, why it does it and how it does it in less than 30-seconds. If that is not possible, then your code is too complex and difficult to maintain. A good rule of thumb is that a method should be no more than a screen in length.
* Keep it simple
* Avoid nesting blocks of statements more than 2 or 3 levels deep. This adds to the complexity of the code. A method should be easy to read and understand. Easy to maintain is the goal.
* Avoid nesting method calls too deeply, since this can introduce undue complexity.
* Avoid using compound predicates:

     if (x>0 && x<100 && y>0 && y<100 || z==1000)

     Think of all the combinations you will have to write to adequately test the above condition (2^5 or 32 different combinations).


- Every day: Revisit, Refactor, and Improve -> left the 'things' better than you meet

- Never write anything if it is not necessary.
  For example, do not define elements in a page object that maybe we will not use.

## Samples

- Use functional names when possible (vs use html names)

    Example:
```groovy
        BAD:
            "clickBtnGetStatus"
         OK:
            "showStatus"
```

- Selenium framework encapsulated in PageObject.
    So to access a text field you should have accessor methods that take and return a string, check boxes should use booleans, and buttons should be represented by action oriented method names.
     In general page object operations should return fundamental types (strings, dates) or other page objects.
     I favor having no assertions in page objects
    "Having page objects be responsible for creating other page objects in response to things like navigation is common advice. However some practitioners prefer that page objects return some generic browser context, and the tests control which page objects to build on top of that context based on the flow of the test (particularly conditional flows). Their preference is based on the fact that the test script knows what pages are expected next and this knowledge doesn't need to be duplicated in the page objects themselves."
    --> In our case we use IOC to inject pages, so we don't need to retun a PageObject from a PageObject.(http://martinfowler.com/bliki/PageObject.html)

    Example:
```groovy
        BAD:
            @Step
            public void clickBtnIsModified() {
                liSjsonListBoxSelectorPage.getBtnIsModified().click(); --> we are expossing de WebElementFacade 'BtnIsModified'
            }
        OK:
            @Step
            public void clickBtnGetSelected() {
                liSjsonListBoxSelectorPage.checkSelected();
            }
```

- Never put call of a method inside an Assert, it has an special timeout:
    Example:
```groovy
        BAD:
            Assert.assertNotNull("Error popup must appear",popupWidget.isDisplayed());
        OK:
            boolean isPopupDisplayed = popupWidget.isDisplayed()
            Assert.assertTrue("Error popup must appear",isPopupDisplayed);
```

 - When put @Step? Only if required! On every @Step Serenity will take two screenshots, one before the executiion and the other after the execution.
```groovy
         BAD:
            @Step
            public void clickBtnIsModified() {
                liSjsonListBoxSelectorPage.getBtnIsModified().click();
            }
        OK:
            public void clickBtnGetSelected() {
                liSjsonListBoxSelectorPage.checkSelected();
            }
```
* Selectors approach. In order of preference:
  - By id. Example:
```java
private WebElementFacade btnOK;
```
  - By name (if it is unique)
        Example:
```groovy
@FindBy(name="btnG")
WebElementFacade searchButton;
```
  - By cssSelector: [https://saucelabs.com/resources/articles/selenium-tips-css-selectors]https://saucelabs.com/resources/articles/selenium-tips-css-selectors
        Example:
```groovy
@FindBy(css = ".Appcombo_menu")
private WebElementFacade appcomboMenu;

Also in Infinity we can use "zentext" to find objects
  @FindBy(css = td[zentext='hola']
```
- Allways "optimize imports" --> IntelliJ settings or Ctrl + Alt + O

- Injection of components (pages) without any annotation
    Example:
```groovy
        BAD:
            @Steps
            private PopupWidget popupWidget; --> It is not Ok because "popupWidget" extends a PageObject
        OK:
           private PopupWidget popupWidget;
```

- Use LOGGER to log details of the execution:
```groovy

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import java.lang.invoke.MethodHandles;
    ....
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
```
- Om every page function put this line as template:
```groovy
  Example:

  public void test(){

        switchToFrame(IFRAME);
        ....

    }
```
- All the asserts should be only in StepsDefinitions classes, and using: org.assertj.core.api.Assertions.assertThat
    @See: http://joel-costigliola.github.io/assertj/

- Finding improvements:
```groovy
            StepDef:
                private LISjsProviderPage liSjsProviderPage;

                @Steps
                private LISjsProviderSteps lisjsProviderSteps;

                @Then("^verify 'Success' and 'Always' callbacks executed$")
                public void verifyAndCallbacksExecuted() {
                    Assert.assertTrue(lisjsProviderSteps.getResultSuccessAlways().contains("Succes")
                            && lisjsProviderSteps.getResultSuccessAlways().contains("Always"));
                }
            Step:
                public String getResultSuccessAlways() {
                    return liSjsProviderPage.getResultSuccessAlways();
                }

            Page:
                private static final String RESULT_200_OUTPUT_FIELD = "div[id='partialResult200']";

                public String getResultsSuccessAlways() {
                    return $.byCSSSelector(RESULT_200_OUTPUT_FIELD).getText();
                }
```

Result:
 * The step is not needed
 * Only one access to the browser
 * AseertJ use
```groovy
        StepDef:
			private LISjsProviderPage liSjsProviderPage;

            @Then("^verify 'Success' callback executed$")
            public void verifySuccessCallbacksExecuted() {
                String actual = liSjsProviderPage.getSuccessCallback();
                assertThat(actual).contains(SUCCES).contains(ALWAYS);
            }
        Page:
            private WebElementFacade partialResult200;

            public String getSuccessCallback() {
                return partialResult200.getText();
            }
```

* XPath is the last tecnice to use, but if you need, this is an exemple of how it works:
 ```html
    <a>
    <li> parent 1
        <div>
            <span class="child-title child-style">title 1</span>
            <span class="child-date child-style"> date 1</span>
            <span class="child-author">author 1</span>
        </div>
    </li>
    </a>
 ```
If you want to get Date Info, you should use
```groovy
  WebElementFacade parent = driver.findElement(By.xpath("//a/li"));
  WebElementFacade date = parent.findElement(By.xpath("div/span[contains(@class, 'child-date')]"));
  WebElementFacade date = parent.findElement(By.xpath("//span[contains(@class, 'child-date')]"))
```

