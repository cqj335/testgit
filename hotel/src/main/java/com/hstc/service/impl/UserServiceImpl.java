package com.hstc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hstc.dao.UserMapper;
import com.hstc.entity.User;
import com.hstc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserMapper userMapper;


    @Override
    public User findByUnameAndPassword(String uname, String upassword, Integer ustate) {
        User user=null;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uname",uname);
        queryWrapper.eq("upassword",upassword);
        queryWrapper.eq("ustate",ustate);
        user =userMapper.selectOne(queryWrapper);
       return user;
    }

    @Override
    public void register(User user) {
        userMapper.insert(user);
    }
    @Override
    public User findUname(String username) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
         queryWrapper.eq("uname",username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void edtUser(User user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uname",user.getUname());
        userMapper.update(user,queryWrapper);
    }

    @Override
    public IPage<User> findByPage(Integer page, Integer size) {
        return userMapper.selectPage(new Page<>(page, size), null);
    }

    @Override
    public boolean delete(Integer uid) {
        int i=userMapper.deleteById(uid);
        return i==1?true:false;
    }

    @Override
    public boolean save(User user) {
        int i=userMapper.insert(user);
        return i==1?true:false;
    }

    @Override
    public User findByuid(Integer uid) {
        return userMapper.selectById(uid);
    }

    @Override
    public boolean update(User muser) {
        int i=userMapper.updateById(muser);
        return i==1?true:false;
    }

    @Override
    public IPage<User> findBySearcename(String content,Integer page, Integer size) {
        return userMapper.selectPage(new Page<User>(page, size), new QueryWrapper<User>()
                .like("uname",content));
    }
}