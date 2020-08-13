package design_patterns.creater_type.factory.facrory_method;

import design_patterns.creater_type.factory.Unit;

/**
 * 工厂方法模式
 * 完美解决了简单工厂添加新的单位只需要添加新的工厂就行了
 */
public abstract class FactoryMethod {
    public abstract Unit createUnit();
}
