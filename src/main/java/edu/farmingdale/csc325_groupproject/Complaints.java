package edu.farmingdale.csc325_groupproject;

public class Complaints {
    public String CrimeDate;
    public String CrimeTime;
    public String CrimeCode;
    public String CrimeLoc;
    public String CrimeDesc;
    public String CrimeWeap;
    public int CrimePost;
    public String CrimeDist;
    public String CrimeNeigh;
    public String CrimeLoc1;
    public int CrimeTot;
    public String CrimeName;
    
   public Complaints(){
       this.CrimeDate = null;
       this.CrimeTime = null;
       this.CrimeCode = null;
       this.CrimeLoc = null;
       this.CrimeDesc = null;
       this.CrimeWeap = null;
       this.CrimePost = -1;
       this.CrimeDist = null;
       this.CrimeNeigh = null;
       this.CrimeLoc1 = null;
       this.CrimeTot = -1;
       this.CrimeName = null;
   }

    public String getCrimeDate() {
        return CrimeDate;
    }

    public void setCrimeDate(String CrimeDate) {
        this.CrimeDate = CrimeDate;
    }

    public String getCrimeTime() {
        return CrimeTime;
    }

    public void setCrimeTime(String CrimeTime) {
        this.CrimeTime = CrimeTime;
    }

    public String getCrimeCode() {
        return CrimeCode;
    }

    public void setCrimeCode(String CrimeCode) {
        this.CrimeCode = CrimeCode;
    }

    public String getCrimeLoc() {
        return CrimeLoc;
    }

    public void setCrimeLoc(String CrimeLoc) {
        this.CrimeLoc = CrimeLoc;
    }

    public String getCrimeDesc() {
        return CrimeDesc;
    }

    public void setCrimeDesc(String CrimeDesc) {
        this.CrimeDesc = CrimeDesc;
    }

    public String getCrimeWeap() {
        return CrimeWeap;
    }

    public void setCrimeWeap(String CrimeWeap) {
        this.CrimeWeap = CrimeWeap;
    }

    public int getCrimePost() {
        return CrimePost;
    }

    public void setCrimePost(int CrimePost) {
        this.CrimePost = CrimePost;
    }

    public String getCrimeDist() {
        return CrimeDist;
    }

    public void setCrimeDist(String CrimeDist) {
        this.CrimeDist = CrimeDist;
    }

    public String getCrimeNeigh() {
        return CrimeNeigh;
    }

    public void setCrimeNeigh(String CrimeNeigh) {
        this.CrimeNeigh = CrimeNeigh;
    }

    public String getCrimeLoc1() {
        return CrimeLoc1;
    }

    public void setCrimeLoc1(String CrimeLoc1) {
        this.CrimeLoc1 = CrimeLoc1;
    }

    public int getCrimeTot() {
        return CrimeTot;
    }

    public void setCrimeTot(int CrimeTot) {
        this.CrimeTot = CrimeTot;
    }

    public String getCrimeName() {
        return CrimeName;
    }

    public void setCrimeName(String CrimeName) {
        this.CrimeName = CrimeName;
    }
   
   public String toString(){
       return "Crime Time: " + this.getCrimeTime() + "\nCrime Date: " + this.getCrimeDate()+
               "\nCrime Code: " + this.getCrimeCode() + "\nCrime Location: " + this.getCrimeLoc() +
                "\nCrime Description: " + this.getCrimeDesc() + "\nCrime Weapon: " + this.getCrimeWeap() +
               "\nCrime Post: " + this.getCrimePost() + "\nCrime Disttrict: " + this.getCrimeDist() + 
               "\nCrime Neighborhood: " + this.getCrimeNeigh() + "\nCrime Location Coords: " + this.getCrimeLoc1() + 
               "\nCriminal Name: " + this.getCrimeName() + "\nCrime Incident Count: " + this.getCrimeTot(); 
   }
   
}