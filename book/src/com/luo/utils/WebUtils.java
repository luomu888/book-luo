package com.luo.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public class WebUtils {

    /**
     * �� Map �е�ֵע�뵽��Ӧ�� JavaBean �����С�
     * @param value
     * @param bean
     */
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            //System.out.println("ע��֮ǰ��" + bean);
            // ����������Ĳ�����ע�뵽 user ������
            BeanUtils.populate(bean,value);
            //System.out.println("ע��֮��" + bean);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * ���ַ���ת����Ϊ int ���͵�����
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return  defaultValue;
    }

}
