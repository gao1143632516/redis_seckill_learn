import com.order.OrderApplication;
import com.order.dao.OrderDao;
import com.order.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author gmq
 * @create 2020-09-14 14:02
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrderApplication.class})
public class SpringbootTests {
    @Resource
    private OrderDao orderDao;

    @Test
    public void testSaveOrder(){
        Order order = new Order();
        order.setUserId(1);
        order.setGoodsId(1);
        order.setSeckillId(1);
        order.setOrderNum("1111");
        orderDao.saveOrder(order);
    }
    @Test
    public void getOrderInfo(){
        List<Map<String, Object>> list = orderDao.getOrderInfo();
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
