package com.xindaibao.cashloan.core.mapper;

import com.xindaibao.cashloan.core.model.KanyaUser;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.mapper.RDBatisDao;
import com.xindaibao.cashloan.core.model.KanyaUserLocation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@RDBatisDao
public interface KanyaUserMapper extends BaseMapper<KanyaUser, Long> {

    KanyaUserLocation getBaseModelByUserId(Long userId);

    List<KanyaUser> findByPrimary(Map<String, Object> params);

    int updateUserState(Map<String, Object> params);

    int selectRegisteredCount();
    List<KanyaUser> queryInvited(@Param("inviteCode") String inviteCode);//邀请人信息查询
    //添加用户
    int save(KanyaUser kanyaUser);
    //通过手机号查询用户
    KanyaUser findByMobile(String mobile);
    //添加黑名单
    void addBlackList(Long id);

    void callAble();
}