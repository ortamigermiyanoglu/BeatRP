package com.aresrd.android.beatrp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anthony on 16-01-25.
 */
public class MenuListAdapter extends ArrayAdapter<MenuActionItem> {

    int resource;
    Activity activity;

    public MenuListAdapter(int resource, Activity activity, MenuActionItem[] items) {
        super(activity, resource, items);

        this.resource = resource;
        this.activity = activity;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public View getView (int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if(rowView == null) {
            rowView = activity.getLayoutInflater().inflate(resource, null);

            MenuItemViewHolder viewHolder = new MenuItemViewHolder();

            viewHolder.menuItemImageView = (ImageView)rowView.findViewById(R.id.menu_item_image_view);
            viewHolder.menuItemTextView = (TextView)rowView.findViewById(R.id.menu_item_text_view);

            rowView.setTag(viewHolder);
        }

        MenuItemViewHolder holder = (MenuItemViewHolder)rowView.getTag();

        if(position == MenuActionItem.ITEM1.ordinal()) {
            holder.menuItemImageView.setImageDrawable(activity.getDrawable(R.drawable.ic_action_home));
            holder.menuItemTextView.setText(activity.getResources().getString(R.string.menu_home));
        }
        else if(position == MenuActionItem.ITEM2.ordinal()) {
            holder.menuItemImageView.setImageDrawable(activity.getDrawable(R.drawable.ic_action_collection));
            holder.menuItemTextView.setText(activity.getResources().getString(R.string.menu_collection));
        }
        else if(position == MenuActionItem.ITEM3.ordinal()) {
            holder.menuItemImageView.setImageDrawable(activity.getDrawable(R.drawable.ic_action_payment));
            holder.menuItemTextView.setText(activity.getResources().getString(R.string.menu_payment));
        }
        else if(position == MenuActionItem.ITEM4.ordinal()) {
            holder.menuItemImageView.setImageDrawable(activity.getDrawable(R.drawable.ic_action_expense));
            holder.menuItemTextView.setText(activity.getResources().getString(R.string.menu_expense));
        }
        else if(position == MenuActionItem.ITEM5.ordinal()) {
            holder.menuItemImageView.setImageDrawable(activity.getDrawable(R.drawable.ic_action_customers));
            holder.menuItemTextView.setText(activity.getResources().getString(R.string.menu_customers));
        }
        else if(position == MenuActionItem.ITEM6.ordinal()) {
            holder.menuItemImageView.setImageDrawable(activity.getDrawable(R.drawable.ic_action_vendors));
            holder.menuItemTextView.setText(activity.getResources().getString(R.string.menu_vendors));
        }
        else if(position == MenuActionItem.ITEM7.ordinal()) {
            holder.menuItemImageView.setImageDrawable(activity.getDrawable(R.drawable.ic_action_account));
            holder.menuItemTextView.setText(activity.getResources().getString(R.string.menu_account));
        }
        else if(position == MenuActionItem.ITEM8.ordinal()) {
            holder.menuItemImageView.setImageDrawable(activity.getDrawable(R.drawable.ic_action_invoice));
            holder.menuItemTextView.setText(activity.getResources().getString(R.string.menu_invoices));
        }

        return rowView;
    }

    private static class MenuItemViewHolder {
        public ImageView menuItemImageView;
        public TextView menuItemTextView;
    }





}
