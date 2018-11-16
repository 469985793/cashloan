package com.xindaibao.cashloan.core.common.util.base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * base64解码
 */
public abstract class CharacterEncoder {
	
	public static final Logger logger = LoggerFactory.getLogger(CharacterEncoder.class);

	public CharacterEncoder() {
		
	}

	protected abstract int bytesPerAtom();

	protected abstract int bytesPerLine();

	protected void encodeBufferPrefix(OutputStream outputstream) throws IOException {
		pStream = new PrintStream(outputstream);
	}

	protected void encodeBufferSuffix(OutputStream outputstream) throws IOException {
	}

	protected void encodeLinePrefix(OutputStream outputstream, int i) throws IOException {
	}

	protected void encodeLineSuffix(OutputStream outputstream) throws IOException {
		// pStream.println();
	}

	protected abstract void encodeAtom(OutputStream outputstream, byte abyte0[], int i, int j) throws IOException;

	protected int readFully(InputStream inputstream, byte abyte0[]) throws IOException {
		for (int i = 0; i < abyte0.length; i++) {
			int j = inputstream.read();
			if (j == -1)
				return i;
			abyte0[i] = (byte) j;
		}

		return abyte0.length;
	}

	public void encode(InputStream inputstream, OutputStream outputstream) throws IOException {
		byte abyte0[] = new byte[bytesPerLine()];
		encodeBufferPrefix(outputstream);
		do {
			int j = readFully(inputstream, abyte0);
			if (j == 0)
				break;
			encodeLinePrefix(outputstream, j);
			for (int i = 0; i < j; i += bytesPerAtom())
				if (i + bytesPerAtom() <= j)
					encodeAtom(outputstream, abyte0, i, bytesPerAtom());
				else
					encodeAtom(outputstream, abyte0, i, j - i);

			if (j < bytesPerLine())
				break;
			encodeLineSuffix(outputstream);
		} while (true);
		encodeBufferSuffix(outputstream);
	}

	public void encode(byte abyte0[], OutputStream outputstream) throws IOException {
		ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
		encode(((InputStream) (bytearrayinputstream)), outputstream);
	}

	public String encode(byte abyte0[]) {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
		String s = null;
		try {
			encode(((InputStream) (bytearrayinputstream)), ((OutputStream) (bytearrayoutputstream)));
			s = bytearrayoutputstream.toString("8859_1");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return s;
	}

	public void encodeBuffer(InputStream inputstream, OutputStream outputstream) throws IOException {
		byte abyte0[] = new byte[bytesPerLine()];
		encodeBufferPrefix(outputstream);
		int j;
		do {
			j = readFully(inputstream, abyte0);
			if (j == 0)
				break;
			encodeLinePrefix(outputstream, j);
			for (int i = 0; i < j; i += bytesPerAtom())
				if (i + bytesPerAtom() <= j)
					encodeAtom(outputstream, abyte0, i, bytesPerAtom());
				else
					encodeAtom(outputstream, abyte0, i, j - i);

			encodeLineSuffix(outputstream);
		} while (j >= bytesPerLine());
		encodeBufferSuffix(outputstream);
	}

	public void encodeBuffer(byte abyte0[], OutputStream outputstream) throws IOException {
		ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
		encodeBuffer(((InputStream) (bytearrayinputstream)), outputstream);
	}

	public String encodeBuffer(byte abyte0[]) {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
		try {
			encodeBuffer(((InputStream) (bytearrayinputstream)), ((OutputStream) (bytearrayoutputstream)));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return bytearrayoutputstream.toString();
	}

	protected PrintStream pStream;
}
