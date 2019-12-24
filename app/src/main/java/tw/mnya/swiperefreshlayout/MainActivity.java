package tw.mnya.swiperefreshlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView random_number = (TextView) findViewById(R.id.random_number);

        // Loading class (指定程式)
        class Loading implements Runnable {
            @Override
            public void run() {
                // 隨機生成 1~9999 數字
                int ranNum = (int) (Math.random() * 9999 + 1);
                random_number.setText(String.valueOf(ranNum));
            }
        }

        // 首次執行指定程式
        Loading loading = new Loading();
        loading.run();

        // 下拉重新載入
        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // 動畫結束
                mySwipeRefreshLayout.setRefreshing(false);

                // 重新執行指定程式
                Loading loading = new Loading();
                loading.run();
            }
        });
    }
}
