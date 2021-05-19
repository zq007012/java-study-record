package com.lagou.zq.code.task5;

//@EgAnnotation(value = "刘亦菲",value3 = "slfa")
@EgAnnotation("")
public class UseAnnotation {
    private String name;
    private int id;
    public UseAnnotation() {
    }
    public UseAnnotation(String name, int id) {
        setName(name);
        setId(id);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
