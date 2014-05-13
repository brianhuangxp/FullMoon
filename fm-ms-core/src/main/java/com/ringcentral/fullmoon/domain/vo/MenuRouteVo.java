package com.ringcentral.fullmoon.domain.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by brain.huang on 14-1-3.
 */
@Entity
public class MenuRouteVo extends BaseVo {
    @Id
    @Column(name = "MENU_ID")
    private String menuId;

    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "route_path")
    private String routePath;

    @Column(name = "module_path")
    private String modulePath;

    @Column(name = "module_name")
    private String moduleName;

    @Column(name = "load_css")
    private String loadCss;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_url")
    private String menuUrl;

    @Column(name = "parent_id")
    private Long parentId;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoutePath() {
        return routePath;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    public String getModulePath() {
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }

    public String getLoadCss() {
        return loadCss;
    }

    public void setLoadCss(String loadCss) {
        this.loadCss = loadCss;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
