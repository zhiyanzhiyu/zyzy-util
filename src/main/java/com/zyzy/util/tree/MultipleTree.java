package com.zyzy.util.tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 一棵多叉树
 * 
 * @author BillZhao
 *
 */
public class MultipleTree {

	/**
	 * 将数据节点列表构造成一棵树
	 * 
	 * @param dnList
	 * @return
	 */
	public Node buildTree(List<DataNode> dnList) {

		HashMap<String, Node> nodeList = new HashMap<String, Node>();
		// 根节点
		Node root = null;
		// 根据结果集构造节点列表（存入散列表）
		for (Iterator<DataNode> it = dnList.iterator(); it.hasNext();) {
			DataNode dn = it.next();
			Node node = new Node();
			node.id = dn.getId();
			node.text = dn.getText();
			node.flag = false;
			node.parentId = dn.getParentId();
			nodeList.put(node.id, node);
		}
		// 构造无序的多叉树
		Set<Entry<String, Node>> entrySet = nodeList.entrySet();
		for (Iterator<Entry<String, Node>> it = entrySet.iterator(); it.hasNext();) {
			@SuppressWarnings("rawtypes")
			Node node = (Node) ((Map.Entry) it.next()).getValue();
			if (node.parentId == null || node.parentId.equals("A-0") || node.parentId.equals("I-0")) {
				root = node;
			} else {
				((Node) nodeList.get(node.parentId)).addChild(node);
			}
		}
		return root;
	}

	/**
	 * 根据节点ID，查找一个节点
	 * 
	 * @param node1
	 * @param t
	 * @return
	 */
	public Node getOneNode(Node node, String nodeId) {
		Node pNode = null;
		if ((node.id).equals(nodeId)) {
			pNode = node;
		} else if (node.children != null && node.children.getSize() != 0) {
			pNode = node.children.getChildByNodeId(nodeId);
		} 
		
		return pNode;
	}

	/**
	 * 根据某一个节点，得到其直系父节点，及所有子节点
	 * 
	 * @param node
	 * @param t
	 * @return
	 */
	public Node getParentTree(Node tree, Node node) {
		Node pNode = getOneNode(tree, node.parentId);

		if (pNode != null) {
			pNode.removeChildren();
			pNode.addChild(node);
			getParentTree(tree, pNode);
		}
		return pNode;
	}

	/**
	 * 遍历并标记直系父节点
	 * @param tree
	 * @param node
	 * @return
	 */
	public Node travParentAndFlag(Node tree, Node node) {
		Node pNode = getOneNode(tree, node.parentId);
		// 标记父节点
		if (pNode != null) {
			pNode.flag = true;
			travParentAndFlag(tree, pNode);
		}
		return tree;
	}

	/**
	 * 遍历并标记所有子节点
	 * @param node
	 * @return
	 */
	public Node travChildrenAndFlag(Node node) {
		if (node.children != null && node.children.getSize() > 0)
			node.children.changeChildFlag(true);
		return node;
	}

	/**
	 * 根据某一个节点，遍历其直系父节点，及所有子节点
	 * 
	 * @param node
	 * @param tree
	 * @return
	 */
	public Node travAndFlag(Node tree, String nodeId) {
		Node node = getOneNode(tree, nodeId);
		this.travParentAndFlag(tree, node);
		this.travChildrenAndFlag(node);
		return tree;
	}

	public Node removeNodeByFlag(Node tree, Boolean flag) {

		if (tree.children != null && tree.children.getSize() > 0) {
			tree.children.removeChild(false);
		}
		return tree;
	}
}
