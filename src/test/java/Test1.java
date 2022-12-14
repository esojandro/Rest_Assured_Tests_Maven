import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class Test1 { // Page: https://reqres.in/

    @Test
    public void getUsers() {
        baseURI = "https://reqres.in/api";

        String requestBody = given() // Given para enviar un body
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200) //Cod response
                .extract()
                .body()
                .asString();

        System.out.println(requestBody); // Para mostrar el get
    }

    @Test
    public void getEspecificUser() {
        baseURI = "https://reqres.in/api";

        given() // Given para enviar un body
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200) //Cod response
                .body("data[1].first_name", equalTo("Lindsay"));
    }

    @Test
    public void getSingleUser() {
        baseURI = "https://reqres.in/api";

        given() // Given para enviar un body
                .when()
                .get("/users/2")
                .then()
                .statusCode(200); //Cod response
    }

    @Test
    public void postUser() {
        baseURI = "https://reqres.in/api";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Toby");
        map.put("job", "perro guardian");

        given() // Given para enviar un body
                .log().all() // Log para la petición
                .body(map.toString())
                .when()
                .post("/users")
                .then()
                .log().all() // Log para la respuesta
                .statusCode(201); //Cod response
    }

    @Test
    public void updateUser() { //Revisar luego
        baseURI = "https://reqres.in/api";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Toby");
        map.put("job", "perro guardian de galletas");

        given() // Given para enviar un body
                .log().all() // Log para la petición
                .body(map.toString())
                .when()
                .put("/users/2")
                .then()
                .log().all()
                .statusCode(200); //Cod response
    }

    @Test
    public void deleteUser() { //Revisar luego
        baseURI = "https://reqres.in/api";

        given() // Given para enviar un body
                .log().all() // Log para la petición
                .when()
                .delete("/users/2")
                .then()
                .log().all()
                .statusCode(204); //Cod response
    }
}
