package com.easysitp.easysitp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.easysitp.easysitp.utils.JSONParser;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Route {
    GoogleMap mMap;
    Context context;
    String lang;
    String distancia;

    public boolean drawRoute(GoogleMap map, Context c, ArrayList<LatLng> points, String language, boolean optimize) {
        mMap = map;
        context = c;
        lang = language;
        if (points.size() == 2) {
            String url = makeURL(points.get(0).latitude, points.get(0).longitude, points.get(1).latitude, points.get(1).longitude, "driving");
            new connectAsyncTask(url, false).execute();
            return true;
        } else if (points.size() > 2) {
            String url = makeURL(points, "driving", optimize);
            new connectAsyncTask(url, false).execute();
            return true;
        }

        return false;

    }

    public void drawRoute(GoogleMap map, Context c, LatLng source, LatLng dest, String language) {
        mMap = map;
        context = c;

        String url = makeURL(source.latitude, source.longitude, dest.latitude, dest.longitude, "driving");
        new connectAsyncTask(url, false).execute();
        lang = language;

    }

    public static String getDistancia(String result) {
        final JSONObject json;
        try {
            json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONArray arrayLegs = routes.getJSONArray("legs");
            JSONObject legs = arrayLegs.getJSONObject(0);
            Step step = new Step(legs);

            return step.distance;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "no";
    }

    public String makeURL(ArrayList<LatLng> points, String mode, boolean optimize) {
        StringBuilder urlString = new StringBuilder();

        if (mode == null)
            mode = "driving";

        urlString.append("http://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(points.get(0).latitude);
        urlString.append(',');
        urlString.append(points.get(0).longitude);
        urlString.append("&destination=");
        urlString.append(points.get(points.size() - 1).latitude);
        urlString.append(',');
        urlString.append(points.get(points.size() - 1).longitude);

        urlString.append("&waypoints=");
        if (optimize)
            urlString.append("optimize:true|");
        urlString.append(points.get(1).latitude);
        urlString.append(',');
        urlString.append(points.get(1).longitude);

        for (int i = 2; i < points.size() - 1; i++) {
            urlString.append('|');
            urlString.append(points.get(i).latitude);
            urlString.append(',');
            urlString.append(points.get(i).longitude);
        }


        urlString.append("&sensor=true&mode=" + mode);


        return urlString.toString();
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    public String makeURL(double sourcelat, double sourcelog, double destlat, double destlog, String mode) {
        StringBuilder urlString = new StringBuilder();

        if (mode == null)
            mode = "driving";

        urlString.append("https://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(sourcelat);
        urlString.append(",");
        urlString
                .append(sourcelog);
        urlString.append("&destination=");// to
        urlString
                .append(destlat);
        urlString.append(",");
        urlString.append(destlog);
        urlString.append("&sensor=false&mode=" + mode + "&alternatives=true&language=" + lang);
        urlString.append("&key=AIzaSyC0cS5GzPZxwrm1NLt_i30roitcHlSmO40");

        return urlString.toString();
    }

    private void drawPath(String result, boolean withSteps) {

        try {
            //Tranform the string into a json object
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);

            for (int z = 0; z < list.size() - 1; z++) {
                LatLng src = list.get(z);
                LatLng dest = list.get(z + 1);
                Polyline line = mMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(src.latitude, src.longitude), new LatLng(dest.latitude, dest.longitude))
                        .width(10)
                        .endCap(new RoundCap())
                        .startCap(new RoundCap())
                        .color(Color.rgb(52, 151, 253)).geodesic(true));
            }


            if (withSteps) {
                JSONArray arrayLegs = routes.getJSONArray("legs");
                JSONObject legs = arrayLegs.getJSONObject(0);
                JSONArray stepsArray = legs.getJSONArray("steps");
                //put initial point
            }

        } catch (JSONException e) {


        }

    }

    private class connectAsyncTask extends AsyncTask<Void, Void, String> {
        String url;
        boolean steps;
        private ProgressDialog progressDialog;

        connectAsyncTask(String urlPass, boolean withSteps) {
            url = urlPass;
            steps = withSteps;

        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            JSONParser jParser = new JSONParser();
            String json = jParser.getJSONFromUrl(url);
            return json;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                drawPath(result, steps);
                Log.i("error1", result);
            }
        }
    }

    public static class Step {
        public String distance;

        Step(JSONObject stepJSON) {
            try {
                distance = stepJSON.getJSONObject("distance").getString("value");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}