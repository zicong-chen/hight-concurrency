package top.chenzicong.highconcurrency.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.chenzicong.highconcurrency.dao.GoodsDao;
import top.chenzicong.highconcurrency.vo.GoodsVo;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsDaoTest {

    @Autowired
    GoodsDao goodsDao;

    @Test
    public void getGoodsList(){
        List<GoodsVo> goodsVos = goodsDao.listGoodsVo();
    }
}
