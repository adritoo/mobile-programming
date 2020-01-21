package fr.android.adrien.handler;

        import android.app.Activity;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.widget.ProgressBar;

    class ProgressTestActivity extends Activity {
    private Handler handler;
    private ProgressBar progress;
    /** Called everytime the activity is created */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        handler = new Handler();
    }
    public void startProgress(View view) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    final int value = i;
                    // simulate a slow network !
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progress.setProgress(value);
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }
}