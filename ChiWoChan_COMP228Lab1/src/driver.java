public class driver {
    public static void main(String[] args) {
        // Create a Singer object with no-argument constructor
        Singers singer1 = new Singers();

        // Display default values
        System.out.println("Default values for singer1:");
        System.out.println("Singer ID: " + singer1.getId());
        System.out.println("Singer Name: " + singer1.getName());
        System.out.println("Address: " + singer1.getAddress());
        System.out.println("Date of Birth: " + singer1.getDateOfBirth());
        System.out.println("Number of Albums Published: " + singer1.getNumOfAlbumsPublished());

        // Set values using setters
        singer1.setId(1);
        singer1.setName("Bruno");
        singer1.setAddress("Mars");
        singer1.setDateOfBirth("1988-01-01");
        singer1.setNumOfAlbumsPublished(8);

        // Display updated values
        System.out.println("\nUpdated values for singer1:");
        System.out.println("Singer ID: " + singer1.getId());
        System.out.println("Singer Name: " + singer1.getName());
        System.out.println("Address: " + singer1.getAddress());
        System.out.println("Date of Birth: " + singer1.getDateOfBirth());
        System.out.println("Number of Albums Published: " + singer1.getNumOfAlbumsPublished());

    }

}
