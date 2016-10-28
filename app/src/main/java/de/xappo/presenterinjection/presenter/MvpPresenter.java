package de.xappo.presenterinjection.presenter;

import android.support.annotation.NonNull;

/**
 * Created by knoppik on 28.10.16.
 */
public interface MvpPresenter<T> {
    /**
     * Attach the view to this presenter
     *
     * @param view
     */
    void attachView(@NonNull final T view);

    /**
     * Detach the view from this presenter
     */
    void detachView();
}
