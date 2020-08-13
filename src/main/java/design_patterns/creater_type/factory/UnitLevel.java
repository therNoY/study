package design_patterns.creater_type.factory;

/**
 * 单位对应的等级信息
 */
public abstract class UnitLevel {

    private Integer level;

    private Integer attach;

    private Integer defence;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAttach() {
        return attach;
    }

    public void setAttach(Integer attach) {
        this.attach = attach;
    }

    public Integer getDefence() {
        return defence;
    }

    public void setDefence(Integer defence) {
        this.defence = defence;
    }
}
