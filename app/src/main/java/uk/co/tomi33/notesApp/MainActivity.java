package uk.co.tomi33.notesApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                changeActivity(view);
            }
        });

        Button getNotesButton = (Button) findViewById((R.id.buttonGetNotes));
        getNotesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    getNotes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        displayNotes();
    }


    public void changeActivity(View view) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public List<HashMap<String, String>> getNotes() {
        String[] fileList = fileList();
        List<HashMap<String, String>> notes = new ArrayList<HashMap<String, String>>();
        for (String fileName : fileList) {
            HashMap<String, String> note = new HashMap<String, String>();

            FileInputStream fis = null;
            try {
                fis = openFileInput(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            String content = Utils.readStringFromInputStream(fis);

            note.put("title", fileName);
            note.put("content", content);

            notes.add(note);
        }

        return notes;
    }


    private void displayNotes() {
        List<HashMap<String, String>> notes = getNotes();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutLinearVertical);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for(HashMap<String, String> note : notes) {
            TextView textView = new TextView(this);
            textView.setText(note.get("content"));
            linearLayout.addView(textView);
        }
    }
}
