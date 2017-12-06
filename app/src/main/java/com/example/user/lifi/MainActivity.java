package com.example.user.lifi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

     private EditText mChildValueEditText;
     private TextView mChildValueTextView;
     private Button mAddButton, mRemoveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChildValueEditText = (EditText) findViewById(R.id.childValueEditText);
        mAddButton = (Button) findViewById(R.id.addContent);
        mRemoveButton = (Button) findViewById(R.id.removeContent);
        mChildValueTextView = (TextView) findViewById(R.id.childValueTextView);




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mRef = database.getReference("gf");

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String childValue = mChildValueEditText.getText().toString();
                mRef.setValue(childValue);

            }
        });

        mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            mRef.removeValue();
            }
        });

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String childValue = String.valueOf(dataSnapshot.getValue());
                mChildValueTextView.setText(childValue);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
