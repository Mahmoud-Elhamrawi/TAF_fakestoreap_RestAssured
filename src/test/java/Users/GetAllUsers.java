package Users;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllUsers {

    @Test
    public void getUsers()
    {
        Response res = given().baseUri("https://fakestoreapi.com")
                .pathParam("user","users")
                .when().get("/{user}");
        res.prettyPrint();
        Assert.assertEquals(res.statusCode() , 200);

        System.out.println(res.body().jsonPath().getString("id"));

        for (int i=0 ;i<10;i++)
        {
            Assert.assertEquals(res.body().jsonPath().getInt("id["+i+"]") , i+1);

        }

    }

   @Test
    public void getSingleUser()
   {
       Response res = given().baseUri("https://fakestoreapi.com")
               .pathParam("user","users")
               .pathParam("userID","1")
               .when().get("/{user}/{userID}") ;

       res.prettyPrint();
       Assert.assertEquals(res.statusCode(),200);
       Assert.assertEquals(res.body().jsonPath().getInt("id") , 1);
   }


   @Test
    public void getLimitUser()
   {
       Response res = given().baseUri("https://fakestoreapi.com")
               .pathParam("user","users")
               .queryParam("limit","4")
               .when().get("/{user}") ;

       res.prettyPrint();
       Assert.assertEquals(res.statusCode() , 200);
       System.out.println(res.body().jsonPath().getString("id"));


       for (int i=0 ; i<4;i++)
       {
           Assert.assertEquals(res.body().jsonPath().getInt("id["+i+"]") , i+1);
       }


   }


   @DataProvider(name = "sort")
   public Object[] sort()
   {
       Object typeSort[]={"desc","asc"};
       return typeSort;
   }

   @Test(dataProvider = "sort")
    public void getUsersSort(String type)
   {
       Response res = given().baseUri("https://fakestoreapi.com")
               .pathParam("user","users")
               .queryParam("sort",type)
               .when().get("/{user}") ;

       res.prettyPrint() ;
       Assert.assertEquals(res.statusCode() , 200 );
   }



}
