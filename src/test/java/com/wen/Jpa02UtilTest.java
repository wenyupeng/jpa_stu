package com.wen;

import com.wen.pojo.Customer;
import com.wen.utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Jpa02UtilTest {

    /**
     * 需求： 保存一个客户实体到数据库表中
     *
     * 入门的步骤：
     * 1、根据配置文件创建实体类管理器工厂
     * 2、生成实体类管理器
     * 3、获取事务对象
     * 4、开启事务
     * 5、通过实体类管理器完成CRUD操作
     * 6、提交事务|回滚事务
     * 7、释放资源
     *
     * JPA入门的API介绍：
     *      Persistence：                            重用程度： 一般
     *          作用: 根据核心配置文件中的持久化单元生成 实体管理器工厂
     *          主要方法：
     *                  createEntityManagerFactory("持久化单元名称")
     *
     *
     *
     *
     *      EntityManagerFactory：                   重要程度： 比较重要
     *           作用： 主要用于生成实体管理器
     *           主要方法：
     *                  createEntityManager()
     *           细节：
     *                  1、此对象EntityManagerFactory它是一个重量级对象，它里面维护了很多信息
     *                  2、此对象是一个线程安全的对象，在多线程环境下不会出现并发问题
     *           使用原则：
     *                  一个web项目只有一个EntityManagerFactory就够了
     *
     *
     *
     *
     *      EntityManager：                          重要程度： 非常重用
     *           作用：主要与数据库打交道，CRUD都是通过他来完成，获取事务对象
     *           主要方法：
     *                  getTransaction(); 获取事务对象
     *                  persist()： 保存一个对象
     *                  merge() : 更新一个对象
     *                  remove() : 删除一个对象
     *                  find()| getReference() : 根据id查询一个对象
     *            细节：
     *                  它不是一个线程安全的对象，每一次操作应该都是一个新的对象，它是一个轻量级对象
     *
     *
     *
     *      EntityTransaction：                      重要程度：会用就行
     *            作用： 事务管理
     *            主要方法：
     *                  begin():开启事务
     *                  commit();提交事务
     *                  rollback();回滚事务
     *
     *
     */
    @Test
    public void test1(){
        Customer c = new Customer();
        c.setCustName("纯情小鸭鸭游艇公司");
        c.setCustLevel("VIP级");
        c.setCustAddress("天河八巷");


        //2、生成实体类管理器
        EntityManager em = JpaUtil.createEntityManager();
        //3、通过实体类管理器获取事务对象
        EntityTransaction tx = em.getTransaction();
        //4、开启事务
        tx.begin();
        //5、通过实体类管理器完成CRUD操作
        em.persist(c);
        //6、提交事务|回滚事务
        tx.commit();
        //7、释放资源
        em.close();
        //factory.close(); 不关闭工厂

    }


    /**
     * 以后的代码模板
     */
    @Test
    public void test2(){
        Customer c = new Customer();
        c.setCustName("纯情小狗狗游艇公司");
        c.setCustLevel("砖石级");
        c.setCustAddress("天河7巷");

        //实体类管理器
        EntityManager em = null;
        //事务对象
        EntityTransaction tx = null;
        try{
            //获取实体类管理器
            em = JpaUtil.createEntityManager();
            //获取事务对象
            tx = em.getTransaction();
            //开启事务
            tx.begin();
            //进行增删改查操作
            em.persist(c);
            //提交事务
            tx.commit();
        }catch (Exception e){
            //回滚事务
            tx.rollback();
            e.printStackTrace();
        }finally {
            //释放资源
            em.close();
        }

    }



}
