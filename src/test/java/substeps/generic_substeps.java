package substeps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static org.junit.Assert.assertEquals;

public class generic_substeps {
    String url = "https://jsonplaceholder.typicode.com/";

    private static String newAlbumBody = "{\n" +
            "  \"userId\": \"1\",\n" +
            "  \"id\": \"1\",\n" +
            "  \"title\": \"new album\" \n}";

    public void GetUserById() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get( url +"users/1")
                .then()
                .extract().response();
        assertEquals(200,response.statusCode());
        assertEquals("Bret",response.jsonPath().getString("username"));
    }
    public void CreateNewAlbum() {
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(newAlbumBody)
                .when()
                .post(url + "albums")
                .then()
                .extract().response();
        assertEquals(201,response.statusCode());
        assertEquals("new album", response.jsonPath().getString("title"));
        assertEquals("1", response.jsonPath().getString("userId"));
        assertEquals("101", response.jsonPath().getString("id"));
    }
    public void GetListOfAllAlbums() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get( url +"albums")
                .then()
                .extract().response();
        assertEquals(200,response.statusCode());
        assertEquals("eaque aut omnis a",response.jsonPath().getString("title[4]"));
    }
    public void VerifyTheAlbumBelongsToTheAppropriateUser() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get( url +"albums/9")
                .then()
                .extract().response();
        assertEquals(200,response.statusCode());
        assertEquals("1",response.jsonPath().getString("userId"));
    }
    public void DeleteUser () {
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .when()
                .delete(url + "users/1")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals("{}",response.jsonPath().get().toString());
    }
}
