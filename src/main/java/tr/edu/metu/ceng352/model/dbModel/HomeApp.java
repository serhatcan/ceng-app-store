package tr.edu.metu.ceng352.model.dbModel;

/**
 * Created by Serhat CAN on 31.05.2015.
 */
public class HomeApp {
    int id;
    String title;
    Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
