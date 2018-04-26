/**
 * @author   zhangyp
 * @created  2013-10-8 下午6:39:25
 * @email    zviolet@163.com
 * TODO
 */
package com.vtobank.database;

/**
 * 实例化DataSet对象
 * @author zhangfb
 * @version 1.0.0.1
 */
public class RuntimeContext {
	
	private static IDataSet dataset = null;
	
	public static IDataSet getDataSet(){
		if( dataset == null ){
			dataset = (IDataSet)DataSetProxy.getInstance().bind(new DataSet());
		}
		return dataset;
	}

}
