package scmanager.connector;

import java.util.Random;

import my.widget.prototype.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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
      // Set the text
      //remoteViews.setTextViewText(R.id.update, String.valueOf(number));

      // Register an onClickListener
      /*Intent intent = new Intent(context, MyWidgetProvider.class);

      intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
      intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);*/
      Intent active = new Intent(context, MyWidgetProvider.class);
      active.setAction(WIDGET_INTENT);
      
      PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, active, 0);/*PendingIntent.getBroadcast(context,
          0, intent, PendingIntent.FLAG_UPDATE_CURRENT);*/
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
	/*Intent inn = new Intent();
    inn.setAction(WIDGET_INTENT );
    Log.i("Intent:", intent.getAction());
    context.sendBroadcast(inn);*/
	if (intent.getAction().equals(WIDGET_INTENT)) {
	    Intent in = new Intent();
	    in.setAction(WIDGET_INTENT );
	    Log.i("Intent:", intent.getAction());
	    context.sendBroadcast(in);
	} else if (intent.getAction().equals(WIDGET_INTENT_MAP)) {
		Intent in = new Intent();
	    in.setAction(WIDGET_INTENT_MAP );
	    Log.i("Intent:", intent.getAction());
	    context.sendBroadcast(in);
	} else if (intent.getAction().equals(WIDGET_INTENT_ADD_CONTACT)) {
		Intent in = new Intent();
	    in.setAction(WIDGET_INTENT_ADD_CONTACT );
	    Log.i("Intent:", intent.getAction());
	    context.sendBroadcast(in);
	}
	 super.onReceive(context, intent);
}
  
} 
