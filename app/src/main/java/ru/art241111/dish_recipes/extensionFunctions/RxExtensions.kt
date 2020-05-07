package ru.art241111.kotlinmvvm.extensionFunctions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * TODO: Read about this.
 * @author Artem Geraimov.
 */
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}