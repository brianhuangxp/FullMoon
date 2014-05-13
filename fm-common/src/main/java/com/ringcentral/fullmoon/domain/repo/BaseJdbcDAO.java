package com.ringcentral.fullmoon.domain.repo;

import java.util.List;
import java.util.Map;

import com.ringcentral.fullmoon.common.system.PageInfo;
import org.springframework.jdbc.core.RowMapper;

public interface BaseJdbcDAO {

    /**
     * 执行JDBC语句分页查询的方法
     *
     * @param sqlStr 查询sql
     * @param totalCount 总记录数
     * @param pageSize 分页记录数
     * @param pageIndex 页码
     * @return 查询结果对象分页列表
     * @author 魏义凯
     */
    public PageInfo sqlQueryForPage(String sqlStr, int totalCount, int pageSize, int pageIndex);

    /**
     * 执行JDBC语句分页查询的方法
     *
     * @param sqlStr 查询SQL
     * @param params 查询参数
     * @param pageInfo 分页条件
     * @return 查询结果对象分页列表
     * @author 魏义凯
     */
    public PageInfo sqlQueryForPage(String sqlStr, Object[] params, PageInfo pageInfo);

    /**
     * 执行JDBC语句分页查询的方法
     *
     * @param sqlStr 查询sql
     * @param pageInfo 分页条件
     * @return 查询结果对象分页列表
     * @author 魏义凯
     */
    public PageInfo sqlQueryForPage(String sqlStr, PageInfo pageInfo);
    /**
     * 执行JDBC语句分页查询的方法
     *
     * @param sqlStr 查询SQL语句
     * @param params 查询参数
     * @param pageSize 分页记录数
     * @param pageIndex 页码
     * @return 查询结果对象分页列表
     * @author 魏义凯
     */
    public PageInfo sqlQueryForPage(String sqlStr, Object[] params, int totalRec, int pageSize, int pageIndex);
    /**
     * 通过JDBC原生SQL查询列表，返回HashMap对象组成的列表
     *
     * @param sqlStr 查询SQL
     * @return 结果HashMap对象组成的列表
     * @author 魏义凯
     */
    public List sqlQueryForList(String sqlStr);
    /**
     * 通过JDBC原生SQL查询列表，返回HashMap对象组成的列表
     *
     * @param sqlStr 查询SQL语句
     * @param params 查询参数
     * @return 结果HashMap对象组成的列表
     * @author 魏义凯
     */
    public List sqlQueryForList(String sqlStr, Object[] params);
    /**
     * 单记录查询,返回字段名为主键的MAP
     *
     * @param sqlStr 查询sql
     * @return 结果HashMap
     * @author 魏义凯
     */
    public Map sqlQueryForMap(String sqlStr);
    /**
     * 单记录查询,返回字段名为主键的MAP
     *
     * @param sqlStr 查询sql
     * @param params 查询参数
     * @return 结果HashMap
     * @author 魏义凯
     */
    public Map sqlQueryForMap(String sqlStr, Object[] params);
    /**
     * JDBC语句查询对象的方法
     *
     * @param sqlStr SQL查询语句
     * @param params 参数数组
     * @param clz 对象Class
     * @return 结果对象
     * @author 魏义凯
     */
    public Object sqlQueryForObject(String sqlStr, Object[] params, Class clz);
    /**
     * JDBC语句查询列表的方法
     *
     * @param sqlStr SQL查询语句
     * @param params 参数数组
     * @param rowMapper RowMapper
     * @return 检索结果List集合
     * @author 魏义凯
     */
    public Object sqlQueryForObject(String sqlStr, Object[] params, RowMapper rowMapper);
    /**
     * 通过检索数据，返回RowMapper定义的用户类型List
     *
     * @param sqlStr
     * @param rowMapper
     * @return 结果List集合
     * @author 魏义凯
     */
    public List sqlQueryForListWithRowMapper(String sqlStr, RowMapper rowMapper);

    /**
     * 执行删除SQL
     *
     * @param deleteSql 要执行的SQL
     * @author 魏义凯
     */
    public void executeDeleteSQL(String deleteSql);
    /**
     * 执行插入SQL
     *
     * @param insertSql 要执行的SQL
     * @author 魏义凯
     */
    public void executeInsertSQL(String insertSql);
    /**
     * 执行更新SQL
     *
     * @param updateSql 要执行的SQL
     * @return 影响行数
     * @author 魏义凯
     */
    public int executeUpdateSQL(String updateSql);
    /**
     * 执行更新SQL
     *
     * @param updateSql 要执行的SQL
     * @param params 参数
     * @return 影响行数
     * @author 魏义凯
     */
    public int executeUpdateSQL(String updateSql, Object[] params);
    /**
     * 执行更新SQL
     *
     * @param updateSql 要执行的SQL
     * @param params 参数
     * @param argTypes 类型
     * @return 影响行数
     * @author 魏义凯
     */
    public int executeUpdateSQL(String updateSql, Object[] params, int[] argTypes);

}