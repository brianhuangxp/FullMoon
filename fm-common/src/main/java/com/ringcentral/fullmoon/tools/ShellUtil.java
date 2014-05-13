package com.ringcentral.fullmoon.tools;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.io.InputStreamReader;

public class ShellUtil {

	/**
	 * ִ���ⲿshell����
	 * 
	 * @param command
	 *            �ⲿshell����
	 * @param result
	 *            ���صĽ��
	 * @return
	 */
	public static boolean exec(String[] command, List result) {
		try {
			// ����һ���ⲿ���
			Process process = Runtime.getRuntime().exec(command);

			// ���ErrorStream
//			process.getErrorStream().close();//����ֱ�ӹر�ErrorStream
			InputStream errStream = process.getErrorStream();
			if (errStream != null) {
				BufferedReader errBr = new BufferedReader(
						new InputStreamReader(errStream));
				try {
					while (errBr != null && errBr.readLine() != null) {
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// ������
			BufferedInputStream bis = new BufferedInputStream(process
					.getInputStream());

			BufferedReader inReader = new BufferedReader(new InputStreamReader(
					bis));

			String str = null;

			while ((str = inReader.readLine()) != null) {
				if (str.length() == 0) {
					break;
				}

				result.add(str);
			}
			process.destroy();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
