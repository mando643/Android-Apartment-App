package com.example.apartmentlikestracker.ui.gallery;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.apartmentlikestracker.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


        SQLiteDatabase myDB;

        TextView results = (TextView) root.findViewById(R.id.results);
        String data = "";

        try
        {
            myDB = SQLiteDatabase.openDatabase("data/data/" + getActivity().getPackageName() + "/databases/aptlikes.db",
                    null, SQLiteDatabase.OPEN_READONLY);

            String mySQL = "SELECT * FROM apts";
            Cursor crs = myDB.rawQuery(mySQL, null);

            if (crs.moveToFirst())
            {
                do
                {
                    data += crs.getString(0) + "\n" +
                            crs.getInt(1) + " bedrooms " +
                            crs.getInt(2) + " baths\n$" +
                            crs.getInt(3) + " per month\n" +
                            crs.getString(4) + "\n\n";
                }
                while (crs.moveToNext());
                myDB.close();
            }
        }
        catch (SQLiteException e)
        {
            data = "Unable to display records";
        }

        results.setText(data);

        return root;
    }
}
