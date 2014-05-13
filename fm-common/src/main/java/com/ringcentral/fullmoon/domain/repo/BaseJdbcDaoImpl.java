package com.ringcentral.fullmoon.domain.repo;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import com.ringcentral.fullmoon.common.system.PageInfo;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不推荐使用 JdbcDaoSupport的方法，以后有空需要移除并改写积累方法，JdbcDaoSupport在service层使用同一个事务。依赖spring dataSource bean
 * 建议使用JPA方式
 */
public class BaseJdbcDaoImpl extends JdbcDaoSupport implements BaseJdbcDAO {
	@Autowired
	protected EntityManager em;


    @Autowired
    public void initDataSource(@Qualifier("dataSource") DataSource ds){
        this.setDataSource(ds);
    }

    public boolean isEmpty(Object obj){
        return (obj instanceof  String) ? null == obj || "".equals(obj) : null == obj;
    }

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
    public PageInfo sqlQueryForPage(String sqlStr, int totalCount, int pageSize, int pageIndex) {
        return sqlQueryForPage(sqlStr, null, totalCount, pageSize, pageIndex);
    }

    /**
     * 执行JDBC语句分页查询的方法
     *
     * @param sqlStr 查询SQL
     * @param params 查询参数
     * @param pageInfo 分页条件
     * @return 查询结果对象分页列表
     * @author 魏义凯
     */
    public PageInfo sqlQueryForPage(String sqlStr, Object[] params, PageInfo pageInfo) {
        PageInfo page=pageInfo;
        if (page == null) {
            page = new PageInfo();
        }
        return sqlQueryForPage(sqlStr, params, page.getTotalCount(), page.getPageSize(), page.getPageIndex());
    }

    /**
     * 执行JDBC语句分页查询的方法
     *
     * @param sqlStr 查询sql
     * @param pageInfo 分页条件
     * @return 查询结果对象分页列表
     * @author 魏义凯
     */
    public PageInfo sqlQueryForPage(String sqlStr, PageInfo pageInfo) {
        PageInfo page=pageInfo;
        if (page == null) {
            page = new PageInfo();
        }
        return sqlQueryForPage(sqlStr, null, page.getTotalCount(), page.getPageSize(), page.getPageIndex());
    }

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
    public PageInfo sqlQueryForPage(String sqlStr, Object[] params, int totalRec, int pageSize, int pageIndex) {
        PageInfo pageInfo = new PageInfo(pageSize, pageIndex, totalRec);
        try {
            if (totalRec == 0) {
                String temp = sqlStr.replaceAll("ORDER BY.*", "");
                temp = temp.replaceAll("order by.*", "");
                String tSql = "select count(*) TOTALCOUNT from (" + temp + ") T";
                pageInfo.setTotalCount(Integer.parseInt(((Map) sqlQueryForMap(tSql, params)).get("TOTALCOUNT").toString()));
            } else {
                pageInfo.setTotalCount(totalRec);
            }
            if (pageIndex == 0) {
                pageInfo.setPageIndex(1);
            } else {
                pageInfo.setPageIndex(pageIndex);
            }
            if (pageSize == 0) {
                pageInfo.setPageSize(PageInfo.DEFAULT_PAGE_SIZE);
            } else {
                pageInfo.setPageSize(pageSize);
            }


            StringBuilder sqlStrBuff = new StringBuilder(" SELECT * FROM ( ");
            sqlStrBuff.append(" SELECT temp.* ,ROWNUM num FROM ( ");
            sqlStrBuff.append(sqlStr);
            sqlStrBuff.append("　) temp where ROWNUM <= " + pageInfo.getPageIndex()*pageInfo.getPageSize());
            sqlStrBuff.append(" ) WHERE　num >=" +((pageInfo.getPageIndex()-1)*pageInfo.getPageSize()+1));

            logger.debug( " sqlStr is " + sqlStrBuff.toString() );

            List dataList = super.getJdbcTemplate().queryForList(sqlStrBuff.toString(),params);
            pageInfo.setDataList(dataList);

        } catch (HibernateException e) {
            logger.error(sqlStr);
            e.printStackTrace();
            throw e;
        }
        return pageInfo;
    }

