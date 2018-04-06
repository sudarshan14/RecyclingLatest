package sudarshan.bhatt.recycling;

/**
 * Created by 10608780 on 03-04-2018.
 */

public class MyLocationDataModel {

    private String homeLocation;
    private String officeLocation;
    private String partyLocation;

    public MyLocationDataModel() {
    }

    public MyLocationDataModel(String homeLocation, String officeLocation, String partyLocation) {
        this.setHomeLocation(homeLocation);
        this.setOfficeLocation(officeLocation);
        this.setPartyLocation(partyLocation);
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getPartyLocation() {
        return partyLocation;
    }

    public void setPartyLocation(String partyLocation) {
        this.partyLocation = partyLocation;
    }
}
