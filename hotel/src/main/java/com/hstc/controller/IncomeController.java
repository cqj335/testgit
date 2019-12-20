package com.hstc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.entity.Income;
import com.hstc.service.IncomeService;
import com.hstc.utills.TimeUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/10/8.
 */
@Controller
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @GetMapping("income")
    public ModelAndView findAll(Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 2;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("income");
        IPage<Income> iPage0 = incomeService.findByPage(page, size);
        List<Income>list=iPage0.getRecords();
        Integer countincome=list.size();
        Float summoney=0f;
        for (int i=0;i<list.size();i++) {
            Income income=list.get(i);
            summoney+=income.getTprice();
        }
        List<Income>list2=incomeService.findall();
        Integer countincome1=list2.size();
        Float summoney1=0f;
        for (int i=0;i<list2.size();i++) {
            Income income=list2.get(i);
            summoney1+=income.getTprice();
        }
        mav.addObject("countincome",countincome);
        mav.addObject("summoney",summoney);
        mav.addObject("countincome1",countincome1);
        mav.addObject("summoney1",summoney1);
        mav.addObject("iPage", iPage0);
        mav.addObject("pages", iPage0.getPages());
        return mav;

    }

    @GetMapping("addincome")
    public ModelAndView getIncome() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("addincome");
        return mav;

    }

    @PostMapping("income")
    public ModelAndView saveIncome(Income income) {

        //执行插入操作
        boolean isok = incomeService.save(income);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:income");
        return mav;

    }

    @GetMapping("income/{Iid}")
    public ModelAndView editIncome(@PathVariable Integer Iid) {
        //获得单个学生信息
        Income income = incomeService.findByIid(Iid);

        ModelAndView mav = new ModelAndView();
        //存入mav，下发到网页中
        mav.addObject("income", income);
        mav.setViewName("editincome");

        return mav;
    }

    @PutMapping("income")
    public ModelAndView updIncome(Income income) {
        System.out.println(income);
        boolean isok = incomeService.update(income);
        System.out.println("更新结果为" + isok);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:income");
        return mav;
    }

    @DeleteMapping("income/{Iid}")
    public ModelAndView delete(@PathVariable Integer Iid) {
        boolean b = incomeService.delete(Iid);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:../income");
        return mav;
    }

    @GetMapping("searchdate")
    public ModelAndView findAllu(String content, Integer page, Integer size)throws ParseException {
        if (page == null) page = 1;
        if (size == null) size = 999;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("income");
        IPage<Income> iPage0 = incomeService.findByPage(page, size);
        List<Income>list=iPage0.getRecords();
        List result=new ArrayList();
        Integer countincome=0;
        Float summoney=0f;
        for (int i=0;i<list.size();i++) {
            Income income=list.get(i);
            String data1=new SimpleDateFormat("yyyy-MM-dd").format(income.getLeavedate());
            if (content.equals(data1)){
                result.add(income);
                countincome+=1;
                summoney+=income.getTprice();
            }

        }
        iPage0.setRecords(result);
        List<Income>list2=incomeService.findall();
        Integer countincome1=list2.size();
        Float summoney1=0f;
        for (int i=0;i<list2.size();i++) {
            Income income=list2.get(i);
            summoney1+=income.getTprice();
        }
        mav.addObject("countincome",countincome);
        mav.addObject("summoney",summoney);
        mav.addObject("countincome1",countincome1);
        mav.addObject("summoney1",summoney1);
        mav.addObject("iPage", iPage0);
        mav.addObject("pages", iPage0.getPages());
        return mav;

    }

    @GetMapping("money")
    public ModelAndView findAlld(Integer page, Integer size) throws ParseException {
        if (page == null) page = 1;
        if (size == null) size = 2;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("money");
        List<Income>list=incomeService.findall();
        List result=new ArrayList();
        Float summoney=0f;
        Calendar now = Calendar.getInstance();
        int year1=now.get(Calendar.YEAR);
        int month1=now.get(Calendar.MONTH)+1;

        for (int i=0;i<list.size();i++) {
            Income income=list.get(i);

            String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(income.getLeavedate().getTime()).toString().split("-");
            int year2 = Integer.parseInt(strNow[0]);
            int month2 = Integer.parseInt(strNow[1]);

            if (year1==year2&&month1==month2){
                result.add(income);
                summoney+=income.getTprice();
            }
        }

        list=result;
        Integer countincome=result.size();
        List<Income>list2=incomeService.findall();
        Integer countincome1=0;
        Float summoney1=0f;
        for (int i=0;i<list2.size();i++) {
            Income income=list2.get(i);

            String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(income.getLeavedate().getTime()).toString().split("-");
            int year2 = Integer.parseInt(strNow[0]);
            int month2 = Integer.parseInt(strNow[1]);
            if (year1==year2){
                countincome1+=1;
                summoney1+=income.getTprice();
            }
        }
        mav.addObject("countincome",countincome);
        mav.addObject("summoney",summoney);
        mav.addObject("countincome1",countincome1);
        mav.addObject("summoney1",summoney1);
        mav.addObject("list", list);
        return mav;

    }

    @GetMapping("countmonth")
    public ModelAndView findAllq(String year,String month) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("money");
        List<Income>list=incomeService.findall();
        List result=new ArrayList();
        Float summoney=0f;
        int year1;
        int month1;
        if (year==null||month==null){
            Calendar now = Calendar.getInstance();
            year1=now.get(Calendar.YEAR);
            month1=now.get(Calendar.MONTH)+1;
        }else{
            year1=Integer.parseInt(year);
            month1=Integer.parseInt(month);
        }


        for (int i=0;i<list.size();i++) {
            Income income=list.get(i);

            String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(income.getLeavedate().getTime()).toString().split("-");
            int year2 = Integer.parseInt(strNow[0]);
            int month2 = Integer.parseInt(strNow[1]);

            if (year1==year2&&month1==month2){
                result.add(income);
                summoney+=income.getTprice();
            }
        }

        list=result;
        Integer countincome=result.size();
        List<Income>list2=incomeService.findall();
        Integer countincome1=0;
        Float summoney1=0f;
        for (int i=0;i<list2.size();i++) {
            Income income=list2.get(i);

            String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(income.getLeavedate().getTime()).toString().split("-");
            int year2 = Integer.parseInt(strNow[0]);
            int month2 = Integer.parseInt(strNow[1]);
            if (year1==year2){
                countincome1+=1;
                summoney1+=income.getTprice();
            }
        }
        mav.addObject("countincome",countincome);
        mav.addObject("summoney",summoney);
        mav.addObject("countincome1",countincome1);
        mav.addObject("summoney1",summoney1);
        mav.addObject("list", list);
        return mav;

    }
}
