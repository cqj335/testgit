package com.hstc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hstc.dao.BookMapper;
import com.hstc.dao.IncomeMapper;
import com.hstc.entity.Book;
import com.hstc.entity.Income;
import com.hstc.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/10/8.
 */
@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeMapper incomeMapper;
    @Autowired
    private BookMapper bookMapper;
    @Override
    public IPage<Income> findByPage(Integer page, Integer size) {
        return incomeMapper.selectPage(new Page<>(page, size), null);
    }

    @Override
    public int saveIncome(Income income) {
        int i = 0;
        if (income!=null){
            i = incomeMapper.insert(income);
        }else{
            i = -1;
        }
        return i;
    }

    @Override
    public boolean save(Income income) {
        int i=incomeMapper.insert(income);
        return i==1?true:false;
    }

    @Override
    public Income findByIid(Integer Iid) {
        return incomeMapper.selectById(Iid);
    }

    @Override
    public boolean update(Income income) {
        int i=incomeMapper.updateById(income);
        return i==1?true:false;
    }

    @Override
    public boolean delete(Integer Iid) {
        int i=incomeMapper.deleteById(Iid);
        return i==1?true:false;
    }

    @Override
    public IPage<Income> searchdate(Date date1, Integer page, Integer size) {
        return incomeMapper.selectPage(new Page<Income>(page, size), new QueryWrapper<Income>()
                .like("leavedate",date1));
    }

    @Override
    public List<Income> findall() {
        return incomeMapper.selectList(null);
    }
}
