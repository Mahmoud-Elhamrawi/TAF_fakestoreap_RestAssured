package Users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import seialization.userLog;

import static io.restassured.RestAssured.given;

public class userLogin {


   @Test
   public void userLogin() throws JsonProcessingException {

       userLog  login = new userLog() ;
       login.setUsername("mor_2314");
       login.setPassword("83r5^_");

       ObjectMapper mapper = new JsonMapper() ;
        String  data= mapper.writeValueAsString(login);



    Response res = given().baseUri("https://fakestoreapi.com")
            .pathParam("auth","auth")
            .pathParam("login","login")
            .contentType(ContentType.JSON)
            .body(data)
            .when().post("/{auth}/{login}") ;

    res.prettyPrint() ;
       Assert.assertEquals(res.statusCode()  , 200);
          String token =res.body().jsonPath().get("token");
       System.out.println(token);




}





}
