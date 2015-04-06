package com.jdk.nio.async;

import java.nio.channels.CompletionHandler;

/**
 * User: Constantine Solovev
 * Created: 06.04.15  23:05
 */


public abstract class Handler<V, A> implements CompletionHandler<V, A> {


    @Override
    public abstract void completed(V result, A attachment);

    @Override
    public void failed(Throwable exc, A attachment) {
        System.out.println("Huston we have a problem " +  exc.getMessage());
    }
}
