package scmanager.connector;

import java.util.Random;

import scmanager.connector.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MyWidgetProvider extends AppWidgetProvider {

  private static final String ACTION_CLICK = "ACTION_CLICK";
  static final int check = 1111;
  public String WIDGET_INTENT = "manager.ui.screens.custom.intent.action.DIAL_NUMBER";
  public String WIDGET_INTENT_MAP = "manager.ui.screens.custom.intent.action.OPEN_MAP";
  public String WIDGET_INTENT_ADD_CONTACT = "manager.ui.screens.custom.intent.action.ADD_CONTACT";
  
  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager,
      int[] appWidgetIds) {

    // Get all ids
    ComponentName thisWidget = new ComponentName(context,
        MyWidgetProvider.class);
    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
    for (int widgetId : allWidgetIds) {
      // Create some random data
      int number = (new Random().nextInt(100));

      RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
          R.layout.widget_layout);
      Log.w("WidgetExample", String.valueOf(number));
      
      Intent active = new Intent(context, MyWidgetProvider.class);
      active.setAction(WIDGET_INTENT);
      
      PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);
      Intent active2 = new Intent(context, MyWidgetProvider.class);
      active2.setAction(WIDGET_INTENT_MAP);
      PendingIntent pendingIntentMap = PendingIntent.getBroadcast(context, 0, active2, 0);
      Intent active3 = new Intent(context, MyWidgetProvider.class);
      active3.setAction(WIDGET_INTENT_ADD_CONTACT);
      PendingIntent pendingIntentAddContacts = PendingIntent.getBroadcast(context, 0, active3, 0);
      
      remoteViews.setOnClickPendingIntent(R.id.bSay, pendingIntent);
      remoteViews.setOnClickPendingIntent(R.id.bMap, pendingIntentMap);
      remoteViews.setOnClickPendingIntent(R.id.bAddContacts, pendingIntentAddContacts);
      appWidgetManager.updateAppWidget(widgetId, remoteViews);
    }
  }

  
    @Override
    public void onReceive(Context context, Intent intent) {
	    if (intent.getAction().equals(WIDGET_INTENT)) {
	        Intent in = new Intent();
	        in.setAction(WIDGET_INTENT );
	        checkIfInstalled(context);
	        context.sendBroadcast(in);
	    } else if (intent.getAction().equals(WIDGET_INTENT_MAP)) {
		    Intent in = new Intent();
	        in.setAction(WIDGET_INTENT_MAP );
	        checkIfInstalled(context);
	        context.sendBroadcast(in);
	    } else if (intent.getAction().equals(WIDGET_INTENT_ADD_CONTACT)) {
		    Intent in = new Intent();
	        in.setAction(WIDGET_INTENT_ADD_CONTACT );
	        checkIfInstalled(context);
	        context.sendBroadcast(in);
	    }
	    super.onReceive(context, intent);
    }
    
    private void checkIfInstalled(Context context) {
		boolean installed  =   appInstalledOrNot("manager.ui.screens", context);  
		if(!installed)
        {
        	Intent i = new Intent(context, AlertActivity.class);
        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	context.startActivity(i);
        }
    }
		private boolean appInstalledOrNot(String uri, Context context)
	    {
	        PackageManager pm = context.getPackageManager();
	        boolean app_installed = false;
	        try
	        {
	               pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
	               app_installed = true;
	               Log.i("Found!", "Installed");
	        }
	        catch (PackageManager.NameNotFoundException e)
	        {
	        	Log.i("Not Found!", "Not Installed");
	               app_installed = false;
	               
	        }
	        return app_installed ;
	}
  
} 
