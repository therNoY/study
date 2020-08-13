package design_patterns.active_type.state;

import java.util.Objects;

public class Voter {
    private String name;

    public Voter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Voter) {
            Voter voter = (Voter) o;
            return name.equals(voter.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
