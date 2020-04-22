package com.chayedan.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chayedan.health.dao.MemberDao;
import com.chayedan.health.pojo.Member;
import com.chayedan.health.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author chayedan666
 * @version 1.0
 * @className: MemberServiceImpl
 * @description:
 * @date: 2020/4/21
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Transactional
    @Override
    public void smsLogin(String telephone) {
        // 根据手机号获取会员信息
        Member member = memberDao.findByTelephone(telephone);
        if( member == null){
            // 不是会员，自动注册
            member = new Member();
            member.setPhoneNumber(telephone);
            member.setRegTime(new Date());
            // 调用Service保存会员
            memberDao.add(member);
        }
    }
}
