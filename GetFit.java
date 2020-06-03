package com.example.fittestbmi;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.text.method.LinkMovementMethod;
        import android.widget.TextView;

public class GetFit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_fit);

        TextView text1=findViewById(R.id.tvFit1);
        text1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text2=findViewById(R.id.tvFit2);
        text2.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text3=findViewById(R.id.tvFit3);
        text3.setMovementMethod(LinkMovementMethod.getInstance());
    }



}
