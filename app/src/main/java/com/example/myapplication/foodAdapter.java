package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

class foodAdapter extends ArrayAdapter<food> {

    //יצירה של אדאפטר מותאם אישית מסוג רשימה של עצם פוד לעמוד של המוצרים הקימים וניתן לרוכשם

    private Context context; //הצהרה על האקטיבטי שבו יקרא האדאטפר(כלומר ההקשר שלו)
    private ArrayList<food> list;//הצהרה של רשימה מסוג העצם פוד
    private food dish = new food();//הצהרה על עצם פוד


    //פעולה בונה לאדאפטר
    public foodAdapter(Context context, ArrayList<food> list) {
        super(context, R.layout.fooditemlist, list);
        this.list = list;
        this.context = context;
    }


    //יוצר תא שבו יוכל העצם פוד(תמונה כותרת אידי תיאור מחיר)
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //נקביל מידע ונציג את המידע המקתבל מהעצם לאקמסל של דיסליסט
        View view = layoutInflater.inflate(R.layout.fooditemlist, parent, false);
        dish = list.get(position);
        TextView title = (TextView) view.findViewById(R.id.custom_name);
        ImageView ivProduct = (ImageView) view.findViewById(R.id.custom_image);
        TextView des = (TextView) view.findViewById(R.id.descriptionofthedish);
        TextView price = (TextView) view.findViewById(R.id.price);

        ivProduct.setImageBitmap(dish.getBitmap());
        title.setText(String.valueOf(dish.getTitle()));
        des.setText(String.valueOf(dish.getDes()));
        price.setText(String.valueOf(dish.getPrice()) + "₪");
        return view;
    }

}


