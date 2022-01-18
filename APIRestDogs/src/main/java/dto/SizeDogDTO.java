package dto;

public class SizeDogDTO {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">   
    private Integer id;
    private String sizeDogName;
    //</editor-fold>
    public SizeDogDTO() {
        id = 0;
        sizeDogName = "";
    }
    public SizeDogDTO(Integer id, String sizeDogName) {
        this.id = id;
        this.sizeDogName = sizeDogName;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSizeDog() {
        return sizeDogName;
    }

    public void setSizeDog(String sizeDogName) {
        this.sizeDogName = sizeDogName;
    }
    //</editor-fold>
}
