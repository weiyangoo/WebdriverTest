import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Webdirver {

  private WebDriver driver;
  private String baseUrl;
  
  HandleCsv csv = new HandleCsv();//里面是从info.csv文件中读取的学号邮箱数据
  String[] user = new String[109];
  String[] email = new String[109];
  String[] password = new String[109];
  //int a = 1;
  //int b = 2;
 // int c = 3;
  
  @Before
  public void setUp() throws Exception {
	 //我的火狐浏览器没有默认安装，需要自己在程序中设置安装路径
	System.setProperty("webdriver.firefox.bin","D:\\Mozilla Firefox\\firefox.exe");//启动火狐浏览器
    //创建一个FireFox的浏览器实例
	driver = new FirefoxDriver();

    baseUrl = "http://www.ncfxy.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testWebdirver() throws Exception {

    //读取info.csv中的数据
    
    user = csv.readCsv(1);
    email = csv.readCsv(2);
    password = csv.readCsv(3);
    
    for(int i=0;i<109;i++){
  	  
   	 	//让浏览器访问网站http://www.ncfxy.com/
    	driver.get(baseUrl + "/");
    	//通过id“name”找到input的DOM
        driver.findElement(By.id("name")).clear();
        //输入关键字
        driver.findElement(By.id("name")).sendKeys(user[i]);
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys(password[i]);
        driver.findElement(By.id("submit")).click();
        System.out.println("user:" + user[i] + " Login result:");
        //寻找邮箱标签
        java.util.List<WebElement> judge = driver.findElement(By.id("table-main")).findElements(By.xpath("//td"));
        if (judge.size() == 0) {
			System.out.println("login fail");
        } 
        else if (email[i].equals(judge.get(1).getText().toString())) {
			System.out.println("login success, and email is right");
		} 
        else {
			System.out.println("login success, but email is error");
		}
     } 
     driver.quit();
   }
}
