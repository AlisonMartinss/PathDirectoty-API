package PathCarrer.API.Model.MyStandardsResponde;

public class Response<T>{
    private T data;
    private String message;


    public Response(T data) {
        this.data = data;
    }

    public Response(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}