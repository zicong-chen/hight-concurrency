package top.chenzicong.highconcurrency.elasticsearchTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.chenzicong.highconcurrency.model.Commodity;
import top.chenzicong.highconcurrency.service.CommodityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    CommodityService commodityService;

    @Test
    public void insertTest() {
        Commodity commodity = new Commodity();
        commodity.setSkuId("1501009001");
        commodity.setName("原味切片面包（10片装）");
        commodity.setCategory("101");
        commodity.setPrice(880);
        commodity.setBrand("良品铺子");
        commodityService.save(commodity);

        commodity = new Commodity();
        commodity.setSkuId("1501009002");
        commodity.setName("原味切片面包（6片装）");
        commodity.setCategory("101");
        commodity.setPrice(680);
        commodity.setBrand("良品铺子");
        commodityService.save(commodity);

        commodity = new Commodity();
        commodity.setSkuId("1501009004");
        commodity.setName("元气吐司850g");
        commodity.setCategory("101");
        commodity.setPrice(120);
        commodity.setBrand("百草味");
        commodityService.save(commodity);



    }


    @Test
    public void testQuery(){
        Iterable<Commodity> commodities = commodityService.getAll();
        commodities.forEach(System.out::println);
    }
}
