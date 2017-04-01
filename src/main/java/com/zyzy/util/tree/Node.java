package com.zyzy.util.tree;

/**
 * 树节点类
 * @author BillZhao
 *
 */
public class Node {
	/**
	 * 节点编号
	 */
	public String id;
	/**
	 * 节点内容
	 */
	public String text;
	
	/**
	 * 遍历标记
	 */
	public boolean flag;
	
	/**
	 * 父节点编号
	 */
	public String parentId;
	
	/**
	 * 孩子节点列表
	 */
	public Children children = new Children();
	
	
	// 先序遍历，拼接JSON字符串
	public String toString() {
		String result = "{" + "id : '" + id + "'" + ", text : '" + text + "'" + ", flag : '" + flag + "'";

		if (children != null && children.getSize() != 0) {
			result += ", children : " + children.toString();
		} else {
			result += ", leaf : true";
		}

		return result + "}";
	}

	// 兄弟节点横向排序
	public void sortChildren() {
		if (children != null && children.getSize() != 0) {
			children.sortChildren();
		}
	}

	// 添加孩子节点
	public void addChild(Node node) {
		this.children.addChild(node);
	}
	
	public void removeChildren(){
		this.children.cleanChilds();
	}
}