package com.ringcentral.fullmoon.domain.bo.system;

import com.ringcentral.fullmoon.domain.vo.MenuRouteVo;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the SYS_USER database table.
 */
@Entity
@Table(name = "SYS_USER")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "SysUser.findMenuList",
                // oracle
                /*query = "select menu_id, menu_name, menu_url, parent_id, route_path, module_path, r.route_id, " +
                        "module_name, load_css from sys_menu m, sys_route r " +
                        "where m.route_id = r.route_id(+) and m.menu_id in " +
//                        "where m.route_id = r.route_id and m.menu_id in " +
                        "   (select m1.menu_id from sys_menu m1, sys_menu_re_role mrr" +
                        "       where m1.menu_id = mrr.menu_id and (mrr.role_id in " +
                        "           (select role_id from sys_user_re_role where user_id = ?) and" +
                        "               m1.disable <> '1')" +
                        "   or m1.parent_id = -1) " +
                        "and (r.disable is null or r.disable <> '1') order by m.menu_id",*/
                // common sql
                query = "select menu_id, menu_name, menu_url, parent_id, route_path, module_path, r.route_id, " +
                        "module_name, load_css from SYS_MENU m left join SYS_ROUTE r on m.route_id = r.route_id " +
                        "where m.menu_id in " +
//                        "where m.route_id = r.route_id and m.menu_id in " +
                        "   (select m1.menu_id from SYS_MENU m1, SYS_MENU_RE_ROLE mrr" +
                        "       where m1.menu_id = mrr.menu_id and (mrr.role_id in " +
                        "           (select role_id from SYS_USER_RE_ROLE where user_id = ?) and" +
                        "               m1.disable <> '1')" +
                        "   or m1.parent_id = -1) " +
                        "and (r.disable is null or r.disable <> '1') order by m.menu_id",
                resultClass = MenuRouteVo.class
        )
})
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_PWD")
    private String userPwd;

    @Column(name = "DISABLE")
    private String disable;

    public SysUser() {
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

}