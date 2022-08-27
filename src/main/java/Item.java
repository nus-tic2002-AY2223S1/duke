public class Item {

    public String name;
    public int type;

    public Item() {
        setName(null);
        setType(0);
    }

    public Item(String name) {
        this.name = name;
        setType(0);
    }

    public Item(String name, int type) {
        this.name = name;
        setType(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        if (type >= 0) {
            this.type = type;
        }
    }
}
