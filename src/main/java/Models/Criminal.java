package Models;

public class Criminal {
    
    public String CrimeDate,Neighborhood,Name,Description,Address;
    public int Post;

    
    public Criminal(){
       this.CrimeDate = null;
       this.Neighborhood = null;
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

    @Override
    public String toString(){
       return this.getAddress()+this.getCrimeDate()+this.getDescription()+this.getName()+this.getNeighborhood()+this.getPost();
   }
}
