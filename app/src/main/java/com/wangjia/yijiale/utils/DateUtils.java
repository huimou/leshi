package com.wangjia.yijiale.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DateUtils {
	private static final String SEARCH_HISTORY = "search_history";
	public static float getRatingBarValue(float value) {
		float f = 0.5f;
		int i = (int) value;
		float v = value - i;
		if (v >= 0.5 && v <= 0.9) {
			i += 1;
		}
		f = i * 0.5f;
		return f;

	}
	/**
	 * 保存历史搜索记录
	 */
	public static void saveSearchHistory(String searchContent, Context context) {
		SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY, 0);
		try {
			String longhistory = sp.getString(SEARCH_HISTORY, "");
			String[] tmpHistory = longhistory.split(",");

			List<String> lstHistory = new ArrayList<String>(
					Arrays.asList(tmpHistory));
			if (lstHistory!=null && lstHistory.size() > 0) {// 移除历史，添加新数据
				for (int i = 0; lstHistory!=null && i < lstHistory.size(); i++) {
					if (searchContent.equals(lstHistory.get(i))) {
						lstHistory.remove(i);
						break;
					}
				}
				lstHistory.add(0, searchContent);
			}
			if (lstHistory!=null && lstHistory.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0;lstHistory!=null &&  i < lstHistory.size(); i++) {
					sb.append(lstHistory.get(i) + ",");
				}
				sp.edit().putString(SEARCH_HISTORY, sb.toString()).commit();
			} else {// 添加新数据
				sp.edit().putString(SEARCH_HISTORY, searchContent + ",").commit();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	/**
	 * 将时间戳转成时间格式yyyy-MM-dd hh:mm:ss
	 * @param mill
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String convertTimeMillis(long mill){
		Date date=new Date(mill*1000L);
		String strs="";
		try {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		strs=sdf.format(date);
		} catch (Exception e) {
		e.printStackTrace();
		}
		return strs;
		}

}
