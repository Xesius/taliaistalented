package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

class foodcartAdapter extends ArrayAdapter<foodcart> {
    //יצירה של אדאפטר מותאם אישית מסוג רשימה של עצם פודקארט לעמוד של העגלת קניות


    private Context context; //הצהרה על האקטיבטי שבו יקרא האדאטפר(כלומר ההקשר שלו)
    private ArrayList<foodcart> list;//הצהרה של רשימה מסוג העצם פוד
    private foodcart dish = new foodcart();//הצהרה על עצם פוד
    foodcartDB cartDB;//הצהרה על דאטאבייס שיכיל בתוכו את המוצרים לעגלת הקניות


    //פעולה בונה לאדאפטר
    public foodcartAdapter(Context context, ArrayList<foodcart> list) {
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
        View view = layoutInflater.inflate(R.layout.fooditemlistcart, parent, false);
        dish = list.get(position);
        TextView title = (TextView) view.findViewById(R.id.custom_name);
        TextView des = (TextView) view.findViewById(R.id.descriptionofthedish);
        TextView price = (TextView) view.findViewById(R.id.price);

        title.setText(String.valueOf(dish.getTitle()));
        des.setText(String.valueOf(dish.getDes()));
        price.setText(String.valueOf(dish.getPrice()) + "₪");
        return view;
    }

    //פונקציה המחוקת את התא מהרשימה ומהדאטאבייס
    public void remove(int i, Context context) {
        cartDB = new foodcartDB(context);
        cartDB.deleteFoodCart(dish.getIndex());
        list.remove(i);
        notifyDataSetChanged();
    }
}


