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
    public void userHasAccessToJsonPlaceholder() { theApplication.CreateNewUser(); }

    @When("user successfully created album")
    public void userSuccessfullyCreatedAlbum() {
        theApplication.CreateNewAlbum();
    }

    @And("user is able to get album by id")
    public void userIsAbleToGetAlbumById() {
        theApplication.GetAlbumById();
    }

    @And("user is able to update album")
    public void userIsAbleToUpdateAlbumById() {
        theApplication.UpdateAlbum();
    }

    @Then("verify the album belongs to the appropriate user")
    public void verifyTheAlbumBelongsToTheAppropriateUser() { theApplication.VerifyTheAlbumBelongsToTheAppropriateUser(); }

    @And("delete album")
    public void deleteAlbum() {
        theApplication.DeleteAlbum();
    }
}
