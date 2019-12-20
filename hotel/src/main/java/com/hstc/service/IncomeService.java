package com.hstc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.entity.Income;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/10/8.
 */
public interface IncomeService {
    public IPage<Income> findByPage(Integer page, Integer size);
    public int saveIncome(Income income);
    public boolean save(Income income);
    public Income findByIid(Integer Iid);

    public boolean update(Income income);

    public boolean delete(Integer Iid);

    public IPage<Income> searchdate(Date date1, Integer page, Integer size);

    public List<Income> findall();
}
