package sudarshan.bhatt.recycling;

import java.util.ArrayList;

/**
 * Created by 10608780 on 03-04-2018.
 */

public class GenerateData {


    protected ArrayList<MyCarsDataModel> carDetails() {
        ArrayList<MyCarsDataModel> carList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            MyCarsDataModel carsDataModel = new MyCarsDataModel();
            carsDataModel.setCarName("Car : " + i);
            carsDataModel.setCarModel("Model " + i);
            carsDataModel.setCarPrice("Price" + (i + 10) * 8796);

            carList.add(carsDataModel);
        }

        return carList;


    }

    protected ArrayList<MyLocationDataModel> myLocationDetails() {

        ArrayList<MyLocationDataModel> locationList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            MyLocationDataModel locationDataModel = new MyLocationDataModel();

            locationDataModel.setHomeLocation("Home " + i);
            locationDataModel.setOfficeLocation("Office " + i);
            locationDataModel.setPartyLocation("Party " + i);

            locationList.add(locationDataModel);
        }
        return locationList;
    }

    protected  ArrayList<String> stringArrayListData(){

        ArrayList<String> stringArrayList = new ArrayList<>();

        for(int i= 0; i<5; i++){
            stringArrayList.add("item :"+i);
        }

        return  stringArrayList;

    }
}
