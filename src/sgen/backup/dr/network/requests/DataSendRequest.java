package sgen.backup.dr.network.requests;

import android.os.AsyncTask;
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

public class DataSendRequest extends AsyncTask<String, Void, HttpResponse> {
    public interface DataSendRequestCallback {
        public void onComplete(JSONObject json);
        public void onFail();
    }

    private DataSendRequestCallback callback;

    public DataSendRequest(DataSendRequestCallback callback) {
        this.callback = callback;
    }

    @Override
    protected HttpResponse doInBackground(String... contents) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            String url = RequestConfig.BASE_URL + "/data/send/";
            HttpPost request = new HttpPost(url);
            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            list.add(new BasicNameValuePair("send_id_u", contents[0]));
            list.add(new BasicNameValuePair("send_id_d", contents[1]));
            list.add(new BasicNameValuePair("send_personal", contents[2]));
            list.add(new BasicNameValuePair("send_selfish", contents[3]));
            if (contents[4] == null || contents[4].equals("")) contents[4] = "{}";
            list.add(new BasicNameValuePair("send_past", contents[4]));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
//            StringEntity params = new StringEntity(
//                    "{" +
//                            "\"send_id_u\":\"" + contents[0] + "\"" +
//                            "\"send_id_d\":\"" + contents[1] + "\"" +
//                            "\"send_personal\":\"" + contents[2] + "\"" +
//                            "\"send_selfish\":\"" + contents[3] + "\"" +
//                            "\"send_past\":\"" + contents[4] + "\"" +
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
