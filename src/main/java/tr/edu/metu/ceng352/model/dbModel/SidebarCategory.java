package tr.edu.metu.ceng352.model.dbModel;

/**
 * Created by Serhat CAN on 01.06.2015.
 */
public class SidebarCategory {
    private int id;
    private String title;
    private String description;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
