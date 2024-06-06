package Cucumber.CucumberBDD;

import java.util.Optional;
//import io.restassured.path.json.JsonPath;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Request;
import org.openqa.selenium.devtools.v85.network.model.RequestId;
import org.openqa.selenium.devtools.v85.network.model.Response;

import com.jayway.jsonpath.JsonPath;

import gherkin.JSONParser;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;





public class Auth {
	public static void main (String[] args) {

        System.setProperty("webdriver.chrome.driver", "D://chromedriver-win64//chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();

        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(),requestConsume->{
            Request request = requestConsume.getRequest();
            //System.out.println(request.getUrl());
        });
        final RequestId[] requestId = new RequestId[1];
        devTools.addListener(Network.responseReceived(),responseConsume->{
            Response response = responseConsume.getResponse();
            // System.out.println(response.getStatus()+" "+response.getUrl());
            requestId[0] = responseConsume.getRequestId();
          
            if (response.getUrl().contains("ws_api.php?")) {
                //System.out.println(response.getStatus()+" "+response.getUrl());
                //  Assert.assertEquals(response.getStatus(), 200);
                String responseBody =devTools.send(Network.getResponseBody(requestId[0])).getBody();
                System.out.println(responseBody);
                //JSONObject json = new JSONObject(responseBody);
                //String name = json.getString("forecast");
                //System.out.println("name is:"+name);
                //double cool = json.getDouble("request[0].type");
                JsonParser jsParser = new JsonParser();
                JsonObject jsObject = (JsonObject)jsParser.parse(responseBody);
                JsonObject location= jsObject.getAsJsonObject("location");
                String loc = location.get("name").getAsString();
                System.out.println(loc);
                
                
            }

        });
      
        //System.out.println(responseBody);
        driver.get("https://weatherstack.com/");
        driver.quit();
        

    }


}
