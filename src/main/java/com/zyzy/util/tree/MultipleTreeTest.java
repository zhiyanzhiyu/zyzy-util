package com.zyzy.util.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultipleTreeTest {
	public static void main(String[] args) {
		// 读取层次数据结果集列表
		List dataList = VirtualDataGenerator.getVirtualResult();

		// 节点列表（散列表，用于临时存储节点对象）
		HashMap nodeList = new HashMap();
		// 根节点
		Node root = null;
		// 根据结果集构造节点列表（存入散列表）
		for (Iterator it = dataList.iterator(); it.hasNext();) {
			Map dataRecord = (Map) it.next();
			Node node = new Node();
			node.id = (String) dataRecord.get("id");
			node.text = (String) dataRecord.get("text");
			node.parentId = (String) dataRecord.get("parentId");
			nodeList.put(node.id, node);
		}
		// 构造无序的多叉树
		Set entrySet = nodeList.entrySet();
		for (Iterator it = entrySet.iterator(); it.hasNext();) {
			Node node = (Node) ((Map.Entry) it.next()).getValue();
			if (node.parentId == null || node.parentId.equals("")) {
				root = node;
			} else {
				((Node) nodeList.get(node.parentId)).addChild(node);
			}
		}
		// 输出无序的树形菜单的JSON字符串
		System.out.println(root.toString());
		// 对多叉树进行横向排序
		root.sortChildren();
		// 输出有序的树形菜单的JSON字符串
		System.out.println(root.toString());
	}

}




/**
 * 节点比较器
 */
class NodeIDComparator implements Comparator {
	// 按照节点编号比较
	public int compare(Object o1, Object o2) {
		int j1 = Integer.parseInt(((Node) o1).id);
		int j2 = Integer.parseInt(((Node) o2).id);
		return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
	}
}

/**
 * 构造虚拟的层次数据
 */
class VirtualDataGenerator {
	// 构造无序的结果集列表，实际应用中，该数据应该从数据库中查询获得；
	public static List getVirtualResult() {
		List dataList = new ArrayList();

		HashMap dataRecord1 = new HashMap();
		dataRecord1.put("id", "112000");
		dataRecord1.put("text", "廊坊银行解放道支行");
		dataRecord1.put("parentId", "110000");

		HashMap dataRecord2 = new HashMap();
		dataRecord2.put("id", "112200");
		dataRecord2.put("text", "廊坊银行三大街支行");
		dataRecord2.put("parentId", "112000");

		HashMap dataRecord3 = new HashMap();
		dataRecord3.put("id", "112100");
		dataRecord3.put("text", "廊坊银行广阳道支行");
		dataRecord3.put("parentId", "112000");

		HashMap dataRecord4 = new HashMap();
		dataRecord4.put("id", "113000");
		dataRecord4.put("text", "廊坊银行开发区支行");
		dataRecord4.put("parentId", "110000");

		HashMap dataRecord5 = new HashMap();
		dataRecord5.put("id", "100000");
		dataRecord5.put("text", "廊坊银行总行");
		dataRecord5.put("parentId", "");

		HashMap dataRecord6 = new HashMap();
		dataRecord6.put("id", "110000");
		dataRecord6.put("text", "廊坊分行");
		dataRecord6.put("parentId", "100000");

		HashMap dataRecord7 = new HashMap();
		dataRecord7.put("id", "111000");
		dataRecord7.put("text", "廊坊银行金光道支行");
		dataRecord7.put("parentId", "110000");

		dataList.add(dataRecord1);
		dataList.add(dataRecord2);
		dataList.add(dataRecord3);
		dataList.add(dataRecord4);
		dataList.add(dataRecord5);
		dataList.add(dataRecord6);
		dataList.add(dataRecord7);

		return dataList;
	}
}