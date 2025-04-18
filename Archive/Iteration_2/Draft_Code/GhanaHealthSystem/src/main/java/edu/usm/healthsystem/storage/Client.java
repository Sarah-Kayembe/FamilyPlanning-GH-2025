public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private String sex;
    private String maritalStatus;
    private int age;
    private int parity;

    public Client() {}

    public Client(String firstName, String lastName, String sex, String maritalStatus, int age, int parity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.maritalStatus = maritalStatus;
        this.age = age;
        this.parity = parity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }
    public String getMaritalStatus() { return maritalStatus; }
    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getParity() { return parity; }
    public void setParity(int parity) { this.parity = parity; }
}
