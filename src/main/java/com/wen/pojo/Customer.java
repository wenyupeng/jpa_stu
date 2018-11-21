package com.wen.pojo;

import javax.persistence.*;

/**
 *  @Entity         ： 标注当前类是实体类
 *  @Table          ： 标注当前实体类与数据库表映射
 *                      属性：name ：写的表名
 *  @Id              ：表示当前属性是主键属性
 *  @GeneratedValue ：表示主键的生成策略
 *                          四种：IDENTITY、SEQUENCE、TABLE、AUTO
 *  @Column          : 映射类的属性和表的字段
 *                      属性：name: 字段名称
 *
 *   主键生成策略： 写在@GeneratedValue注解中的 strategy的属性中
 *              IDENTITY ： 使用数据库的自增长策略  例如：mysql中的AUTO_INCREMENT 【重点】
 *              SEQUENCE:   使用数据库的序列机制    例如：Oracle    【重点】
 *              TABLE:      使用数据库的表来维护主键，每次都从数据库表中查询id，然后加一作为下面的主键值，不好，一般不用
 *              AUTO ：     官方：jpa根据数据库选择最优的策略    但是用这个它只会采用Table策略
 */
@Entity
@Table(name="cst_customer")
public class Customer {

    @Id
    /*
    Oracle
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "abc")
    @SequenceGenerator(
            name = "abc",//序列名称，给策略引用的
            sequenceName = "seq_cusotmer_id",//对应的是数据库中的序列
            initialValue = 1,//从及开始
            allocationSize = 1//每次增长1，步长
    )*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)//数据库的自增长策略:mysql
    @Column(name="cust_id")
    private Long custId;

    @Column(name="cust_name")
    private String custName;

    @Column(name="cust_source")
    private String custSource;

    @Column(name="cust_industry")
    private String custIndustry;

    @Column(name="cust_level")
    private String custLevel;

    @Column(name="cust_phone")
    private String custPhone;

    @Column(name="cust_address")
    private String custAddress;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}
