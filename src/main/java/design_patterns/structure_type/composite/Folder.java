package design_patterns.structure_type.composite;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Node {

    private String name;
    private List<Node> nodeList = new ArrayList<Node>();

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void add(Node node) {
        this.nodeList.add(node);
    }

    @Override
    public void remove(Node node) {
        this.nodeList.remove(node);
    }

    @Override
    public void print() {
        System.out.println(this.getName());
        for (Node node : this.nodeList) {
            node.print();
        }
    }

}
