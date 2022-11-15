package Models;

import javafx.scene.image.Image;

public class User 
{   
    private String username;
    private String password;
    private int security;
    private String firstName;
    private String lastName;
    private String email;
    private int securityLvl;
    private Image profilePic;
    

    public User() {
        this.username = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.securityLvl = 0;
    }

    public User(String username, String password,String firstName, String lastName, String email,int securityLvl) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.securityLvl = securityLvl;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getSecurityLvl() {
        return securityLvl;
    }
    public void setSecurityLvl(int securityLvl) {
        this.securityLvl = securityLvl;
    }
    
    public int getSecurityLevel() {
        return securityLvl;
    }
    public void setSecurityLevel(int securityLevel) {
        this.securityLvl = securityLevel;
    }

    public Image getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(String name) {
        this.profilePic = new Image("/Aesthetics/"+name);
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", securityLevel=" + securityLvl + '}';
    }
    
}
