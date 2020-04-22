package com.chayedan.health.dao;

import com.chayedan.health.pojo.Member;

/**
 * @author chayedan666
 * @version 1.0
 * @className: MemberDao
 * @description: 会员Dao接口
 * @date: 2020/4/21
 */
public interface MemberDao {
    /**
     * 根据电话查找是否有该会员
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    /**
     * 保存会员信息
     * @param member
     */
    void add(Member member);


}
