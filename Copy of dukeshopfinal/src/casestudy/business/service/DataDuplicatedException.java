/**
 * ���ϸ� : DataDuplicatedException.java
 * �ۼ��� : 2014. 2. 14.
 * ���ϼ��� : 
 */
package casestudy.business.service;

/**
 * �ߺ��� ������ ������ ��� �߻��ϴ� ����
 * 
 * @author �����(kidmania@hotmail.com)
 *
 */
public class DataDuplicatedException extends Exception {
	private static final long serialVersionUID = 1L;

	public DataDuplicatedException() {
		super();
	}

	public DataDuplicatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataDuplicatedException(String message) {
		super(message);
	}

	public DataDuplicatedException(Throwable cause) {
		super(cause);
	}	
}
