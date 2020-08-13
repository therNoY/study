package design_patterns.creater_type.factory;

import design_patterns.creater_type.factory.abstract_factory.AbstractFactory;
import design_patterns.creater_type.factory.easy_factory.EasyFactory;
import design_patterns.creater_type.factory.facrory_method.FactoryMethod;
import design_patterns.creater_type.factory.facrory_method.LoadFactory;

public class FactoryMain {

    public static void main(String[] args) {
//        easyFactory();
//        factoryMethod();
        abstractFactory();
    }

    /**
     * 测试简单工厂
     */
    public static void easyFactory() {
        Unit unit = EasyFactory.instance().createUnit("Load");
        unit.disPlay();
    }

    ;

    /**
     * 测试工厂方法
     * 工厂方法是简单工厂设计的升级
     */
    public static void factoryMethod() {
        FactoryMethod factoryMethod = new LoadFactory();
        Unit unit = factoryMethod.createUnit();
        unit.disPlay();
    }

    /**
     * 之前都是测试创建一个单位 使用以上连个工厂模式可以解决
     * 但是如果要创建的是一个产品族呢 比如创建单位的时候要创建军队的时候
     * 除了创建单位还要创建对应的领土 单纯的简单工厂和工厂方法就无法满足了
     * 抽象工厂是工厂方法维度的升级
     */
    public static void abstractFactory() {
        AbstractFactory factory = new design_patterns.creater_type.factory.abstract_factory.LoadFactory();
        Unit unit = factory.initArmy();
        unit.disPlay();
    }
}
