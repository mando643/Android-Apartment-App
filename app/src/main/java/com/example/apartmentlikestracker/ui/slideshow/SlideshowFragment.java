package com.example.apartmentlikestracker.ui.slideshow;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.apartmentlikestracker.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        Button btn = (Button) root.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText aptAddress = (EditText) root.findViewById(R.id.addressInput);
                String address = aptAddress.getText().toString();

                EditText aptBedRooms = (EditText) root.findViewById(R.id.bedroomInput);
                String numBedrooms = aptBedRooms.getText().toString();
                int numRooms = Integer.parseInt(numBedrooms);

                EditText aptBaths = (EditText) root.findViewById(R.id.bathInput);
                String numBaths = aptBaths.getText().toString();
                int baths = Integer.parseInt(numBaths);

                EditText aptRent = (EditText) root.findViewById(R.id.rentInput);
                String rentAmount = aptRent.getText().toString();
                int rent = Integer.parseInt(rentAmount);

                EditText aptNotes = (EditText) root.findViewById(R.id.notesInput);
                String notes = aptNotes.getText().toString();

                SQLiteDatabase myDB;

                try
                {
                    myDB = SQLiteDatabase.openDatabase("data/data/" + getActivity().getPackageName() + "/databases/aptlikes.db",
                            null, SQLiteDatabase.OPEN_READWRITE);

                    String mySQL = "INSERT INTO apts (ADDRESS, NUMBEDRMS, NUMBATHS, MONTHLYRENT, NOTES) VALUES ('" + address + "', " + numRooms + ", " + baths + ", " + rent + ", '" + notes + "');";
                    myDB.execSQL(mySQL);
                    myDB.close();
                }
                catch (SQLiteException e)
                {
                    return;
                }
            }
        });
        return root;
    }
}
