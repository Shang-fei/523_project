

/** 
 *@Description: ���ַ���ת��ΪMD5 
 */ 

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
   
public class ParseMD5 { 
  

	public static String parseStrToMd5L32(String str){ 
		String reStr = null;
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(str.getBytes()); 
			StringBuilder stringBuffer = new StringBuilder();
			for(byte b : bytes){
				int bt = b & 0xff;
				if(bt < 16){
					stringBuffer.append(0);
				}
				stringBuffer.append(Integer.toHexString(bt));
			}
			reStr = stringBuffer.toString();
		} catch(NoSuchAlgorithmException e) { 
			e.printStackTrace();
		}
		return reStr;
	}

}