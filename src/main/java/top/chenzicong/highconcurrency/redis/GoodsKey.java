package top.chenzicong.highconcurrency.redis;

import top.chenzicong.highconcurrency.model.Goods;

public class GoodsKey extends BasePrefix {
    public GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static GoodsKey getGoodsList = new GoodsKey(60, "gl");
}
