package com.xindaibao.cashloan.cl.sdk.utils;

import com.xindaibao.cashloan.cl.sdk.constant.Constant;
import com.xindaibao.cashloan.cl.sdk.exception.CreditLoanSDKException;
import com.xindaibao.cashloan.cl.sdk.face.ocr.CreditLoanOCRClient;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author
 * @Description:
 * @Date: Created in 2017/9/17 下午11:23
 * @Modified:
 */
public class Image2Base64 {
    public static String convert(String filePath){
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filePath, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            // 转base64
            String fileBase64 = Base64Util.encode(result);
            CreditLoanOCRClient client1 = CreditLoanOCRClient.getClient();
            return client1.getByImageBase64(Constant.TEMP_API_KEY, Constant.TEMP_CECRET_KEY, fileBase64);
        } catch (IOException e) {
            throw new CreditLoanSDKException(400, Constant.ERROR_IO_EXCEPTION, e);
        } finally {
            try {
                if (fc != null)
                    fc.close();
            } catch (IOException e) {
                throw new CreditLoanSDKException(400, Constant.ERROR_IO_CLOSE_EXCEPTION, e);
            }
        }
    }
}
