package com.xindaibao.cashloan.core.common.util;

/**
 * 根据http://bitcollider.cvs.sourceforge.net/bitcollider/jbitcollider/plugins/org
 * .bitpedia.collider.core/src/org/bitpedia/util/Base32.java?view=markup 这个改写的
 * base32实现 Base32 - encodes and decodes RFC3548 Base32 (see
 * http://www.faqs.org/rfcs/rfc3548.html )
 * 
 * @author
 * @author
 */
public final class Base32 {

	private static final char[] base32Chars = { 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', '2', '3', '4', '5', '6', '7' };

	private static final int[] base32Lookup = { 0xFF, 0xFF, 0x1A, 0x1B, 0x1C,
			0x1D, 0x1E, 0x1F, // '0', '1', '2', '3', '4', '5', '6', '7'
			0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, // '8', '9', ':',
															// ';', '<', '=',
															// '>', '?'
			0xFF, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, // '@', 'A', 'B',
															// 'C', 'D', 'E',
															// 'F', 'G'
			0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, // 'H', 'I', 'J',
															// 'K', 'L', 'M',
															// 'N', 'O'
			0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, // 'P', 'Q', 'R',
															// 'S', 'T', 'U',
															// 'V', 'W'
			0x17, 0x18, 0x19, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, // 'X', 'Y', 'Z',
															// '[', '\', ']',
															// '^', '_'
			0xFF, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, // '`', 'a', 'b',
															// 'c', 'd', 'e',
															// 'f', 'g'
			0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, // 'h', 'i', 'j',
															// 'k', 'l', 'm',
															// 'n', 'o'
			0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, // 'p', 'q', 'r',
															// 's', 't', 'u',
															// 'v', 'w'
			0x17, 0x18, 0x19, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF // 'x', 'y', 'z',
															// '{', '|', '}',
															// '~', 'DEL'
	};

	private static final int base32LookupLength = base32Lookup.length;

	/**
	 * Encodes byte array to Base32 String.
	 * 
	 * @param bytes
	 *            Bytes to encode.
	 * @return Encoded byte array <code>bytes</code> as a String.
	 * 
	 */
	public static String encode(final byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		int i = 0;
		int index = 0;
		int digit;
		int currByte;
		int nextByte;
		StringBuilder base32 = new StringBuilder((bytes.length + 7) * 8 / 5);

		while (i < bytes.length) {
			currByte = (bytes[i] >= 0) ? bytes[i] : (bytes[i] + 256); // unsign

			/* Is the current digit going to span a byte boundary? */
			if (index > 3) {
				if ((i + 1) < bytes.length) {
					nextByte = (bytes[i + 1] >= 0) ? bytes[i + 1]
							: (bytes[i + 1] + 256);
				} else {
					nextByte = 0;
				}

				digit = currByte & (0xFF >> index);
				index = (index + 5) % 8;
				digit <<= index;
				digit |= nextByte >> (8 - index);
				i++;
			} else {
				digit = (currByte >> (8 - (index + 5))) & 0x1F;
				index = (index + 5) % 8;
				if (index == 0)
					i++;
			}
			base32.append(base32Chars[digit]);
		}

		return base32.toString();
	}

	/**
	 * Decodes the given Base32 String to a raw byte array.
	 * 
	 * @param base32
	 * @return Decoded <code>base32</code> String as a raw byte array.
	 */
	public static byte[] decode(final String base32) {
		if(base32 == null || base32.length() == 0){
			return null;
		}
		int i, index, lookup, offset, digit, slength = base32.length();
		byte[] bytes = new byte[slength * 5 / 8];

		for (i = 0, index = 0, offset = 0; i < slength; i++) {
			lookup = base32.charAt(i) - '0';

			/* Skip chars outside the lookup table */
			if (lookup < 0 || lookup >= base32LookupLength) {
				continue;
			}

			digit = base32Lookup[lookup];

			/* If this digit is not in the table, ignore it */
			if (digit == 0xFF) {
				continue;
			}

			if (index <= 3) {
				index = (index + 5) % 8;
				if (index == 0) {
					bytes[offset] |= digit;
					offset++;
					if (offset >= bytes.length)
						break;
				} else {
					bytes[offset] |= digit << (8 - index);
				}
			} else {
				index = (index + 5) % 8;
				bytes[offset] |= (digit >>> index);
				offset++;

				if (offset >= bytes.length) {
					break;
				}
				bytes[offset] |= digit << (8 - index);
			}
		}
		return bytes;
	}
}
