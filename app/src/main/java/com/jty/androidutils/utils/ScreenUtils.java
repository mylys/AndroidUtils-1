package com.jty.androidutils.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * 屏幕管理类
 * @author jty 
 */

public class ScreenUtils {
	
	private ScreenUtils(){
		/* cannot be instantiated */  
		throw new UnsupportedOperationException("cannot be instantiated");
	}
	
	/** 
     * 获得屏幕高度 
     *  
     * @param context 
     * @return 
     */
	public static int getScreenHeight(Context context){
		WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics=new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
		
	}
	/** 
     * 获得屏幕宽度 
     *  
     * @param context 
     * @return 
     */
	public static int getScreenWidth(Context context){
		WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics=new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}
	/** 
     * 获得状态栏的高度 
     *  
     * @param context 
     * @return 
     */
	public static int getStatusHeight(Context context){
		int statusHeight=-1;
		
		try {
			Class<?> clazz=Class.forName("com.android.internal.R$dimen");
			Object object=clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusHeight;
	}
	/** 
     * 获取当前屏幕截图，包含状态栏 
     *  
     * @param activity 
	 * @return 
     * @return 
     */
	public static Bitmap snapShotWithStatusBar(Activity activity){
		View view=activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bitmap=view.getDrawingCache();
		int width = getScreenWidth(activity);
		int height = getScreenHeight(activity);
		Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, width, height);
		view.destroyDrawingCache();
		return bmp;
		
	}
	 /** 
     * 获取当前屏幕截图，不包含状态栏 
     *  
     * @param activity 
     * @return 
     */ 
	public static Bitmap snapShotWithoutStatusBar(Activity activity){
		View view = activity.getWindow().getDecorView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bitmap=view.getDrawingCache();
		Rect frame=new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		
		int height = getScreenHeight(activity);
		int width = getScreenWidth(activity);
		Bitmap bp = Bitmap.createBitmap(bitmap,
				0,statusBarHeight,width,height-statusBarHeight);
		view.destroyDrawingCache();
		return bp;
	}
	
	
}
