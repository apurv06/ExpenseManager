package in.codingninjas.envision.expensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CustomAdapter extends ArrayAdapter {

    Context mContext;
    ArrayList<Item> itemsList;
LayoutInflater inflater;

    // Constructor
    public CustomAdapter(Context context, ArrayList<Item> itemsList){
        super(context,0);
        mContext = context;
        this.itemsList = itemsList;
        inflater=(LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
    }

    // This function return the number of different type of views that will be there in the list view.
    @Override
    public int getViewTypeCount(){
        return 2;
    }

    // This function returns the type of item(in our case header or list item) that adapter wants to know in getView function.
    @Override
    public int getItemViewType(int position) {
      return   itemsList.get(position).getItemType();
    }

    // This function gives the total count of items that will be there in the list.
    @Override
    public int getCount() {
        return itemsList.size();
    }

    // This function returns the object of the itemList that has to inflated at that position.
    @Override
    public Object getItem(int position) {
        if(1==getItemViewType(position))
        {
            return itemsList.get(position);
        }
        else
        {
            return itemsList.get(position);
        }
    }


    // This function returns the unique id associated with every inflated layout, since it's is not useful in our case so
    // we return the position, which is also unique for every item.
    @Override
    public long getItemId(int position) {

        return position;
    }




    // This is the function in which we have to inflate the layout as per its TYPE
    // this function gets the type of each item from getItemViewType and on the basis of it we apply if else and inflate the layout.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(getItemViewType(position)==1)
        {
            View view=convertView;
            if(view==null)
            {

                view= inflater.inflate(R.layout.expense_row_layout,parent,false);
                TextView textView1=view.findViewById(R.id.expenseName);
                TextView textView2=view.findViewById(R.id.expenseAmount);
                TextView textView3=view.findViewById(R.id.expenseTime);
                TextView textView4=view.findViewById(R.id.expenseDate);
                ExpenseViewHolder hold=new ExpenseViewHolder();
                hold.title=textView1;
                hold.amount=textView2;
                hold.time=textView3;
                hold.date=textView4;
                view.setTag(hold);
            }

            ExpenseViewHolder hold=(ExpenseViewHolder) view.getTag();

                Expense obj=(Expense) itemsList.get(position);

            hold.title.setText(obj.getName());
            hold.date.setText(obj.getDate());

            hold.amount.setText(Integer.toString(obj.getAmount()));
            hold.time.setText(obj.getTime());

            return  view;
        }
        else
        {
            View view=convertView;
            if(view==null)
            {

                view= inflater.inflate(R.layout.header_item_layout,parent,false);
                TextView textView1=view.findViewById(R.id.headerTitleTextView);

                HeaderItemViewHolder holder =new HeaderItemViewHolder(textView1);

                view.setTag(holder);
            }

            HeaderItemViewHolder tv= (HeaderItemViewHolder) view.getTag();

                HeaderItem headerItem=(HeaderItem) itemsList.get(position);

                tv.headerTitleTextView.setText(headerItem.getHeaderTitle());
            return  view;
        }



    }


}
