public class Singers {
    private int id;
    private String name;
    private String address;
    private String dateOfBirth;
    private int numOfAlbumsPublished;

    //no arguments , 1 argument, 2 arguments, 3 arguments, 4 arguments, and 5 arguments constructors
    public Singers() {}

    public Singers(int id) {
        this.id = id;
    }
    public Singers(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Singers(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Singers(int id, String name, String address, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
    public Singers(int id, String name, String address, String dateOfBirth, int numOfAlbumsPublished) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.numOfAlbumsPublished = numOfAlbumsPublished;
    }

    //setter to set the values of individual instance variables of the singer object

    public void setId(int id) {
        this.id = id;}
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setNumOfAlbumsPublished(int numOfAlbumsPublished) {
        this.numOfAlbumsPublished = numOfAlbumsPublished;
    }

    // Setter to set all values at once
    public void setAllValues(int id, String name, String address, String dateOfBirth, int numOfAlbumsPublished) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.numOfAlbumsPublished = numOfAlbumsPublished;
    }

    //Getters to get the current individual values of each instance variables of the Singer object.
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public int getNumOfAlbumsPublished() {
        return numOfAlbumsPublished;
    }
}
