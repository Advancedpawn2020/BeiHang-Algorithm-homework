package output;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhangrikang
 * @date 2022/11/10
 */
public class MultiOutputStream extends OutputStream {
    OutputStream outputStream1, outputStream2;

    public MultiOutputStream(OutputStream stream1, OutputStream stream2)
            throws IOException {
        outputStream1 = stream1;
        outputStream2 = stream2;
    }

    @Override
    public void write(int b) throws IOException {
        outputStream1.write(b);
        outputStream2.write(b);
    }
}

