package com.orange.web.action.holder;


import javax.servlet.http.HttpSession;

import com.orange.cas.entity.MemberEntity;
import com.orange.cas.entity.UserEntity;
import com.orange.common.ManageContainer;

public class LoginUserHolder {
    public static UserEntity getLoginUser(){
        HttpSession session = RequestHolder.getSession();
        return session == null ? null : (UserEntity)session.getAttribute(ManageContainer.session_user_info);
    }
    public static MemberEntity getLoginMember(){
        HttpSession session = RequestHolder.getSession();
        return session == null ? null : (MemberEntity)session.getAttribute(ManageContainer.session_member_info);
    }
}
