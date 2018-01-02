package com.onsemble.onsemble;
import android.widget.*;
import android.content.Context;
import java.util.ArrayList;
import android.view.*;
import com.squareup.picasso.Picasso;
public class UsersAdapter extends ArrayAdapter<User>{
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_employee, parent, false);
        }
        // Lookup view for data population
        TextView employeeName = (TextView) convertView.findViewById(R.id.text_view_employee_name);
        ImageView employeeImage = (ImageView) convertView.findViewById(R.id.image_view_employee);
        // Populate the data into the template view using the data object
        employeeName.setText(user.firstName + " " + user.lastName);
        Picasso.with(this.getContext()).load(user.image).into(employeeImage);
        // Return the completed view to render on screen
        return convertView;
    }
}
