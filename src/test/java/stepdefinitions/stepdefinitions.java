package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import substeps.generic_substeps;


public class stepdefinitions {
    @Steps
    generic_substeps theApplication;
    @Given("user has access to JsonPlaceholder")
    public void userHasAccessToJsonPlaceholder() {
        theApplication.GetUserById();
    }

    @When("user successfully created album")
    public void userSuccessfullyCreatedAlbum() {
        theApplication.CreateNewAlbum();
    }

    @And("user is able to list all of the albums")
    public void userIsAbleToListAllOfTheAlbums() {
        theApplication.GetListOfAllAlbums();
    }

    @Then("verify the album belongs to the appropriate user")
    public void verifyTheAlbumBelongsToTheAppropriateUser() {
        theApplication.VerifyTheAlbumBelongsToTheAppropriateUser();
    }

    @And("delete user")
    public void deleteUser() {
        theApplication.DeleteUser();
    }
}
