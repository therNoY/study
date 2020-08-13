package design_patterns.creater_type.factory.abstract_factory;

import design_patterns.creater_type.factory.Load;
import design_patterns.creater_type.factory.LoadUnitLevel;
import design_patterns.creater_type.factory.Unit;
import design_patterns.creater_type.factory.UnitLevel;

public class LoadFactory extends AbstractFactory{
    @Override
    public Unit initArmy() {
        Unit unit = new Load();
        UnitLevel unitLevel = new LoadUnitLevel();
        return unit;
    }
}
