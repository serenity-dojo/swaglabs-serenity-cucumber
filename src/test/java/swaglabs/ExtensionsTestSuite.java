package swaglabs;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"progress"},
        features = "classpath:features/extensions",
        glue = {"swaglabs"}
)
public class ExtensionsTestSuite {
}
