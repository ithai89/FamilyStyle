package com.rcji.familystyle;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Util class with useful methods.
 * 
 * @author Ivan
 *
 */

public class Util {
	
	public static enum Time {HOURS, MINUTES, DAYS, MONTHS, YEARS};
	/**
	 * Formats SQL timestamp to simple and readable date format
	 * 
	 * @param strTime String which contains date in yyyy-MM-dd'T'HH:mm:ss.SSS'Z' form
	 * @return Formatted string with date
	 */
	public static String getFormattedDate(String strTime) {
		SimpleDateFormat format =
	            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	        	format.setTimeZone(TimeZone.getTimeZone("UTC"));
	        
	        try {
	            Date parsed = format.parse(strTime);
	            return DateFormat.getDateTimeInstance(
						DateFormat.MEDIUM, DateFormat.SHORT).format(parsed);
	        }
	        catch(ParseException pe) {
	            Log.e("Date", "ERROR: Cannot parse \"" + strTime + "\"");
	        }
	        return "Error: Unknown Date";
	}
	
	
	/**
	 * Formats SQL timestamp to simple and readable time format in HH:MM
	 * 
	 * @param strTime String which contains date in yyyy-MM-dd'T'HH:mm:ss.SSS'Z' form
	 * @return Formatted string with time
	 */
	public static String getFormattedTime(String strTime) {
		SimpleDateFormat format =
	            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	        	format.setTimeZone(TimeZone.getTimeZone("UTC"));
	        
	        try {
	            Date parsed = format.parse(strTime);
	            return DateFormat.getTimeInstance(
						DateFormat.SHORT).format(parsed);
	        }
	        catch(ParseException pe) {
	            Log.e("Date", "ERROR: Cannot parse \"" + strTime + "\"");
	        }
	        return "Error: Unknown Time";
	}
	
	
	/**
	 * Returns either 'Yesterday', 'Today', 'Later'. Might need a better 
	 * function name.
	 * TODO Rename function
	 * @return String
	 */
	public static String getRelativeDate(String strTime) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date parsed = null;
		try {
			parsed = format.parse(strTime);
			
			
		} catch (ParseException pe) {
			Log.e("Date", "ERROR: Cannot parse \""
					+ strTime + "\"");
		}
		Calendar calendarFromNow = Calendar.getInstance();
		Calendar calendarFromDate = Calendar.getInstance();
		calendarFromDate.setTime(parsed);
		
		int nowDay = calendarFromNow.get(Calendar.DAY_OF_YEAR);
		int meetupDay = calendarFromDate.get(Calendar.DAY_OF_YEAR);
		int nowYear = calendarFromNow.get(Calendar.YEAR);
		int meetupYear = calendarFromDate.get(Calendar.YEAR);
		
		int dayDiff = meetupDay - nowDay;
		int yearDiff = meetupYear - nowYear;
		if (yearDiff >= 1) {
			return "Later";
		}
		else if (yearDiff <= -1) {
			return "Past";
		}
		else if (dayDiff == 0) {
			return "Today";
		}
		else if (dayDiff == 1) {
			return "Tomorrow";
		}
		else if (dayDiff == -1) {
			return "Yesterday";
		}
		else if (dayDiff > 1) {
			return "Later";
		}
		else {
			return "Past";
		}
		
	}
	
	
	/**
	 * Takes a date object and computes the difference between current time in milliseconds and returns
	 * a String with the amount of minutes
	 * 
	 * @param date Date object to compare difference in time
	 * @return
	 */
	public static String getDifferenceInTime(String strTime, Time t) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date parsed = null;
		try {
			parsed = format.parse(strTime);
			
			
		} catch (ParseException pe) {
			Log.e("Date", "ERROR: Cannot parse \""
					+ strTime + "\"");
		}
		Calendar calendarFromNow = Calendar.getInstance();
		Calendar calendarFromDate = Calendar.getInstance();
		calendarFromDate.setTime(parsed);
		
		long msFromNow = calendarFromNow.getTimeInMillis();
		long msFromDate = calendarFromDate.getTimeInMillis();
		long diff = msFromDate - msFromNow;
		long diffSeconds = diff/1000;
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		long diffDays = diff / (24 * 60 * 60 * 1000);
		if (t==Time.MINUTES) {
			return "" + diffMinutes;
		}
		else if (t==Time.HOURS) {
			return "" + diffHours;
		}
		else if (t==Time.DAYS){
			return "" + diffDays;
		}
		else {
			return "" + diffSeconds;
		}
		
	}
	
	public static String convertToRelativeTime(String strTime) {
		Integer minTemp = Integer.parseInt(strTime);
		String convertedTime = "";
		
		if (minTemp < 0) {
			convertedTime = "" + (minTemp * -1) + " mins ago";
		}
		else if (minTemp == 0){
			convertedTime = "going on now";
		}
		else if (minTemp > 0 && minTemp < 60){
			convertedTime = "in " + minTemp + " minutes";
		}
		else if (minTemp >= 60 && minTemp < 1440) {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(1);
			nf.setGroupingUsed(false); 	
			convertedTime = "in " + nf.format((float)minTemp / 60) + " hours";
		}
		else {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(1);
			nf.setGroupingUsed(false);
			convertedTime = "in " + nf.format((float)minTemp / (60*24)) + " days";
		}
		
		return convertedTime;
	}
	
	public static void applyCustomFont(ViewGroup list, Typeface customTypeface) {
        for (int i = 0; i < list.getChildCount(); i++) {
            View view = list.getChildAt(i);
            if (view instanceof ViewGroup) {
                applyCustomFont((ViewGroup) view, customTypeface);
            } else if (view instanceof TextView) {
                ((TextView) view).setTypeface(customTypeface);
            }
        }
    }
	
	public static void toast(Context thisActivity, String message) {
		Toast toast = Toast.makeText(thisActivity, message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	
	/*
	public static void generateNotification(Context context, String msg, String title, Intent intent) {
	       int icon = R.drawable.status_icon;
	       long when = System.currentTimeMillis();

	       Notification notification = new Notification(icon, title, when);
	       notification.setLatestEventInfo(context, title, msg,
	               PendingIntent.getActivity(context, 0, intent, 0));
	       notification.flags |= Notification.FLAG_AUTO_CANCEL;

	       SharedPreferences settings = Prefs.get(context);
	       int notificatonID = settings.getInt("notificationID", 0); // allow multiple notifications

	       NotificationManager nm =                            
	               (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
	       nm.notify(notificatonID, notification);
	       playNotificationSound(context);

	       SharedPreferences.Editor editor = settings.edit();
	       editor.putInt("notificationID", ++notificatonID % 32);
	       editor.commit();
	   }

	   public static void playNotificationSound(Context context) {
	       Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	       if (uri != null) {
	           Ringtone rt = RingtoneManager.getRingtone(context, uri);
	           if (rt != null) {
	               rt.setStreamType(AudioManager.STREAM_NOTIFICATION);
	               rt.play();
	           }
	       }
	   }
		*/
	
	
	
	/**
	 * Checks to see if any of the fields in the collection contains empty strings. 
	 * 
	 * @param array EditText array
	 * @return true if all not empty, false otherwise
	 */
	public static boolean areAnyFieldsEmpty(EditText...ets) {
		for (EditText e: ets) {
			if (e.getText().toString().trim().equals("")) {
				return true;
			}
		}
		return false;
	}
}
