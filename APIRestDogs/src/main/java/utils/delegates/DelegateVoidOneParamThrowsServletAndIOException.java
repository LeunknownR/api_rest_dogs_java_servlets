
package utils.delegates;

import java.io.IOException;
import javax.servlet.ServletException;

@FunctionalInterface
public interface DelegateVoidOneParamThrowsServletAndIOException<P> {
    public void Execute(P p) throws ServletException, IOException;
}
