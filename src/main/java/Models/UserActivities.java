package Models;

public class UserActivities {
    
    boolean firstTimeUser;
    String lastPage;
    boolean Editting;
    public Criminal criminalUpdate = new Criminal();
    public Complaint complaintUpdate = new Complaint();
    public User userUpdate = new User();
    public User firstSignIn = new User();
    public User current = new User();

    public UserActivities() {
        firstTimeUser = false;
        lastPage = "SignIn";
        Editting = false;
        criminalUpdate = null;
        complaintUpdate = null;
        userUpdate = null;
    }

    public boolean isFirstTimeUser() {
        return firstTimeUser;
    }public void setFirstTimeUser(boolean firstTimeUser) {
        this.firstTimeUser = firstTimeUser;
    }

    public String getLastPage() {
        return lastPage;
    }public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isEditting() {
        return Editting;
    }public void setEditting(boolean Editting) {
        this.Editting = Editting;
    }
    
    public void setEdittingCriminal(Criminal cr){
        this.criminalUpdate = cr;
        setEditting(true);
    }
    public void setEdittingComplaint(Complaint cp){
        this.complaintUpdate = cp;
        setEditting(true);
    }
    public void setEdittingUser(User u){
        this.userUpdate = u;
        setEditting(true);
    }
}
