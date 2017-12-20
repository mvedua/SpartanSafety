package mvedua.msu.com.spartansafety.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mvedua.msu.com.spartansafety.R;

/**
 * Created by mvedua on 12/19/2017.
 */

class Resource {
    private String title;
    private String description;
    private String phoneNumber;
    private String color;

    Resource(String title, String description, String phoneNumber, String color){
        this.title = title;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.color = color;
    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getColor() { return color; }
}

class ResourceAdapter extends ArrayAdapter<Resource>{

    ResourceAdapter(Context context, ArrayList<Resource> resources){
        super(context, 0, resources);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Resource resource = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.resource_listview_layout, parent, false);
        }

        TextView resTitle = convertView.findViewById(R.id.title);
        TextView resDesc = convertView.findViewById(R.id.description);

        resTitle.setText(resource.getTitle());
        resDesc.setText(resource.getDescription());

        resTitle.setTextColor(Color.parseColor(resource.getColor()));

        return convertView;
    }
}

public class ResourcesFragment extends Fragment{

    ArrayList<Resource> resources = new ArrayList<Resource>();
    ListView listView;
    static ResourceAdapter adapter;

    private void initResources(){
        //TODO: Add Correct Phone Numbers
        resources.add(new Resource("Alcohol, Tobacco, & Other Drugs", "Collegiate Recovery Program", "(586)214-9433", "#3A172B"));
        resources.add(new Resource("Counseling Center", "Schedule an Appointment", "ToBeSet", "#2E4348"));
        resources.add(new Resource("Crisis Text Line", "Get Help Now: Free, 24/7, Confidential", "ToBeSet", "#9E5B3E"));
        resources.add(new Resource("HIV Counseling & Testing", "Schedule an Appointment", "ToBeSet", "#285631"));
        resources.add(new Resource("Medical Emergency", "Call 911", "911", "#5C1B17"));
        resources.add(new Resource("Medical & Wellness Visits", "Schedule an Appointment", "ToBeSet", "#3A172B"));
        resources.add(new Resource("Nutrition Counseling", "Schedule an Appointment", "ToBeSet", "#2E4348"));
        resources.add(new Resource("Olin Phone Information Nurse", "24 Hour Service", "ToBeSet", "#9E5B3E"));
        resources.add(new Resource("Sexual Assault Hotline", "24 Hour Crisis Line", "ToBeSet", "#285631"));
        resources.add(new Resource("STI Counseling & Testing", "Schedule an Appointment", "ToBeSet", "#5C1B17"));
        resources.add(new Resource("Suicide Prevention Hotline", "24 Hour Service", "ToBeSet", "#3A172B"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.resources_fragment, container, false);
        listView = view.findViewById(R.id.resourceList);
        adapter = new ResourceAdapter(getContext(), resources);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Resource clickedResource = (Resource)parent.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("\n\t\tPhone Number: " +clickedResource.getPhoneNumber())
                        .setTitle(clickedResource.getTitle());
                builder.setPositiveButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+clickedResource.getPhoneNumber()));
                        startActivity(callIntent);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initResources();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
