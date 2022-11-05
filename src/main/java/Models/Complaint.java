package Models;

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
    }
public void setCrimeTime(String CrimeTime) {
        this.CrimeTime = CrimeTime;
    }

    public String getCrimeDesc() {
        return CrimeDesc;
    }
    public void setCrimeDesc(String CrimeDesc) {
        this.CrimeDesc = CrimeDesc;
    }

    public String getNeighborhood() {
        return Neighborhood;
    }
    public void setNeighborhood(String Neighborhood) {
        this.Neighborhood = Neighborhood;
    }

    public String getCrimeName() {
        return CrimeName;
    }
    public void setCrimeName(String CrimeName) {
        this.CrimeName = CrimeName;
    }
   
   public String toString(){
       return "Crime Time: " + this.getCrimeTime() + "\nCrime Date: " + this.getCrimeDate()+"Crime Neighborhood: " + this.getNeighborhood()+"Crime Name: " + this.getCrimeName();
   }
}
