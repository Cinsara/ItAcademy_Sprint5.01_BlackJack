package cat.itacademy.s05.t01.dto;

public class ChangePlayerNameRequest {
    private String newName;

    public ChangePlayerNameRequest(){
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
