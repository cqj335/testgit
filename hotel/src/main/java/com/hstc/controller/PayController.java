package com.hstc.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.hstc.config.AlipayConfig;
import com.hstc.entity.Book;
import com.hstc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class PayController {
    @Autowired
    private BookService bookService;
    @RequestMapping("/index")
    public ModelAndView toTest(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/pay")
    public void payController(HttpServletRequest request, HttpServletResponse response, @Valid Book book, BindingResult bindingResult, HttpSession session) throws IOException {
        //获得初始化的AlipayClient
        System.out.println(book);
        boolean i=bookService.save(book);
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);


        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("bianhao").getBytes("ISO-8859-1"), "UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("jiage").getBytes("ISO-8859-1"), "UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("mingcheng").getBytes("ISO-8859-1"), "UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("miaoshu").getBytes("ISO-8859-1"), "UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.charset);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/refund")
    public void refundController(HttpServletRequest request, HttpServletResponse response,@Valid Book book,String bianhao,String jiage, BindingResult bindingResult, HttpSession session) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("bianhao").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        /*String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"),"UTF-8");*/
        //请二选一设置
        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = new String(request.getParameter("jiage").getBytes("ISO-8859-1"),"UTF-8");
        //退款的原因说明
        /*String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes("ISO-8859-1"),"UTF-8");*/
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        /*String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"),"UTF-8");*/

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"refund_amount\":\""+ refund_amount +"\"}");

        //请求
        String form = "";
        try {
            form = alipayClient.execute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
//        response.setContentType("text/html;charset=" + AlipayConfig.charset);
//        response.getWriter().write(form);//直接将完整的表单html输出到页面
//        response.getWriter().flush();
//        response.getWriter().close();

        System.out.println(form);

    }

}

