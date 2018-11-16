import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sparta on 2017/10/27.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath:config/web/web-main.xml", "classpath:config/spring/*.xml"}) //加载配置文件
public class BaseTest {

}
