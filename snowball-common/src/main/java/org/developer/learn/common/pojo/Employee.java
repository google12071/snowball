package org.developer.learn.common.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Employee
 * @Description:
 * @Author lfq
 * @Date 2020/5/13
 **/
@Data
@Slf4j
public class Employee extends Person implements HumanBehavior, BiologyBehavior {
    public static Integer totalNum = 0;
    public int empNo;
    protected String position;
    private int salary;

    public Employee(String name, Integer age, String address, int empNo, String position, int salary) {
        super(name, age, address);
        this.empNo = empNo;
        this.position = position;
        this.salary = salary;
        Employee.totalNum++;
    }

    public Employee(int empNo, String position, int salary) {
        this.empNo = empNo;
        this.position = position;
        this.salary = salary;
        Employee.totalNum++;
    }

    public void sayHello() {
        System.out.println(String.format("Hello, 我是 %s, 今年 %s 岁, 家乡是%s, 我目前的工作是%s, 月入%s元\n", name, age, getAddress(), position, salary));
    }

    private void work() {
        System.out.println(String.format("My name is %s, 工作中勿扰.", name));
    }

    @Override
    public void breath() {
        log.info("breath implements BiologyBehavior");
    }

    @Override
    public void laugh() {
        log.info("breath implements HumanBehavior");
    }

    @Override
    public void sport(String sportName) {
        log.info("i like playing,{}", sportName);
    }

    /**
     * static静态方法 计算乘方
     *
     * @param x
     * @return
     */
    public static Double square(Double x) {
        return x * x;
    }
}
