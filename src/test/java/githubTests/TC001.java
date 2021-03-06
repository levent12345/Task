package githubTests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class TC001 {

// verifying name, id, status code and status line with using sort and pagination
//****  sort=stars , order=desc (descending order)  ****

    @Test
   public void verifyName1(){

        //Specify base URI

        RestAssured.baseURI="https://api.github.com";

        //Spesify data format

        given().header("Accept","application/json");

        //Response object    (using  query paramater  sort=stars and order=desc)
        //pagination 1 page and each page has 13 element

        Response response=when().get("/search/repositories?q=autor_name&page=1&per_page=13&sort=stars&order=desc");

        //Status Code verification ( I should get status code 200 )

        int statusCode=response.statusCode();
        Assert.assertEquals(response.statusCode(),200);

        //Status Line verification (I should get status line HTTP/1.1 200 OK)

        String statusLine=response.statusLine();
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");

        // Name verification  (first name is = "github_url" )

        String name=response.path("items.name[0]");
        Assert.assertEquals(name,"github_url");

        //Id Verification (first id is =24922317)

        int id=response.path("items.id[0]");
        Assert.assertEquals(id,24922317);

   //*****************************************************************************

        //This part is not nessesary  but I just put it to see result from console
        // Print response on console window

        //All names= store all names inside list.
        List<String> Allnames=response.path("items.name"); // store all names inside list

        // Print response on console window
        System.out.println("Status code is :"+statusCode);
        System.out.println("Status Line is :"+statusLine);
        System.out.println("name: "+name);
        System.out.println("ID : "+id);
        System.out.println("How many names :"+Allnames.size()); //how many names have
        System.out.println("All Names: "+ Allnames);  // All names (list of names)




    }








}
