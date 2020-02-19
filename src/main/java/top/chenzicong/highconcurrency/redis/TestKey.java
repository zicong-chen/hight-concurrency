package top.chenzicong.highconcurrency.redis;

public class TestKey extends BasePrefix {
    public TestKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static TestKey testKey = new TestKey(60, "test");
}
