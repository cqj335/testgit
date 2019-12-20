package com.hstc.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101100664308";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIRW0x4iZZ2haJeet6ic3cg/+IT+tb7d/GI4c6CO2Kiv2akITIUcONfW1+IDEAepnz6eLX1k5XD6xhn/hg8sDolsYA3NW742KsbotDHAD57YYivQF9M9O7XGcGOzSZ3dtG84dSCxcHi3S+LxDoQibGXwEHgBPEA9kD9bIZlACOmtAgMBAAECgYAdZzEW8+uB31fewBvsMZYxJM1xtynoU2f85rACD1rh99gzby2ZMZELm6dxht6r5SsFfZ17y8wSkxUqt5QwkMhVu8tV0GdzdkGX2rg8N0obeyrhmY1+fqqsb3AY3C45/NDeGvQhtd0hHPJW5KHijSsLXTMKFWp2a8IKUjEjliHJHQJBAM8YNxBLDyM/Yt71myKHMuXgGtqAS5GkDkwYko+KfTf1M637ed17n/lQjWHtKy2WMSsl1jLaeG7/IHLjiePpfuMCQQCjl1ASMFCY/ipiPykuQXfKzabm3/I1p5k8M6EsV5+9w/abrtu62nCo9tqUO5NOuBAFKzPu5ch4mq77K1cEH0ovAkEAurWsP0ZMuYyWSUnJuhGGc/XegAyXFRGByl2FFWDYkDDDjSJitqqUiiRzs0wuV4VHgw0z1tldJ0VhLfOzCAYl3QJAWA1ayN9+kkrD72vFs6MDYoJ84moJyb1zKkcz6lUjoxW2SCe/F3z6BMgR8JV8fXNvFAkkA+Nkmx3PL1wtkwdwewJAQipEQcWtZX1SbXSxb3s2PL9kP2SzXqmfnlPDG7tZah68LAc3HhsoUBzFl7W7y0hVxSubnUVAi90w+ZskFS910Q==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/demo/userRoomType";

    // 签名方式
    public static String sign_type = "RSA";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

