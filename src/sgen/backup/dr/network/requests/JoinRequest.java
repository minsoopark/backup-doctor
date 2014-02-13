package sgen.backup.dr.network.requests;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import sgen.backup.dr.network.RequestConfig;

import java.util.ArrayList;
import java.util.List;

public class JoinRequest extends AsyncTask<String, Void, HttpResponse> {
    public interface JoinRequestCallback {
        public void onComplete(JSONObject json);
        public void onFail();
    }

    private JoinRequestCallback callback;

    public JoinRequest(JoinRequestCallback callback) {
        this.callback = callback;
    }

    @Override
    protected HttpResponse doInBackground(String... contents) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            String url = RequestConfig.BASE_URL + "/add/user/";
            HttpPost request = new HttpPost(url);
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            list.add(new BasicNameValuePair("id", contents[0]));
            list.add(new BasicNameValuePair("password", contents[1]));
            list.add(new BasicNameValuePair("name", contents[2]));
            list.add(new BasicNameValuePair("gender", contents[3]));
            list.add(new BasicNameValuePair("phone", contents[4]));
            list.add(new BasicNameValuePair("birth", contents[5]));
            list.add(new BasicNameValuePair("height", contents[6]));
            list.add(new BasicNameValuePair("weight", contents[7]));
            list.add(new BasicNameValuePair("blood", contents[8]));
            list.add(new BasicNameValuePair("smoking", contents[9]));
            list.add(new BasicNameValuePair("drinking", contents[10]));
            list.add(new BasicNameValuePair("disease", contents[11]));
            list.add(new BasicNameValuePair("medicine", contents[12]));
            list.add(new BasicNameValuePair("allergy", contents[13]));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
//            StringEntity params = new StringEntity(
//                    "{" +
//                            "\"id\":\"" + contents[0] + "\"" +
//                            "\"password\":\"" + contents[1] + "\"" +
//                            "\"name\":\"" + contents[2] + "\"" +
//                            "\"gender\":\"" + contents[3] + "\"" +
//                            "\"phone\":\"" + contents[4] + "\"" +
//                            "\"birth\":\"" + contents[5] + "\"" +
//                            "\"height\":\"" + contents[6] + "\"" +
//                            "\"weight\":\"" + contents[7] + "\"" +
//                            "\"blood\":\"" + contents[8] + "\"" +
//                            "\"smoking\":\"" + contents[9] + "\"" +
//                            "\"drinking\":\"" + contents[10] + "\"" +
//                            "\"disease\":\"" + contents[11] + "\"" +
//                            "\"medicine\":\"" + contents[12] + "\"" +
//                            "\"allergy\":\"" + contents[13] + "\"" +
//                            "}");
            request.addHeader("Content-Type", "application/x-www-form-urlencoded");
            request.addHeader("Accept", "application/json");
            request.setEntity(entity);

            response = httpClient.execute(request);

        } catch (Exception ex) {
            callback.onFail();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        return response;
    }

    @Override
    protected void onPostExecute(HttpResponse response) {
        if(response.getStatusLine().getStatusCode() >= 400) {
            callback.onFail();
        } else {
            try {
                if (response.getStatusLine().getStatusCode() == 200) {
                    callback.onComplete(null);
                } else {
                    callback.onFail();
                }
            } catch (Exception e) {
                callback.onFail();
            }
        }
    }
}
