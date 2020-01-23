import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class InstaService {

    private static WebDriver driver;

    public static void likeService()
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver");

        driver = new ChromeDriver();

        int minSleep = 1000;
        int maxSleep = 3000;
        int minArticle = 1;
        int maxArticle = 2;
        boolean indexingFromZero = false;
//            WebDriver driver = new ChromeDriver();
        String baseUrl = "https://www.instagram.com/accounts/login/?source=auth_switcher";

        driver.get(baseUrl);

        FluentWait<WebDriver> webDriverWait = new WebDriverWait(driver, 30).pollingEvery(Duration.ofSeconds(1));
        webDriverWait.until(ExpectedConditions.urlToBe("https://www.instagram.com/"));

        driver.findElement(By.className("aOOlW")).click();
        WebElement exploreButton = driver.findElement(By.xpath("//a[@href='/explore/']"));
        exploreButton.click();
        try {
            int randomNum = ThreadLocalRandom.current().nextInt(minSleep, maxSleep + 1);
            Thread.sleep(randomNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true)
        {
            for (int i = 0; i <= DataStorage.hashTags.size(); i++) {
                if (i == DataStorage.hashTags.size()) {
                    i = 0;
                }

                driver.navigate().to("http://www.instagram.com/explore/tags/" + DataStorage.hashTags.get(i) + "/");
                List<WebElement> articleList = driver.findElements(By.xpath("//div[contains(@class, 'v1Nh3')]"));
                List<WebElement> myElements = new ArrayList<>();

                for (WebElement articles : articleList)
                {

                    int randomArticleClick = ThreadLocalRandom.current().nextInt(minArticle, maxArticle + 1);
                    if (randomArticleClick != 1) {
                        articles.click();
                    } else {
                        continue;
                    }

                    try
                    {
                        int randomNum = ThreadLocalRandom.current().nextInt(minSleep, maxSleep + 1);
                        Thread.sleep(randomNum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try
                    {
                        WebElement likeButton = driver.findElement(By.xpath("//*[name()='svg' and @aria-label='Like' and @height='24']"));
                        likeButton.click();
                    } catch (NoSuchElementException | StaleElementReferenceException e) {
                        System.out.println("Article already liked");
                    }

                    try
                    {
                        int randomNum = ThreadLocalRandom.current().nextInt(minSleep, maxSleep + 1);
                        Thread.sleep(randomNum);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    WebElement exitButton = driver.findElement(By.xpath("//button[contains(@class, 'ckWGn')]"));
                    exitButton.click();
                    try
                    {
                        int randomNum = ThreadLocalRandom.current().nextInt(minSleep, maxSleep + 1);
                        Thread.sleep(randomNum);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();

                    }
                    myElements.add(articles);
                    if (myElements.size() > 20)
                    {
                        break;
                    }
                }
            }

        }

    }

    public static void addHashTagService(String text)
    {
        DataStorage.hashTags.addElement(text);
        System.out.println(DataStorage.hashTags);

    }
    public static void stopService()
    {
        driver.close();

    }
    public static void clearService()
    {
        DataStorage.hashTags.clear();
    }

}

