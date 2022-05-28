import io.github.bonigarcia.wdm.WebDriverManager;
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

public class HomeTest { // ####### IN-1 to IN-3 from solution Kuba
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        //System.setProperty("webdriver.chrome.driver", "/Users/patittawut/Documents/Selenium-workspace/Driver/chromedriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Sign in as user
        driver.get("https://interview-prep-test.herokuapp.com/");
        driver.findElement(By.xpath("//input[@placeholder='Enter Username']")).sendKeys("test@yahoo.com");
        driver.findElement(By.xpath("//input[@placeholder='Enter Password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Test(testName = "IN-1",description = "open url, login, Title")
    public void pageTitle(){
        // 2. Verify page title - expected "Interview App"
        Assert.assertEquals(driver.getTitle(), "Interview App");
    }

    @Test(testName = "IN-2 - Test Sign Out button", description = "Testing visibility of the Sign out button")
    public void verifySignOutBtn() {
        // 1. Verify Sign out button is present
        //driver.findElement(By.xpath("//u[contains(text(),'Sign out')]")).isDisplayed();
        Assert.assertTrue(driver.findElement(By.xpath("//nav//a/u[text()='Sign out']")).isEnabled());
    }
    @Test(testName = "IN-2 - Test Manage Access button", description = "Testing button is not visible")
    public void verifySignOutBtn2() {
       List<WebElement> elementList = driver.findElements(By.xpath("//*[text()='Manage Access']"));
        Assert.assertEquals(elementList.size(), 0);
    }

    @Test(testName = "IN-3 - Default dashboards", description = "Validate 3 dashboards are present")
    public void verifyBtns(){
        // 1. Verify 3 dashboards present:- All Topics- Coding- Soft skills
        String[] data = {"All Topics", "Coding", "Soft skills"};
        for(String text: data){
            Assert.assertTrue(driver.findElement(
                    By.xpath("//form[@class='form-inline']//button[text()='" + text + "']")).isEnabled());
        }
    }

    @Test(testName = "IN-4")
    public void addTest() throws InterruptedException {
        //Add a statement in Dont's field using letters & numbers only
        driver.findElement(By.xpath("//div[@class='col-md-3 dont']//button")).click();
        driver.findElement(By.id("inputArea2")).sendKeys("test team 5 Don't");
        driver.findElement(By.xpath("(//*[@type='submit'])[5]")).click();
        driver.findElement(By.xpath("(//div[@class='anyClass'])[2]/div[last()]//div/span/button")).click();
    }

    @Test(testName = "IN-5")
    public void dashboardQuestion(){
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

        driver.findElement(By.xpath("//button[@class='btn btn-light mb-2 border shadow-lg rounded']")).click();

        List<WebElement> allQuestions = driver.findElements(By.xpath("//div[@class='col-md-8']"));
        allQuestions.forEach((each -> System.out.println(each.getText())));

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
