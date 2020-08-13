package design_patterns.creater_type.factory.facrory_method;

import design_patterns.creater_type.factory.Load;
import design_patterns.creater_type.factory.Unit;

/**
 * 专门用来生产load
 */
public class LoadFactory extends FactoryMethod{

    @Override
    public Unit createUnit() {
        return new Load();
    }
}
