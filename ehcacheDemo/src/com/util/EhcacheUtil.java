package com.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheUtil {

	private Cache cache ;
	
	private void loadPrame(){
		try {
			CacheManager cm = CacheManager.create("config/ehcache.xml");
			
//			cache = new Cache("b", 5000, false, false, 5, 2);
//			cm.addCache(cache);
//			cache = cm.getCache("b");
			
			cache = cm.getCache("a");
			
			PrameUtil pUtil = new PrameUtil();
			
			for(int i=1;i<6;i++){
				cache.put(new Element(String.valueOf(i),pUtil.getPrame(String.valueOf(i))));
			}
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (CacheException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public String getPrame(String key){
		String value = "";
		try {
			if(cache == null){loadPrame();}
			Element el = cache.get((String)key);
			if(el == null){loadPrame();el = cache.get((String)key);}
			if(el == null){System.out.println("ц╩сп");}
			value = el.getValue().toString();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (CacheException e) {
			e.printStackTrace();
		}
		return value;
	}
}
