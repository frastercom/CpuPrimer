package json.object;

public class CPU {

    private Long id;
    private String model;
    private String name;
    private String ggz;
    private String description;

    public CPU() {
    }

    public CPU(String model, String name, String ggz, String description) {
        this.model = model;
        this.name = name;
        this.ggz = ggz;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGgz() {
        return ggz;
    }

    public void setGgz(String ggz) {
        this.ggz = ggz;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
