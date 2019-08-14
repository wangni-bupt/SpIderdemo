package com.ztl.sipder.jiexi;
/**
 *  JsonΩ‚Œˆ£®Œﬁ”√£©
 */
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonOperatorUtil {
	public static JSONObject ToJSONObject(String str) {
		return JSONObject.fromObject(str);
	}
	
	public static JSONArray toJSONArray(String str) {
		return JSONArray.fromObject(str);
	}
	
	public static void main(String[] args) {
		String str="{\"one\":\"1\"}";
		JSONObject jsonobject=JsonOperatorUtil.ToJSONObject(str);
		JSONArray jsonArray=JsonOperatorUtil.toJSONArray(str);
		Iterator<JSONObject> iterator=jsonArray.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}
}
