package StepDefinitions;

import Utils.CommonUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarRegSteps extends CommonUtils {
    public String carRegNum;


    @Given("^I am on Home Page$")
    public void i_am_on_Home_Page() throws Throwable
    {
       String url = readPropertyFile("url");
        driver.get(url);
        driver.manage().window().maximize();
        waitForTime(3);
    }

    @When("^I read data from the input txt file and compare to the output file\\.$")
    public void i_read_data_from_the_input_txt_file_and_compare_to_the_output_file() throws Throwable
    {
        final Pattern pattern = Pattern.compile("[A-Z]{2}[0-9]{2}[ _A-Z]{4}");
        String inputFile = readPropertyFile("InputFile");
        BufferedReader read = readTextFile(inputFile);

        String line;
        while ((line = read.readLine()) != null) {
            final Matcher match = pattern.matcher(line);
            while (match.find()) {
                int start = match.start(0);
                int end = match.end(0);
                carRegNum = line.substring(start, end).replaceAll("\\s+", "");

                driver.findElement(By.id("vrm-input")).sendKeys(carRegNum);
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                driver.findElement(By.xpath("//button[@class='jsx-1095771833 ']")).click();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                if (hasPopupAppeared() == true)
                {
                    driver.findElement(By.xpath("//*[@id=\"m\"]/div[2]/div[12]/div/div/dl/div/dd[2]/a")).click();
                    System.out.println("Specifications did not match for:- "+ carRegNum);
                //
                    driver.findElement(By.id("vrm-input")).clear();
                    driver.navigate().back();
                }
                else
                {
                    String registration = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/span[1]/div[2]/dl[1]/dd[1]")).getText();
                    String make = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/span[1]/div[2]/dl[2]/dd[1]")).getText();
                    String model = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/span[1]/div[2]/dl[3]/dd[1]")).getText();
                    String color = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/span[1]/div[2]/dl[4]/dd[1]")).getText();
                    String year = driver.findElement(By.xpath("//body[1]/div[1]/div[1]/div[2]/div[5]/div[1]/div[1]/span[1]/div[2]/dl[5]/dd[1]")).getText();

                    String outputFile = readPropertyFile("OutputFile");
                    BufferedReader compare = readTextFile(outputFile);

                    String resultLine;
                    for (resultLine = compare.readLine(); resultLine != null; resultLine = compare.readLine())
                    {
                        if (resultLine.contains(registration))
                        {
                            String details[]= resultLine.split(",");
                            if(registration.equals(details[0]))
                            {
                                Assert.assertEquals(make,details[1]);
                                Assert.assertEquals(model,details[2]);
                                Assert.assertEquals(color,details[3]);
                                Assert.assertEquals(year,details[4]);
                                System.out.println("Specifications matched for:- "+ carRegNum);
                            }
                        }
                    }
                }
                waitForTime(4);
                driver.findElement(By.id("vrm-input")).clear();
                driver.navigate().back();
                waitForTime(4);
            }
        }
    }

    @Then("^I should display appropriate results$")
    public void i_should_display_appropriate_results() throws Throwable
    {
       System.out.println("All tests have been executed successfully");
    }
}
