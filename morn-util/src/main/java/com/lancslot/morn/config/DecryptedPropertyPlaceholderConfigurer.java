package com.lancslot.morn.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecryptedPropertyPlaceholderConfigurer extends
        PropertyPlaceholderConfigurer {

    private static final Logger logger = LoggerFactory
            .getLogger(DecryptedPropertyPlaceholderConfigurer.class);

    private static Pattern encryptedPattern = Pattern
            .compile("encrypted\\{(.*?)\\}");


    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactory, Properties props)
            throws BeansException {
        // 解密property
        doValueDecryptProcess(props);
        super.processProperties(beanFactory, props);

    }

    
    public void doValueDecryptProcessLuos(Properties props){

        String secretKey = SdfCryptoUtil.retrieveSecretKey(
                SdfCryptoUtil.DEFAULT_SECRET_KEY_PROPERTY_FILE_NAME,
                SdfCryptoUtil.DEFAULT_SECRET_KEY_NAME);
        if (secretKey == null || secretKey.length() <= 0) {
            logger.error(
                    "Retrieve secret key={} from property={} occured error.",
                    SdfCryptoUtil.DEFAULT_SECRET_KEY_NAME,
                    SdfCryptoUtil.DEFAULT_SECRET_KEY_PROPERTY_FILE_NAME);
            return;
        }

        Iterator iterator = props.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = (String) props.get(key);
            if (value != null && value.length() > 0) {
                String decrtptedProp = decryptProperty(value, secretKey);// 解密Property
                if (decrtptedProp != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug(
                                "Encrypted property={},decrypted property={}",
                                value, decrtptedProp);
                    }
                    props.put(key, decrtptedProp);
                    System.out.println("#### key="+key + "  ,  decrtptedProp=" + decrtptedProp);
                }
            }
        }
    
    }
    
    /**
     *
     * 功能描述：解密property
     *
     * @param props
     *
     */
    private void doValueDecryptProcess(Properties props) {

        String secretKey = SdfCryptoUtil.retrieveSecretKey(
                SdfCryptoUtil.DEFAULT_SECRET_KEY_PROPERTY_FILE_NAME,
                SdfCryptoUtil.DEFAULT_SECRET_KEY_NAME);
        if (secretKey == null || secretKey.length() <= 0) {
            logger.error(
                    "Retrieve secret key={} from property={} occured error.",
                    SdfCryptoUtil.DEFAULT_SECRET_KEY_NAME,
                    SdfCryptoUtil.DEFAULT_SECRET_KEY_PROPERTY_FILE_NAME);
            return;
        }

        Iterator iterator = props.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = (String) props.get(key);
            if (value != null && value.length() > 0) {
                String decrtptedProp = decryptProperty(value, secretKey);// 解密Property
                if (decrtptedProp != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug(
                                "Encrypted property={},decrypted property={}",
                                value, decrtptedProp);
                    }
                    props.put(key, decrtptedProp);
                }
            }
        }
    }

    /**
     *
     * 功能描述：解密Property
     *
     * @param property
     * @param secretKey
     * @return
     */
    private String decryptProperty(String property, String secretKey) {
        String decrtptedProp = null;
        String matchContent = getMatchContent(property);
        if (matchContent != null) {
            try {
                decrtptedProp = SdfCryptoUtil.decrypt(matchContent, secretKey);
            } catch (Exception e) {
                logger.error(
                        "Decrypt property={} occured error.ERROR MESSAGE={}",
                        property, e.getMessage());
            }
        }

        return decrtptedProp;
    }

    /**
     *
     * 功能描述：返回匹配类似encrypted{}的字符串
     *
     * @param property
     * @return
     *
     * @since 2014年10月16日
     *
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     */
    private String getMatchContent(String property) {
        String encryptedContent = null;
        Matcher matcher = encryptedPattern.matcher(property);
        while (matcher.find()) {
            encryptedContent = matcher.group(1);// 只取第一组
        }
        return encryptedContent;
    }

    @Override
    public void setLocations(Resource[] locations) {
        super.setLocations(locations);
    }

    @Override
    public void setLocation(Resource location) {
        super.setLocation(location);
    }

}

