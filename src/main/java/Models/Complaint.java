package Models;

import com.google.cloud.firestore.DocumentSnapshot;

public class Complaint {
    
    public String CrimeDate,CrimeTime,CrimeName,Neighborhood,CrimeDesc;
    
   public Complaint(){
   }

    public String getCrimeDate() {
        return CrimeDate;
    }public void setCrimeDate(String CrimeDate) {
        this.CrimeDate = CrimeDate;
    }

    public String getCrimeTime() {
        return CrimeTime;
    }public void setCrimeTime(String CrimeTime) {
        this.CrimeTime = CrimeTime;
    }

    public String getCrimeDesc() {
        return CrimeDesc;
    }public void setCrimeDesc(String CrimeDesc) {
        this.CrimeDesc = CrimeDesc;
    }

    public String getNeighborhood() {
        return Neighborhood;
    }public void setNeighborhood(String Neighborhood) {
        this.Neighborhood = Neighborhood;
    }
   
   @Override
    public String toString(){
       return this.CrimeDesc;
    }
    public void fillComplaintInfo(DocumentSnapshot DS) {
        if(DS != null){
            this.setCrimeDate(DS.get("crimeDate").toString());
            this.setCrimeDesc(DS.get("Description").toString());
            this.setCrimeTime(DS.get("crimeTime").toString());
            this.setNeighborhood(DS.get("Neighborhood").toString());
        }
    }
}
