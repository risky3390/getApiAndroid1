package lp3i.com.getapiandroid1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTampil;
    private TextView tvTampil;
    String url = "http://www.gookkis.com/hello.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnTampil.setOnClickListener(this);
    }

    private void initView() {
        btnTampil = (Button) findViewById(R.id.btn_tampil);
        tvTampil = (TextView) findViewById(R.id.tv_tampil);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tampil:
                ambilData();
            break;
        }
    }

    private void ambilData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responData = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTampil.setText(responData);
                    }
                });
            }
        });
    }


}
