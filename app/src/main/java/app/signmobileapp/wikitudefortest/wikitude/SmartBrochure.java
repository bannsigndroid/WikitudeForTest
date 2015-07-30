package app.signmobileapp.wikitudefortest.wikitude;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;

import java.util.List;

import app.signmobileapp.wikitudefortest.MainActivity;
import app.signmobileapp.wikitudefortest.VideoViewActivity;
import app.signmobileapp.wikitudefortest.data.Brochure;
import app.signmobileapp.wikitudefortest.data.GetData;

/**
 * Created by surachetteerapoj on 7/23/2015 AD.
 */
public class SmartBrochure extends SampleCamActivity {
    GetData getData = new GetData();
    public SmartBrochure() {

        getData.getbrochre();
    }

    @Override
    public String getARchitectWorldPath() {
        return "brochure/index.html";
    }

    @Override
    public ArchitectView.ArchitectUrlListener getUrlListener() {
        return new ArchitectView.ArchitectUrlListener() {
            @Override
            public boolean urlWasInvoked(String uriString) {
                Uri invokedUri = Uri.parse(uriString);
                Brochure br = new Brochure();
                Log.d("host", " is " + invokedUri.getHost());
                Log.d("urlWasInvoked", " is "+invokedUri.getQueryParameter("id"));
                Log.d("name"," is "+invokedUri.getQueryParameter("title"));


                for(int i =0; i<MainActivity.brochureList.size(); i++){
                    br = MainActivity.brochureList.get(i);
                    if(invokedUri.getQueryParameter("title").equals(br.getBrochureID())){
                        Log.d("brochureName",br.getBrochureName());
                        MainActivity.brochure=br;
                    }
                }

                if ("video".equalsIgnoreCase(invokedUri.getHost())) {
                    Log.d("youtube", "is ok " + "");
                    if (br.getBrochureVideoURL().equals("")) {
                        Toast.makeText(SmartBrochure.this, " ไม่มีข้อมูล", Toast.LENGTH_LONG).show();
                        return true;
                    }

                    String url = MainActivity.brochure.getBrochureVideoURL();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);


//                    Intent i = new Intent(SmartBrochure.this,VideoViewActivity.class);
//                    startActivity(i);
                }

                return true;
            }
        };
    }
}
