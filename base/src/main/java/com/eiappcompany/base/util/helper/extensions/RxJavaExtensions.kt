package com.eiappcompany.base.util.helper.extensions

import android.util.Log
import com.eiappcompany.base.util.helper.SingleLiveEvent
import com.eiappcompany.base.util.viewState.ViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable =
    apply { compositeDisposable.add(this) }


fun SingleLiveEvent<Boolean>.showAnim() {
    value = true
}

fun SingleLiveEvent<Boolean>.closeAnim() {
    value = false
}


fun <T> Observable<T>.makeObservable(progress: SingleLiveEvent<Boolean>?): Observable<T> {
    if (progress == null) {
        return this.makeObservableNoLoad()
    }

    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { progress.showAnim() }
        .doFinally { progress.closeAnim() }
}

fun <T> Observable<Response<ViewState<T>>>.makeObservableWithStatus(): Observable<Response<ViewState<T>>> {


    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { ViewState.loading(null) }

        .doOnError {
            Log.d("ViewState", "doOnError")
            Log.d("ViewState", "==" + it.message)
            it.message?.let { it1 -> ViewState.error<T>(it1) }
        }

}

@CheckReturnValue
fun <T> Observable<T>.makeObservableWithStatus3(): Observable<T> {


    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread(), true)

    /*
.doOnError {
    if (it.message == null) {
        Observable.just<ViewState<T>>(ViewState.error("BirSorunOluştu"))
    } else {
        Observable.just<ViewState<T>>(ViewState.error(it.message!!))
    }

}

     */

}

fun <T> Observable<T>.makeObservableNoLoad(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}


/**
 *
fun denemeObservable() {
makeAsyncTask(viewModel.load, {
backgroundTask()
}, {
Log.d("backgroundTask", "Complete" + it)
}).addTo(viewModel.disposable)
}


fun backgroundTask(): Boolean {
repeat(150000) {
Log.d("backgroundTask", "it=" + it)
}
return false
}

 */

fun <T> makeAsyncTask(
    load: SingleLiveEvent<Boolean>? = null,
    action: () -> T,
    successAction: (T) -> Unit,
    errorAction: (() -> Unit)? = null
): Disposable {
    return Observable.fromCallable { action() }
        .makeObservable(load)
        .subscribe(
            {
                successAction(it)
            },
            { errorAction?.let { it() } })

}

