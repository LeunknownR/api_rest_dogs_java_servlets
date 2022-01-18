package dto;

public class DogDTO {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    private Integer id;
    private RaceDogDTO raceDog;
    private SizeDogDTO sizeDog;
    private String name, description, owner;
    private Double weight;
    private Boolean carried;
    //</editor-fold>

    public DogDTO() {
        this.id = 0;
        this.raceDog = new RaceDogDTO();
        this.sizeDog = new SizeDogDTO();
        this.name = "";
        this.description = "";
        this.owner = "";
        this.weight = 0.0;
        this.carried = false;
    }

    public DogDTO(Integer id, RaceDogDTO raceDog, SizeDogDTO sizeDog, 
            String name, String description, String owner, 
            Double weight, Boolean carried) {
        this.id = id;
        this.raceDog = raceDog;
        this.sizeDog = sizeDog;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.weight = weight;
        this.carried = carried;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public RaceDogDTO getRaceDog() {
        return raceDog;
    }

    public void setRaceDog(RaceDogDTO raceDog) {
        this.raceDog = raceDog;
    }

    public SizeDogDTO getSizeDog() {
        return sizeDog;
    }

    public void setSizeDog(SizeDogDTO sizeDog) {
        this.sizeDog = sizeDog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean isCarried() {
        return carried;
    }

    public void setCarried(Boolean carried) {
        this.carried = carried;
    }
    //</editor-fold>
}
