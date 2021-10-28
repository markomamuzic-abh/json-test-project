package substeps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;


public class generic_substeps {
    String url = "https://jsonplaceholder.typicode.com/";

    private static String newAlbumBody = "{\n" +
            "  \"userId\": \"1\",\n" +
            "  \"id\": \"101\",\n" +
            "  \"title\": \"new album\" \n}";
    private static String updatedAlbumBody = "{\n" +
            "  \"userId\": \"1\",\n" +
            "  \"id\": \"1\",\n" +
            "  \"title\": \"updated album\" \n}";
    private static String newUserBody = "{\n" +
            "  \"id\": \"1\",\n" +
            "  \"name\": \"Otis Driftwood\",\n" +
            "  \"username\": \"Bret\",\n" +
            "  \"email\": \"Sincere@april.biz\",\n" +
            "  \"address\": " + "{\n" +
            "    \"street\": \"Kulas Light\",\n" +
            "    \"suite\": \"Apt. 556\",\n" +
            "    \"city\": \"Gwenborough\",\n" +
            "    \"zipcode\": \"92998-3874\",\n" +
            "    \"geo\": " + "{\n" +
            "      \"lat\": \"-37.3159\",\n" +
            "      \"lng\": \"81.1496\"\n" +
            "     }\n" +
            "    },\n" +
            "  \"phone\": \"1-770-736-8031 x56442\",\n" +
            "  \"website\": \"hildegard.org\",\n" +
            "  \"company\":" + "{\n" +
            "    \"name\": \"Romaguera-Crona\",\n" +
            "    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "    \"bs\": \"harness real-time e-markets\"\n" +
            "     }\n}";


    public void CreateNewUser() {
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(newUserBody)
                .when()
                .post(url + "users")
                .then()
                .extract().response();
        assertEquals(201,response.statusCode());
        assertEquals("Otis Driftwood", response.jsonPath().getString("name"));
        assertEquals("Sincere@april.biz", response.jsonPath().getString("email"));
    }

    /*public void GetUserById() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get( url +"users/1")
                .then()
                .extract().response();
        assertEquals(200,response.statusCode());
        assertEquals("Bret",response.jsonPath().getString("username"));
    }*/
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
   /* public void GetListOfAllAlbums() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get( url +"albums")
                .then()
                .extract().response();
        assertEquals(200,response.statusCode());
        assertEquals("eaque aut omnis a",response.jsonPath().getString("title[4]"));
    }*/
   public void GetAlbumById() {
       Response response = RestAssured.given()
               .contentType(ContentType.JSON)
               .when()
               .get( url +"albums/100")  /* try with id=101 */
               .then()
               .extract().response();
       assertEquals(200,response.statusCode());
       assertEquals("10",response.jsonPath().getString("userId"));
       assertEquals("100",response.jsonPath().getString("id"));
       assertEquals("enim repellat iste",response.jsonPath().getString("title"));
   }
   public void UpdateAlbum() {
       Response response = RestAssured.given()
               .header("Content-type", "application/json")
               .and()
               .body(updatedAlbumBody)
               .when()
               .put( url + "albums/1")/* try with id=101 */
               .then()
               .extract().response();
       assertEquals(200,response.statusCode());
       assertEquals("1", response.jsonPath().getString("userId"));
       assertEquals("1",response.jsonPath().getString("id"));
       assertEquals("updated album", response.jsonPath().getString("title"));
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
 /*   public void DeleteUser () {
        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .when()
                .delete(url + "users/1")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals("{}",response.jsonPath().get().toString());
    }*/
 public void DeleteAlbum () {
     Response response = RestAssured.given()
             .header("Content-type", "application/json")
             .when()
             .delete(url + "albums/101")  /* try with id=101 */
             .then()
             .extract().response();

     assertEquals(200, response.statusCode());
     assertEquals("{}",response.jsonPath().get().toString());
 }
}
