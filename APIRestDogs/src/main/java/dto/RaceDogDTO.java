package dto;

public class RaceDogDTO {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">   
    private Integer id;
    private String raceDogName;
    //</editor-fold>

    public RaceDogDTO() {
        id = 0;
        raceDogName = ""; 
    }

    public RaceDogDTO(Integer id, String raceDogName) {
        this.id = id;
        this.raceDogName = raceDogName;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRaceDogDTO() {
        return raceDogName;
    }

    public void setRaceDogDTO(String raceDogName) {
        this.raceDogName = raceDogName;
    }
    //</editor-fold>
}
