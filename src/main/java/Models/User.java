package Models;

import com.google.cloud.firestore.QueryDocumentSnapshot;

public class User 
{   
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int securityLvl;

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

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", securityLevel=" + securityLvl + '}';
    }
    
    public User DBtoObject(String uN,String pW,QueryDocumentSnapshot doc){
        String fname =  doc.getData().get("firstName").toString();
        String lname =  doc.getData().get("lastName").toString();
        String email =  doc.getData().get("email").toString();
        int lvl =  Integer.parseInt(doc.getData().get("securityLevel").toString());
        
        User client = new User(uN,pW,fname,lname,email,lvl);
        return client;
    }
    
}
