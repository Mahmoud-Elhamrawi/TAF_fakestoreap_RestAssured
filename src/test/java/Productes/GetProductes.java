package Productes;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetProductes {

 public int idProd = 0;

    @Test
    public void restTest()
    {

        Response res =given().baseUri("https://fakestoreapi.com")
                .pathParam("prd","products")
                .when().get("/{prd}");

        Assert.assertEquals(res.statusCode() ,200);
        res.prettyPrint();

        System.out.println("iDS :" +  res.body().jsonPath().get("id"));
        System.out.println("Fid :" +  res.body().jsonPath().get("id[0]"));
        idProd =res.body().jsonPath().get("id[0]") ;
    }

    @Test(dependsOnMethods = "restTest")
    public void getSpecificProd()
    {
        Response res1 =given().baseUri("https://fakestoreapi.com")
                .pathParam("prod","products")
                .pathParam("id",idProd)
                .when().get("/{prod}/{id}");

        res1.prettyPrint();
        Assert.assertEquals(res1.statusCode() , 200);
         Assert.assertEquals(res1.body().jsonPath().get("title"),"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
    }








    @DataProvider(name = "testID")
    public Object[]IDS()
    {
         Object data []={1,2,3,4};
        return data ;
    }

    @Test(dataProvider = "testID")
    public void getSpecificProdWthDDT(int  id)
    {
        Response res1 =given().baseUri("https://fakestoreapi.com")
                .pathParam("prod","products")
                .pathParam("id",id)
                .when().get("/{prod}/{id}");

        res1.prettyPrint();
        Assert.assertEquals(res1.statusCode() , 200);
        Assert.assertEquals(res1.body().jsonPath().getInt("id"),id);
        System.out.println(res1.body().jsonPath().getInt("id"));
    }




    @Test
    public void Limit_results()
    {
        Response res2 = given().baseUri("https://fakestoreapi.com")
                .pathParam("prod","products")
                .queryParam("limit","5")
                .when().get("/{prod}") ;

        res2.prettyPrint();
        Assert.assertEquals(res2.statusCode() ,200);

        String ids = res2.body().jsonPath().get("id").toString();
        System.out.println(ids);
        Assert.assertFalse(res2.body().jsonPath().get("id").toString().contains("6"));
        Assert.assertTrue(res2.body().jsonPath().get("id").toString().contains("3"));

    }


}
