package design_patterns.creater_type.factory.facrory_method;

import design_patterns.creater_type.factory.Unit;
import design_patterns.creater_type.factory.WaterElement;

public class WaterElementFactory extends FactoryMethod{
    @Override
    public Unit createUnit() {
        return new WaterElement();
    }
}
