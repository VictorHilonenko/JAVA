package ua.training.model.entity;

public class NoteBook {
    private int id;
    private String firstName;
    private String loginData;

    public NoteBook() {
    }

    public NoteBook(String firstName, String loginData)
                                    throws NotUniqueLoginException {
        if(DBNoteBook.checkLogin(loginData)){
             throw new NotUniqueLoginException("Not Unique Login",
                                    loginData);
        }
        this.firstName = firstName;
        this.loginData = loginData;
    }

    public NoteBook(int id, String firstName, String loginData) {
        this.id = id;
        this.firstName = firstName;
        this.loginData = loginData;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLoginData() {
        return loginData;
    }
    public void setLoginData(String loginData) {
        this.loginData = loginData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NoteBook{" +
                "firstName='" + firstName + '\'' +
                ", loginData='" + loginData + '\'' +
                '}';
    }
}
