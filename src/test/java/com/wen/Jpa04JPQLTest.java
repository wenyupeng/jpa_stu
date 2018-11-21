package com.wen;

import com.wen.pojo.Customer;
import com.wen.utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * JPQL查询：
 *      Java Persistence Query Language
 *      把sql查询中的表名替换成类名，把sql查询中的字段名替换成属性名，不能加上 select *
 *
 *      sql: select * from cst_customer where cust_id=1
 *      jpql: from Customer where custId=1
 *
 */
public class Jpa04JPQLTest {

    /**
     * 查询全部
     */
    @Test
    public void test1(){
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //=============================================
            //String jpql = "from com.itheima.domain.Customer";//全写
            String jpql = "from Customer";//简写
            //通过实体类管理器创建一个jpql查询对象：Query
            Query query = em.createQuery(jpql);
            //获取结果集
            List<Customer> list = query.getResultList();
            //打印结果集
            for (Customer customer : list) {
                System.out.println(customer);
            }
            //=============================================
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }

    /**
     * 分页查询
     *      使用的是query对象中的两个方法：开始记录索引，每页查询大小
     *      setFirstResutl();  开始记录索引
     *      setMaxResults();   查询几条数据
     */
    @Test
    public void test2(){
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //=============================================
            //String jpql = "from com.itheima.domain.Customer";//全写
            String jpql = "from Customer";//简写
            //通过实体类管理器创建一个jpql查询对象：Query
            Query query = em.createQuery(jpql);
            //分页查询的方法
            query.setFirstResult(2);//起始索引
            query.setMaxResults(2);//页面大小

            //获取结果集
            List<Customer> list = query.getResultList();
            //打印结果集
            for (Customer customer : list) {
                System.out.println(customer);
            }
            //=============================================
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }


    /**
     * 条件查询
     */
    @Test
    public void test3(){
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //=============================================
            //占位符第一种方式
            //String jpql = "from Customer where custName like ? and custLevel = ?";//简写

            //占位符第二种方式
            String jpql = "from Customer where custName like :custName and custLevel = :custLevel";//简写
            //通过实体类管理器创建一个jpql查询对象：Query
            Query query = em.createQuery(jpql);

            //给占位符赋值: 第一种
            /*query.setParameter(1,"%游艇%");//从1开始
            query.setParameter(2,"VIP级");*/
            //给占位符赋值: 第二种
            query.setParameter("custName","%游艇%");//从1开始
            query.setParameter("custLevel","VIP级");


            //获取结果集
            List<Customer> list = query.getResultList();
            //打印结果集
            for (Customer customer : list) {
                System.out.println(customer);
            }
            //=============================================
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }


    /**
     * 排序查询
     *
     *  order by 属性名  asc|desc
     */
    @Test
    public void test4(){
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //=============================================
            Query query = em.createQuery("from Customer where custName like ? order by custId desc");
            query.setParameter(1,"%游艇%");


            //获取结果集
            List<Customer> list = query.getResultList();
            //打印结果集
            for (Customer customer : list) {
                System.out.println(customer);
            }
            //=============================================
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }

    /**
     * 统计查询
     *      count(*)
     *      max
     *      min
     *      avg
     *      sum
     */
    @Test
    public void test5(){
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //=============================================
            //Query query = em.createQuery("select count(*) from Customer where custName like ? ");
            //Query query = em.createQuery("select count(custId) from Customer where custName like ? ");
            Query query = em.createQuery("select max(custId) from Customer where custName like ? ");
            query.setParameter(1,"%游艇%");

            //获取结果集
            //List resultList = query.getResultList();
            //System.out.println(resultList.get(0));

            //获取单一结果集：返回的是一条数据
            Object count = query.getSingleResult();
            System.out.println(count);


            //=============================================
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }

}
