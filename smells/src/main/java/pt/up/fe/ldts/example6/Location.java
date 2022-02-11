package pt.up.fe.ldts.example6;

public class Location {
  private String locationLatitude;
  private String locationLongitude;
  private String locationName;

  public Location(String locationLatitude, String locationLongitude, String locationName){
    this.locationLatitude = locationLatitude;
    this.locationLongitude = locationLongitude;
    this.locationName = locationName;
  }

  public String getLocationLatitude() {
    return locationLatitude;
  }

  public void setLocationLatitude(String locationLatitude) {
    this.locationLatitude = locationLatitude;
  }

  public String getLocationLongitude() {
    return locationLongitude;
  }

  public void setLocationLongitude(String locationLongitude) {
    this.locationLongitude = locationLongitude;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  @Override
  public String toString(){
    return locationLatitude + "," + locationLongitude + " (" + locationName + ")";
  }
}
