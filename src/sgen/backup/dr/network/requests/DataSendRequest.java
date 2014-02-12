package sgen.backup.dr.network.requests;

import android.os.AsyncTask;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import sgen.backup.dr.network.RequestConfig;

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
            StringEntity params = new StringEntity(
                    "{" +
                            "\"send_id_u\":\"" + contents[0] + "\"" +
                            "\"send_id_d\":\"" + contents[1] + "\"" +
                            "\"send_personal\":\"" + contents[2] + "\"" +
                            "\"send_selfish\":\"" + contents[3] + "\"" +
                            "\"send_past\":\"" + contents[4] + "\"" +
                            "}");
            params.setContentType("application/json");
            params.setContentEncoding("UTF-8");
            request.addHeader("Content-Type", "application/json");
            request.addHeader("Accept", "application/json");
            request.setEntity(params);

            response = httpClient.execute(request);

        } catch (Exception ex) {

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
                JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
                callback.onComplete(json);
            } catch (Exception e) {
                callback.onFail();
            }
        }
    }
}
