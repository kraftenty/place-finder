package com.placefinder.placefinder.utility;

public class DistanceCalculator {
    // 두 지점간의 거리 계산 (각각 위도와 경도를 입력으로 받아서 미터 단위의 거리를 double형으로 리턴)
    public static String calculateDistanceInMeters(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // 지구 반지름 (km)

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // 거리를 미터로 변환

        if (distance < 1000) {
            return Integer.toString((int)distance) + "m";
        } else {
            return String.format("%.2f", distance / 1000) + "km";
        }
    }
}
