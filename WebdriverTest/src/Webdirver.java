import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Webdirver {

  private WebDriver driver;
  private String baseUrl;
  
  HandleCsv csv = new HandleCsv();//�����Ǵ�info.csv�ļ��ж�ȡ��ѧ����������
  String[] user = new String[109];
  String[] email = new String[109];
  String[] password = new String[109];
  //int a = 1;
  //int b = 2;
 // int c = 3;
  
  @Before
  public void setUp() throws Exception {
	 //�ҵĻ�������û��Ĭ�ϰ�װ����Ҫ�Լ��ڳ��������ð�װ·��
	System.setProperty("webdriver.firefox.bin","D:\\Mozilla Firefox\\firefox.exe");//������������
    //����һ��FireFox�������ʵ��
	driver = new FirefoxDriver();

    baseUrl = "http://www.ncfxy.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testWebdirver() throws Exception {

    //��ȡinfo.csv�е�����
    
    user = csv.readCsv(1);
    email = csv.readCsv(2);
    password = csv.readCsv(3);
    
    for(int i=0;i<109;i++){
  	  
   	 	//�������������վhttp://www.ncfxy.com/
    	driver.get(baseUrl + "/");
    	//ͨ��id��name���ҵ�input��DOM
        driver.findElement(By.id("name")).clear();
        //����ؼ���
        driver.findElement(By.id("name")).sendKeys(user[i]);
        driver.findElement(By.id("pwd")).clear();
        driver.findElement(By.id("pwd")).sendKeys(password[i]);
        driver.findElement(By.id("submit")).click();
        System.out.println("user:" + user[i] + " Login result:");
        //Ѱ�������ǩ
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
