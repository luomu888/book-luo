package com.luo.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ﳵ����
 */
public class Cart {
    /**
     * key ����Ʒ��ţ�value ����Ʒ��Ϣ
     */
    private Map<Integer,CartItem> items=new LinkedHashMap<Integer,CartItem>();
    /**
     * �����Ʒ��
     * @param cartItem ���ﳵ��
     */
    public void addItem(CartItem cartItem){
        //�Ȳ鿴���ﳵ���Ƿ���ӹ�����Ʒ������Ѿ���ӣ��������ۼӣ��ܽ����£����û����ӹ�����ֱ�ӷŵ�������
        CartItem item=items.get(cartItem.getId());
        if (item==null){
            // ֮ǰû��ӹ�����Ʒ
            items.put(cartItem.getId(),cartItem);
        }else {
            // �Ѿ� ��ӹ������
            //�����ۼ�
            item.setCount(item.getCount()+1);
            //�����ܽ��
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * ɾ����Ʒ��
     * @param id ��Ʒ���
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * ��չ��ﳵ
     */
    public void clear(){
        items.clear();
    }

    /**
     * �޸���Ʒ���������½��
     * @param id ��Ʒ���
     * @param count ��Ʒ����
     */
    public void updateCount(Integer id,Integer count){
        //�Ȳ鿴���ﳵ���Ƿ��д���Ʒ���޸���Ʒ�����������ܽ��
        CartItem cartItem=items.get(id);
        if (cartItem!=null){
            cartItem.setCount(count);
            //�����ܽ��
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount=0;

        for (Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
