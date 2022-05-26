import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/patittawut/Documents/Selenium-workspace/Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(testName = "IN-1",description = "open url, login")



    public void pageTitle(){
        //1. Open url
        driver.get("https://interview-prep-test.herokuapp.com/");
        // 2. Verify page title - expected "Interview App"
        Assert.assertEquals(driver.getTitle(), "Interview App");
        // 3. Login using username test@yahoo.com
        //                password test123
        driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Test(testName = "IN-2", description = "verify Sign out button")
    // 2. Verify Manage Access button is not present
    public void verifySignOutBtn() {
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // 1. Verify Sign out button is present
        driver.findElement(By.xpath("//u[contains(text(),'Sign out')]")).isDisplayed();
    }

    @Test(testName = "IN-3", description = "verify all dashboards are present")
    public void verifyBtns(){
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // 1. Verify 3 dashboards present:
        // - All Topics
        // - Coding
        // - Soft skills
        List<WebElement> dashboard = driver.findElements(By.xpath("//*[text()='All Topics']"));
        dashboard.forEach((options -> System.out.println(options.isDisplayed())));
    }

    @Test(testName = "IN-4")
    public void addTest() throws InterruptedException {
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Add a statement in Do's field using letters & numbers only
        driver.findElement(By.cssSelector("div[class='col-md-7 do'] button[class='btn btn-success badge-pill newbtn mb-3']")).click();
        driver.findElement(By.id("inputArea1")).sendKeys("test team 5");
        driver.findElement(By.cssSelector("button[class='btn btn-outline-white btn-sm btn-success']")).click();
        driver.findElement(By.xpath("(//div[@class='anyClass'])[1]/div[last()]//div/span/button")).click();
        //Add a statement in Dont's field using letters & numbers only
        driver.findElement(By.xpath("//div[@class='col-md-3 dont']//button")).click();
        driver.findElement(By.id("inputArea2")).sendKeys("test team 5 Don't");
        driver.findElement(By.xpath("(//*[@type='submit'])[5]")).click();
        driver.findElement(By.xpath("(//div[@class='anyClass'])[2]/div[last()]//div/span/button")).click();
    }

    @Test(testName = "IN-5")
    public void dashboardQuestion(){
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Add a question to dashboard accepting only letters, numbers & special characters
        driver.findElement(By.className("codeChallengeImg")).click();
        driver.findElement(By.xpath("//button[text()='Enter new question ']")).click();
        //Add question
        driver.findElement(By.id("question")).sendKeys("QuestionN");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //delete
        driver.findElement(By.xpath("//div[@class='row question-section shadow-sm'][1]/div/span/button[1]")).click();

        //click to logo TLA to dashboard page
        driver.findElement(By.className("logo")).click();

        //click to Soft skills button
        driver.findElement(By.xpath("//button[text()='Soft skills']")).click();
        driver.findElement(By.xpath("//button[text()='Enter new question ']")).click();
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("QuestionS");
        //delete
        driver.findElement(By.xpath("//div//div//div//span/button[1]")).click();
    }

    @Test(testName = "IN-6")
    public void modifyDashQues(){
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.xpath("//button[text()='Soft skills']")).click();
        driver.findElement(By.xpath("//button[text()='Enter new question ']")).click();
        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("QuestionS!@12$$++");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //edit from Test IN-5
        driver.findElement(By.xpath("//div[@class='row question-section shadow-sm'][1]/div/span/button[2]")).click();
        driver.findElement(By.cssSelector("textarea[name='newQuestion']")).sendKeys("123");
        driver.findElement(By.cssSelector("button[class='btn btn-sm-outline-success']")).click();

        //delete
        driver.findElement(By.xpath("//div[2]//div[2]//span[1]//button[1]")).click();
    }

    @Test(testName = "IN-7")
    public void allTopics(){
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.xpath("//button[@class='btn btn-light mb-2 border shadow-lg rounded']")).click();

        List<WebElement> allQuestions = driver.findElements(By.xpath("//div[@class='col-md-8']"));
        allQuestions.forEach((each -> System.out.println(each.getText())));

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
