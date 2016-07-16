package uk.co.tomi33.notesApp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Button button = (Button) findViewById(R.id.buttonSave);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    saveNote(view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void saveNote(View view) throws Exception{
        EditText noteTitleField = (EditText) findViewById(R.id.editTextNoteTitle);
        EditText noteContentField = (EditText) findViewById(R.id.editTextNoteContent);

        String noteTitle = noteTitleField.getText().toString();
        String noteContent = noteContentField.getText().toString();

        FileOutputStream fos = openFileOutput(noteTitle, Context.MODE_PRIVATE);
        fos.write(noteContent.getBytes());
        fos.close();
    }

}
