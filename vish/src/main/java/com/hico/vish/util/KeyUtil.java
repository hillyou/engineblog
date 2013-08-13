package com.hico.vish.util;

import java.util.Iterator;
import java.util.LinkedList;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public final class KeyUtil {
	protected final static String KEY_SPLITOR=":";

	public static Key stringToKey(String stringkey){
		Key parent=null;
		if(stringkey!=null && !"".equals(stringkey.trim())){
			try{
				String[] keypair=stringkey.split(KEY_SPLITOR);
				for (int i = 0; i < keypair.length; i++) {
					String kind=trim(keypair[i]);
					Long id=Long.valueOf(trim(keypair[++i]));
					if(parent ==null){
						parent=KeyFactory.createKey(kind, id);
					}else{
						parent=KeyFactory.createKey(parent, kind, id);
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return parent;
	}
	
	
	public static String keyToString(Key orignal){
		if(orignal!=null){
			StringBuilder stringkey=new StringBuilder();
			Iterator<Key> keys=getPathIterator(orignal);
			while (keys.hasNext()) {
				Key key = keys.next();
				stringkey.append(key.getKind());
				stringkey.append(KEY_SPLITOR);
				stringkey.append(key.getId());
				if(keys.hasNext()){
					stringkey.append(KEY_SPLITOR);
				}
			}
			return stringkey.toString();
		}
		return "";
	}
	
	public static void main(String[] args){
		String key="Blog:12:Article:13";
		Key s=stringToKey(key);
		System.out.println(s);
		System.out.println(keyToString(s));
	}
	
	
	 private static Iterator<Key> getPathIterator(Key key) {
	    LinkedList<Key> stack = new LinkedList<Key>();
	    stack.addFirst(key);
	    Key parent=key.getParent();
	    if(parent!=null){
	    	 stack.addFirst(parent);
	    	 Key parent1=parent.getParent();
	    	 if(parent1!=null){
	    		 stack.addFirst(parent1);
	    		 Key parent2=parent1.getParent();
	    		 if(parent2!=null){
	    			 stack.addFirst(parent2);
	    			 Key parent3=parent2.getParent();
		    		 if(parent3!=null){
		    			 stack.addFirst(parent3);
		    		 }
	    		 }
	    	 }
	    }
	    return stack.iterator();
	  }
	
	private static String trim(String s){
		return s==null?"":s.trim();
	}
}
