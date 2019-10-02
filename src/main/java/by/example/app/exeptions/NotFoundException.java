package by.example.app.exeptions;

public class NotFoundException extends RuntimeException {

	public NotFoundException() {}

	public NotFoundException(String ex) {
		super(ex);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
