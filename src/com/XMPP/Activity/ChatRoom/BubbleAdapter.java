package com.XMPP.Activity.ChatRoom;

import java.util.ArrayList;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.XMPP.R;
import com.XMPP.Model.BubbleMessage;
import com.XMPP.util.MessageType;
import com.rockerhieu.emojicon.EmojiconTextView;

/**
 * AwesomeAdapter is a Custom class to implement custom row in ListView
 * 
 * @author Adil Soomro
 * 
 */
public class BubbleAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<BubbleMessage> mMessages;
	private BubbleMessage positonMessage;

	public BubbleAdapter(Context context, ArrayList<BubbleMessage> messages) {
		super();
		this.mContext = context;
		this.mMessages = messages;
	}

	@Override
	public int getCount() {
		return mMessages.size();
	}

	@Override
	public Object getItem(int position) {
		return mMessages.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		positonMessage = (BubbleMessage) this.getItem(position);
		
		//
		switch (getItemViewType(position)) {
		
		case 0:
			convertView = getTimeMessage(position, convertView, parent);
			return convertView;
		case 1:
			convertView = getTextMessage(position, convertView, parent);
			return convertView;
		case 2:

			break;

		}

		return convertView;
	}

	public View getTimeMessage(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null || ((ViewHolder)convertView.getTag()).time ==  null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.bubble_text, parent, false);
			holder.time = (EmojiconTextView) convertView
					.findViewById(R.id.message_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.time.setText(positonMessage.getMessage());
		holder.time.setTextSize(mContext.getResources().getDimension(R.dimen.bubbleTTextsizeTime));
		holder.time.setMinimumHeight(10);
		LayoutParams lp = (LayoutParams) holder.time.getLayoutParams();
		lp.gravity = Gravity.CENTER;
		holder.time.setLayoutParams(lp);
		return convertView;

	}


	public View getTextMessage(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null || ((ViewHolder)convertView.getTag()).message ==  null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.bubble_text, parent, false);
			holder.message = (EmojiconTextView) convertView
					.findViewById(R.id.message_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.message.setText(positonMessage.getMessage());
		holder.message.setTextSize(mContext.getResources().getDimension(R.dimen.bubbleTTextsizeText));
		
		LayoutParams lp = (LayoutParams) holder.message.getLayoutParams();

		if (positonMessage.isMine()) {
			holder.message.setBackgroundResource(R.drawable.bubble_right);
			lp.gravity = Gravity.RIGHT;
		} else {
			holder.message.setBackgroundResource(R.drawable.bubble_left);
			lp.gravity = Gravity.LEFT;

		}
		holder.message.setLayoutParams(lp);

		return convertView;
	}

	
	@Override
	public int getItemViewType (int position){
		positonMessage = (BubbleMessage) this.getItem(position);
		if(positonMessage.getType() == MessageType.TIME)
			return 0;
		if(positonMessage.getType() == MessageType.TEXT)
			return 1;
		return 1;
	}
	private class ViewHolder {
		EmojiconTextView message;
		EmojiconTextView time;
	}
	


	@Override
	public long getItemId(int position) {
		// Unimplemented, because we aren't using Sqlite.
		return position;
	}

}