    /**
     * 通过JDBC原生SQL查询列表，返回HashMap对象组成的列表
     *
     * @param sqlStr 查询SQL
     * @return 结果HashMap对象组成的列表
     * @author 魏义凯
     */
    public List sqlQueryForList( String sqlStr ){
        return super.getJdbcTemplate().queryForList( sqlStr );
    }

    /**
     * 通过JDBC原生SQL查询列表，返回HashMap对象组成的列表
     *
     * @param sqlStr 查询SQL语句
     * @param params 查询参数
     * @return 结果HashMap对象组成的列表
     * @author 魏义凯
     */
    public List sqlQueryForList( String sqlStr, Object[] params ){
        return super.getJdbcTemplate().queryForList(sqlStr, params);
    }
    /**
     * 单记录查询,返回字段名为主键的MAP
     *
     * @param sqlStr 查询sql
     * @return 结果HashMap
     * @author 魏义凯
     */
    public Map sqlQueryForMap(String sqlStr) {
        return sqlQueryForMap(sqlStr, null);
    }
    /**
     * 单记录查询,返回字段名为主键的MAP
     *
     * @param sqlStr 查询sql
     * @param params 查询参数
     * @return 结果HashMap
     * @author 魏义凯
     */
    public Map sqlQueryForMap(String sqlStr, Object[] params) {
        List list = sqlQueryForList(sqlStr, params);
        if (list != null && list.size() > 0) {
            return (Map) list.get(0);
        } else {
            return new HashMap();
        }
    }
    /**
     * JDBC语句查询对象的方法
     *
     * @param sqlStr SQL查询语句
     * @param params 参数数组
     * @param clz 对象Class
     * @return 结果对象
     * @author 魏义凯
     */
    public Object sqlQueryForObject(String sqlStr,Object[] params,Class clz){
        return super.getJdbcTemplate().queryForObject(sqlStr, params,clz);
    }
    /**
     * JDBC语句查询列表的方法
     *
     * @param sqlStr SQL查询语句
     * @param params 参数数组
     * @param rowMapper RowMapper
     * @return 检索结果List集合
     * @author 魏义凯
     */
    public Object sqlQueryForObject(String sqlStr,Object[] params,RowMapper rowMapper){
        return super.getJdbcTemplate().queryForObject(sqlStr, params, rowMapper);
    }
    /**
     * 通过检索数据，返回RowMapper定义的用户类型List
     *
     * @param sqlStr
     * @param rowMapper
     * @return 结果List集合
     * @author 魏义凯
     */
    public List sqlQueryForListWithRowMapper( String sqlStr, RowMapper rowMapper ){
        return super.getJdbcTemplate().query( sqlStr, rowMapper );
    }

    /**
     * 执行删除SQL
     *
     * @param deleteSql 要执行的SQL
     * @author 魏义凯
     */
    public void executeDeleteSQL( String deleteSql ){
        super.getJdbcTemplate().execute( deleteSql );
    }
    /**
     * 执行插入SQL
     *
     * @param insertSql 要执行的SQL
     * @author 魏义凯
     */
    public void executeInsertSQL( String insertSql ){
        super.getJdbcTemplate().update( insertSql );
    }
    /**
     * 执行更新SQL
     *
     * @param updateSql 要执行的SQL
     * @return 影响行数
     * @author 魏义凯
     */
    public int executeUpdateSQL( String updateSql ){
        return super.getJdbcTemplate().update( updateSql );
    }
    /**
     * 执行更新SQL
     *
     * @param updateSql 要执行的SQL
     * @param params 参数
     * @return 影响行数
     * @author 魏义凯
     */
    public int executeUpdateSQL(String updateSql, Object[] params) {
        return super.getJdbcTemplate().update(updateSql, params);
    }
    /**
     * 执行更新SQL
     *
     * @param updateSql 要执行的SQL
     * @param params 参数
     * @param argTypes 类型
     * @return 影响行数
     * @author 魏义凯
     */
    public int executeUpdateSQL(String updateSql, Object[] params,int[] argTypes) {
        return super.getJdbcTemplate().update(updateSql, params, argTypes);
    }
}
