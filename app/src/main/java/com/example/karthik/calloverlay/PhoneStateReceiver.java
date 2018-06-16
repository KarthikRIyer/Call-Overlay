package com.example.karthik.calloverlay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by karthik on 17/6/18.
 */

public class PhoneStateReceiver extends BroadcastReceiver {

    public PhoneStateReceiver(){}

    @Override
    public void onReceive(final Context context, Intent intent){
        Toast.makeText(context,"Receiver Started",Toast.LENGTH_SHORT).show();

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        final String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

        final String contactName = getContactName(incomingNumber,context);

//        final String emailID = getEmail(incomingNumber,context);

        final Intent mIntent = new Intent(context,DisplayActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.putExtra("number",incomingNumber);

        if(contactName != null && !contactName.isEmpty()){
            mIntent.putExtra("name",contactName);
        }

//        if(emailID != null && !emailID.isEmpty()){
//            mIntent.putExtra("email",emailID);
//        }

        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            Toast.makeText(context,"Ringing State. Number : "+incomingNumber,Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    context.startActivity(mIntent);
                }
            },500);
        }
        if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            Toast.makeText(context,"Offhook State",Toast.LENGTH_SHORT).show();
        }
        if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
            Toast.makeText(context,"Idle State",Toast.LENGTH_SHORT).show();
        }
    }

    public String getContactName(final String phoneNumber, Context context){
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phoneNumber));
        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};
        String contactName="";
        Cursor cursor = context.getContentResolver().query(uri,projection,null,null,null);
//
        if(cursor != null){
            if(cursor.moveToFirst()){
                contactName = cursor.getString(0);
            }
            cursor.close();
        }
        return contactName;
    }

//    public String getEmail(final  String phoneNumber,Context context){
//
//        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phoneNumber));
//        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};
//        Cursor cursor = context.getContentResolver().query(uri,projection,null,null,null);
//        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//        String emailId = "";
//        Cursor emailCur = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id},null);
//        while (emailCur.moveToNext()){
//            emailId = emailId + emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))+"\n";
//        }
//
//        return emailId;
//
//    }

}
