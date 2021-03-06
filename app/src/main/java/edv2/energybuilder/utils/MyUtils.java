package edv2.energybuilder.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import static edv2.energybuilder.utils.MyUtils.formatDate;

public class MyUtils {

    static final String formatDate = "yyyy-MM-dd";
    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    /***
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDate(){
        return formatDate(Calendar.getInstance().getTime());
    }

    public static String formatDate(Date date){
        SimpleDateFormat df = new SimpleDateFormat(formatDate);
        return  df.format(date);
    }


    public static String formatDateNumber(int i){
        String result = String.valueOf(i);
        if(i<10){
            result = "0"+result;
        }
        return result;
    }

    public static String formatDecimalValueWithLocation(String number,int decimals){
        if(number.isEmpty()){
            return number;
        }
        String format = "%."+decimals+"f";
        number = String.format(format, Float.valueOf(number));
        NumberFormat nf = NumberFormat.getInstance();
        String decSeparator = ".";
        if (nf instanceof DecimalFormat) {
            DecimalFormatSymbols sym = ((DecimalFormat) nf).getDecimalFormatSymbols();
             decSeparator = String.valueOf(sym.getDecimalSeparator());
        }
//        return number.replace(".",decSeparator); //ANdroid decimal keyboard  ="."
        return number;
    }

    public static String formatDecimalValue(String number){
        NumberFormat nf = NumberFormat.getInstance();
        String decSeparator = ",";
        if (nf instanceof DecimalFormat) {
            DecimalFormatSymbols sym = ((DecimalFormat) nf).getDecimalFormatSymbols();
             decSeparator = String.valueOf(sym.getDecimalSeparator());
        }
        return number.replace(decSeparator,".");
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

