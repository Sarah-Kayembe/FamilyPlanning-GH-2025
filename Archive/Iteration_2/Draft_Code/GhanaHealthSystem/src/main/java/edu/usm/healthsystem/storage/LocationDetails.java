public class LocationDetails {
    private int id;
    private String address;
    private String district;
    private String subDistrict;
    private String facilityZone;

    public LocationDetails() {}

    public LocationDetails(String address, String district, String subDistrict, String facilityZone) {
        this.address = address;
        this.district = district;
        this.subDistrict = subDistrict;
        this.facilityZone = facilityZone;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getSubDistrict() { return subDistrict; }
    public void setSubDistrict(String subDistrict) { this.subDistrict = subDistrict; }
    public String getFacilityZone() { return facilityZone; }
    public void setFacilityZone(String facilityZone) { this.facilityZone = facilityZone; }
}
