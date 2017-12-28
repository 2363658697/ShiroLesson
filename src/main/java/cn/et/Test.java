package cn.et;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

/**
 * 密码加密
 * @author Administrator
 *
 */
public class Test {

	// Shiro 内部的一些数据的存储 / 表示都使用了 base64 和 16 进制字符串。
	//可能经常用到的类 CodecSupport，提供了 toBytes(str,"utf-8") / toString(bytes,"utf-8") 用于在 byte 数组 /String 之间转换
	public static void main(String[] args) {
		base64();
		Str16();
	}

	// base64
	public static void base64() {
		String str = "hello";
		//加密
		String base64Encoded = Base64.encodeToString(str.getBytes());
		//解密
		String str2 = Base64.decodeToString(base64Encoded);

		System.out.println(str + "--------------" + base64Encoded
				+ "----------" + str2);
	}

	// 16 进制字符串
	public static void Str16() {
		String str = "hello";
		
		String base64Encoded = Hex.encodeToString(str.getBytes());
		
		String str2 = new String(Hex.decode(base64Encoded.getBytes()));

		System.out.println(str + "--------------" + base64Encoded
				+ "----------" + str2);
	}

}
