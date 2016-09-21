package com.cab.alington.cab.splashscreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;


import com.cab.alington.cab.R;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;


/**
 * Created by Soniya on 7/10/2015.
 */
public class AlertUtils {

    public static void displaySnackBar(Context context, String msg, int snackColor){
        int color = snackColor == 0 ? R.color.green : snackColor;
        SnackbarManager.show(
                Snackbar.with(context)
                        .text(msg)
                        .actionLabel("OK")
                        .actionColorResource(R.color.white)
                        .color(ContextCompat.getColor(context, color))
                        .textTypeface(Typeface.create("sans-serif-medium", 0))
                        .type(SnackbarType.MULTI_LINE)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {

                            }
                        })
        );
    }



   /* public static void displayAlertdialog(final Context context, String msg, final Intent intent, String positiveText){
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle)
                .setCancelable(false)
                .setMessage(msg)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(intent);
                    }
                });
        builder.create().show();
    }

    public static void gcmAlertDailog(final Context context, String subject, String msg, final Intent intent, String positiveText){
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle)
                .setCancelable(false)
                .setTitle(subject)
                .setMessage(msg)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (intent != null) {
                            context.startActivity(intent);
                        }
                    }
                });
        builder.create().show();
    }*/

    /*public static void displayDialog(final Context context, String subject, String msg, final Intent intent, String positiveText, String negativeText){
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle)
                .setCancelable(false)
                .setTitle(subject)
                .setMessage(msg);
        if(negativeText!=null){
            builder.setNegativeButton(negativeText, null);

        }

        if(positiveText!=null){
            builder.setPositiveButton(positiveText, null);
        }

        builder.create().show();
    }

    public static MaterialDialog displayMaterialProgressDialog(Context mContenxt, String msg){
        MaterialDialog mMaterialDialog = new MaterialDialog.Builder(mContenxt).title(msg)
                .cancelable(false)
                .progress(true, 0).progressIndeterminateStyle(true).show();
        return mMaterialDialog;
    }
*/
}
