package com.pluralsight.bddfundamentals.mileage;

import com.pluralsight.bddfundamentals.airport.Passenger;

import java.util.ArrayList;
import java.util.List;

public class StatisticsUtil {

    private static List<Integer> createMileageList(Mileage mileage) {
        List<Integer> mileagesList = new ArrayList<>();

        for(Passenger passenger : mileage.getPassengersMileageListMap().keySet()) {
            mileagesList.addAll(mileage.getPassengersMileageListMap().get(passenger));
        }

        return mileagesList;
    }

    public static int totalDistance(Mileage mileage) {
        List<Integer> mileagesList = createMileageList(mileage);
        return mileagesList.stream().mapToInt(Integer::intValue).sum();
    }

    public static int minimumDistance(Mileage mileage) {
        List<Integer> mileagesList = createMileageList(mileage);
        return mileagesList.stream().mapToInt(Integer::intValue).min().getAsInt();
    }

    public static int maximumDistance(Mileage mileage) {
        List<Integer> mileagesList = createMileageList(mileage);
        return mileagesList.stream().mapToInt(Integer::intValue).max().getAsInt();
    }

}
