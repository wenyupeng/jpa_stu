package com.wen.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA的工具类： 保证一个项目只有一个EntityManagerFactory
 */
public class JpaUtil {
    //全局变量
    private static EntityManagerFactory factory = null;

    //静态代码块
    static{
        //生成工厂
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    /**
     * 提供一个方法获取实体类管理器
     */
    public static EntityManager createEntityManager(){
        return factory.createEntityManager();
    }

}
