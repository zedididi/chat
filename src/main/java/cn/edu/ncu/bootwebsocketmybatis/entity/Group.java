package cn.edu.ncu.bootwebsocketmybatis.entity;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/18  9:13
 * @package: cn.edu.ncu.bootwebsocketmybatis.entity
 * @project: boot-websocket-mybatis
 */
public class Group {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
