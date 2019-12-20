package com.hstc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.entity.User;

public interface UserService {
    public User findByUnameAndPassword(String uname, String upassword, Integer ustate);
    public void register(User user);
    public User findUname(String username);
    public void edtUser(User user);

    public IPage<User> findByPage(Integer page, Integer size);

    public boolean delete(Integer uid);

    public boolean save(User user);

    public User findByuid(Integer uid);

    public boolean update(User muser);

    public IPage<User> findBySearcename(String content,Integer page, Integer size);
}
