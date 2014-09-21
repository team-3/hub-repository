/**
 * ���ϸ� : DataNotFoundException.java
 * �ۼ��� : 2014. 2. 14.
 * ���ϼ��� : 
 */
package casestudy.business.service;

/**
 * ������ �������� ���� ��� �߻��ϴ� ����
 * 
 * @author �����(kidmania@hotmail.com)
 *
 */
public class DataNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}
}
