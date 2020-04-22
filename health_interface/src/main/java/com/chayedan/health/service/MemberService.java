package com.chayedan.health.service;

/**
 * @author chayedan666
 * @version 1.0
 * @className: MemberService
 * @description:
 * @date: 2020/4/21
 */
public interface MemberService {
    /**
     * 会员基于手机号登录
     * @param telephone
     */
    void smsLogin(String telephone);
}
