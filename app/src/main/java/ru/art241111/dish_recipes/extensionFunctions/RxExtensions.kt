package ru.art241111.kotlinmvvm.extensionFunctions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author Artem Gerasimov.
 */
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}
