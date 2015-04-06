package com.jdk.nio.async;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

/**
 * User: Constantine Solovev
 * Created: 04.04.15  13:41
 */


public class Main {

    /* Попробовть AsynchronousSocketChannel
    *
    * 1 запросить 3 страницы
    * 2 выввести в консоль колличество символов на каждой странице
    *
    * */

    private static final String HOST = "habrahabr.ru";
    private static final int PORT = 80;
    private static final int THREADS = 2;

    private static final String[] PAGES = {
        "http://habrahabr.ru/company/yandex/blog/230367/",
        "http://habrahabr.ru/company/yandex/blog/230368/",
        "http://habrahabr.ru/company/yandex/blog/230369/",
        "http://habrahabr.ru/company/yandex/blog/230370/"
    };


    public static void main(String[] args) throws IOException, InterruptedException {

        AsynchronousChannelGroup channelGroup =
                AsynchronousChannelGroup.withFixedThreadPool(THREADS, Executors.defaultThreadFactory());

        AsynchronousSocketChannel[] channels = new AsynchronousSocketChannel[PAGES.length];
        SocketAddress address = new InetSocketAddress(HOST, PORT);

        for (int index = 0; index < PAGES.length; index++) {

            channels[index] = AsynchronousSocketChannel.open(channelGroup);
            AsynchronousSocketChannel currentChannel = channels[index];

            Attachment attachment = new Attachment(currentChannel, index);

            /* get connection */

            currentChannel.connect(address, attachment, new Handler<Void, Attachment>() {

                /* writing request */

                @Override
                public void completed(Void result, Attachment attachment) {

                    System.out.println("complete connection");
                    attachment.channel.write(getRequestByteBuffer(attachment.index), attachment, new Handler<Integer, Attachment>() {

                        /*Reading answer*/

                        @Override
                        public void completed(Integer result, Attachment attachment) {

                            final ByteBuffer readBuff = ByteBuffer.allocate(32000);
                            final StringBuilder stringBuilder = new StringBuilder();

                            attachment.channel.read(readBuff, attachment, new Handler<Integer, Attachment>() {

                                @Override
                                public void completed(Integer result, Attachment attachment) {

                                    if (result != -1) {
                                        readBuff.rewind();
                                        stringBuilder.append(new String(readBuff.array(), Charset.forName("UTF8")));
                                        readBuff.clear();
                                        attachment.channel.read(readBuff, attachment, this);

                                    } else {
                                        String content = stringBuilder.toString();
                                        System.out.println(attachment.index + " - " + content.split("\\s").length);
                                    }
                                }

                            });
                        }
                    });
                }

            });

        }

        channelGroup.shutdown();

    }

    private static ByteBuffer getRequestByteBuffer(int index) {
        String path = PAGES[index];
        String requestTemplate = "GET %s HTTP/1.0\r\nHost: %s \r\n\r\n";
        String request = String.format(requestTemplate, path, HOST);
        return ByteBuffer.wrap(request.getBytes());
    }

}
