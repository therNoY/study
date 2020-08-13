package design_patterns.creater_type.factory.easy_factory;

import design_patterns.creater_type.factory.Load;
import design_patterns.creater_type.factory.Unit;
import design_patterns.creater_type.factory.WaterElement;

/**
 * 简单工厂
 * 简单工厂模式违背了 2.开闭原则
 * 想要添加新的单位需要修改createUnit
 */
public class EasyFactory {

    private static EasyFactory easyFactory = new EasyFactory();

    public static EasyFactory instance() {
        return easyFactory;
    }

    public Unit createUnit(String name) {

        switch (name) {
            case "Load":
                return new Load();
            case "WaterElement":
                return new WaterElement();
            default:
                return null;
        }
    }
}
