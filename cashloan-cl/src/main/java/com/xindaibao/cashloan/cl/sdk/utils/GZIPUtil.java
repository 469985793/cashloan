package com.xindaibao.cashloan.cl.sdk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @author
 * @Description: GZIP工具类
 * @Date: Created in 2017/9/13 下午11:32
 * @Modified:
 */
public class GZIPUtil {
    private static final Logger logger = LoggerFactory.getLogger(GZIPUtil.class);

    /**
     * 使用gzip进行解压缩
     * @param compressedStr
     * @return
     */
    public static String gunzip(String compressedStr){
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        ByteArrayInputStream in=null;
        GZIPInputStream ginzip=null;
        byte[] compressed=null;
        String decompressed = null;
        try {
            // 对返回数据BASE64解码
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in=new ByteArrayInputStream(compressed);
            ginzip=new GZIPInputStream(in);
            // 解码后对数据gzip解压缩
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset); }
            // 最后对数据进行utf-8转码
            decompressed=out.toString("utf-8");
        } catch (IOException e) {
            logger.error("gunzip error ", e);
        } finally {
            //TODO ByteArrayOutputStream/ByteInputStream 都是对内存中的字节数据的访问，
            // 只是一个虚拟的流，没有占用网络、磁盘文件等资源，所以没有关闭的必要，实现上也是空
        }
        return decompressed;
    }
}
