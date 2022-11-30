package Models;

import com.google.cloud.firestore.DocumentSnapshot;

public class Criminal {
    
    public String CrimeDate,CrimeTime,Neighborhood,Name,Description,Address;
    public int Post;

    public Criminal(){
       this.CrimeDate = null;
        this.Neighborhood = null;
        this.Name = null;
        this.Description = null;
        this.Address = null;
        this.CrimeTime = null;
        this.Post = 0;
    }

    public String getName() {
        return Name;
    }public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }public void setDescription(String Descrption) {
        this.Description = Descrption;
    }

    public String getAddress() {
        return Address;
    }public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getPost() {
        return Post;
    }public void setPost(int Post) {
        this.Post = Post;
    }
    
    public String getCrimeDate() {
        return CrimeDate;
    }public void setCrimeDate(String CrimeDate) {
        this.CrimeDate = CrimeDate;
    }

    public String getNeighborhood() {
        return Neighborhood;
    }public void setNeighborhood(String Neighborhood) {
        this.Neighborhood = Neighborhood;
    }

    public String getCrimeTime() {
        return CrimeTime;
    } public void setCrimeTime(String CrimeTime) {
        this.CrimeTime = CrimeTime;
    }
    
    @Override
    public String toString() {
        return this.Name;
    }

    public void fillCriminalInfo(DocumentSnapshot DS) {
        try {
            if (DS != null) {
            this.setName(DS.get("Name").toString());
            this.setNeighborhood(DS.get("Neighborhood").toString());
            this.setCrimeDate(DS.get("crimeDate").toString());
            this.setAddress(DS.get("Address").toString());
            this.setCrimeTime(DS.get("crimeTime").toString());
            this.setDescription(DS.get("Description").toString());
            }
        } catch (NullPointerException e) {
            
        }
    }
}
