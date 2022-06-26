package ws;

/**
 * @author: bowen
 * @description:
 * @date: 2022/6/23  6:02 PM
 **/
@FunctionalInterface
public interface OkxWebSocketCallBack<T> {

    void onResponse(T response);

    default void onFailure(Throwable t){
        System.out.println("onFailure"+t.getMessage());
    }

}
