package com.xindaibao.cashloan.core.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/2/13.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MapRender {
    private Collection<Map> mapList;
	private Map renders=new HashMap();
	public static final Logger logger = LoggerFactory.getLogger(MapRender.class);
    public MapRender(Collection<Map> mapList) {
        this.mapList = mapList;
    }

    public interface Render{
        Object render(Object value,Object key,Map map,Object[] results);
    }
	
	public MapRender registerRender(Object[][] renders){
        this.renders=new HashMap();
        for(Object[] render:renders){
			this.renders.put(render[0], (Render) render[1]);
        }
        return this;
    }
    public Collection<Map> render(){
    	Collection<Map> newMapList = null;
            try {
				newMapList=mapList.getClass().newInstance();
				for(Map item:mapList){
				    Map newMap=item.getClass().newInstance();
				    newMap.putAll(item);

				    newMapList.add(newMap);

				    for(Object renderKey:renders.entrySet()){
				        Render render= (Render) renders.get(renderKey);
				        Object value=item.get(renderKey);

				        if(item.containsKey(renderKey)){
				            Object[] par=new Object[2];
				            value=render.render(value,renderKey,item,par);
				            newMap.put(renderKey,value);
				            if(par[0]!=null && par[1]!=null){
				                newMap.put(par[0],par[1]);
				            }
				        }
				    }
				}

				mapList.clear();
				mapList.addAll(newMapList);
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error(e.getMessage(),e);
			}
            return newMapList;
    }
}
