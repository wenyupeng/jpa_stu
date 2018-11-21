package com.wen;

import com.wen.pojo.Customer;
import com.wen.utils.JpaUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Jpa03CRUDTest {

    /**
     * 新增一个对象到数据库
     */
    @Test
    public void testAdd(){
        Customer c = new Customer();
        c.setCustName("纯情小猪猪电脑公司");
        c.setCustLevel("白银级");
        c.setCustAddress("天河6巷");

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

    /**
     * 查询一个对象
     *      find()方法：
     *           第一个参数：要查询的实体类的字节码对象
     *           第二个参数：主键值
     *
     *      getReference()方法：
     *           第一个参数：要查询的实体类的字节码对象
     *           第二个参数：主键值
     *
     *   这两个方法的区别：
     *       find()：立即加载                不管用不用马上查询
     *       getReference()：延迟加载        什么时候用什么时候查询
     *
     *   延迟加载： 什么时候用什么时候加载，减少数据库压力
     *
     *   细节：
     *          在EntityManager中有对象的缓存【map】，提高查询效率【了解】
     */
    @Test
    public void testFindOne(){
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //====================================================
            //进行增删改查操作
            Customer c = em.find(Customer.class, 3L);//立即加载
            Customer c2 = em.find(Customer.class, 3L);//立即加载
            System.out.println(c==c2);
            //Customer c = em.getReference(Customer.class, 3L);//延迟加载
            System.out.println("find查询一个对象："+ c);
            //====================================================
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }

    /**
     * 更新对象
     *
     *  使用的方法：merge()
     */
    @Test
    public void testUpdate(){
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //====================================================
            //进行增删改查操作

            //1、new一个对象给对象赋值进行更新，要保证id有值，这种方式有可能把其他属性值清空【不建议】
            /*Customer c = new Customer();
            c.setCustId(3L);
            c.setCustName("贝吉塔飞船公司");*/

            //2、推荐的方式：先查询出来，再更新
            Customer c = em.find(Customer.class, 3L);
            c.setCustName("布玛飞船公司");
            em.merge(c);
            //====================================================
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }

    /**
     *  删除对象
     */
    @Test
    public void testDelete(){
        EntityManager em = null;
        EntityTransaction tx = null;
        try{
            em = JpaUtil.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //====================================================
            //进行增删改查操作
            Customer c = em.find(Customer.class, 3L);

            //删除
            em.remove(c);
            //====================================================
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }

}
