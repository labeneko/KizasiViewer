package labeneko.com.kizasiviewer;

import retrofit.RetrofitError;

/**
 * REST通信のコールバックに利用するinterface
 */
public interface RequestListener<T> {
    void onSuccess(T response);
    void onFailure(RetrofitError error);
}
