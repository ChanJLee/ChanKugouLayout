package com.chan.slidingofflayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by chan on 15-7-16.
 */
public class KuGouActivity extends Activity {

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.demo_layout);

        Button button = (Button) findViewById(R.id.m_testButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KuGouActivity.this,"test",Toast.LENGTH_SHORT).show();
            }
        });

        KuGouLayout layout = (KuGouLayout) button.getParent();
        layout.setOnCloseListener(new KuGouLayout.OnCloseListener() {
            @Override
            public void onCloseListener() {
                KuGouActivity.this.finish();
            }
        });
    }

    static public Intent getIntent(Context context){

        return new Intent(context,KuGouActivity.class);
    }
}
