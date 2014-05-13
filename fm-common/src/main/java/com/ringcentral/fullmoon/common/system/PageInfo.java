package com.ringcentral.fullmoon.common.system;

import java.util.List;

/**
 * ��ҳ��ϢDTO����
 */
public class PageInfo {
	/**
	 * �ܼ�¼��
	 */
	private int totalCount = 0;

	/**
	 * ÿҳ��¼��Ĭ��10
	 */
	private int pageSize = 10;

	/**
	 * ��ҳ��
	 */
	private int pageCount;

	/**
	 * ��ǰҳ
	 */
	private int pageIndex = 1;

	/**
	 * ����б�
	 */
	private List<?> dataList;

	/**
	 * Ĭ�Ϸ�ҳ��¼����
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	public PageInfo(){
	
	}

	/**
	 * ��ݷ�ҳ��¼�����
	 * @param pageSize ��ҳ��¼��
	 */
	public PageInfo(int pageSize) {
		setPageSize(pageSize);
	}

	/**
	 * ��ݷ�ҳ��¼��͵�ǰҳ�봴������
	 * @param pageSize ��ҳ��¼��
	 * @param pageIndex ҳ��
	 */
	public PageInfo(int pageSize, int pageIndex) {
		setPageSize(pageSize);
		setPageIndex(pageIndex);
	}

	/**
	 * ��ݷ�ҳ��¼��ǰҳ����ܼ�¼�����
	 * @param pageSize ��ҳ��¼��
	 * @param pageIndex ҳ��
	 * @param totalCount �ܼ�¼��
	 */
	public PageInfo(int pageSize, int pageIndex, int totalCount) {
		setPageSize(pageSize);
		setPageIndex(pageIndex);
		setTotalCount(totalCount);
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ����ÿҳ��¼������Ѿ����ܼ�¼�������ҳ��
	 * ��ҳ��¼����Ϊ0��Ϊ0�����Ĭ�Ϸ�ҳ��¼��
	 * @param pageSize pageSize
	 * @author κ�忭
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		if (pageSize == 0){
			this.pageSize = DEFAULT_PAGE_SIZE;
		}
		if (this.totalCount > 0) {
			this.pageCount = this.totalCount / this.pageSize;
			if ((this.totalCount % this.pageSize) > 0) {
				this.pageCount += 1;
			}
		}

	}

	/**
	 * ������ҳ��
	 * getTotalCount
	 * @return ��ҳ��
	 * @author κ�忭
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * ������ҳ��
	 * 
	 * @param totalCount totalCount
	 * @author κ�忭
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount == 0) {
			this.totalCount = 0;
			this.pageCount = 0;
		} else {
			this.totalCount = totalCount;
			if (pageSize == 0){
				this.pageSize = DEFAULT_PAGE_SIZE;
			}
			this.pageCount = this.totalCount / this.pageSize;
			if ((this.totalCount % this.pageSize) > 0) {
				this.pageCount += 1;
			}
		}
	}

}
