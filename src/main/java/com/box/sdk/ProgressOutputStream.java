package com.box.sdk;

import java.io.IOException;
import java.io.OutputStream;

/**
 * An {@link OutputStream} that can report the progress of writing to another OutputStream to a
 * {@link ProgressListener}.
 */
class ProgressOutputStream extends OutputStream {
    private final OutputStream stream;
    private final ProgressListener listener;

    private long total;
    private long totalWritten;
    private int progress;

    ProgressOutputStream(OutputStream stream, ProgressListener listener, long total) {
        this.stream = stream;
        this.listener = listener;
        this.total = total;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public void close() throws IOException {
        this.stream.close();
    }

    @Override
    public void write(byte[] b) throws IOException {
        this.stream.write(b);
        this.totalWritten += b.length;
        this.listener.onProgressChanged(this.totalWritten, this.total);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        this.stream.write(b, off, len);
        if (len < b.length) {
            this.totalWritten += len;
        } else {
            this.totalWritten += b.length;
        }
        this.listener.onProgressChanged(this.totalWritten, this.total);
    }

    @Override
    public void write(int b) throws IOException {
        this.stream.write(b);
        this.totalWritten++;
        this.listener.onProgressChanged(this.totalWritten, this.total);
    }
}
